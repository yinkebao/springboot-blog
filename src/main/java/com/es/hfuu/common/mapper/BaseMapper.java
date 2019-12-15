package com.es.hfuu.common.mapper;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.common.vo.PagingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseMapper
 * @Description Mybatis的基础接口
 * @Author ykb
 * @Date 2019/11/8
 */
public interface BaseMapper<T extends BaseDomain, S extends PagingVO> {

    /**
     * 根据id获取对象
     * @Title: getEntityById
     * @Description: 根据id获取对象
     * @param paramLong long类型的Id
     * @return T 实体对象
     */
    T getEntityById(@Param(value = "id") Long paramLong);

    /**
     * 根据实体类获取对象
     * @Title: getEntityByEntity
     * @Description: 根据实体类获取对象
     * @param paramT 实体对象
     * @return T 实体对象
     */
    T getEntityByEntity(T paramT);

    /**
     * 根据Map对象获取对象
     * @Title: getEntityByMap
     * @Description: 根据Map对象获取对象
     * @param paramMap map对象
     * @return T 实体对象
     */
    T getEntityByMap(Map<String, Object> paramMap);

    /**
     * 保存一个对象
     * @Title: saveEntity
     * @Description: 保存一个对象
     * @param paramT 实体对象
     * @return int 执行成功的条数
     */
    int saveEntity(T paramT);

    /**
     * 保存多个对象
     * @Title: saveEntities
     * @Description: 保存多个对象
     * @param paramTs 实体对象集合
     * @return int 执行成功的条数
     */
    void saveEntities(@Param(value = "list") List<T> paramTs);

    /**
     * 修改一个对象
     * @Title: updateEntity
     * @Description: 修改一个对象
     * @param paramT 实体对象
     * @return int 执行成功的条数
     */
    int updateEntity(T paramT);

    /**
     * 根据id删除一个对象
     * @Title: deleteEntityById
     * @Description: 根据id删除一个对象
     * @param paramLong Long类型Id
     * @return int 执行成功的条数
     */
    int deleteEntityById(@Param(value = "id") Long paramLong);

    /**
     * 根据id数组批量删除数据
     * @Title: deleteEntitiesByIds
     * @Description: 根据id数组批量删除数据
     * @param paramArrayOfLong Long类型的id数组
     * @return int 执行成功的条数
     */
    int deleteEntitiesByIds(@Param(value = "ids") List<Long> paramArrayOfLong);

    /**
     * 根据实体类分页查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByEntity
     * @Description: 根据实体类分页查询返回一个对象集合【分页查询】
     * @param paramS 实体对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForPageListByEntity(S paramS);

    /**
     * 根据Map分页查询返回一个对象集合【分页查询】
     * @Title: listEntitiesForPageListByMap
     * @Description: 根据Map分页查询返回一个对象集合【分页查询】
     * @param paramMap map对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForPageListByMap(Map<String, Object> paramMap);

    /**
     * 根据实体类查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByEntity
     * @Description: 根据实体类查询返回一个对象集合【不分页查询】
     * @param paramS 实体对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForListByEntity(S paramS);

    /**
     * 根据Map查询返回一个对象集合【不分页查询】
     * @Title: listEntitiesForListByMap
     * @Description: 根据Map查询返回一个对象集合【不分页查询】
     * @param paramMap map对象
     * @return List<T> 实体列表
     */
    List<T> listEntitiesForListByMap(Map<String, Object> paramMap);
}

