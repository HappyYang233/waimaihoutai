package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.ResInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ResInfoMapper {

    ResInfo[]  showALlRes();
    ResInfo findResByName(String resName);
    ResInfo findResById(int id);
    int addRes(ResInfo resInfo);
    int editRes(ResInfo resInfo);
    int removeRes(int id);
    int editNotice(int resId,String notice);
}
