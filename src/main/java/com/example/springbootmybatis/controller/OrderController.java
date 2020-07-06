package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.*;
import com.example.springbootmybatis.entity.UtilEntity.RedisUtil;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.FoodService;
import com.example.springbootmybatis.service.OrderDeatailService;
import com.example.springbootmybatis.service.OrderService;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.util.TimerUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserService userService;
    @Autowired
    OrderDeatailService orderDeatailService;
    @Autowired
    FoodService foodService;
    @RequestMapping("/wx/order/pay")
    @Transactional
    public Status pay(@RequestBody JSONObject jsonObject){
        if(Static.orderStatus==1) {
            new Status(20, "当前时间段不允许下单");
        }
        String sessionId = jsonObject.getString("sessionId");
        JSONObject idAndkey = (JSONObject) redisUtil.get(sessionId);
        String openId = idAndkey.getString("openid");
        User user = userService.findByOpenId(openId);
        BigDecimal totalPrice = new BigDecimal(jsonObject.getInteger("totalPrice"));
        BigDecimal res = user.getWallet().subtract(totalPrice);
        //判断钱够不够
        if(res.compareTo(new BigDecimal(0))==-1)
        {
         return    new Status(30,"钱不够，请充值");
        }
        else{
            //下单成功 进入支付流程
            user.setWallet(res);
            userService.updateWallet(user);
        }
        int resId= jsonObject.getInteger("resId");
        int num=getNum(resId);
        Order order = new Order();
        JSONObject address=jsonObject.getJSONObject("address");
        order.setAddress(address.getString("addressInfo"));
        order.setGetMobile(jsonObject.getJSONObject("userInfo").getString("userMobile"));
        order.setGetName(jsonObject.getJSONObject("userInfo").getString("userName"));
        order.setResId((Integer)jsonObject.get("resId"));
        order.setPayMoney(totalPrice);
        order.setTotalMoney(totalPrice);
        order.setNum(num);
        order.setStatus((byte) 0);
        order.setOpenId(openId);
        order.setFoodType(Static.orderType);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        order.setAddTime(time);
        JSONArray jsonArray = jsonObject.getJSONArray("buyCar");
        //设置第一件商品图片到订单中
        String url = jsonArray.getJSONObject(0).getString("foodImageUrl");
        order.setFoodImageUrl(url);
        orderService.pay(order);
        int orderId=order.getId();
//        JSONArray jsonArray = jsonObject.getJSONArray("buyCar");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        for(int i=0;i<jsonArray.size();i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            orderDetail.setFoodId((Integer) object.getInteger("id"));
            orderDetail.setNum((Integer) object.getInteger("num"));
            orderDetail.setFoodName( object.getString("foodName"));
            foodService.addTotalSales(object.getInteger("num"),object.getInteger("id"));
            orderDeatailService.savafood(orderDetail);
        }
        return new Status(1,"下单成功");
    }
    @Transactional
    @RequestMapping("/wx/order/findAll")
    public Status findAll(@RequestBody JSONObject jsonObject){
        String sessionId = jsonObject.getString("sessionId");
        JSONObject idAndkey = (JSONObject) redisUtil.get(sessionId);
        String openId = idAndkey.getString("openid");
        //查找openid下所有的order
        Order[] orders = orderService.findAll(openId);
        if(orders==null)
            return new Status('5',"无订单");
        String ordersString = JSONObject.toJSONString(orders);
        JSONArray jsonArray = JSONArray.parseArray(ordersString);
        jsonArray= sortJSONArray(jsonArray);
        for(int i=0;i<jsonArray.size();i++)
        {
            JSONObject jsonObject1= jsonArray.getJSONObject(i);
           int id = jsonObject1.getInteger("id");
           OrderDetail[] orderDetails =orderDeatailService.findByOrderId(id);
           String orderDetailsString = JSON.toJSONString(orderDetails);
           JSONArray jsonArray1 = JSONArray.parseArray(orderDetailsString);
           jsonObject1.put("orderDetail",jsonArray1);
        }
        System.out.println(jsonArray);
        return new Status(1,jsonArray);
    }
    @RequestMapping("/wx/order/cancel")
    public Status cancelOrder(@RequestBody JSONObject jsonObject){
        //获取订单id
        int id = jsonObject.getInteger("id");
        int totalMoney = jsonObject.getInteger("totalMoney");
        BigDecimal price = new BigDecimal(totalMoney);
        Order order = orderService.checkStatus(id);
        if(order.getStatus()!=0)
        {
           return  new Status(8,"该订单已过取消时间");
        }
        String sessionId = jsonObject.getString("sessionId");
        JSONObject idAndkey = (JSONObject) redisUtil.get(sessionId);
        String openId = idAndkey.getString("openid");
        //设置订单状态为2-订单取消
        orderService.updateStatus((byte)2,id);
        User user = userService.findByOpenId(openId);
        price=price.add(user.getWallet());
        user.setWallet(price);
        userService.updateWallet(user);
        return new Status(9,"取消成功");
    }
    @RequestMapping("/wx/order/findOrderDetail")
    public Status  findOrderDetail(@RequestBody JSONObject jsonObject)
    {

        List<Object> foodId = jsonObject.getJSONArray("params");
        int[] list =  new int[foodId.size()];
        for(int i= 0;i<foodId.size();i++)
        {
            list[i]=(int)foodId.get(i);
        }
        Food[] foods = orderService.findFoodByFoodIdArray(list);
        return new Status(1,foods);
    }
        public JSONArray sortJSONArray(JSONArray jsonArray)
        {
            List<JSONObject> list  =  JSONArray.parseArray(jsonArray.toJSONString(),JSONObject.class);
            Collections.sort(list, new Comparator<JSONObject>() {
//                SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    int a = o1.getInteger("id");
                    int b = o2.getInteger("id");
                    if(a>b){
                        return -1;
                    }
                    else if(a==b){
                        return 0;
                    }
                    else{
                        return 0;
                    }
//                    String a = o1.getString("addTime");
//                    String b = o2.getString("addTime");
//
//                    try {
//                        Date datea = sdf.parse(a);
//                        Date dateb = sdf.parse(b);
//                        if(datea.compareTo(dateb)==1) {
//                            return -1;
//                        }
//                         else if(datea.compareTo(dateb)==0)
//                            {
//                                return 0;
//                            }
//                         else {
//                             return 0;
//                         }
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                        return 2;
//                    }

                }
            });
            JSONArray jsonArray1 = JSONArray.parseArray(list.toString());
            return jsonArray1;
        }
        public  int getNum(int  resId){
            int num=1;
            String id  = Integer.toString(resId);
            if(redisUtil.hasKey("num")) {
                String string = (String) redisUtil.get("num");
                JSONObject jsonObject = JSONObject.parseObject(string);
                //判断缓存中有没有该食堂的编号
                if(jsonObject.containsKey(id))
                {
                    num=(Integer) jsonObject.get(id);
                    num++;
                    jsonObject.put(id,num);
                }
                else {
                    jsonObject.put(id,1);
                }
                redisUtil.set("num",JSONObject.toJSONString(jsonObject),TimerUtil.getSecondsNextEarlyMorning());
                return num;
            }
            else{
                //没有num表示还没有一个订单
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(id,num);
                redisUtil.set("num",JSONObject.toJSONString(jsonObject),TimerUtil.getSecondsNextEarlyMorning());
                return num;
            }

        }
        @RequestMapping("admin/findOrderByPage")
        public Status testsxxx(int pageNum,int pageSize,int resId)
        {
//            PageHelper.startPage(pageNum,pageSize);
            int index=pageNum-1;
            int start = index*pageSize;
            int end = start+pageSize;

            List<OrderVo> orders = orderService.findOrderAndOrderDetail(resId);
            if(orders==null)
                return new Status(0,"获取订单列表失败");
            int total = orders.size();
            if(end>total)
                end=total;
            List<OrderVo> list = orders.subList(start,end);
            Map<String,Object> map = new HashMap<>();
            map.put("total",total);
            map.put("list",list);
            return new Status(1,map);
        }
        @RequestMapping("/admin/removeOrder")
        public Status removeOrder(int id)
        {
            int flag = orderService.removeOrder(id);
            if(flag==1)
                return new Status(1,"删除订单成功");
            return new Status(0,"删除订单失败");
        }
        @RequestMapping("/admin/findOrderByDate")
        public Status findOrderByDate(int resId,String date,int foodType)
        {
            List<OrderVo> list = orderService.findOrderByDate(resId,date,foodType);
            if(list!=null)
                return new Status(1,list);
            return new Status(0,"查找失败");
        }
        @RequestMapping("/admin/getOneDayMoney")
        public Status getOneDayMoney(String time,int resId)
        {
            BigDecimal bigDecimal = orderService.getOneDayMoney(time,resId);
            if(bigDecimal!=null)
            {
                int money =bigDecimal.intValue();
                return new Status(1,money);
            }
            return new Status(0,0);
        }
        @RequestMapping("/admin/getAllMoney")
        public Status getAllMoney(int resId)
        {
            BigDecimal bigDecimal = orderService.getAllMoney(resId);
            if(bigDecimal!=null)
            {
                int money = bigDecimal.intValue();
                return new Status(1,money);
            }
            return new Status(0,0);
        }
        @RequestMapping("/admin/countAddress")
        public Status countAddress(int resId)
        {
            List<Map<String , Object>> list = orderService.countAddress(resId);
            System.out.println(list);
            if(list!=null)
            {
                return new Status(1,list);
            }
            return new Status(0,"null");
        }
        @RequestMapping("/admin/countSales")
        public Status countSales(int resId)
        {
            List<Map<String , Object>> list = orderService.countSales(resId);
            if(list!=null)
            {
                return new Status(1,list);
            }
            return new Status(0,"null");
        }


}
