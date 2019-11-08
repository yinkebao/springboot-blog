package com.es.hfuu.common.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @ClassName MyLocalResolver
 * @Description
 * @Author ykb
 * @Date 2019/9/9
 **/
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale = Locale.getDefault();
        String language = httpServletRequest.getParameter("l");
        if (!StringUtils.isEmpty(language)){
            String[] localeStr = language.split("_");
            locale = new Locale(localeStr[0],localeStr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
