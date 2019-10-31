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

    Cadre selectByNameID(String name, String id);//手贱，应该是selectByNamePassword()
    //按投票别名查找
    List<Cadre> selectByAlias(String alias);

    int updateHuseCadreJob(String newpost,String cadre_name);//updateByNameJob的附带必要操作
}
