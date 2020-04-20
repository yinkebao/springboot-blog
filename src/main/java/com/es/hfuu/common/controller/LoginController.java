package com.es.hfuu.common.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.es.hfuu.common.constants.Constants;
import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.service.SessionService;
import com.es.hfuu.common.util.base.Md5Util;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.base.UuidUtil;
import com.es.hfuu.common.util.redis.constans.RedisConstants;
import com.es.hfuu.common.util.redis.util.SessionRedisUtil;
import com.es.hfuu.common.util.threadlocal.ThreadLocalMap;
import com.es.hfuu.common.util.web.CookieUtil;
import com.es.hfuu.common.util.web.IpAddressUtil;
import com.es.hfuu.common.vo.EsResult;
import com.es.hfuu.plugin.user.domain.User;
import com.es.hfuu.plugin.user.mapper.UserMapper;
import com.es.hfuu.plugin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 登录控制层
 * @Author lsx
 * @Date 2019/9/9
 **/
@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/user/login")
    public String login(String userName, String passWord,String lastUrl, Model model, Map<String, Object> map, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String ipAddr = IpAddressUtil.getIpAddr(request);
        String uri = request.getRequestURI();
        if (StringUtil.isEmpty(lastUrl)){
            lastUrl = "/";
        }
        User user = userService.getSimpleUserByUserName(userName);
        if (user == null){
            logger.error("登录失败，用户名不存在");
            throw new IllegalArgumentException("登录失败，用户名不存在");
        }
        if (userName.equals(user.getUserName()) && Md5Util.md5Encrypt(passWord).equals(user.getPassword())) {
            session.setAttribute("userName", userName);
            session.setAttribute("user", user);
            saveSession(user,ipAddr,uri,response);
            model.addAttribute("session",session);
            ThreadLocalMap.put(Constants.THREADLOCAL_USERNAME, userName);
            return "redirect:"+ (lastUrl.equals(Constants.ERROR) ? "/" : lastUrl);
        } else {
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping(value = "/user/outLogin")
    @ResponseBody
    public EsResult outLogin(String userName,HttpSession session){
      session.removeAttribute("userName");
      session.removeAttribute("user");
      User user = userService.getSimpleUserByUserName(userName);
        return providerServiceInvokeFunction(sessionService::deleteSessionByUserId,user.getId());
    }

    /**
     * 登录后保存session
     * @param user 用户信息
     * @param ipAddr IP地址
     * @param uri 请求url
     */
    private void saveSession(User user,String ipAddr,String uri,HttpServletResponse response){
        Session session = new Session();
        session.setLogin(true);
        session.setSessionId(RedisConstants.PREFIX_SESSION+ UuidUtil.uuid());
        session.setUserId(user.getId());
        session.setUserName(user.getUserName());
        session.setNickName(user.getNickName());
        session.setLoginIp(ipAddr);
        session.setLoginDate(Calendar.getInstance().getTime());
        session.setAccessTime(Calendar.getInstance().getTime());
        session.setLastUrl(uri);
        sessionService.saveSession(session);
        CookieUtil.saveCookie(response, RedisConstants.LOGIN_SESSION_ID, session.getSessionId());
        CookieUtil.saveCookie(response, RedisConstants.LOGIN_USER_NAME, session.getUserName());
    }

    @RequestMapping("/saveSession")
    @ResponseBody
    public Session saveSession(){
        Session session = new Session();
        session.setLogin(true);
        session.setSessionId(RedisConstants.PREFIX_SESSION+ UuidUtil.uuid());
        session.setUserId(654686671689879552L);
        session.setUserName("lsx");
        sessionService.saveSession(session);
        sessionService.getSessionBySessionId(session.getSessionId());
        return session;
    }

    @RequestMapping("/listSessions")
    @ResponseBody
    public List<String> listSessions(){
        return sessionService.listSessionIdsByUserId(654686671689879552L);
    }

    @RequestMapping("/user/register")
    public String register(){
        return "/register";
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
