package com.yc.log.model;

public class PlanVO {
    /**
     * id
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 标记
     */
    private String remark;
    /**
     * 区分存量优化与网络演进：4G,5G
     */
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
