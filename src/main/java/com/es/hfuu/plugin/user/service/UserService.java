package com.es.hfuu.plugin.user.service;

import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.vo.UserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ykb
 * @description 用户服务层接口
 * @date 2019/11/8
 **/
public interface UserService {

    PageInfo<User> listUsersByParamForPage(UserVO userVO);

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
     * 根据用户名获取用户的简单信息
     * @Title: getSimpleUserById
     * @param userName 用户名
     * @return User 用户对象
     */
    User getSimpleUserByUserName(String userName);

    /**
     * 根据用户Id获取用户的全部信息
     * @Title: getFullUserById
     * @param id 用户Id
     * @return User 用户对象
     */
    User getFullUserById(Long id);

    /**
     * 根据用户Ids删除用户
     * @Title: deleteUsersByIds
     * @param ids 用户Ids
     */
    int deleteUsersByIds(String ids);
}
