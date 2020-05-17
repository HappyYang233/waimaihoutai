package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

        User findByOpenId(String openid);
        User findById(int id);
        int savaUserByOpenId(User user);
        int updateWallet(User user);
        List<User> findAllUser();
        int savaAddUser(User user);
        int editUser(User user);
        int removeUser(int id);
}
