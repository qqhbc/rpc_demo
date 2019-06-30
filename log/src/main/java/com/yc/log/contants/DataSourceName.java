package com.yc.log.contants;

public enum DataSourceName {
    BLOG("blog"),TEST("test"),JIAOHU_DB("jiaohu_db");
    
    private String msg;
    
    DataSourceName(String msg){
        this.msg = msg;
    }
     
    public String get(){
        return msg;
    }
}
