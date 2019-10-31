package com.huse.mapper;

import com.huse.pojo.Cadre;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CadreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cadre record);

    int insertSelective(Cadre record);

    Cadre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cadre record);

    int updateByPrimaryKey(Cadre record);

    List<Cadre> getCadreList(int start, int number);

    int count();

    List<Cadre> fuzzyQuery(@Param("info") String info);

    Cadre selectByName(String name);

    Cadre selectByNameID(String name, String id);

    List<Cadre> selectByAlias(String alias);

    Cadre selectByIdFoundName(Integer id);

    int updateHuseCadreJob(String job,String cadre_name);//updateByNameJob的附带必要操作
}