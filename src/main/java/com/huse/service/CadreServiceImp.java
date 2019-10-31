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

    @Override
    public Cadre selectByName(String name) {
        return cadreMapper.selectByName(name);
    }

    @Override
    public Cadre selectByNameID(String name, String id) {
        return cadreMapper.selectByNameID(name,id);
    }

    @Override
    public List<Cadre> selectByAlias(String alias) {
        return cadreMapper.selectByAlias(alias);
    }


    @Override
    public int updateHuseCadreJob(String newpost,String cadre_name) {
        return cadreMapper.updateHuseCadreJob(newpost,cadre_name);
    }
}
