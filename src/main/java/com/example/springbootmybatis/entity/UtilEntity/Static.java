package com.example.springbootmybatis.entity.UtilEntity;

import com.example.springbootmybatis.util.TimerUtil;

public class Static {
    //下单标志位 0表示允许下单 1表示不允许下单
    public  static  int orderStatus = TimerUtil.getInitOrderStatus();
    //用餐类型  0表示午餐  1表示晚餐
    public static int orderType=TimerUtil.getInitOrderType();
    public static int num = 0;

}
