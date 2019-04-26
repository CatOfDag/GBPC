package com.huse.service;

import com.huse.pojo.Participant;

import java.util.List;

public interface ParticipantService {
    int deleteByPrimaryKey(Integer id);

    int insert(Participant record);

    int insertSelective(Participant record);

    Participant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Participant record);

    int updateByPrimaryKey(Participant record);

    List<Participant> getParticipantLists(int startRows,int limit);

    int count();
}
