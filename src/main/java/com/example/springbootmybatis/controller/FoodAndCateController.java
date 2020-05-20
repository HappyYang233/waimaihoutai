package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.entity.FoodCategory;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.FoodCategoryService;
import com.example.springbootmybatis.service.FoodService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodAndCateController {
    @Autowired
    FoodService foodService;
    @Autowired
    FoodCategoryService foodCategoryService;

    @RequestMapping("/wx/food/showMenu")
    public Status showMenu(@RequestBody JSONObject params){
            int resId = (Integer) params.get("resId");
            if(Static.orderStatus==1)
                return new Status(20,"该时间段不允许下单");
            Food[] foods = foodService.findAllByFoodType(0,resId);
            FoodCategory[] foodCategorys = foodCategoryService.findByResId(resId);
            JSONObject jsonObject ;

            if(foods!=null && foodCategorys!=null){
                Map<String,Object> map = new HashMap<>();
                JSONArray returnCates = JSONArray.parseArray(JSONObject.toJSONString(foodCategorys));
                JSONArray returnFoods = JSONArray.parseArray(JSONObject.toJSONString(foods));
                map.put("cates",returnCates);
                map.put("foods",returnFoods);
                return new Status(1,map);

            }

        return new Status(0,"获取菜单失败");

    }
    @RequestMapping("/admin/getCate")
    public Status getCate(int resId)
    {
        List<FoodCategory> list = foodCategoryService.getCateByResId(resId);
        if(list!=null)
            return new Status(1,list);
        return new Status(0,"获取分类列表失败");
    }
    @RequestMapping("/admin/addCate")
    public Status addCate(@RequestBody JSONObject jsonObject)
    {
        FoodCategory foodCategory = new FoodCategory();
        int resId = jsonObject.getInteger("resId");
        JSONObject addForm = jsonObject.getJSONObject("addForm");
        foodCategory.setResId(resId);
        foodCategory.setName(addForm.getString("name"));
        foodCategory.setDesc(addForm.getString("desc"));
        foodCategory.setAddTime(addForm.getString("addTime"));
        int flag = foodCategoryService.addCate(foodCategory);
        if(flag==1)
            return new Status(1,"添加菜品分类成功");
        return   new Status(0,"添加菜品分类失败");
    }
    @RequestMapping("/admin/findCateById")
    public Status findCateById(int id)
    {
        FoodCategory foodCategory = foodCategoryService.findCateById(id);
        if(foodCategory!=null)
            return new Status(1,foodCategory);
        return new Status(0,"查找失败");
    }
    @RequestMapping("/admin/editCate")
    public  Status editCate(@RequestBody JSONObject jsonObject)
    {
        String obj = JSONObject.toJSONString(jsonObject);
        FoodCategory foodCategory = JSON.parseObject(obj,FoodCategory.class);
        int flag = foodCategoryService.editCate(foodCategory);
        if(flag==1)
            return new Status(1,"修改分类成功");
        return new Status(0,"修改分类失败");
    }
    @RequestMapping("/admin/removeCate")
    public  Status removeCate(int id)
    {
        int flag = foodCategoryService.removeCate(id);
        if(flag==1)
            return new Status(1,"删除分类成功");
        return new Status(0,"删除分类十八爱");
    }
    @RequestMapping("/admin/findFoodByPage")
    public Status findFoodByPage(@RequestBody JSONObject jsonObject)
    {
        System.out.println(jsonObject);
        int resId = Integer.parseInt(jsonObject.getString("resId"));
        JSONObject object = jsonObject.getJSONObject("form");
        int pageNum = object.getInteger("pageNum");
        int pageSize = object.getInteger("pageSize");
        PageHelper.startPage(pageNum,pageSize);
        List<Food> list = foodService.findAllFoodByResId(resId);
        PageInfo<Food> pageInfo = new PageInfo<Food>(list);
        Map<String,Object> map = new HashMap<>();
        map.put("food",pageInfo);
        map.put("cate",foodCategoryService.findByResId(resId));
        if(list!=null)
            return new Status(1,map);
        return new Status(0,"查询菜品列表失败");

    }
    @RequestMapping("/admin/addFood")
    public Status addFood(@RequestBody JSONObject jsonObject)
    {
        Food food = JSON.parseObject(JSONObject.toJSONString(jsonObject),Food.class);
        int flag = foodService.addFood(food);
        if(flag==1)
            return new Status(1,"添加菜品成功");
        else
            return new Status(0,"添加菜品失败");
    }
    @RequestMapping("/admin/findFoodById")
    public Status findFoodById(int id)
    {
        Food food = foodService.findFoodById(id);
        if(food!=null)
            return new Status(1,food);
        return new Status(0,"获取菜品失败");
    }
    @RequestMapping("/admin/editFood")
    public  Status editFood(@RequestBody JSONObject jsonObject)
    {
        Food food = JSON.parseObject(JSONObject.toJSONString(jsonObject),Food.class);
        int flag = foodService.editFood(food);
        if(flag==1)
            return  new Status(1,"编辑商品信息成功");
        return  new Status(0,"编辑商品信息失败");
    }
    @RequestMapping("/admin/removeFood")
    public Status removeFood(int id)
    {
        int flag = foodService.removeFood(id);
        if(flag==1)
            return new Status(1,"删除商品成功");
        return new Status(0,"删除商品失败");
    }
}
