package com.example.springbootmybatis.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private  Integer id;
    private  Integer resId;
    private String userName;
    private String mobile;
    private String password;
    private String email;
    private String trueName;
    private String sex;
    private byte status;
    private String addTime;
    private int type;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", resId=" + resId +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", trueName='" + trueName + '\'' +
                ", sex='" + sex + '\'' +
                ", status=" + status +
                ", addTime='" + addTime + '\'' +
                ", type=" + type +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
