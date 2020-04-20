package com.es.hfuu.plugin.user.mapper;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lsx
 * @description TODO
 * @date 2019/11/8
 **/
public interface UserMapper extends BaseMapper<User, UserVO> {

    /**
     * 解锁/锁定用户
     * @Title: updateUserStatusByLock
     * @param user 用户对象
     * @return void
     */
    void updateUserStatusByLock(User user);

    /**
     * 启用/禁用用户
     * @Title: updateUserStatusByShutDown
     * @param user 用户对象
     * @return void
     */
    void updateUserStatusByShutDown(User user);

    /**
     * 修改用户密码
     * @Title: updateUserByPassword
     * @param user 用户对象
     * @return User
     */
    void updateUserByPassword(User user);

    /**
     * 根据用户Id获取用户的简单信息
     * @Title: getSimpleUserById
     * @param id 用户Id
     * @return User 用户对象
     */
    User getSimpleUserById(Long id);

    /**
     * 根据用户名获取用户的简单信息
     *
     * @param userName 用户名
     * @return User 用户对象
     * @Title: getSimpleUserById
     */
    User getSimpleUserByUserName(@Param("userName")String userName);

    /**
     * 根据用户Id获取用户的全部信息
     * @Title: getFullUserById
     * @param id 用户Id
     * @return User 用户对象
     */
    User getFullUserById(Long id);
}
