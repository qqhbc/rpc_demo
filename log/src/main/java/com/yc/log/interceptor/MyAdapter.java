package com.yc.log.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter{
    public void addInterceptors(InterceptorRegistry registry) {

        //添加拦截器
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
