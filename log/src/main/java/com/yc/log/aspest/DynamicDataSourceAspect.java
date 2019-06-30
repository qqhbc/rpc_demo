package com.yc.log.aspest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yc.log.annotation.DataSourceRouting;
import com.yc.log.contants.DataSourceName;
import com.yc.log.util.DynamicDataSourceContextHolder;

@Aspect
@Order(-10)
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
    
//    @Pointcut("@annotation(com.yc.log.annotation.DataSourceRouting)")
//    public void DataSourceRouting(){}
//    
//    @Before("DataSourceRouting() && @annotation(dsr)")
//    public void switchDataSource(JoinPoint point,DataSourceRouting dsr) {
//        if (!DynamicDataSourceContextHolder.containDataSourceKey(dsr.description())) {
//            logger.error("DataSource [{}] doesn't exist, use default DataSource [{}]", dsr.description());
//        } else {
//            DynamicDataSourceContextHolder.setDataSourceKey(dsr.description());
//            logger.info("Switch DataSource to [{}] in Method [{}]",
//                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
//        }
//    }
//    
//    @After("DataSourceRouting() && @annotation(dsr)")
//    public void restoreDataSource(JoinPoint point, DataSourceRouting dsr) {
//        DynamicDataSourceContextHolder.clearDataSourceKey();
//        logger.info("Restore DataSource to [{}] in Method [{}]",
//                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
//    }
    
    
    @Before("@annotation(dsr)")
    public void doBefore(DataSourceRouting dsr){
        if(dsr == null){
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceName.BLOG.get());
            logger.info("switch datasource is [{}]",DataSourceName.BLOG.get());
        }else{
            DynamicDataSourceContextHolder.setDataSourceKey(dsr.description());
            logger.info("switch datasource is [{}]",dsr.description());
        }
    }
    
    @After("@annotation(dsr)")
    public void doAfter(DataSourceRouting dsr){
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
    


}
