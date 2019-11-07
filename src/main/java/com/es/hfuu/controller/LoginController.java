package com.es.hfuu.controller;

import com.es.hfuu.util.web.IpAddressUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description
 * @Author ykb
 * @Date 2019/9/9
 **/
@Controller
public class LoginController {

    @RequestMapping(value = "/user/login")
    public String login(String userName, String passWord,
                        Map<String, Object> map, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String ipAddr = IpAddressUtil.getIpAddr(request);
        String uri = request.getRequestURI();
        if (userName.equals("admin") && "123".equals(passWord)) {
            session.setAttribute("userName", userName);
            return "redirect:/main.html";
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
