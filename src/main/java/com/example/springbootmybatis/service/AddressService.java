package com.example.springbootmybatis.service;

import com.example.springbootmybatis.entity.Address;
import com.example.springbootmybatis.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressMapper addressMapper;
    public  Address[] findByResId(int resId){
        return addressMapper.findByResId(resId);
    }
   public List<Address> getAddressList(int resId){
        return addressMapper.getAddressList(resId);
    }
   public int addAddress(Address address)
   {
       return addressMapper.addAddress(address);
   }
    public Address findAddressById(int id){
        return  addressMapper.findAddressById(id);
    }
    public int editAdress(Address address){
        return addressMapper.editAddress(address);
    }
    public int removeAdress(int id){
        return addressMapper.removeAddress(id);
    }
}
