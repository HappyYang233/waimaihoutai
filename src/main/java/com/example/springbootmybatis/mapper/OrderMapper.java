package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.entity.Order;
import com.example.springbootmybatis.entity.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {
    int pay(Order order);
    //查找openid下所有的订单
    Order[] findAll(String openId);
    int updateStatus(byte status,int id);
    Food[] findFoodByFoodIdArray(int[] foodId);
    Order checkStatus(int id);
    void setOrderFinish();
    List<Order> test(int id);
    List<OrderVo> findOrderAndOrderDetail(int resId);
    int removeOrder(int id);
    List<OrderVo> findOrderByDate(int resId,String date,int foodType);
    BigDecimal getOneDayMoney(String time,int resId);
    BigDecimal getAllMoney(int resId);
    List<Map<String,Object>> countAddress(int resId);
    List<Map<String,Object>> countSales(int resId);
}
