//package com.yc.log.config;
//
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//@Configuration
//@MapperScan(basePackages = {"com.yc.log.mapper.test"},sqlSessionFactoryRef = "testSqlSessionFactory")
//public class TestDataBaseConfig {
//    @Bean(name = "jiaohuDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.test")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().build();
//    }
//    
//    @Bean(name = "testSqlSessionFactory")
//    public SqlSessionFactory blogSqlSessionFactory(@Qualifier("jiaohuDataSource") DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//    @Bean(name = "testTransactionManager")
//    public DataSourceTransactionManager blogTransactionManager(@Qualifier("jiaohuDataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
