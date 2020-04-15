package com.huse.service.Impl;

import com.huse.mapper.ParticipantMapper;
import com.huse.pojo.Participant;
import com.huse.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImp implements ParticipantService {

    @Autowired
    private ParticipantMapper participantMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return participantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Participant record) {
        return participantMapper.insert(record);
    }

    @Override
    public int insertSelective(Participant record) {
        return participantMapper.insertSelective(record);
    }

    @Override
    public Participant selectByPrimaryKey(Integer id) {
        return participantMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Participant record) {
        return participantMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Participant record) {
        return participantMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Participant> getParticipantLists(int startRows, int limit) {
        return participantMapper.getParticipantLists(startRows,limit);
    }

    @Override
    public int count() {
        return participantMapper.count();
    }

    @Override
    public int forbidden(int state) {
        return participantMapper.forbidden(state);
    }

    @Override
    public Participant selectByPIN(String pin) {
        return participantMapper.selectByPIN(pin);
    }

    @Override
    public List<Participant> selectByRole(String role) {
        return participantMapper.selectByRole(role);
    }

    @Override
    public int updateByPIN(Participant record) {
        return participantMapper.updateByPIN(record);
    }
}