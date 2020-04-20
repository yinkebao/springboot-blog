package com.es.hfuu.plugin.user.vo;

import com.es.hfuu.common.vo.PagingVO;

/**
 * @ClassName UserVO
 * @Description 用户查询vo类
 * @Author lsx
 * @Date 2019/11/8
 */
public class UserVO extends PagingVO {

    /** 昵称 */
    private String nickName;
    /** 用户名 */
    private String userName;

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
}
