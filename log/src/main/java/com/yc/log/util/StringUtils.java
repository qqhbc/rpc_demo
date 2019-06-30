package com.yc.log.util;

import java.util.UUID;

public class StringUtils {
    public static String getUUID(){
       return UUID.randomUUID().toString().replaceAll("\\D", "");
    }
}
