package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

//    User getUser(int id);
//    int insertUser(User user);
        //查找
        User findByOpenId(String openid);
        User findById(int id);
        //插入
        int savaUserByOpenId(User user);
        int updateWallet(User user);
}
