package com.example.springbootmybatis.entity;

import java.io.Serializable;

public class FoodCategory implements Serializable {
    private int id;
    private  int resId;
    private  String name;
    private  String desc;
    private  byte status;
    private String addTime;

    @Override
    public String toString() {
        return "FoodCategory{" +
                "id=" + id +
                ", resId=" + resId +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                ", addTime='" + addTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
