package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    Address[] findByResId(int resId);
    List<Address> getAddressList(int resId);
    int addAddress(Address address);
    Address findAddressById(int id);
    int editAddress(Address address);
    int removeAddress(int id);
}
