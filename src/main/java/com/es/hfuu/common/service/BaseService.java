package com.es.hfuu.common.service;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.common.vo.PagingVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Description 业务类的基础接口
 * @Author lsx
 * @Date 2019/11/8
 */
public interface BaseService<T extends BaseDomain, S extends PagingVO> {

    /**
     * 根据id获取对象
     * @Title: getEntityById
     * @Description: 根据id获取对象
     * @param id long类型的Id
     * @return T 实体对象
     */
    T getEntityById(Long id);

    /**
     * 根据实体类获取对象
     * @Title: getEntityByEntity
     * @Description: 根据实体类获取对象
     * @param entity 实体对象
     * @return T 实体对象
     */
    T getEntityByEntity(T entity);

    /**
     * 根据Map对象获取对象
     * @Title: getEntityByMap
     * @Description: 根据Map对象获取对象
     * @param map map对象
     * @return T 实体对象
     */
    T getEntityByMap(Map<String, Object> map);

    /**
     * 保存一个对象
     * @Title: saveEntity
     * @Description: 保存一个对象
     * @param entity 实体对象
     * @return int 执行成功的条数
     */
    T saveEntity(T entity);

    /**
     * 保存多个对象
     * @Title: saveEntities
     * @Description: 保存多个对象
     * @param entities 实体对象集合
     * @return int 执行成功的条数
     */
    void saveEntities(List<T> entities);

    /**
     * 修改一个对象
     * @Title: updateEntity
     * @Description: 修改一个对象
     * @param entity 实体对象
     * @return int 执行成功的条数
     */
    T updateEntity(T entity);

    /**
     * 根据id删除一个对象
     * @Title: deleteEntityById
     * @Description: 根据id删除一个对象
     * @param id Long类型Id
     * @return int 执行成功的条数
     */
    int deleteEntityById(Long id);

    /**
     * 根据id数组批量删除数据
     * @Title: deleteEntitiesByIds
     * @Description: 根据id数组批量删除数据
     * @param ids Long类型的id数组
     * @return int 执行成功的条数
     */
    int deleteEntitiesByIds(List<Long> ids);

    /**
     * 根据id数组批量删除数据
     * @Title: deleteEntitiesByIds
     * @Description: 根据id数组批量删除数据
     * @param ids String类型的id字符串，逗号隔开
     * @return int 执行成功的条数
     */
    int deleteEntitiesByIds(String ids);

    /**
     * 根据实体类查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByEntity
     * @Description: 根据实体类查询返回一个对象集合【分页查询】
     * @param pagingVO 实体对象
     * @return PageInfo<T> 分页对象
     */
    PageInfo<T> listEntitiesForPageListByEntity(S pagingVO);

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
    PageInfo<T> listEntitiesForPageListByMap(Map<String, Object> map, Integer pageNum, Integer pageSize, String sidx, String sord);

    /**
     * 根据Map查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByMap
     * @Description: 根据Map查询返回一个对象集合【分页查询】
     * @param map map对象
     * @param pagingVO 分页信息
     * @return PageInfo<T> 分页对象
     */
    PageInfo<T> listEntitiesForPageListByMap(Map<String, Object> map, PagingVO pagingVO);

    /**
     * 根据实体类查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByEntity
     * @Description: 根据实体类查询返回一个对象集合【不分页查询】
     * @param pagingVO 实体对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForListByEntity(S pagingVO);

    /**
     * 根据Map查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByMap
     * @Description: 根据Map查询返回一个对象集合【不分页查询】
     * @param map map对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForListByMap(Map<String, Object> map);
}
