package com.es.hfuu.common.util.web;

import com.es.hfuu.common.util.base.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @ClassName: CookieUtil
 * @Description: Cookie的工具类
 * 		1、Cookie的key和path都一样，后边的cookie可以覆盖前边的cookie。
 * 		2、Cookie的key一样并且都未设置path，后边的cookie可以覆盖前边的cookie】
 * @Author ykb
 * @Date 2019/11/7
 */
public class CookieUtil {
	private static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	private static final String DEFAULT_COOKIE_PATH = "/";
	private static final String DEFAULT_COOKIE_ENCODING = "utf-8";
	private static final int DEFAULT_COOKIE_MAX_AGE = -1;
	private static final boolean DEFAULT_COOKIE_ENCODE = false;
	private static final boolean DEFAULT_COOKIE_DECODE = false;

	/**
	 * @Title: saveCookie
	 * @Description: 保存信息到cookie中；
	 * @param response 服务器向浏览器响应的对象【里边包含了很多响应的信息包含请求后返回的数据，当调用了addCookie后，response会包含cookie信息】
	 * @param key 保存到cookie中的key
	 * @param value 保存到cookie的value
	 * @return void 无
	 */
	public static void saveCookie(HttpServletResponse response, String key, String value){
		saveCookie(response, key, value, DEFAULT_COOKIE_PATH, DEFAULT_COOKIE_MAX_AGE,DEFAULT_COOKIE_ENCODE);
	}

	/**
	 * @Title: saveCookie
	 * @Description: 保存信息到cookie中；
	 * @param response 服务器向浏览器响应的对象【里边包含了很多响应的信息包含请求后返回的数据，当调用了addCookie后，response会包含cookie信息】
	 * @param key 保存到cookie中的key
	 * @param value 保存到cookie的value
	 * @param path 保存到cookie的路径
	 * @param maxAge 保存cookie的有效期。
	 *               1、0表示立即销毁cookie
	 *               2、参数为负数或者不设置, 表示cookie会在当前窗口关闭后失效;
	 *               3、大于1是设置的销毁时间，单位是秒，例如1小时后销毁，就可以设置60*60
	 * @param isEncode 是否将value进行编码
	 * @return void 无
	 */
	public static void saveCookie(HttpServletResponse response, String key, String value, String path, int maxAge, boolean isEncode){
		if(null == response || StringUtil.isEmpty(key) || StringUtil.isEmpty(value)){
			logger.error("参数信息为空，向cookie中添加信息失败！！！");
			return;
		}
		if(isEncode){
			try {
				value = URLEncoder.encode(value, DEFAULT_COOKIE_ENCODING);
			} catch (Exception e) {
				throw new IllegalArgumentException("cookie的值编码失败！！！");
			}
		}
		Cookie cookie = new Cookie(key, value);
		if(StringUtil.isEmpty(path)){
			path = DEFAULT_COOKIE_PATH;
		}
		cookie.setPath(path);
		if(maxAge >= 0){
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	/**
	 * @Title: deleteCookie
	 * @Description: 根据key和value将cookie中以存的信息设置为失效的。
	 * @param response 服务器向浏览器响应的对象【里边包含了很多响应的信息包含请求后返回的数据】
	 * @param key 保存到cookie中的key
	 * @param value 保存到cookie的value
	 * @param path 保存到cookie的路径
	 * @return void 无
	 */
	public static void deleteCookie(HttpServletResponse response, String key, String value, String path){
		saveCookie(response, key, value, path, 0, false);
	}

	/**
	 * @Title: getCookie
	 * @Description: 从cookie中获取信息
	 * @param request 浏览器向服务器端请求的对象【里边包含了很多请求的信息包含参数】
	 * @param key 保存到cookie中的key
	 * @return String
	 */
	public static String getCookie(HttpServletRequest request, String key){
		return getCookie(request, key, DEFAULT_COOKIE_DECODE);
	}

	/**
	 * @Title: getCookie
	 * @Description: 从cookie中获取信息
	 * @param request 浏览器向服务器端请求的对象【里边包含了很多请求的信息包含参数】
	 * @param key 保存到cookie中的key
	 * @param isDecode 保存到cookie的路径
	 * @return String
	 */
	public static String getCookie(HttpServletRequest request, String key, boolean isDecode){
		Cookie[] cookies = request.getCookies();
		if (cookies == null || StringUtil.isEmpty(key)) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (key.equals(cookie.getName())) {
				if(isDecode){
					try {
						return URLDecoder.decode(cookie.getValue(), DEFAULT_COOKIE_ENCODING);
					} catch (UnsupportedEncodingException e) {
						throw new IllegalArgumentException("获取cookie并进行Utf-8解码失败");
					}
				}
				return cookie.getValue();
			}
		}
		return null;
	}
}
