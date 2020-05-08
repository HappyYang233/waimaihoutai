package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.UtilEntity.RedisUtil;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.mapper.UserMapper;
import com.example.springbootmybatis.service.UserService;
import com.example.springbootmybatis.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
     */
    @RequestMapping("/user/login")
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
        int money = jsonObject.getInteger("money");
        String sessionId = jsonObject.getString("sessionId");
        JSONObject jsonObject1 = (JSONObject) redisUtil.get(sessionId);
        String openId = jsonObject.getString("openid");
        User user  = userService.findByOpenId(openId);
        BigDecimal newprice = user.getWallet().add(new BigDecimal(money));
        user.setWallent(newprice);
        userService.updateWallet(user);
        return new Status(1,"成功");
    }
}
