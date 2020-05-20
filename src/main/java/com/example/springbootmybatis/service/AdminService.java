package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Admin;
import com.example.springbootmybatis.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public Admin login(String username){
        return adminMapper.login(username);
    }
    public List<Admin> findAll()
    {
        return adminMapper.findAll();
    }
    public  int addAdmin(Admin admin){
        return adminMapper.addAdmin(admin);
    }
    public    Admin  findAdminById(int id){
        return adminMapper.findAdminById(id);
    }
    public  int   editAdmin(Admin admin){
        return adminMapper.editAdmin(admin);
    }
    public int removeAdmin(int id){
        return adminMapper.removeAdmin(id);
    }
}
