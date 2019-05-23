package com.huse.mapper;

import com.huse.pojo.Participant;

import java.util.List;

public interface ParticipantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Participant record);

    int insertSelective(Participant record);

    Participant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Participant record);

    int updateByPrimaryKey(Participant record);

    List<Participant> getParticipantLists(int start, int limit);

    int count();

    int forbidden(int state);

    Participant selectByPIN(String PIN);
}