package com.example.springbootmybatis.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private int id;
    private  int resId;
    private String addressInfo;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", resId=" + resId +
                ", addressInfo='" + addressInfo + '\'' +
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

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
