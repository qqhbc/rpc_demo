package com.yc.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.log.mapper.blog.MenuMapper;
import com.yc.log.model.po.Menu;
import com.yc.log.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getListMenu() {
        return menuMapper.getListMenu();
    }

    @Override
    public List<Menu> getListMenuLeaf() {
        List<Menu> parentMenu = menuMapper.getListMenuByParentId(0);
        return getList(parentMenu);
    }

    private List<Menu> getList(List<Menu> parentMenu) {
        for (Menu menu : parentMenu) {
            List<Menu> list = menuMapper.getListMenuByParentId(menu.getId());
            if (list.size() > 0) {
                menu.setHasSubMenu(true);
                menu.setSubMenu(list);
                getList(list);
            }
        }
        return parentMenu;
    }

    @Override
    public void editNameById(Menu menu) {
        menuMapper.editNameById(menu);
    }

    @Override
    public void addMenu(Menu menu) {
        menu.setParentId(menu.getId() == null ? 0 : menu.getId());
        menuMapper.addMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        List<Menu> list = menuMapper.getListMenuByParentId(id);
        if(list.size()>0){
            for(Menu menu : list){
                deleteMenu(menu.getId());
            }
        }
        menuMapper.deleteMenu(id);
    }

}
