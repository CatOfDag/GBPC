package com.huse.service.Impl;

import com.huse.mapper.ScoreResultMapper;
import com.huse.pojo.ScoreResult;
import com.huse.service.ScoreResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreResultServiceImp implements ScoreResultService {

    @Autowired
    ScoreResultMapper scoreResultMapper;

    @Override
    public ScoreResult selectByPrimaryKey(int id) {
        return scoreResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public double selectFVScoreByname(String name) {
        return scoreResultMapper.selectFVScoreByname(name);
    }

    @Override
    public double selectLVScoreByname(String name) {
        return scoreResultMapper.selectLVScoreByname(name);
    }

    @Override
    public double selectOVScoreByname(String name) {
        return scoreResultMapper.selectOVScoreByname(name);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return scoreResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScoreResult scoreResult) {
        return scoreResultMapper.insert(scoreResult);
    }

    @Override
    public int insertSelective(ScoreResult scoreResult) {
        return scoreResultMapper.insertSelective(scoreResult);
    }

    @Override
    public int updateByPrimaryKeySelective(ScoreResult scoreResult) {
        return scoreResultMapper.updateByPrimaryKeySelective(scoreResult);
    }

    @Override
    public int updateByPrimaryKey(ScoreResult scoreResult) {
        return scoreResultMapper.updateByPrimaryKey(scoreResult);
    }

    @Override
    public List<ScoreResult> getScoreList(int start, int end) {
        return scoreResultMapper.getScoreList( start,  end);
    }

    @Override
    public List<ScoreResult> fuzzyQuery(String name, String alias) {
        return scoreResultMapper.fuzzyQuery( name,  alias);
    }

    @Override
    public int count() {
        return scoreResultMapper.count();
    }

    @Override
    public List<ScoreResult> selectByRole(String role) {
        return scoreResultMapper.selectByRole(role);
    }

    @Override
    public int updateFVScore(String name, double s) {
        return scoreResultMapper.updateFVScore(name,s);
    }

    @Override
    public int updateLVScore(String name, double s) {
        return scoreResultMapper.updateLVScore(name,s);
    }

    @Override
    public int updateOVScore(String name, double s) {
        return scoreResultMapper.updateOVScore(name,s);
    }
}
