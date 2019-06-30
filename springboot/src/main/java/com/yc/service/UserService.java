package com.yc.service;

import java.util.List;

import com.yc.entity.User;

public interface UserService {
	
	String verifyByName(User user);

	List<User> findAll();

	User getUserById(Integer id);

	void removeUserById(Integer id);

	void updateUserById(User user);

	void add(User user);

	Integer getTotalCounts();
}
