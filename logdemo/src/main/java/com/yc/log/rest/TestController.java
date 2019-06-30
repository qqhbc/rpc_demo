package com.yc.log.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yc.log.annotation.SystemControllerLog;
import com.yc.log.mapper.TestMapper;
import com.yc.log.model.PlanVO;
import com.yc.log.model.Test;
import com.yc.log.service.TestService;
import com.yc.log.template.RestTemplateService;

@RestController
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;
    @Autowired
    private RestTemplateService rpcService;
    @Autowired
    private TestMapper mapper;
    
    @GetMapping("/test")
    @SystemControllerLog(description = "查询用户")
    public List<Test> findTest() {
        List<Test> test = testService.getTest();
        LOGGER.info("bbbb");
        return test;
    }
    
    @GetMapping("/test-segment")
    public Test get() {
        Test test = mapper.getTestById(11);
        return test;
    }
    
    @GetMapping("/re-test")
    @SystemControllerLog(description = "测试查询用户")
    public Map findTest(String address,String name){
        String url = "http://127.0.0.1:8414/smart5g/ptn/v1/planmanagement/list";
        Map<String,Object> headers = new HashMap<>();
        headers.put("network-code", "test");
        headers.put("city", 1);
        return rpcService.get(url, headers, Map.class);
    }
    
    @GetMapping("/test-params")
    public Map testParam(@RequestParam String type) {
        String url = "http://127.0.0.1:8414/**/list?type={type}";
        Map<String, Object> headers = new HashMap<>();
        headers.put("network-code", "test");
        headers.put("city", 1);
        headers.put("type", type);
        headers.put("name", "hty");
        return rpcService.get(url, headers, Map.class);
    }
    
    @PostMapping("/update")
    public Map update(@RequestParam String type) {
        String url = "http://127.0.0.1:8414/**/update?type={type}";
        Map<String, Object> headers = new HashMap<>();
        headers.put("network-code", "test");
        headers.put("city", 1);
        headers.put("type", type);
        PlanVO vo = new PlanVO();
        vo.setId(10);
        vo.setName("test");
        vo.setRemark("test");
        Map<String,String> map = new HashMap<>();
        map.put("account", "***");
        String user = JSONObject.toJSONString(map);
        headers.put("user-info", user);
        String body1 = JSONObject.toJSONString(vo);
        return rpcService.post(url, headers, body1, Map.class);
    }
    
}
