package com.es.hfuu.common.controller;

import com.es.hfuu.common.util.web.IpAddressUtil;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 登录控制层
 * @Author ykb
 * @Date 2019/9/9
 **/
@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/user/login")
    public String login(String userName, String passWord,
                        Map<String, Object> map, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String ipAddr = IpAddressUtil.getIpAddr(request);
        String uri = request.getRequestURI();
        User user = userMapper.getSimpleUserByUserName(userName);
        if (user == null){
            logger.error("登录失败，用户名不存在");
            throw new IllegalArgumentException("登录失败，用户名不存在");
        }
        if (userName.equals(user.getUserName()) && passWord.equals(user.getPassword())) {
            session.setAttribute("userName", userName);
            return "index";
        } else {
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/list")
    public String list(){
        return "/emp/list";
    }

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
