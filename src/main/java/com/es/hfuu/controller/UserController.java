package com.es.hfuu.controller;

import com.es.hfuu.domain.User;
import com.es.hfuu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ykb
 * @className UserController
 * @description
 * @date 2019/11/7
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private User user;

    @RequestMapping("getById")
    @ResponseBody
    public void getById(Long id){
        user = userService.getById(id);
        System.out.println(user);
    }
}
