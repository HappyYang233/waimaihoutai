package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.entity.ResInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ResInfoMapper {

    ResInfo[]  showALlRes();

}
