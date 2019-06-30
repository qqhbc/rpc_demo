package com.yc.log.config;

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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.yc.log.contants.DataSourceName;
import com.yc.log.util.DynamicRoutingDataSource;

@Configuration
public class DataSourceConfigurer {
    @Bean("blogDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.blog")
    public DataSource blogDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean("jiaohuDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.jiaohu_db")
    public DataSource jiaohuDbDataSource(){
        return DataSourceBuilder.create().build();
    }
    
    @Bean("testDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }
    
    @Bean("dynamicDataSource")
    public DynamicRoutingDataSource dynamicDataSource(@Qualifier("blogDataSource") DataSource blogDataSource,
            @Qualifier("testDataSource") DataSource testDataSource,@Qualifier("jiaohuDbDataSource") DataSource jiaohuDbDataSource){
        Map<Object, Object> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put(DataSourceName.BLOG.get(), blogDataSource);
        dataSourceMap.put(DataSourceName.TEST.get(), testDataSource);
        dataSourceMap.put(DataSourceName.JIAOHU_DB.get(), jiaohuDbDataSource);

        return new DynamicRoutingDataSource(blogDataSource, dataSourceMap);
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DynamicRoutingDataSource dynamicDataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public DataSourceTransactionManager  transactionManager(@Qualifier("dynamicDataSource") DynamicRoutingDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
