package com.es.hfuu.common.util.exception.domain;

import com.es.hfuu.common.util.exception.base.handler.DefaultExceptionHandler;
import com.es.hfuu.common.util.exception.base.handler.ExceptionHandler;

/**
 * @author ykb
 * @className ExceptionDefinition
 * @description 异常定义
 * @date 2019/11/8
 **/
public class ExceptionDefinition {
    private String errorCode;
    private Boolean isLogging;
    private ExceptionHandler handler;
    private String expLevel;

    ExceptionDefinition() {
    }

    public ExceptionDefinition(String errorCode, String expLevel, Boolean isLogging, ExceptionHandler handler) {
        this.errorCode = errorCode;
        this.isLogging = isLogging;
        this.expLevel = expLevel;
        this.handler = handler;
    }

    public ExceptionDefinition(String errorCode, String expLevel) {
        new ExceptionDefinition(errorCode, expLevel, Boolean.TRUE, new DefaultExceptionHandler());
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ExceptionHandler getHandler() {
        return handler;
    }

    public void setHandler(ExceptionHandler handler) {
        this.handler = handler;
    }

    public Boolean getIsLogging() {
        return this.isLogging;
    }

    public void setIsLogging(Boolean isLogging) {
        this.isLogging = isLogging;
    }

    public String getExpLevel() {
        return this.expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }
}
