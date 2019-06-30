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
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//@Configuration
//@MapperScan(basePackages = {"com.yc.log.mapper.blog"},sqlSessionFactoryRef = "blogSqlSessionFactory")
//public class BlogDataBaseConfig {
//    @Bean(name = "blogDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.blog")
//    public DataSource dataSource(){
//        return DataSourceBuilder.create().build();
//    }
//    
//    @Bean(name = "blogSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory blogSqlSessionFactory(@Qualifier("blogDataSource") DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
//        return bean.getObject();
//    }
//    @Bean(name = "blogTransactionManager")
//    @Primary
//    public DataSourceTransactionManager blogTransactionManager(@Qualifier("blogDataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
