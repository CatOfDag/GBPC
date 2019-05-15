package com.huse.service;

import com.huse.pojo.Info;

public interface InfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);

    Info selectByCadreName(String cadreName);

    int insertCadreName(String cadreName);

    int updateByCadreName(Info info);
}
