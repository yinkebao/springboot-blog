package com.es.hfuu.common.vo;

import java.io.Serializable;

/**
 * @className EsResult
 * @description 服务层调用返回值
 * @author ykb
 * @date 2019/11/8
 **/
public class EsResult <T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功的编码
     */
    public static final String SUCCESS = "0";

    /**
     * 失败的编码
     */
    public static final String FAIL = "1";

    /**
     * 没有权限的编码
     */
    public static final String NO_PERMISSION = "2";

    /**
     * ture:成功，false:失败
     */
    private boolean success;

    /**
     * 请求返回的编码
     */
    private String code;

    /**
     * 请求返回的信息
     */
    private String msg;

    /**
     * 请求成功后有返回值的结果。
     */
    private T module;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public EsResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    /**
     * 设置请求成功的信息
     * @Title: setModuleWithSuccess
     * @Description: 设置请求成功的信息
     * @param module 请求成功的数据
     * @return void 无
     */
    public void setModuleWithSuccess(T module) {
        this.module = module;
        setCode(SUCCESS);
        setMsg("success");
        setSuccess(true);
    }

    /**
     * 设置请求成功时无返回值的信息
     * @Title: success
     * @Description: 设置请求成功的信息
     * @return void 无
     */
    public EsResult<T> success() {
        setCode(SUCCESS);
        setMsg("success");
        setSuccess(true);
        return this;
    }

    /**
     * 设置请求失败的信息
     * @Title: setModuleWithSuccess
     * @Description: 设置请求失败的信息
     * @param code 请求编码
     * @param msg 请求信息
     * @return void 无
     */
    public void setErr(String code, String msg) {
        setCode(code);
        setMsg(msg);
        setSuccess(false);
    }
}
