package com.yc.log.model;

import java.util.Date;

public class TestModel {
    private String name;
    private Boolean hasSuccess;
    private Double account;
    private Double price;
    private Integer num;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(Boolean hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TestModel [name=" + name + ", hasSuccess=" + hasSuccess + ", account=" + account + ", price=" + price
                + ", num=" + num + ", date=" + date + "]";
    }

}
