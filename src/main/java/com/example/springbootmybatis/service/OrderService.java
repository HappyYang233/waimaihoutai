package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.entity.Order;
import com.example.springbootmybatis.entity.OrderVo;
import com.example.springbootmybatis.mapper.OrderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    public  int pay( Order order)
    {
        return orderMapper.pay(order);
    }
    public Order[] findAll(String openId)
    {
        return orderMapper.findAll(openId);
    }
    public  int updateStatus(byte status , int id)
    {
        return orderMapper.updateStatus(status,id);
    }
   public  Food[] findFoodByFoodIdArray(int[] foodId){
        return orderMapper.findFoodByFoodIdArray(foodId);
    }
    public Order checkStatus(int id)
    {
        return orderMapper.checkStatus(id);
    }
    public void setOrderFinish(){
        orderMapper.setOrderFinish();
    }
    public List<OrderVo> findOrderAndOrderDetail(int resId){
        return  orderMapper.findOrderAndOrderDetail(resId);
    }
    public int removeOrder(int id)
    {
        return orderMapper.removeOrder(id);
    }
    public  List<OrderVo> findOrderByDate(int resId,String date,int foodType){
        return orderMapper.findOrderByDate(resId,date,foodType);
    }
}
