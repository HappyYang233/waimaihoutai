package com.example.springbootmybatis.service;


import com.example.springbootmybatis.entity.UtilEntity.Static;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlarmTaskService {
    @Autowired
    OrderService orderService;
    @Scheduled(cron = "0 0 10,16 * * *")
    public void orderStop() throws InterruptedException {
        Static.orderStatus=1;
        System.out.println("不允许下单");
    }
    @Scheduled(cron = "0 0 0,13 * * * ")
    public void orderOpen()
    {
        Static.orderStatus=0;
        System.out.println("允许下单");
    }
    @Scheduled(cron = "0 0 13 * * * ")
    public void dinnerOpen()
    {
        Static.orderType=1;
        System.out.println("现阶段是晚餐标志位");
    }
    @Scheduled(cron = "0 0 0 * * *")
    public void  lunchOpen()
    {
        Static.orderType=0;
        System.out.println("现阶段是午餐标志位");
    }
    @Scheduled(cron = "0 0 10,16 * * *")
    public void setOrderFinish()
    {
        System.out.println("设置订单完成");
        orderService.setOrderFinish();
    }

}

