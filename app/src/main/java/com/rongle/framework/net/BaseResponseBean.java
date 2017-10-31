package com.rongle.framework.net;

/**
 * Created by tanggongwen on 17-6-20.
 */

public class BaseResponseBean<T> {
    private String error_no;
    private String error_msg;
    private T t;

    public String getError_no() {
        return error_no;
    }

    public void setError_no(String error_no) {
        this.error_no = error_no;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "error_no:" + error_no
                + "error_msg:" + error_msg
                + "result:" + t.toString();
    }
}
