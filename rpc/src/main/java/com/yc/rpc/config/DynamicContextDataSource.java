package com.yc.rpc.config;

public class DynamicContextDataSource {
    private static final ThreadLocal<String> contextHandler = new ThreadLocal<>();
    
    public static String get(){
        return contextHandler.get();
    }
    
    public static void set(String key){
        contextHandler.set(key);
    }
    
    public static void clear(){
        contextHandler.remove();
    }
}

