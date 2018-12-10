package com.cc.dapp.manager.api.util;

import java.sql.Timestamp;

/**
 * 日期工具类
 *
 * @author sunli
 * @date 2018/12/07
 */
public class DateUtil {

    /**
     * 返回当前时间的时间戳
     *
     * @return 当前时间的时间戳
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
