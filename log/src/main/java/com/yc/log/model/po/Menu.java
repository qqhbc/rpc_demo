package com.yc.log.model.po;

import java.util.List;

public class Menu {
    private Integer id;
    private Integer parentId;
    private String name;
    private Boolean hasSubMenu = false;
    private List<Menu> subMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasSubMenu() {
        return hasSubMenu;
    }

    public void setHasSubMenu(Boolean hasSubMenu) {
        this.hasSubMenu = hasSubMenu;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

}
