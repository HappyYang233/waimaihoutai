package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodMapper {
    //微信端查找一个食堂午餐或晚餐的所有菜品，这里要展示上架的商品
    Food[] findAllByResIdAndType(int type,int resId);
    int addTotalSales(int num,int id);
    List<Food> findAllFoodByResId(int resId);
    int addFood(Food food);
    Food findFoodById(int id );
    int editFood(Food food);
    int removeFood(int id);
    int changeFoodStatus(int id,int status);
}
