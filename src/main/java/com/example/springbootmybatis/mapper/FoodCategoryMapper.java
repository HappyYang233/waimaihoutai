package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.FoodCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryMapper {
    FoodCategory[] findByResId(int resId);
}
