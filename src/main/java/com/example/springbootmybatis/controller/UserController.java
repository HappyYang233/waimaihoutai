package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.UtilEntity.RedisUtil;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.util.WxUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 小程序用户登陆
     * @param jsonObject
     * @return
     *
     *
     */
    @RequestMapping("/wx/user/login")
    public Status userLogin(@RequestBody JSONObject jsonObject)
    {
        //取得传入参数
        String code=jsonObject.getString("code");
        String encrypteData =jsonObject.getString("encryptedData");
        String iv=jsonObject.getString("iv");
        String rawData =jsonObject.getString("rawData");
        String signature=jsonObject.getString("signature");
        //获取openid session_key
        Map<String,Object> hashMap = WxUtil.getWxUserOpenid(code);
        String session_key = (String)hashMap.get("session_key");
        String openId= (String) hashMap.get("openid");
        if(hashMap.size()==0)
            return new Status(0,"登陆失败");
        //解密敏感信息 并以uuid为key 包含openid 和 session_key 的json对象为值 传入redis缓存中
        JSONObject jsonObject1 = WxUtil.getUserInfo(encrypteData,session_key,iv);
        //随机生成uuid
        JSONObject jsonObject2 = new JSONObject(hashMap);
        String uuid = UUID.randomUUID().toString();
        redisUtil.set(uuid,jsonObject2,1000);//设置seesionid 过期时间 单位秒
        //判断用户是注册还是登录，注册需存信息到数据库，然后返回用户信息
        User findUser = userService.findByOpenId(openId);
        if(findUser==null)
        {
            System.out.println("新用户 需要注册");
            User newUser = new User();
            newUser.setOpenId(openId);
//            int userId = userService.savaUserByOpenId(newUser);
//            findUser=userService.findById(userId);
            userService.savaUserByOpenId(newUser);
            findUser=userService.findById(newUser.getId());
        }
        else {
            System.out.println("老用户");
        }
        Map<String ,Object> returnInfo = new HashMap<>();
        returnInfo.put("userId",findUser.getId());
        returnInfo.put("wallet",findUser.getWallet());
        returnInfo.put("uuid",uuid);
        JSONObject returnJson = new JSONObject(returnInfo);
        return new Status(1,returnJson);
    }


    @RequestMapping("/wx/user/addmoney")
    public Status addmoney(@RequestBody JSONObject jsonObject)
    {
        BigDecimal money = new BigDecimal(jsonObject.getString("money"));
        String sessionId = jsonObject.getString("sessionId");
        JSONObject jsonObject1 = (JSONObject) redisUtil.get(sessionId);
        String openId = jsonObject1.getString("openid");
        User user  = userService.findByOpenId(openId);
        BigDecimal newprice = user.getWallet().add(money);
        user.setWallet(newprice);
        userService.updateWallet(user);
        return new Status(1,"成功");
    }
    @RequestMapping("/admin/findAllUser")
    public  Status findAllUser()
    {
        List<User> all = userService.findAllUser();
        return new Status(1,all);
    }
    @RequestMapping("/admin/findUserByPage")
    public Status findUserByPage(int pageNum,int pageSize)
    {
        System.out.println(pageNum+"+sdasdasd"+pageSize);
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
        return  new Status(1,pageInfo);
    }
    @RequestMapping("/admin/addUser")
    public  Status addUser(@RequestBody JSONObject jsonObject)
    {
        System.out.println("adduser"+jsonObject);
        User user  = new User();
        user.setUserName(jsonObject.getString("userName"));
        user.setPassword(jsonObject.getString("password"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobile(jsonObject.getString("mobile"));
        user.setSex(jsonObject.getString("sexx"));
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        user.setAddTime(time);
        int flag = userService.savaAddUser(user);
        if(flag==1)
        {
            return new Status(1,"添加成功");
        }
        else
        {
            return new Status(0,"添加失败");
        }
    }
    @RequestMapping("/admin/findUserById")
    public Status findUserById(int id )
    {
        User user = userService.findById(id);
        if(user==null)
        {
            return new Status(0,"无此用户");
        }
        return new Status(1,user);
    }
    @RequestMapping("/admin/editUser")
    public  Status edidUser(@RequestBody JSONObject jsonObject)
    {

        String obj = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(obj,User.class);
        int flag = userService.editUser(user);
        if(flag==1){
            return new Status(1,"修改成功");
        }
        else
        {
            return new Status(0,"修改失败");
        }

    }
    @RequestMapping("/admin/removeUser")
    public  Status removeUser(int id )
    {
        int flag = userService.removeUser(id);
        if(flag==1)
            return new Status(1,"删除成功");
        return new Status(0,"删除失败");
    }
}
