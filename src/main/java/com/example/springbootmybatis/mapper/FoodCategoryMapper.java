package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.FoodCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryMapper {
    FoodCategory[] findByResId(int resId);
    List<FoodCategory> getCateByResId(int resId);
    int addCate(FoodCategory foodCategory);
    FoodCategory findCateById(int id);
    int editCate(FoodCategory foodCategory);
    int removeCate(int id);
}
