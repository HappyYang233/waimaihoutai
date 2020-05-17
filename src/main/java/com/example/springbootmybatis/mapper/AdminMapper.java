package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    Admin login(String username);
}
