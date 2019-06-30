package com.yc.log.aspest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class TestAspect {
    
    @Pointcut("execution(* com.yc.log.service.*.*(..))")
    public void testPoint(){
        
    }
    
    @Before("testPoint()")
    public void doBefore(){
        System.out.println("before");
    }
    
    @After("testPoint()")
    public void doAfter(){
        System.out.println("doAfter");
    }
    
    @AfterReturning("testPoint()")
    public void doAfterReturning(){
        System.out.println("doAfterReturning");
    }
}
