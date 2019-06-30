package com.yc.log.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class TestAspect {

    @Pointcut("@annotation(com.yc.log.annotation.TestController1)")
    public void ControllerAspect() {}
    
    @Before("ControllerAspect()")
    public void doBefore() {
        System.out.println("--------------> doBefore");
    }
    
    @After("ControllerAspect()")
    public void doAfter() {
        System.out.println("--------------------> doAfter");
    }
    
    @AfterReturning("ControllerAspect()")
    public void doAfterReturning() {
        System.out.println("----------------------------> doAfterReturning");
    }
    
    @AfterThrowing("ControllerAspect()")
    public void doAfterThrowing() {
        System.out.println("-------------------> doAfterThrowing");
    }
    
}
