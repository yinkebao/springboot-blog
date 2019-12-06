package com.es.hfuu.plugin.user.controller;

import com.es.hfuu.common.controller.BaseController;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.service.UserService;
import com.es.hfuu.plugin.user.vo.UserVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

import java.util.List;

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

    @ApiOperation(value = "分页查询用户列表信息")
    @RequestMapping(value="/page",method = RequestMethod.GET)
    public EsResult<PageInfo<User>> listUsersByParamForPage(UserVO userVO) {
        return providerServiceInvokeFunction(userService::listUsersByParamForPage,userVO);
    }

    @ApiOperation(value = "保存用户信息", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName",value = "昵称", required = true),
            @ApiImplicitParam(name = "userName",value = "用户名", required = true),
            @ApiImplicitParam(name = "password",value = "密码", required = true),
            @ApiImplicitParam(name = "phone",value = "联系方式", required = true),
            @ApiImplicitParam(name = "birthDay",value = "生日", dataType = "Date")
    })
    @RequestMapping(value="/saveUser",method = RequestMethod.POST)
    public EsResult<User> saveUser(User user) {
        return providerServiceInvokeFunction(userService::saveUser,user);
    }

    @ApiOperation(value = "修改用户信息", notes = "根据User对象修改用户")
    @RequestMapping(value="/updateUser",method = RequestMethod.PATCH)
    public EsResult<User> updateUser(User user) {
        return providerServiceInvokeFunction(userService::updateUser,user);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据userId获取用户")
    @RequestMapping(value="/getUserById",method = RequestMethod.GET)
    public EsResult<User> getUserById(Long id) {
        return providerServiceInvokeFunction(userService::getSimpleUserById,id);
    }

    @ApiOperation(value = "根据id删除用户信息", notes = "根据ids删除用户")
    @RequestMapping(value="/deleteUsersByIds",method = RequestMethod.DELETE)
    public EsResult<Boolean> deleteUsersByIds(@RequestParam String ids) {
        return providerServiceInvokeConsumer(userService::deleteUsersByIds,ids);
    }
}
