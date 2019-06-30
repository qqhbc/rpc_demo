package com.yc.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.log.annotation.SystemServiceLog;
import com.yc.log.annotation.TestController1;
import com.yc.log.mapper.TestMapper;
import com.yc.log.model.Test;
import com.yc.log.service.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    
    @Override
    @SystemServiceLog(description = "查询用户")
    @TestController1
    public List<Test> getTest() {
        System.out.println("-------------------------------->----------------------------");
        List<Test> test = testMapper.getTest();
        System.out.println(test.get(0));
        return test;
    }

}
