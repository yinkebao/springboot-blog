package com.es.hfuu.common.util.exception.domain;

/**
 * @author ykb
 * @className ExceptionResult
 * @description 异常处理返回值
 * @date 2019/11/8
 **/
public class ExceptionResult {

    private String errorCode;
    private String message;
    private String expLevel;

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExpLevel() {
        return this.expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }

    @Override
    public String toString() {
        return "Result [errorCode=" + this.errorCode + ", message=" + this.message + ", expLevel="
                + this.expLevel + "]";
    }
}
