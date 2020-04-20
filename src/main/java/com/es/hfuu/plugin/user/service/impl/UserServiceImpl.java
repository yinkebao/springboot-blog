package com.es.hfuu.plugin.user.service.impl;

import com.es.hfuu.common.mapper.BaseMapper;
import com.es.hfuu.common.service.impl.BaseServiceImpl;
import com.es.hfuu.common.util.base.Md5Util;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.mapper.UserMapper;
import com.es.hfuu.plugin.user.service.UserService;
import com.es.hfuu.plugin.user.vo.UserVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.*;

/**
 * @className UserServiceImpl
 * @description 用户服务层实现
 * @author lsx
 * @date 2019/11/8
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserVO> implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 获取分页列表
     *
     * @param userVO 查询参数
     * @return User
     */
    @Override
    public PageInfo<User> listUsersByParamForPage(UserVO userVO) {
        userVO.setRows(userVO.getLimit());
        return listEntitiesForPageListByEntity(userVO);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return User
     * @Title: saveUser
     */
    @Override
    public User saveUser(User user) {
        setUserParam(user);
        setPassword(user);
        return saveEntity(user);
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
        setPassword(user);
        dbInvokeFunction(getBaseMapper()::updateEntity,"修改用户信息",user);
        return getSimpleUserById(user.getId());
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
        requireNonNulls("请提供用户信息", user, user.getId(), user.getLock());
        dbInvokeConsumer(userMapper::updateUserStatusByLock, getExceptionTitle(), user);
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
        requireNonNulls("请提供用户信息", user, user.getId(), user.getEnabled());
        dbInvokeConsumer(userMapper::updateUserStatusByShutDown, getExceptionTitle(), user);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户对象
     * @return User
     * @Title: updateUserByPassword
     */
    @Override
    public void updateUserByPassword(User user) {
        requireNonNulls("请提供用户信息", user, user.getId(), user.getPassword());
        setPassword(user);
        dbInvokeConsumer(userMapper::updateUserByPassword, getExceptionTitle(), user);
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
        requireNonNull("请提供用户id", id);
        return dbInvokeFunction(userMapper::getSimpleUserById, getExceptionTitle(), id);
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
        requireNonNull("请提供用户名", userName);
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
     * 根据用户Ids删除用户
     *
     * @param ids 用户Ids
     * @Title: deleteUsersByIds
     * @return int
     */
    @Override
    public int deleteUsersByIds(String ids) {
        return deleteEntitiesByIds(ids);
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

    /**
     * 密码加密
     *
     * @param user 用户对象
     * @return void
     */
    private void setPassword(User user) {
        if (!StringUtil.isEmpty(user.getPassword())){
            user.setPassword(Md5Util.md5Encrypt(user.getPassword()));
        }
    }

    /**
     * 设置用户默认参数
     * lock：false 未锁定
     * admin：false 非管理员
     * shutDown：true
     *
     * @param user 用户对象
     * @return void
     */
    private void setUserParam(User user) {
        if (user.getLock() == null){
            user.setLock(false);
        }
        if (user.getAdmin() == null){
            user.setAdmin(false);
        }
        if (user.getEnabled() == null){
            user.setEnabled(true);
        }
        if (user.getIsDeleted() == null){
            user.setIsDeleted(false);
        }
    }
}
