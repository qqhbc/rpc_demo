package com.yc.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;  
import org.springframework.web.context.request.ServletRequestAttributes; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.yc.log.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.yc.log.annotation.SystemControllerLog;
import com.yc.log.annotation.SystemServiceLog;
import com.yc.log.constant.WebConstant;
import com.yc.log.model.Log;
import com.yc.log.model.User;
import com.yc.log.service.LogService;

@Aspect
@Component
@Order(2)
public class SystemLogAspect {

    @Autowired
    private LogService logService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAspect.class);

    //service切点
    @Pointcut("@annotation(com.yc.log.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }
    //Controller层切点
    @Pointcut("@annotation(com.yc.log.annotation.SystemControllerLog)")  
    public void controllerAspect() {  
    }   

    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */  
    @Before("controllerAspect()")  
    public void doBefore(JoinPoint joinPoint) {  
  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();
        User user1 = new User();
        user1.setName("nima");
        session.setAttribute("user", user1);
        //读取session中的用户  
        User user = (User) session.getAttribute(WebConstant.CURRENT_USER);  
        //请求的IP  
        String ip = request.getRemoteAddr();  
        try {  
            //*========控制台输出=========*//  
            System.out.println("=====前置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));  
            System.out.println("请求人:" + user.getName());  
            System.out.println("请求IP:" + ip);  
            //*========数据库日志=========*//  
            Log log = new Log();  
            log.setAppName(joinPoint.getTarget().getClass().toString().replaceAll("(\\w+\\.)|(^\\w+\\s)", ""));
            log.setId(StringUtils.getUUID());
            log.setMethodDescription(getControllerMethodDescription(joinPoint));  
            log.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setLogType(0);  
            log.setRequestIp(ip);  
            log.setExceptionCode(null);  
            log.setExceptionDetail(null);  
            log.setRequestParams((null));  
            log.setRequestUri(request.getRequestURL().toString());
            log.setUser(user.getName());  
            log.setCreateDate(new Date());  
            //保存数据库  
            logService.add(log);  
            System.out.println("=====前置通知结束=====");  
            LOGGER.info("日志信息：{}",user.getName());
        } catch (Exception e) {  
            //记录本地异常日志  
            LOGGER.error("==前置通知异常==");  
            LOGGER.error("异常信息:{}", e.getMessage());  
        }  
    }
    
    /** 
     * 异常通知 用于拦截service层记录异常日志 
     * 
     * @param joinPoint 
     * @param e 
     */  
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute(WebConstant.CURRENT_USER);  
        //获取请求ip  
        String ip = request.getRemoteAddr();  
        //获取用户请求方法的参数并序列化为JSON格式字符串  
        String params = "";  
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
            for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                params += JSON.toJSONString(joinPoint.getArgs()[i])+";";  
            }  
        }  
        try {  
              /*========控制台输出=========*/  
            System.out.println("=====异常通知开始=====");  
            System.out.println("异常代码:" + e.getClass().getName());  
            System.out.println("异常信息:" + e.getMessage());  
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
            System.out.println("请求人:" + user.getName());  
            System.out.println("请求IP:" + ip);  
            System.out.println("请求参数:" + params);  
               /*==========数据库日志=========*/  
            Log log = new Log();
            log.setId(StringUtils.getUUID());
            log.setAppName(joinPoint.getTarget().getClass().toString().replaceAll("(\\w+\\.)|(^\\w+\\s)", ""));
            log.setMethodDescription(getServiceMthodDescription(joinPoint));  
            log.setExceptionCode(e.getClass().getName());  
            log.setLogType(1);  
            log.setExceptionDetail(e.getMessage());  
            log.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setRequestParams(params);  
            log.setRequestUri(request.getRequestURL().toString());
            log.setUser(user.toString());  
            log.setCreateDate(new Date());  
            log.setRequestIp(ip);  
            //保存数据库  
            logService.add(log);  
            System.out.println("=====异常通知结束=====");  
        } catch (Exception ex) {  
            //记录本地异常日志  
            LOGGER.error("==异常通知异常==");  
            LOGGER.error("异常信息:{}", ex.getMessage());  
        }  
         /*==========记录本地异常日志==========*/  
        LOGGER.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);  
  
    }  
  
  
    /** 
     * 获取注解中对方法的描述信息 用于service层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getServiceMthodDescription(JoinPoint joinPoint)  
            throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class<?> targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class<?>[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemServiceLog.class).description(); 
                    break;  
                }  
            }  
        }  
        return description;  
    }  
  
    
    /** 
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class<?> targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        ok:
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) { 
                    if(arguments.length == 0) {
                        description = method.getAnnotation(SystemControllerLog.class).description(); 
                        break;
                    }
                    for(int i=0;i<clazzs.length;i++) {
                        if(!clazzs[i].isInstance(arguments[i])) {
                            continue;
                        }
                        if(i == clazzs.length-1) {
                            description = method.getAnnotation(SystemControllerLog.class).description();  
                            break ok;
                        }
                    }
                }  
            }  
        }  
        return description;  
    } 
    
}
