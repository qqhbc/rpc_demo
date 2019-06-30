package com.yc.log.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.log.annotation.SystemLogCon;
import com.yc.log.model.po.TUser;
import com.yc.log.model.po.User;
import com.yc.log.model.vo.UserVO;
import com.yc.log.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/test/{id}")
    public TUser getUserby(@PathVariable Integer id){
        return userService.getTUserById(id);
    }
    
    @PostMapping("/insert")
    public int insert(){
        return userService.insert();
    }
    
    @GetMapping("/list")
    @SystemLogCon(description = "获取用户信息",appName = "用户管理")
    public List<UserVO> getUserList(){
        return userService.getUserList();
    }
    
    @GetMapping("/account")
    @SystemLogCon(description = "通过账号获取用户信息",appName = "用户管理")
    public UserVO getUserById(@RequestParam String account){
        return userService.getUserById(account);
    }
    @GetMapping("/{id}")
    @SystemLogCon(description = "多源数据",appName = "test")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }
    
    @PutMapping("/update")
    public int update(){
        return userService.update();
    }
    
    @PostMapping("/export")
    public String Export(HttpServletResponse response){
        return userService.export(response);
    }
    
    @PostMapping("/import")
    public String importExcel(@RequestParam MultipartFile file) throws Exception{
        return userService.importExcel(file);
    }
    
    @GetMapping("/test/jiaohu")
    public List<User> jiaohu(){
        return userService.getUser();
    }
}
