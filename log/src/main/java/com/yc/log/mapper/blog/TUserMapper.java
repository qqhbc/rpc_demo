package com.yc.log.mapper.blog;

import com.yc.log.annotation.SystemLogCon;
import com.yc.log.model.po.TUser;
import com.yc.log.model.po.TUserExample;
import com.yc.log.model.vo.UserVO;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
    @SystemLogCon
    List<TUser> getUserList();

    @Select("select * from t_user where account = #{account}")
    UserVO getUserById(@Param(value = "account") String account);

    @Delete("delete from t_user where id in (2,3) ")
    void delete();
    
    @Select("select id,name,password from t_user where id = #{id}")
    TUser selectById(@Param(value = "id") Integer id);
    
    @Select("select * from t_user")
    TUser select();
}