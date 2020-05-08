package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {

    Address[] findByResId(int resId);
}
