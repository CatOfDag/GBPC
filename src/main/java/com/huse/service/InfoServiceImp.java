package com.huse.service;

import com.huse.mapper.InfoMapper;
import com.huse.pojo.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImp implements InfoService {
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return infoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Info record) {
        return infoMapper.insert(record);
    }

    @Override
    public int insertSelective(Info record) {
        return infoMapper.insertSelective(record);
    }

    @Override
    public Info selectByPrimaryKey(Integer id) {
        return infoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Info record) {
        return infoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Info record) {
        return infoMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Info record) {
        return infoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Info selectByCadreName(String cadreName) {
        return infoMapper.selectByCadreName(cadreName);
    }

    @Override
    public int insertCadreName(String cadreName) {
        return infoMapper.insertCadreName(cadreName);
    }

    @Override
    public int updateByCadreName(Info info) {
        return infoMapper.updateByCadreName(info);
    }
}
