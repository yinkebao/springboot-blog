package com.es.hfuu.service.impl;

import com.es.hfuu.domain.User;
import com.es.hfuu.mapper.UserMapper;
import com.es.hfuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ykb
 * @className UserServiceImpl
 * @description
 * @date 2019/11/7
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }
}
