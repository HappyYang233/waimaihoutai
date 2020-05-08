package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.OrderDetail;
import com.example.springbootmybatis.mapper.OrderDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDeatailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;
    public  int savafood(OrderDetail orderDetail){
        return orderDetailMapper.savafood(orderDetail);
    }
    public OrderDetail[] findByOrderId(int orderId){
       return  orderDetailMapper.findByOrderId(orderId);
    }
}
