package com.es.hfuu.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName Session
 * @Description
 * @Author ykb
 * @Date 2019/11/12
 */
@ApiModel("会话对象Session")
public class Session extends BaseDomain {

    @ApiModelProperty(value = "会话Id", name = "sessionId")
    private String sessionId;

    @ApiModelProperty(value = "用户Id", name = "userId")
    private Long userId;
    @ApiModelProperty(value = "用户名【登录账号】", name = "userName")
    private String userName;
    @ApiModelProperty(value = "昵称", name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "上一次访问链接", name = "lastUrl")
    private String lastUrl;
    @ApiModelProperty(value = "上一次访问时间", name = "accessTime")
    private Date accessTime;
    @ApiModelProperty(value = "上一次访问Ip地址", name = "accessIp")
    private String accessIp;

    @ApiModelProperty(value = "是否登录", name = "login")
    private Boolean login;
    @ApiModelProperty(value = "登录时间", name = "loginDate")
    private Date loginDate;
    @ApiModelProperty(value = "登录IP地址", name = "loginIp")
    private String loginIp;

    @ApiModelProperty(value = "登录验证码", name = "validateCode")
    private String validateCode;

    @ApiModelProperty(value = "存放通过单点登录过来我们系统的标识", name = "loginKey")
    private String loginKey;

    public Session() {
        super();
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }
}

