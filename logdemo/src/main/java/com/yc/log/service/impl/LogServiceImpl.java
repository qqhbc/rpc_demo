package com.yc.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.log.mapper.LogMapper;
import com.yc.log.model.Log;
import com.yc.log.service.LogService;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;
    @Override
    public void add(Log log) {
        logMapper.insertSelective(log);
    }

}
