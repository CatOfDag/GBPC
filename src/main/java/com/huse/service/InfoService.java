package com.huse.service;

import com.huse.pojo.Info;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface InfoService {
    int deleteByPrimaryKey(Integer id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKeyWithBLOBs(Info record);

    int updateByPrimaryKey(Info record);

    Info selectByCadreID(Integer id);

    Info selectByCadreName(String cadreName);

    @Deprecated
    int insertCadreName(String cadreName);

    int updateByCadreName(Info info);
}
