package com.example.springbootmybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food  implements Serializable {
    private int id;
    private int resId;
    private  int foodType;
    private String foodName;
    private String desc;
    private  String foodImageUrl;
    private BigDecimal price;
    private int totalSales;
    private byte status;
    private String addTime;
    private int cateId;

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", resId=" + resId +
                ", foodType=" + foodType +
                ", foodName='" + foodName + '\'' +
                ", desc='" + desc + '\'' +
                ", foodImageUrl='" + foodImageUrl + '\'' +
                ", price=" + price +
                ", totalSales=" + totalSales +
                ", status=" + status +
                ", addTime='" + addTime + '\'' +
                ", cateId=" + cateId +
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

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFoodImageUrl() {
        return foodImageUrl;
    }

    public void setFoodImageUrl(String foodImageUrl) {
        this.foodImageUrl = foodImageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
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

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }
}
