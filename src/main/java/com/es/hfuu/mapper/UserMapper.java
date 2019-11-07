package com.es.hfuu.mapper;

import com.es.hfuu.domain.User;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

/**
 * @author ykb
 * @description TODO
 * @date 2019/11/7
 **/
public interface UserMapper {

    void add();

    @Select(value = "select * from user where id = #{id}")
    User getById(@Param("id") Long id);
}
