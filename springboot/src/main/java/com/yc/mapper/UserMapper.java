package com.yc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yc.entity.User;

@Mapper
public interface UserMapper {
	
	String verifyByName(String name);

	List<User> findAll();

	User getUserById(Integer id);

	void removeUserById(Integer id);

	void updateUserById(User user);

	void add(User user);

	Integer getTotalCounts();
}
