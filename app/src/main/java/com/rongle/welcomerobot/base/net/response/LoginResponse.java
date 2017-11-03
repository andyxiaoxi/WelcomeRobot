package com.rongle.welcomerobot.base.net.response;

/**
 * Created by thj on 2017/11/1.
 * @author thj
 * 登陆响应
 */
public class LoginResponse {
    private String token;
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this. token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
