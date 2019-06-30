package com.yc.rpc.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yc.rpc.client.RestTemplateService;
import com.yc.rpc.model.User;

@RestController
public class TestController {
    
    @Autowired
    private RestTemplateService rpcService;
    
    @SuppressWarnings("rawtypes")
    @GetMapping("/test")
    public Map get(){
        String url = "http://127.0.0.1:8082/log/user/account?account={account}";
        Map<String,Object> headers = new HashMap<>();
        headers.put("account", "56456946");
        return rpcService.get(url, headers, Map.class);
    }
    
    @PostMapping("/test")
    public String addInfo(@RequestBody User user){
        return user.toString();
    } 
}
