package com.example.springbootmybatis.entity;

import java.io.Serializable;

public class ResInfo implements Serializable {
    private int id;
    private  String resName;
    private String contactName;
    private String contactMobile;
    private String resImageUrl;
    private String notice;
    private int deliveryAddressId;
    private int goodsListId;
    private byte status;

    @Override
    public String toString() {
        return "ResInfoMapper{" +
                "id=" + id +
                ", resName='" + resName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                ", resImageUrl='" + resImageUrl + '\'' +
                ", notice='" + notice + '\'' +
                ", deliveryAddressId=" + deliveryAddressId +
                ", goodsListId=" + goodsListId +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getResImageUrl() {
        return resImageUrl;
    }

    public void setResImageUrl(String resImageUrl) {
        this.resImageUrl = resImageUrl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(int deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public int getGoodsListId() {
        return goodsListId;
    }

    public void setGoodsListId(int goodsListId) {
        this.goodsListId = goodsListId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
