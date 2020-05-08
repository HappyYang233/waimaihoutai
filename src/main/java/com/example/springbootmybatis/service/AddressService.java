package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Address;
import com.example.springbootmybatis.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;
    public  Address[] findByResId(int resId){
        return addressMapper.findByResId(resId);
    }
}
