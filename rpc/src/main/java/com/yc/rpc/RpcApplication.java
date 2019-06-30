package com.yc.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication()
@ComponentScan("com.yc.rpc")
public class RpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(){
	    return new RestTemplate();
	}

}
