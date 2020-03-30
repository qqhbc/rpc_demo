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
    public String get(){
        String url = "https://testing-easybuy-core-app-ng.easybuy.loans/appserver/thirdPartyQuery/activateDeviceTag.do";
        Map<String,Object> headers = new HashMap<>(1);
        headers.put("sign", "xxxxx");
        return rpcService.post(url, headers, String.class);
    }
    
    @PostMapping("/test")
    public String addInfo(@RequestBody User user){
        return user.toString();
    } 
}
