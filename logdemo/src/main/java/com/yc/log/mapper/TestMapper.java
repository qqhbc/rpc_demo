package com.yc.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.log.model.Test;

public interface TestMapper {
    
    String TEST_SEGMENT = " t.id,t.t_longitude as longitude,t.t_latitude as latitude ";
    List<Test> getTest();
    
    @Select("select"+ TEST_SEGMENT +"from test as t where id = #{id}")
    Test getTestById(@Param("id") Integer id);
}
