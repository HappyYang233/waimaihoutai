package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Address;
import com.example.springbootmybatis.entity.Admin;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequestMapping("/admin/getAddressList")
    public Status getAddressList(int resId)
    {
      List<Address> list = addressService.getAddressList(resId);
      if(list!=null)
          return new Status(1,list);
      return new Status(0,"获取地址列表失败");
    }
    @RequestMapping("/admin/addAddress")
    public Status addAddress(@RequestBody JSONObject jsonObject)
    {
        Address address = new Address();
        address.setResId(jsonObject.getInteger("resId"));
        address.setAddressInfo(jsonObject.getJSONObject("addForm").getString("addressInfo"));
        int flag = addressService.addAddress(address);
        if(flag==1)
            return new Status(1,"添加地址成功");
        return new Status(0,"添加地址失败");
    }
    @RequestMapping("/admin/findAddressById")
    public Status findAddressById(int id)
    {
        Address address = addressService.findAddressById(id);
        if(address!=null)
            return new Status(1,address);
        return new Status(0,"不存在此地址");
    }

    @RequestMapping("/admin/editAddress")
    public Status editAddress(@RequestBody JSONObject jsonObject)
    {
        String obj = JSONObject.toJSONString(jsonObject);
        Address address = JSON.parseObject(obj,Address.class);
        int flag = addressService.editAdress(address);
        if(flag==1)
            return new Status(1,"修改地址成功");
        return new Status(0,"修改地址失败");
    }
    @RequestMapping("/admin/removeAddress")
    public  Status removeAddress(int id )
    {
        int flag = addressService.removeAdress(id);
        if(flag==1)
            return new Status(1,"删除成功");
        return new Status(0,"删除失败");
    }
}
