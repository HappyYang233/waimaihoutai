package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.ResInfo;
import com.example.springbootmybatis.mapper.ResInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResInfoService {
    @Autowired
    ResInfoMapper mapper;
    public ResInfo[] showALlRes(){
        return mapper.showALlRes();
    }
}
