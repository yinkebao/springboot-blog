package com.es.hfuu.common.service.impl;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.common.idgenerator.IdGenerator;
import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.BaseService;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.exception.util.ExceptionUtil;
import com.es.hfuu.common.vo.PagingVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.*;

/**
 * @ClassName BaseServiceImpl
 * @Description 基础业务信息的实现类(作为其他业务的超类)
 * @Author ykb
 * @Date 2019/11/8
 */
public abstract class BaseServiceImpl<T extends BaseDomain, S extends PagingVO> implements BaseService<T, S> {
    @Autowired
    private IdGenerator idGenerator;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入真实实体类的mapper
     * @Title: getBaseMapper
     * @return BaseMapper<T>
     */
    public abstract BaseMapper<T, S> getBaseMapper();

    /**
     * 根据id获取对象
     * @Title: getEntityById
     * @Description: 根据id获取对象
     * @param id long类型的Id
     * @return T 实体对象
     */
    @Override
    public T getEntityById(Long id) {
        requireNonNull("请提供查询信息", id);
        return dbInvokeFunction(getBaseMapper()::getEntityById, getExceptionTitle(), id);
    }

    /**
     * 根据实体类获取对象
     * @Title: getEntityByEntity
     * @Description: 根据实体类获取对象
     * @param entity 实体对象
     * @return T 实体对象
     */
    @Override
    public T getEntityByEntity(T entity) {
        requireNonNull("请提供查询条件", entity);
        return dbInvokeFunction(getBaseMapper()::getEntityByEntity, getExceptionTitle(), entity);
    }

    /**
     * 根据Map对象获取对象
     * @Title: getEntityByMap
     * @Description: 根据Map对象获取对象
     * @param map map对象
     * @return T 实体对象
     */
    @Override
    public T getEntityByMap(Map<String, Object> map){
        requireNonNull("请提供查询条件", map);
        return dbInvokeFunction(getBaseMapper()::getEntityByMap, getExceptionTitle(), map);
    }

    /**
     * 保存一个对象
     * @Title: saveEntity
     * @Description: 保存一个对象
     * @param entity 实体对象
     * @return int 执行成功的条数
     */
    @Override
    public T saveEntity(T entity) {
        requireNonNull("请提供对象信息", entity);
        if (entity.getId() == null) {
            entity.setId(getId());
        }
        dbInvokeConsumer(getBaseMapper()::saveEntity, getExceptionTitle(), entity);
        return entity;
    }

    /**
     * 保存多个对象
     * @param entities 实体对象集合
     * @return int 执行成功的条数
     * @Title: saveEntities
     * @Description: 保存多个对象
     */
    @Override
    public void saveEntities(List<T> entities) {
        requireValidCollection("请提供对象信息", entities);
        entities.forEach(entity -> entity.setId(getId()));
        dbInvokeConsumer(getBaseMapper()::saveEntities, getExceptionTitle(), entities);
    }

    /**
     * 修改一个对象
     * @Title: updateEntity
     * @Description: 修改一个对象
     * @param entity 实体对象
     * @return int 执行成功的条数
     */
    @Override
    public T updateEntity(T entity) {
        requireValidBaseDomain("请提供对象信息", entity);
        dbInvokeConsumer(getBaseMapper()::updateEntity, getExceptionTitle(), entity);
        return entity;
    }

    /**
     * 根据id删除一个对象
     * @Title: deleteEntityById
     * @Description: 根据id删除一个对象
     * @param id Long类型Id
     * @return int 执行成功的条数
     */
    @Override
    public int deleteEntityById(Long id) {
        requireNonNull("请提供对象信息", id);
        return dbInvokeFunction(getBaseMapper()::deleteEntityById, getExceptionTitle(), id);
    }

    /**
     * 根据id数组批量删除数据
     * @Title: deleteEntitiesByIds
     * @Description: 根据id数组批量删除数据
     * @param ids Long类型的id数组
     * @return int 执行成功的条数
     */
    @Override
    public int deleteEntitiesByIds(List<Long> ids) {
        requireValidCollection("请提供对象信息", ids);
        return dbInvokeFunction(getBaseMapper()::deleteEntitiesByIds, getExceptionTitle(), ids);
    }

    /**
     * 根据id数组批量删除数据
     * @Title: deleteEntitiesByIds
     * @Description: 根据id数组批量删除数据
     * @param ids String类型的id字符串，逗号隔开
     * @return int 执行成功的条数
     */
    @Override
    public int deleteEntitiesByIds(String ids) {
        requireValidString("请提供待删除的ids", ids);
        //将字符串的ids转换成Long类型的数组
        String[] idsStr = ids.replace(" ","").split(",");
        List<Long> idsLong = new ArrayList<>(idsStr.length);
        for (String s : idsStr) {
            idsLong.add(Long.parseLong(s));
        }
        return dbInvokeFunction(getBaseMapper()::deleteEntitiesByIds, getExceptionTitle(), idsLong);
    }

    /**
     * 根据实体类查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByEntity
     * @Description: 根据实体类查询返回一个对象集合【分页查询】
     * @param pagingVO 实体对象
     * @return PageInfo<T> 分页对象
     */
    @Override
    public PageInfo<T> listEntitiesForPageListByEntity(S pagingVO) {
        requireNonNull("请提供查询条件", pagingVO);
        PageHelper.startPage(pagingVO.getPage(), pagingVO.getRows(), StringUtil.joinSortFieldOrder(pagingVO.getSidx(), pagingVO.getSord()));
        return new PageInfo<>(Optional.ofNullable(dbInvokeFunction(getBaseMapper()::listEntitiesForPageListByEntity,
                getExceptionTitle(), pagingVO)).orElse(new ArrayList<>(0)));
    }

    /**
     * 根据Map查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByMap
     * @Description: 根据Map查询返回一个对象集合【分页查询】
     * @param map map对象
     * @param pageNum 当前页
     * @param pageSize 每页显示数
     * @param sidx 排序字段
     * @param sord 排序类型
     * @return PageInfo<T> 分页对象
     */
    @Override
    public PageInfo<T> listEntitiesForPageListByMap(Map<String, Object> map, Integer pageNum,
                                                    Integer pageSize, String sidx, String sord) {
        requireNonNull("请提供查询条件", map);
        PageHelper.startPage(pageNum, pageSize, StringUtil.joinSortFieldOrder(sidx, sord));
        return new PageInfo<>(Optional.ofNullable(dbInvokeFunction(getBaseMapper()::listEntitiesForPageListByMap,
                getExceptionTitle(), map)).orElse(new ArrayList<>(0)));
    }


    /**
     * 根据Map查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByMap
     * @Description: 根据Map查询返回一个对象集合【分页查询】
     * @param map map对象
     * @param pagingVO 分页信息
     * @return PageInfo<T> 分页对象
     */
    @Override
    public PageInfo<T> listEntitiesForPageListByMap(Map<String, Object> map, PagingVO pagingVO) {
        requireNonNull("请提供查询条件", map);
        requireNonNull("请提供分页信息",pagingVO);
        requireNonNull("请提供当前页",pagingVO.getPage());
        requireNonNull("请提供每页数据量",pagingVO.getRows());
        PageHelper.startPage(pagingVO.getPage(), pagingVO.getRows(), StringUtil.joinSortFieldOrder(pagingVO.getSidx(), pagingVO.getSord()));
        return new PageInfo<>(Optional.ofNullable(dbInvokeFunction(getBaseMapper()::listEntitiesForPageListByMap,
                getExceptionTitle(), map)).orElse(new ArrayList<>(0)));
    }

    /**
     * 根据实体类查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByEntity
     * @Description: 根据实体类查询返回一个对象集合【不分页查询】
     * @param pagingVO 实体对象
     * @return List<T> 实体列表
     */
    @Override
    public List<T> listEntitiesForListByEntity(S pagingVO) {
        requireNonNull("请提供查询条件", pagingVO);
        return Optional.ofNullable(dbInvokeFunction(getBaseMapper()::listEntitiesForListByEntity,
                getExceptionTitle(), pagingVO)).orElse(new ArrayList<>(0));
    }

    /**
     * 根据Map查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByMap
     * @Description: 根据Map查询返回一个对象集合【不分页查询】
     * @param map map对象
     * @return List<T> 实体列表
     */
    @Override
    public List<T> listEntitiesForListByMap(Map<String, Object> map) {
        requireNonNull("请提供查询条件", map);
        return Optional.ofNullable(dbInvokeFunction(getBaseMapper()::listEntitiesForListByMap,
                getExceptionTitle(), map)).orElse(new ArrayList<>(0));
    }

    /**
     * 获取异常主题
     * @Title: getExceptionTitle
     * @return String
     */
    protected String getExceptionTitle() {
        return ExceptionUtil.getExceptionTitle(this.getClass());
    }

    /**
     * 获取id
     * @Title: getId
     * @return long
     */
    protected long getId() {
        return idGenerator.getNextVal();
    }

    /**
     * 前台传入的老对象赋值给新对象
     * @Title: setBasicsParamByOldObj
     * @param oldT
     * @param currentT
     * @return void
     */
    protected void setBasicsParamByOldObj(T oldT, T currentT){
        currentT.setUpdateDate(oldT.getUpdateDate());
        currentT.setUpdateUser(oldT.getUpdateUser());
    }
}

