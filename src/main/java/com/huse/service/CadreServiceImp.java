package com.huse.service;

import com.huse.mapper.CadreMapper;
import com.huse.pojo.Cadre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadreServiceImp implements CadreService{

    @Autowired
    private CadreMapper cadreMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cadreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Cadre record) {
        return cadreMapper.insert(record);
    }

    @Override
    public int insertSelective(Cadre record) {
        return cadreMapper.insertSelective(record);
    }

    @Override
    public Cadre selectByPrimaryKey(Integer id) {
        return cadreMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Cadre record) {
        return cadreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Cadre record) {
        return cadreMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Cadre record) {
        return cadreMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Cadre> getCadreList(int start, int number) {
        return cadreMapper.getCadreList(start,number);
    }

    @Override
    public int count() {
        return cadreMapper.count();
    }

    @Override
    public List<Cadre> fuzzyQuery(String info){
        return cadreMapper.fuzzyQuery(info);
    }
}
