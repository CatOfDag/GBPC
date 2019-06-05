package com.huse.mapper;

import com.huse.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    List<Score> getScoreList(@Param("start") int start, int limit);

    int count();

    List<Score> fuzzyQuery(@Param("info") String info,String alias);

    List<String> selectAllCadreName(@Param("alias") String alias);

    List<Score> selectByCadreName(String cadreName);
}