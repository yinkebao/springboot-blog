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
 * @author ykb
 * @date 2019/11/8
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User,UserVO> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listUsersByParamForPage(UserVO userVO) {
        return null;
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
        user.setLock(true);
        setUserParam(user);
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
     */
    @Override
    public int deleteUsersByIds(String ids) {
        requireValidString("请提供用户名", ids);
        //将字符串的ids转换成Long类型的数组
        String[] idsStr = ids.replace(" ","").split(",");
        List<Long> idsLong = new ArrayList<>(idsStr.length);
        for (String s : idsStr) {
            idsLong.add(Long.parseLong(s));
        }
        return dbInvokeFunction(userMapper::deleteEntitiesByIds, getExceptionTitle(), idsLong);
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
     * 设置用户对象参数（密码加密）
     * @Title: setUserParam
     * @param user 用户对象
     * @return void
     */
    private void setUserParam(User user) {
        if (!StringUtil.isEmpty(user.getPassword())){
            user.setPassword(Md5Util.md5Encrypt(user.getPassword()));
        }
    }
}
