package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {

    Admin login(String username);
    List<Admin> findAll();
    int addAdmin(Admin admin);
    Admin  findAdminById(int id);
    int  editAdmin(Admin admin);
    int removeAdmin(int id);
}
