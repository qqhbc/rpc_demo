package com.yc.rpc.config;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    
    private static final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);
    public DynamicRoutingDataSource(Map<Object,Object> map,DataSource dataSource){
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(map);
        super.afterPropertiesSet();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("current datasource {}",DynamicContextDataSource.get());
        return DynamicContextDataSource.get();
    }


}
