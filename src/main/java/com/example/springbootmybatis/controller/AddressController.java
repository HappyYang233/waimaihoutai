package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Address;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @RequestMapping("/wx/order/getAddress")
    public Status getAddress(@RequestBody JSONObject jsonObject){
        int resId = (Integer) jsonObject.get("resId");
        Address[] addresses = addressService.findByResId(resId);
        String json = JSONObject.toJSONString(addresses);
        JSONArray jsonArray = JSONArray.parseArray(json);
        return new Status(1,jsonArray);
    }
}
