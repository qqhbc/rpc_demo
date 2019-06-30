package com.yc.rpc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.rpc.model.User;

@Mapper
public interface UserMapper {

    @Select("select id,name,create_date from t_user")
    @Results(value = {
            @Result(column = "create_date",property = "createDate")
    })
    List<User> getUsers();
    
    @Select("select id,name,create_date from t_user where id = #{id}")
    User getUser(@Param("id") Integer id);
    
}
