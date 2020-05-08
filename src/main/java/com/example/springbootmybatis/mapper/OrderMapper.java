package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int pay(Order order);
    //查找openid下所有的订单
    Order[] findAll(String openId);
}
