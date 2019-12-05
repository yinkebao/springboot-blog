package com.es.hfuu.common.util.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringContextUtil
 * @Description 通过封装applicationContext上线文,获取 spring bean对象 bean启动时候 已经被打印出，可直接根据name、class、name class获取
 * @Author ykb
 * @Date 2019/12/5
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * @Title: getApplicationContext
     * @Description: 获取上下文
     * @return ApplicationContext 上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * @Title: setApplicationContext
     * @Description: 设置上下文
     * @param applicationContextTmp 上下文
     * @return void 无
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContextTmp) throws BeansException {
        if (applicationContext == null) {
            applicationContext = applicationContextTmp;
        }
    }

    /**
     * @Title: getBean
     * @Description: 根据name获取Spring中的Bean
     * @param name bean的名称
     * @return Object object对象
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     * @Title: getBean
     * @Description: 根据Class类型获取Spring中的Bean
     * @param classType Class类型
     * @return T 返回对应class的实例
     */
    public static <T> T getBean(Class<T> classType){
        return applicationContext.getBean(classType);
    }

    /**
     * @Title:
     * @Description: 根据Bean的名字和Class类型获取spring中的Bean
     * @param name bean的名称
     * @param classType Class类型
     * @return T 返回对应class的实例
     */
    public static <T> T getBean(String name, Class<T> classType){
        return applicationContext.getBean(name,classType);
    }

}
