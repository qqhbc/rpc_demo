package com.yc.rpc.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yc.rpc.annotation.DataSourceConfig;
import com.yc.rpc.config.DynamicContextDataSource;
import com.yc.rpc.constant.DataSourceType;

@Aspect
@Order(-10)
@Component
public class DataSourceAspect {
    
    @Before("@annotation(dsc)")
    public void doBefore(DataSourceConfig dsc){
        if(dsc == null){
            DynamicContextDataSource.set(DataSourceType.BLOG.getMsg());
        }else{
            DynamicContextDataSource.set(dsc.value());
        }
    }
    
    @After("@annotation(dsc)")
    public void doAfter(DataSourceConfig dsc){
        DynamicContextDataSource.clear();
    }
}
