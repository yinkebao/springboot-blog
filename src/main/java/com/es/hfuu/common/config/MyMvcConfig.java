package com.es.hfuu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
/**
 * @ClassName: MyMvcConfig
 * @Description: 自定义配置类
 * @author: oracle
 * @date: 2019/9/10
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /** 配置静态资源路径
     *  SptingBoot在ResourceProperties类中默认配置了如下路径：
     *      "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"
     * */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**","/webjars/**").
                addResourceLocations("classpath:/static/",
                        "classpath:/webjars/**",
                        "classpath:/resources/**");
    }*/

    public void otherComfig(){

    }

    /** 配置视图解析器 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 注册拦截器
     * springboot 1.5已经做好了静态资源映射，故不需要再处理静态资源
     *  2.0 之后任然需要处理静态资源问题
     * @Title: addInterceptors
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/templates/**","/user/login","/login",
                        "/asserts/**","/layui/**","/webjars/**","/**.ico");
    }



    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }

}
