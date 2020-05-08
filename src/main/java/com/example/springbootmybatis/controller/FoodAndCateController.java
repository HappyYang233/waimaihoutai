package com.example.springbootmybatis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.entity.FoodCategory;
import com.example.springbootmybatis.entity.UtilEntity.Static;
import com.example.springbootmybatis.entity.UtilEntity.Status;
import com.example.springbootmybatis.service.FoodCategoryService;
import com.example.springbootmybatis.service.FoodService;
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

    @RequestMapping("/food/showMenu")
    public Status showMenu(@RequestBody JSONObject params){
            int resId = (Integer) params.get("resId");
//            if(Static.orderStatus==1)
//                return new Status(20,"该时间段不允许下单");
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
}
