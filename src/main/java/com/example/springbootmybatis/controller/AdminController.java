package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Admin;
import com.example.springbootmybatis.entity.UtilEntity.RedisUtil;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RedisUtil redisUtil;
    @RequestMapping("/admin/login")
    public Status login(@RequestBody JSONObject jsonObject)
    {
       String username = jsonObject.getString("username");
       String password = jsonObject.getString("password");
        Admin admin = adminService.login(username);
        if(admin==null)
        {
            return new Status(31,"用户名错误");
        }
        if(!password.equals(admin.getPassword()))
        {
            return new Status(32,"密码错误");
        }
        if(redisUtil.hasKey(username))
        {
            String token =(String) redisUtil.get(username);
            redisUtil.del(token);
        }
        JSONObject jsonObject1 =null;
        if(admin.getType()==1)
            jsonObject1= JSONObject.parseObject(Static.Admin);
        else {
            jsonObject1=JSONObject.parseObject(Static.SuperAdmin);
        }
        System.out.println(jsonObject1);
        String token = UUID.randomUUID().toString();
        String json = JSONObject.toJSONString(admin);
        redisUtil.set(token,json,5000);
        redisUtil.set(admin.getUserName(),token,5000);
        jsonObject1.put("token",token);
        jsonObject1.put("username",admin.getUserName());
        return new Status(33,jsonObject1);

    }
}
