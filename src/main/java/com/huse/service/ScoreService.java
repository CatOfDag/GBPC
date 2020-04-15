package com.huse.service;

import com.huse.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreService {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    List<Score> getScoreList(int start, int limit);

    int count();

    List<Score> fuzzyQuery(@Param("info") String info,String alias);

    List<String> fuzzyQueryRS(@Param("info") String info,String alias);

    //查找所有的干部姓名
    List<String> selectAllCadreName(String alias);

    //按照干部的姓名查找得分情况
    List<Score> selectByCadreName(String cadreName);

    List<Score> selectLeaderVoteNumByName(String cadreName);

    List<Score> selectOtherVoteNumByName(String cadreName);

    List<String> selectByRole(String role);

    List<Score> selectByPin(String pin);
}
