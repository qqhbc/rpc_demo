package com.yc.rpc.service;

import java.util.List;

import com.yc.rpc.model.User;

public interface UserService {
    User getUser(Integer id);
    List<User> getUsers();
}
