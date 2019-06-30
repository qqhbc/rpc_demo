package com.yc.rpc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yc.rpc.model.User;
import com.yc.rpc.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
}
