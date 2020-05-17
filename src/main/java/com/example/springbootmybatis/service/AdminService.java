package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Admin;
import com.example.springbootmybatis.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public Admin login(String username){
        return adminMapper.login(username);
    }
}
