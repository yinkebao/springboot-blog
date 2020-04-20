package com.es.hfuu.common.util.exception.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className ExceptionTitle
 * @description 异常主题
 * @author lsx
 * @date 2019/11/8
 **/
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionTitle {

    /** 异常主题 */
    String value();
}
