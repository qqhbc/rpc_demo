package com.yc.log.service;

import java.util.List;

import com.yc.log.model.po.Menu;

public interface MenuService {
    
    List<Menu> getListMenu();

    List<Menu> getListMenuLeaf();

    void editNameById(Menu menu);

    void addMenu(Menu menu);

    void deleteMenu(Integer id);
}
