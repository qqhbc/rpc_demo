package com.yc.log.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yc.log.model.po.TUser;
import com.yc.log.model.po.User;
import com.yc.log.model.vo.UserVO;

public interface UserService {
    List<UserVO> getUserList();

    UserVO getUserById(String account);

    User getUserById(Integer id);

    String export(HttpServletResponse response);

    String importExcel(MultipartFile file) throws Exception;

    int update();
    
    TUser getTUserById(Integer id);
    
    int insert();

    /** 娇狐 数据库*/
    List<User> getUser();
}
