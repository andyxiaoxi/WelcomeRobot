package com.rongle.welcomerobot.base.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thj on 2017/11/2.
 * @author thj
 *
 * http 返回的基本基类
 * */
public class BaseEntity<T> {
    private static String SUCCESS_CODE="200";//成功的code

    @SerializedName("error_no")
    private String code;
    @SerializedName("error_msg")
    private String msg;
    private T data;


    public boolean isSuccess(){
        return getCode().equals(SUCCESS_CODE);
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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
