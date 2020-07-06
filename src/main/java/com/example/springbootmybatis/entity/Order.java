package com.example.springbootmybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private  Integer id;
    private int resId;
    private BigDecimal totalMoney;
    private BigDecimal payMoney;
    private String address;
    private int num;
    private String addTime;
    private byte status;
    private String getName;
    private String getMobile;
    private String openId;
    private String foodImageUrl;
    private int foodType;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", resId=" + resId +
                ", totalMoney=" + totalMoney +
                ", payMoney=" + payMoney +
                ", address='" + address + '\'' +
                ", num=" + num +
                ", addTime='" + addTime + '\'' +
                ", status=" + status +
                ", getName='" + getName + '\'' +
                ", getMobile='" + getMobile + '\'' +
                ", openId='" + openId + '\'' +
                ", foodImageUrl='" + foodImageUrl + '\'' +
                ", foodType=" + foodType +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getGetMobile() {
        return getMobile;
    }

    public void setGetMobile(String getMobile) {
        this.getMobile = getMobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public void setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
}
