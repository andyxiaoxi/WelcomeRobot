package com.rongle.welcomerobot.base.net;

/**
 * Created by thj on 2017/11/2.
 * @author  thj
 */
public interface HttpCallback<T> {
     void onSuccess(T response);

     void onFaile();
}
