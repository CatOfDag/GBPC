package com.huse.mapper;

import com.huse.pojo.ScoreResult;

import java.util.List;

public interface ScoreResultMapper {

    ScoreResult selectByPrimaryKey(int id);

    double selectFVScoreByname(String name);

    double selectLVScoreByname(String name);

    double selectOVScoreByname(String name);

    int deleteByPrimaryKey(int id);

    int insert(ScoreResult scoreResult);

    int insertSelective(ScoreResult scoreResult);

    int updateByPrimaryKeySelective(ScoreResult scoreResult);

    int updateByPrimaryKey(ScoreResult scoreResult);

    List<ScoreResult> getScoreList(int start,int limit);

    List<ScoreResult> fuzzyQuery(String name,String alias);

    int count();

    List<ScoreResult> selectByRole(String role);

    int updateFVScore(String name, double finalyvotescore);

    int updateLVScore(String name, double leadervotescore);

    int updateOVScore(String name, double othervotescore);
}
