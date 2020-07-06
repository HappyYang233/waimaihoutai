package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.ResInfo;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.ResInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResInfoController {
    @Autowired
    ResInfoService resInfoService;

    @RequestMapping("/wx/res/showAll")
    public Status showAll()
    {
        ResInfo[] resInfos= resInfoService.showResByStatus();
        if(resInfos==null || resInfos.length==0)
            return new Status(0,"失败请重试");
        String json = JSON.toJSONString(resInfos);
        JSONArray jsonArray = JSONArray.parseArray(json);
        return new Status(1,jsonArray);
    }
    @RequestMapping("/admin/findAllRes")
    public Status findAllRes()
    {
        ResInfo[] resInfos= resInfoService.showALlRes();
        if(resInfos==null || resInfos.length==0)
            return new Status(0,"失败请重试");
        String json = JSON.toJSONString(resInfos);
        JSONArray jsonArray = JSONArray.parseArray(json);
        return new Status(1,jsonArray);
    }
    @RequestMapping("/admin/addRes")
    public Status addRes(@RequestBody JSONObject jsonObject){
        String obj = JSONObject.toJSONString(jsonObject);
        ResInfo resInfo = JSONObject.parseObject(obj,ResInfo.class);
        int flag = resInfoService.addRes(resInfo);
        if(flag==1)
        {
            return new Status(1,"添加食堂成功");
        }
        return  new Status(0,"添加食堂失败");
    }
    @RequestMapping("/admin/findResById")
    public  Status findResById(int id )
    {
        ResInfo resInfo = resInfoService.findResById(id);
        if(resInfo!=null)
            return  new Status(1,resInfo);
        return new Status(0,"不存在该食堂");

    }
    @RequestMapping("/admin/editRes")
    public Status editRes(@RequestBody JSONObject jsonObject)
    {
        String obj = JSONObject.toJSONString(jsonObject);
        ResInfo resInfo = JSONObject.parseObject(obj,ResInfo.class);
        int flag = resInfoService.editRes(resInfo);
        if(flag==1)
            return new Status(1,"修改成功");
        return new Status(0,"修改失败");
    }
    @RequestMapping("/admin/removeRes")
    public  Status removeRes(int id)
    {
        int flag = resInfoService.removeRes(id);
        if(flag==1)
            return new Status(1,"删除成功 ");
        return new Status(0,"删除失败");
    }
    @RequestMapping("/admin/editNotice")
    public Status editNotice(int resId,String notice)
    {
        int flag = resInfoService.editNotice(resId,notice);
        if(flag==1)
            return  new Status(1,"修改公告成功");
        return new Status(0,"修改公告失败");

    }
    @RequestMapping("/admin/changeResStatus")
    public Status changeResStatus(int id,int status)
    {
        int flag = resInfoService.changeResStatus(id,status);
        if(flag==1)
            return new Status(1,"修改成功");
        return new Status(0,"修改失败");
    }


}
