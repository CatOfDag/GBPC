package com.huse.service.Impl;

import com.huse.mapper.ScoreMapper;
import com.huse.pojo.Score;
import com.huse.service.ScoreService;
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
    public List<String> fuzzyQueryRS(String info, String alias) {
        return scoreMapper.fuzzyQueryRS(info,alias);
    }

    @Override
    public List<String> selectAllCadreName(String alias) {
        return scoreMapper.selectAllCadreName(alias);
    }

    @Override
    public List<Score> selectByCadreName(String cadreName) {
        return scoreMapper.selectByCadreName(cadreName);
    }

    @Override
    public List<Score> selectLeaderVoteNumByName(String cadreName) {
        return scoreMapper.selectLeaderVoteNumByName(cadreName);
    }

    @Override
    public List<Score> selectOtherVoteNumByName(String cadreName) {
        return scoreMapper.selectOtherVoteNumByName(cadreName);
    }

    @Override
    public List<String> selectByRole(String role) {
        return scoreMapper.selectByRole(role);
    }

    @Override
    public List<Score> selectByPin(String pin) {
        return scoreMapper.selectByPin(pin);
    }


}
