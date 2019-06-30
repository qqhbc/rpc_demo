package com.yc.log.mapper.blog;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yc.log.model.po.Menu;

@Mapper
public interface MenuMapper {
    
    @Select("select id,parent_id as parentId,name from menu")
    List<Menu> getListMenu();

    @Select("select id,name from menu where parent_id = #{parentId}")
    List<Menu> getListMenuByParentId(int parentId);

    @Update("update menu set name = #{name} where id = #{id}")
    void editNameById(Menu menu);

    @Insert("insert into menu (parent_id,name) values(#{parentId},#{name})")
    void addMenu(Menu menu);
    
    @Delete("delete from menu where id = #{id}")
    void deleteMenu(Integer id);
}
