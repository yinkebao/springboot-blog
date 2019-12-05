package com.es.hfuu.plugin.user.controller;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.*;

/**
 * @author ykb
 * @className UserController
 * @description 用户信息控制层
 * @date 2019/11/8
 **/
@Api(tags="用户信息管理模块",description = "包含新增、删除、修改、查询等操作")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "保存用户信息", notes = "根据User对象创建用户")
    @RequestMapping(value="/saveUser",method = RequestMethod.POST)
    public EsResult<Boolean> saveUser(User user) {
        return providerServiceInvokeConsumer(userService::saveUser,user);
    }

    @ApiOperation(value = "修改用户信息", notes = "根据User对象修改用户")
    @RequestMapping(value="/updateUser",method = RequestMethod.PATCH)
    public EsResult<Boolean> updateUser(User user) {
        return providerServiceInvokeConsumer(userService::updateUser,user);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据User对象修改用户")
    @RequestMapping(value="/getUserById",method = RequestMethod.GET)
    public User getUserById(Long id) {
        return userService.getSimpleUserById(id);
    }



}
