package com.es.hfuu.common.util.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName servletUtil
 * @Description Servlet的工具类
 * @Author ykb
 * @Date 2019/12/5
 */
public class ServletUtil {
    /**
     * @Title: getRequest
     * @Description: 获取当前请求的对象
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * @Title: getResponse
     * @Description: 获取当前相应的对象
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * @Title: isStaticFile
     * @Description: 是否是静态文件
     * @param uri 请求的地址
     * @return boolean true:静态资源、false:不是静态资源
     */
    public static boolean isStaticFile(String uri) {
        ResourceUrlProvider resourceUrlProvider = SpringContextUtil.getBean(ResourceUrlProvider.class);
        String staticUri = resourceUrlProvider.getForLookupPath(uri);
        return staticUri != null;
    }
}
