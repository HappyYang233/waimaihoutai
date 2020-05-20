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
    public  ResInfo findResByName(String resName){
        return mapper.findResByName(resName);
    }
    public ResInfo findResById(int id){
        return mapper.findResById(id);
    }
    public  int addRes(ResInfo resInfo){
        return mapper.addRes(resInfo);
    }
    public  int editRes(ResInfo resInfo){
        return mapper.editRes(resInfo);
    }
    public int removeRes(int id){
        return mapper.removeRes(id);
    }
    public int editNotice(int resId,String notice){
        return mapper.editNotice(resId,notice);
    }
}
