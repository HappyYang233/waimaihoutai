package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Food;
import com.example.springbootmybatis.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    FoodMapper mapper;
    public Food[] findAllByFoodType(int type,int resId){
        return mapper.findAllByResIdAndType(type,resId);
    }
    public int addTotalSales(int num,int id){
        return mapper.addTotalSales(num,id);
    }
    public List<Food> findAllFoodByResId(int resId){
        return mapper.findAllFoodByResId(resId);
    }
    public int addFood(Food food){
        return mapper.addFood(food);
    }
    public Food findFoodById(int id ){
        return mapper.findFoodById(id);
    }
    public int editFood(Food food){
        return mapper.editFood(food);
    }
    public int removeFood(int id){
        return  mapper.removeFood(id);
    }
}
