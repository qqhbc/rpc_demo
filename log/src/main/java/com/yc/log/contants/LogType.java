package com.yc.log.contants;

public enum LogType {
    CONTROLLER("controller"), SERVICE("service");
    private String msg;

    private LogType(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
