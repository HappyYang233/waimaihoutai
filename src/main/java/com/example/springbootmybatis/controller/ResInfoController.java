package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.ResInfo;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.ResInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResInfoController {
    @Autowired
    ResInfoService resInfoService;

    @RequestMapping("/res/showAll")
    public Status showAll()
    {
        ResInfo[] resInfos= resInfoService.showALlRes();
        if(resInfos==null || resInfos.length==0)
            return new Status(0,"失败请重试");
        String json = JSON.toJSONString(resInfos);
        JSONArray jsonArray = JSONArray.parseArray(json);
        return new Status(1,jsonArray);
    }

}
