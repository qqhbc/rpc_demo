package com.yc.log.util;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    public static List<Object> dataSourceKeys = new ArrayList<>();
    
    public static void setDataSourceKey(String key){
        contextHolder.set(key);
    }
    
    public static String getDataSourceKey(){
        return contextHolder.get();
    }
    
    public static void clearDataSourceKey(){
        contextHolder.remove();
    }
    
    public static boolean containDataSourceKey(String key){
        return dataSourceKeys.contains(key);
    }
}
