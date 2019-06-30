package com.yc.log.aspest;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yc.log.annotation.SystemLogCon;
import com.yc.log.annotation.SystemLogService;
import com.yc.log.contants.LogType;
import com.yc.log.mapper.blog.LogMapper;
import com.yc.log.model.po.Log;
import com.yc.log.util.StringUtils;

@Aspect
@Order(2)
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogMapper logMapper;

    @Pointcut("@annotation(com.yc.log.annotation.SystemLogCon)")
    public void controllerAspect() {
    }

    @Pointcut("@annotation(com.yc.log.annotation.SystemLogService)")
    public void serviceAspect() {
    }

    @Before("controllerAspect() && @annotation(con)")
    public void doBefore(JoinPoint joinPoint,SystemLogCon con) throws Exception {
        try {
            Log log = packageLog(joinPoint, LogType.CONTROLLER.getMsg());
            logMapper.insertSelective(log);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Throwable e) throws Exception{
        Log log = packageLog(joinPoint, LogType.SERVICE.getMsg());
        // log.setExceptionCode(e.getCause().toString());
        log.setExceptionDetail(e.getMessage());
        logMapper.insertSelective(log);
    }

    private Log packageLog(JoinPoint joinPoint, String logType) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();
        Method method = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes);
        String desc = "", appName = "", paramter = "";
        if (LogType.CONTROLLER.getMsg().equals(logType)) {
            desc = method.getAnnotation(SystemLogCon.class).description();
            appName = method.getAnnotation(SystemLogCon.class).appName();
        } else {
            desc = method.getAnnotation(SystemLogService.class).description();
            appName = method.getAnnotation(SystemLogService.class).appName();
        }
        Log log = new Log();
        log.setId(StringUtils.getUUID());
        if (LogType.CONTROLLER.getMsg().equals(logType)) {
            log.setLogType(0);
        } else {
            log.setLogType(1);
        }
        log.setCreateTime(new Date());
        log.setAppName(appName);
        log.setMethodName(methodName);
        for (Object arg : joinPoint.getArgs()) {
            paramter += arg.toString() + ",";
        }
        log.setRequestParams(paramter == "" ? "no paramter" : paramter.substring(0, paramter.length() - 1));
        log.setMethodDescription(desc);
        log.setRequestIp(request.getRemoteAddr());
        log.setRequestUrl(request.getRequestURL().toString());
        return log;
    }

}
