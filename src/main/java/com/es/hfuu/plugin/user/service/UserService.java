package com.es.hfuu.plugin.user.service;

import com.es.hfuu.plugin.user.domain.User;

/**
 * @author ykb
 * @description 用户服务层接口
 * @date 2019/11/8
 **/
public interface UserService {

    /**
     * 保存用户信息
     * @Title: saveUser
     * @param user 用户对象
     * @return User
     */
    User saveUser(User user);

    /**
     * 修改用户信息
     * @Title: updateUser
     * @param user 用户对象
     * @return User
     */
    User updateUser(User user);

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
    User updateUserByPassword(User user);

    /**
     * 根据用户Id获取用户的简单信息
     * @Title: getSimpleUserById
     * @param id 用户Id
     * @return User 用户对象
     */
    User getSimpleUserById(Long id);

    /**
     * 根据用户Id获取用户的全部信息
     * @Title: getFullUserById
     * @param id 用户Id
     * @return User 用户对象
     */
    User getFullUserById(Long id);
}