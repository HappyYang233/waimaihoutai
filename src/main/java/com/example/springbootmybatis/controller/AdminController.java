package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Admin;
import com.example.springbootmybatis.entity.ResInfo;
import com.example.springbootmybatis.entity.UtilEntity.RedisUtil;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.AdminService;
import com.example.springbootmybatis.service.ResInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ResInfoService resInfoService;
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
        jsonObject1.put("resId",admin.getResId());
        return new Status(33,jsonObject1);

    }
    @RequestMapping("/admin/findAllAdmin")
    public  Status findAll()
    {
        List<Admin> list =adminService.findAll();
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(list));
        for(int i=0;i<jsonArray.size();i++)
        {
           int resId =jsonArray.getJSONObject(i).getInteger("resId");
           if(resId==0)
           {
               jsonArray.getJSONObject(i).put("resName","");
           }
           else
           {
               ResInfo resInfo = resInfoService.findResById(resId);
               jsonArray.getJSONObject(i).put("resName",resInfo.getResName());
           }
        }
        if(list==null)
            return new Status(0,"查找失败");
        return new Status(1,jsonArray);
    }
    @RequestMapping("/admin/addAdmin")
    public  Status addAdmin(@RequestBody JSONObject jsonObject)
    {
        Admin admin=  new Admin();
        String resName = jsonObject.getString("resName");
        ResInfo resInfo = resInfoService.findResByName(resName);
        int resId = resInfo.getId();
        admin.setResId(resId);
//        admin.setResName(resName);
        admin.setUserName(jsonObject.getString("userName"));
        admin.setPassword(jsonObject.getString("password"));
        admin.setTrueName(jsonObject.getString("trueName"));
        admin.setSex(jsonObject.getString("sex"));
        admin.setMobile(jsonObject.getString("mobile"));
        admin.setEmail(jsonObject.getString("email"));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        admin.setAddTime(time);
        admin.setType(0);
        int flag = adminService.addAdmin(admin);
        if(flag==1)
        {
            return new Status(1,"添加用户成功");
        }
        return new Status(0,"添加用户失败");
    }
    @RequestMapping("/admin/findAdminById")
    public Status findAdminById(int id )
    {
        Admin admin = adminService.findAdminById(id);
        if(admin!=null)
            return new Status(1,admin);
        return new Status(0,"无该用户");

    }
    @RequestMapping("/admin/editAdmin")
    public  Status editAdmin(@RequestBody JSONObject jsonObject)
    {
        String obj = JSONObject.toJSONString(jsonObject);
        Admin admin = JSON.parseObject(obj,Admin.class);
        int flag = adminService.editAdmin(admin);
        if(flag==1)
            return new Status(1,"修改成功");
        return new Status(0,"修改失败");
    }
    @RequestMapping("/admin/removeAdmin")
    public  Status removeAdmin(int id)
    {
        int flag = adminService.removeAdmin(id);
        if(flag==1)
            return new Status(1,"删除成功");
        return new Status(0,"删除失败");
    }

}
