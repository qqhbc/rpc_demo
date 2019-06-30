package com.yc.log.model.vo;

import com.yc.log.model.po.TUser;

public class UserVO {
    private String account;
    
    private String name;
    
    private String phone;
    
    private String address;
    
    private String email;
    
    public String getAccount() {
        return account;
    }



    public void setAccount(String account) {
        this.account = account;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getAddress() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public static UserVO valueOf(TUser user){
        UserVO vo = new UserVO();
        vo.setAccount(user.getAccount());
        vo.setAddress(user.getAddress());
        vo.setEmail(user.getEmail());
        vo.setName(user.getName());
        vo.setPhone(user.getPhone());
        return vo;
    }
}
