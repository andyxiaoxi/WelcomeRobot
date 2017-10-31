package com.rongle.rxjava_retrofit.bean;

/**
 * Created by Administrator on 2017/10/31.
 */
public class LogInfo {
    private String error_msg;
    private String error_no;
    private TokenDataResponse data;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public TokenDataResponse getData() {
        return data;
    }

    public void setData(TokenDataResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "error_msg='" + error_msg + '\'' +
                "error_no='" + error_no + '\'' +
                '}';
    }
}
