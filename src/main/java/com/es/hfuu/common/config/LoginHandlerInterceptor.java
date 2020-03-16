package com.es.hfuu.common.config;

import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.service.SessionService;
import com.es.hfuu.common.service.impl.SessionServiceImpl;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.redis.constans.RedisConstants;
import com.es.hfuu.common.util.redis.util.SessionRedisUtil;
import com.es.hfuu.common.util.web.CookieUtil;
import com.es.hfuu.common.util.web.IpAddressUtil;
import com.es.hfuu.common.util.web.SpringContextUtil;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

/**
 * @ClassName LoginHandlerInterceptor
 * @Description 登录检查的拦截器
 * @Author ykb
 * @Date 2019/9/10
 **/
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SessionService sessionService;

    /**
     * 目标方法执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = (String) request.getSession().getAttribute("userName");
        String sessionId = request.getParameter(RedisConstants.LOGIN_SESSION_ID);
        String url = request.getRequestURI();
        if (userName == null || "".equals(userName)){
            request.setAttribute("lastUrl",url);
            request.setAttribute("msg","未登录，请登录后访问！");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else {
            //进行session有效性的校验。
            if (StringUtil.isEmpty(sessionId)) {
                sessionId = CookieUtil.getCookie(request,RedisConstants.LOGIN_SESSION_ID);
            }
            if(StringUtil.isEmpty(sessionId)){
                System.out.println("session is null");
                return false;
            }
            sessionService = (SessionService) SpringContextUtil.getBean("sessionService");
            Session session = sessionService.getSessionBySessionId(sessionId);
            if (isSessionTimeOut(session)){
                request.setAttribute("msg","登录超时，请重新登录！");
                request.getRequestDispatcher("/login").forward(request,response);
                return false;
            }
            session.setLastUrl(url);
            session.setAccessIp(IpAddressUtil.getIpAddr(request));
            session.setAccessTime(Calendar.getInstance().getTime());
            sessionService.updateSession(session);
            return true;
        }
    }

    /**
     * session有限期验证
     * @param session session对象
     * @return boolean
     */
    private boolean isSessionTimeOut(Session session) {
        Long accessTime = session.getAccessTime().getTime();
        if ((Calendar.getInstance().getTime().getTime() - accessTime) > RedisConstants.SESSION_TIME_OUT) {
            logger.info("用户：{}Session失效，上一次访问时间{}", session.getUserName(), accessTime);
            return true;
        }
        return false;
    }

}
