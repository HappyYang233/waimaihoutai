package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    FoodMapper mapper;
    public Food[] findAllByFoodType(int type,int resId){
        return mapper.findAllByResIdAndType(type,resId);
    }
}
