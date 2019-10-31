package com.huse.service;

import com.huse.pojo.CadreDatail;

import java.util.List;

public interface CadreDatailService {

    int deleteByPrimaryKey(Integer id);

    CadreDatail selectByIdFoundName(Integer id);

    int insert(CadreDatail record);

    int insertname(CadreDatail record);

    int insertSelective(CadreDatail record);

    CadreDatail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CadreDatail record);

    int updateByPrimaryKey(CadreDatail record);

    List<CadreDatail> fuzzyQuery(String info);

    //按投票别名查找
    List<CadreDatail> selectByAlias(String alias);

    int updateByNameJob(CadreDatail record);//改为只引用Name,用户文件导入详细信息所用的
}
