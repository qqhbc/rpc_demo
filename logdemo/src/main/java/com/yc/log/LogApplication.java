package com.yc.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.yc.log.mapper")
@ComponentScan("com.yc.log")
public class LogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		String s = "test";
	    // 返回模板实例
	    return new RestTemplate();
	}

}

