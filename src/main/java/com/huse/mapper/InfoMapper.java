package com.huse.mapper;

import com.huse.pojo.Info;

public interface InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);

    Info selectByCadreName(String cadreName);

    //在表中插入用户名
    int insertCadreName(String cadreName);

    int updateByCadreName(Info info);
}