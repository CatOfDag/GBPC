package com.huse.service.Impl;

import com.huse.mapper.VoteMapper;
import com.huse.pojo.Vote;
import com.huse.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoteServiceImp implements VoteService {

    @Autowired
    private VoteMapper voteMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return voteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vote record) {
        return voteMapper.insert(record);
    }

    @Override
    public int insertSelective(Vote record) {
        return voteMapper.insertSelective(record);
    }

    @Override
    public Vote selectByPrimaryKey(Integer id) {
        return voteMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Vote record) {
        return voteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Vote record) {
        return voteMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Vote> getVoteList(int start, int limit) {
        return voteMapper.getVoteList(start,limit);
    }


    @Override
    public int count() {
        return voteMapper.count();
    }

    @Override
    public String countOperationVote() {
        return voteMapper.countOperationVote();
    }

    @Override
    public List<Vote> selectAllVote() {
        return voteMapper.selectAllVote();
    }

    @Override
    public Vote selectByAlisa(String alisa) {
        return voteMapper.selectByAlisa(alisa);
    }
}
