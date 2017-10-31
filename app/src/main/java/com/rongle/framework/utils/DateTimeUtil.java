package com.rongle.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 时间工具类
 * Created by Administrator on 2017/4/27 0027.
 */

public class DateTimeUtil {
    private static final String TAG = "DateTimeUtil";
    public static final DateFormat NOW_TIME_FORMATTER1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /**
     * 获取年月日 小时：分钟:秒
     *
     * @param seconds
     * @return
     */
    public static String getYMDhms(long seconds) {
        return seconds == 0 ?"":NOW_TIME_FORMATTER1.format(seconds);
    }
}
