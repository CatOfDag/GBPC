package com.huse.service;

import com.huse.mapper.CadreDatailMapper;
import com.huse.pojo.CadreDatail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadreDatailServiceImp implements CadreDatailService{

    @Autowired
    private CadreDatailMapper cadreDatailMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cadreDatailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CadreDatail record) {
        return cadreDatailMapper.insert(record);
    }

    @Override
    public int insertname(CadreDatail record) {
        return cadreDatailMapper.insertname(record);
    }

    @Override
    public int insertSelective(CadreDatail record) {
        return cadreDatailMapper.insertSelective(record);
    }

    @Override
    public CadreDatail selectByPrimaryKey(Integer id) {
        return cadreDatailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CadreDatail record) {
        return cadreDatailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CadreDatail record) {
        return cadreDatailMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CadreDatail> fuzzyQuery(String info) {
        return cadreDatailMapper.fuzzyQuery(info);
    }

    @Override
    public List<CadreDatail> selectByAlias(String alias) {
        return cadreDatailMapper.selectByAlias(alias);
    }

    @Override
    public int updateByNameJob(CadreDatail record) {
        return cadreDatailMapper.updateByNameJob(record);
    }


    @Override
    public CadreDatail selectByIdFoundName(Integer id) {
        return cadreDatailMapper.selectByIdFoundName(id);
    }
}
