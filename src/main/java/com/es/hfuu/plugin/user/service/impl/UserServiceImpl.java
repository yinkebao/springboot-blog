package com.es.hfuu.plugin.user.service.impl;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.mapper.UserMapper;
import com.es.hfuu.plugin.user.service.UserService;
import com.es.hfuu.plugin.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.*;

/**
 * @className UserServiceImpl
 * @description 用户服务层实现
 * @author ykb
 * @date 2019/11/8
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserVO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return User
     * @Title: saveUser
     */
    @Override
    public User saveUser(User user) {
        return null;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return User
     * @Title: updateUser
     */
    @Override
    public User updateUser(User user) {
        return null;
    }

    /**
     * 解锁/锁定用户
     *
     * @param user 用户对象
     * @return void
     * @Title: updateUserStatusByLock
     */
    @Override
    public void updateUserStatusByLock(User user) {

    }

    /**
     * 启用/禁用用户
     *
     * @param user 用户对象
     * @return void
     * @Title: updateUserStatusByShutDown
     */
    @Override
    public void updateUserStatusByShutDown(User user) {

    }

    /**
     * 修改用户密码
     *
     * @param user 用户对象
     * @return User
     * @Title: updateUserByPassword
     */
    @Override
    public User updateUserByPassword(User user) {
        return null;
    }

    /**
     * 根据用户Id获取用户的简单信息
     *
     * @param id 用户Id
     * @return User 用户对象
     * @Title: getSimpleUserById
     */
    @Override
    public User getSimpleUserById(Long id) {
        return null;
    }

    /**
     * 根据用户名获取用户的简单信息
     *
     * @param userName 用户名
     * @return User 用户对象
     * @Title: getSimpleUserById
     */
    @Override
    public User getSimpleUserByUserName(String userName) {
        requireNonNull("请提供用户Id", userName);
        return dbInvokeFunction(userMapper::getSimpleUserByUserName, getExceptionTitle(), userName);
    }

    /**
     * 根据用户Id获取用户的全部信息
     *
     * @param id 用户Id
     * @return User 用户对象
     * @Title: getFullUserById
     */
    @Override
    public User getFullUserById(Long id) {
        return null;
    }

    /**
     * 注入真实实体类的mapper
     *
     * @return BaseMapper<T>
     * @Title: getBaseMapper
     */
    @Override
    public BaseMapper<User, UserVO> getBaseMapper() {
        return userMapper;
    }
}
