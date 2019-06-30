package com.yc.rpc.constant;

public enum DataSourceType {
    BLOG("blog"), TEST("test");
    private String msg;

    DataSourceType(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
