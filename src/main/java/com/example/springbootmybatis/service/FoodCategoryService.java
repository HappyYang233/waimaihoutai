package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.FoodCategory;
import com.example.springbootmybatis.mapper.FoodCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCategoryService {
    @Autowired
    FoodCategoryMapper foodCategoryMapper;
    public FoodCategory[] findByResId(int resId){
        return foodCategoryMapper.findByResId(resId);
    }
}
