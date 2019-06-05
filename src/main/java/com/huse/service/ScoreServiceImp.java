package com.huse.service;

import com.huse.mapper.ScoreMapper;
import com.huse.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return scoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Score record) {
        return scoreMapper.insert(record);
    }

    @Override
    public int insertSelective(Score record) {
        return scoreMapper.insertSelective(record);
    }

    @Override
    public Score selectByPrimaryKey(Integer id) {
        return scoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Score record) {
        return scoreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Score record) {
        return scoreMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Score> getScoreList(int start, int limit) {
        return scoreMapper.getScoreList(start,limit);
    }

    @Override
    public int count() {
        return scoreMapper.count();
    }

    @Override
    public List<Score> fuzzyQuery(String info,String alisa) {
        return scoreMapper.fuzzyQuery(info,alisa);
    }

    @Override
    public List<String> selectAllCadreName(String alias) {
        return scoreMapper.selectAllCadreName(alias);
    }

    @Override
    public List<Score> selectByCadreName(String cadreName) {
        return scoreMapper.selectByCadreName(cadreName);
    }


}
