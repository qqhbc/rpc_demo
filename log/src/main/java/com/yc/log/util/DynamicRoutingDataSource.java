package com.yc.log.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource{

    private static final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class); 
    
    public DynamicRoutingDataSource(DataSource blogDataSource, Map<Object, Object> dataSourceMap) {
        super.setDefaultTargetDataSource(blogDataSource);
        super.setTargetDataSources(new HashMap<>(dataSourceMap));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("current datasource id [{}]",DynamicDataSourceContextHolder.getDataSourceKey() == null ? "blog" :DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

}
