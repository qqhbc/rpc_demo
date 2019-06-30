package com.yc.log.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Log implements Serializable {

    private static final long serialVersionUID = -2656540179386404484L;

    private String id;

    private String appName;

    private Integer logType;

    private String user;

    private String methodName;

    private String requestParams;

    private String methodDescription;

    private String requestIp;

    private String requestUri;

    private String requestPath;

    private String exceptionCode;

    private String exceptionDetail;

    private Date createDate;

    private String status;

    private String content;

}
