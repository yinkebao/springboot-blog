package com.es.hfuu.plugin.user.controller;

import com.es.hfuu.common.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ykb
 * @className UserController
 * @description 用户控制层
 * @date 2019/11/8
 **/
@Api(tags="用户控制层",description = "用户控制层")
@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController {


}
