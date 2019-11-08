package com.es.hfuu.common.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandlerInterceptor
 * @Description 登录检查的拦截器
 * @Author ykb
 * @Date 2019/9/10
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userName = request.getSession().getAttribute("userName");
        if (userName == null){
            request.setAttribute("msg","未登录，请登录后访问！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

}
