package com.huse.service;

import com.huse.pojo.Cadre;

import java.util.List;

public interface CadreService {
    int deleteByPrimaryKey(Integer id);

    int insert(Cadre record);

    int insertSelective(Cadre record);

    Cadre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cadre record);

    int updateByPrimaryKey(Cadre record);

    List<Cadre> getCadreList(int start, int number);

    int count();

    List<Cadre> fuzzyQuery(String info);

    Cadre selectByName(String name);
}
