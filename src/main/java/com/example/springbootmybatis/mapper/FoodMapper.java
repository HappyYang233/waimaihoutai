package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodMapper {
    Food[] findAllByResIdAndType(int type,int resId);
}
