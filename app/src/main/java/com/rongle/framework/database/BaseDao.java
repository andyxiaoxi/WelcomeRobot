package com.rongle.framework.database;

import android.content.Context;
import android.util.Log;

/**
 * Created by wuzj on 15-12-25.
 */
public class BaseDao {
    public static final String TAG = "wuzj_"+BaseDao.class.getSimpleName();
    protected Context mContext;
    protected String mUserId;

    public BaseDao(Context context, String userid) {
        Log.i(TAG, "BaseDao: ");
        mContext = context;
        mUserId = "wuzijing";
        //mUserId = userid;
    }


}
