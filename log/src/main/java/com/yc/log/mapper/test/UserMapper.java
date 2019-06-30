package com.yc.log.mapper.test;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yc.log.model.po.User;
@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getUserById(@Param("id") Integer id);
    
    @Select("select * from user")
    List<User> getUser();
}
