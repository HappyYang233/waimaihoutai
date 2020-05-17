package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public  User findByOpenId(String openId)
    {
        return userMapper.findByOpenId(openId);
    }

    public User findById(int id){
        return userMapper.findById(id);
    }
    public int savaUserByOpenId(User user){
        return userMapper.savaUserByOpenId(user);
    }
//    public User getUser(int id){
//        return userMapper.getUser(id);
//    }
//    public int insert(User user){
//        return userMapper.insertUser(user);
//    }
    public int updateWallet(User user){
        return userMapper.updateWallet(user);
    }
    public List<User> findAllUser()
    {
        return userMapper.findAllUser();
    }
    public int savaAddUser(User user)
    {
        return userMapper.savaAddUser(user);
    }
    public  int editUser(User user){
        return userMapper.editUser(user);
    }
    public int removeUser(int id){
        return userMapper.removeUser(id);
    }
}
