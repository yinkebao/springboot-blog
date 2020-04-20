package com.es.hfuu.common.util.exception.annotation;

import com.es.hfuu.common.util.exception.base.handler.DefaultExceptionHandler;
import com.es.hfuu.common.util.exception.base.handler.ExceptionHandler;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className Exceptional
 * @description
 * @author lsx
 * @date 2019/11/8
 **/
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Exceptional {
    boolean isLogging() default false;

    String errorCode();

    String expLevel() default "error";

    Class<? extends ExceptionHandler> handler() default DefaultExceptionHandler.class;
}
