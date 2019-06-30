package com.yc.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.rpc.annotation.DataSourceConfig;
import com.yc.rpc.mapper.UserMapper;
import com.yc.rpc.model.User;
import com.yc.rpc.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper mapper;
    
    @DataSourceConfig("test")
    public List<User> getUsers(){
       return mapper.getUsers();
    }
    public User getUser(Integer id){
        return mapper.getUser(id);
    }
}
