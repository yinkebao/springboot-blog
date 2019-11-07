package com.es.hfuu.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName WebController
 * @Description
 * @Author ykb
 * @Date 2019/9/5
 **/
@Controller
public class WebController {
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "hello";
    };
}
