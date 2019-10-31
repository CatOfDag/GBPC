package com.huse.mapper;

import com.huse.pojo.CadreDatail;

import java.util.List;

public interface CadreDatailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CadreDatail record);


    int insertname(CadreDatail record);

    int insertSelective(CadreDatail record);

    CadreDatail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CadreDatail record);

    int updateByPrimaryKey(CadreDatail record);

    List<CadreDatail> selectByAlias(String alias);

    List<CadreDatail> fuzzyQuery(String info);

    CadreDatail selectByIdFoundName(Integer id);

    int updateByNameJob(CadreDatail record);//改为只引用Name,用户文件导入详细信息所用的
}