package com.es.hfuu.plugin.user.domain;

import com.es.hfuu.common.domain.BaseDomain;
import com.es.hfuu.plugin.role.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @className User
 * @description 用户
 * @author ykb
 * @date 2019/11/8
 **/
@ApiModel("用户对象User")
public class User extends BaseDomain {

    @ApiModelProperty(value = "昵称", name = "nickName")
    private String nickName;

    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;

    @ApiModelProperty(value = "密码", name = "password")
    private String password;

    @ApiModelProperty(value = "联系方式", name = "phone")
    private String phone;

    @ApiModelProperty(value = "生日", name = "birthDay")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date birthDay;

    @ApiModelProperty(value = "邮箱", name = "email")
    private String email;

    @ApiModelProperty(value = "图像路径", name = "headerUrl")
    private String headerUrl;

    @ApiModelProperty(value = "最后登录时间", name = "lastLoginTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    @ApiModelProperty(value = "最后登录IP", name = "lastLoginIp")
    private String lastLoginIp;

    @ApiModelProperty(value = "上次登录时间", name = "previousLoginTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date previousLoginTime;

    @ApiModelProperty(value = "上次登录Ip", name = "previousLoginIp")
    private String previousLoginIp;

    @ApiModelProperty(value = "登录失败的次数", name = "failureTimes")
    private Integer failureTimes;

    @ApiModelProperty(value = "是否为系统管理员", name = "admin")
    private Boolean admin;

    @ApiModelProperty(value = "用户是否启用", name = "shutDown")
    private Boolean shutDown;

    @ApiModelProperty(value = "是否已锁定", name = "lock")
    private Boolean lock;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getPreviousLoginTime() {
        return previousLoginTime;
    }

    public void setPreviousLoginTime(Date previousLoginTime) {
        this.previousLoginTime = previousLoginTime;
    }

    public String getPreviousLoginIp() {
        return previousLoginIp;
    }

    public void setPreviousLoginIp(String previousLoginIp) {
        this.previousLoginIp = previousLoginIp;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getShutDown() {
        return shutDown;
    }

    public void setShutDown(Boolean shutDown) {
        this.shutDown = shutDown;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }
}
