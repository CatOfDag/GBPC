package com.huse.mapper;

import com.huse.pojo.Result;

public interface ResultMapper {
    int insert(Result record);

    int insertSelective(Result record);
}