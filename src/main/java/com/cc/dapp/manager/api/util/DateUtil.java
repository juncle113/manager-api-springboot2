package com.cc.dapp.manager.api.util;

import java.sql.Timestamp;

public class DateUtil {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    };
}
