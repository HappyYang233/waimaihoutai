package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.FoodCategory;
import com.example.springbootmybatis.mapper.FoodCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryService {
    @Autowired
    FoodCategoryMapper foodCategoryMapper;
    public FoodCategory[] findByResId(int resId){
        return foodCategoryMapper.findByResId(resId);
    }
    public List<FoodCategory> getCateByResId(int resId){
        return foodCategoryMapper.getCateByResId(resId);
    }
    public int addCate(FoodCategory foodCategory){
        return foodCategoryMapper.addCate(foodCategory);
    }
    public FoodCategory findCateById(int id){
        return foodCategoryMapper.findCateById(id);
    }
    public int editCate(FoodCategory foodCategory)
    {
        return foodCategoryMapper.editCate(foodCategory);
    }
    public int removeCate(int id){
        return foodCategoryMapper.removeCate(id);
    }
}
