package com.yc.log.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestCommandLine implements CommandLineRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(TestCommandLine.class);
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("-------------------------------> 测试初始化运行");
        
    }

}
