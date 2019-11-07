package com.example.configviewresolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @ClassName AutoViewResolver
 * @Description
 * @Author ykb
 * @Date 2019/9/6
 **/
public class AutoViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        /** 在这里定义自己的视图解析器逻辑 */
        return null;
    }
}
