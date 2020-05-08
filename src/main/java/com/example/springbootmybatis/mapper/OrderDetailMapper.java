package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailMapper {
    int savafood(OrderDetail orderDetail);
    OrderDetail[] findByOrderId(int orderId);

}
