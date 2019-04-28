package com.huse.service;

import com.huse.pojo.Vote;

import java.util.List;

public interface VoteService {
    int deleteByPrimaryKey(Integer id);

    int insert(Vote record);

    int insertSelective(Vote record);

    Vote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vote record);

    int updateByPrimaryKey(Vote record);

    List<Vote> getVoteList(int start,int limit);

    int count();
}
