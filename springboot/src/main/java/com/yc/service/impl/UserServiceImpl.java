package com.yc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.entity.User;
import com.yc.mapper.UserMapper;
import com.yc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public String verifyByName(User user) {
		String msg = "";
		String password = userMapper.verifyByName(user.getName());
		if(!"".equals(password)&&password!=null){
			if(password.equals(user.getPassword())){
				msg = "success";
			}else{
				msg = "passwordError";
			}
		}else{
			msg = "nameError";
		}
		return msg;
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public void removeUserById(Integer id) {
		userMapper.removeUserById(id);
	}

	@Override
	public void updateUserById(User user) {
		userMapper.updateUserById(user);
	}

	@Override
	public void add(User user) {
		userMapper.add(user);
	}

	@Override
	public Integer getTotalCounts() {
		return userMapper.getTotalCounts();
	}

}
