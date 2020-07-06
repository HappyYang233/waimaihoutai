package com.example.springbootmybatis.entity;

import java.util.List;

public class OrderVo extends Order {
    private List<OrderDetail> list;

    @Override
    public String toString() {
        return "OrderVo{" +
                "list=" + list +
                '}';
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }
}
