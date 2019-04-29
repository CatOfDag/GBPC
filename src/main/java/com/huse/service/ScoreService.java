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

    List<Score> fuzzyQuery(@Param("info") String info);
}
