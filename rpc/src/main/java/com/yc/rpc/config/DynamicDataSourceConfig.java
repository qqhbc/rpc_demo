package com.yc.rpc.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.yc.rpc.constant.DataSourceType;

@Configuration
public class DynamicDataSourceConfig {

    @Bean("blogDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.blog")
    public DataSource blogDataSource(){
        return DataSourceBuilder.create().build();
    }
    
    @Bean("testDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }
    
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("blogDataSource") DataSource blogDataSource,@Qualifier("testDataSource") DataSource testDataSource){
        Map<Object,Object> map = new HashMap<>(2);
        map.put(DataSourceType.BLOG.getMsg(), blogDataSource);
        map.put(DataSourceType.TEST.getMsg(), testDataSource);
        
        return new DynamicRoutingDataSource(map,blogDataSource);
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
