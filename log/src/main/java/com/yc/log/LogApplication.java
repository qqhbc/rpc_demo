package com.yc.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
@ComponentScan("com.yc.log")
@EnableTransactionManagement
public class LogApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}

}

