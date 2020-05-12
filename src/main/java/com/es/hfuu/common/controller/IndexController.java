package com.es.hfuu.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description 首页控制层
 * @Author lsx
 * @Date 2020/2/1
 */
@Controller
@RequestMapping("/system/index")
public class IndexController extends BaseController{

    @GetMapping("/wangEdit")
    public String redirectEdit() {
        return "wangEdit";
    }

    @GetMapping("/chooseTags")
    public String chooseTags() {
        return "chooseTags";
    }

}
