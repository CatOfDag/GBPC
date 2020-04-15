package com.huse.service.Impl;

import com.huse.mapper.AdminMapper;
import com.huse.pojo.Admin;
import com.huse.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Admin> getAdminList(int start, int number) {
        return adminMapper.getAdminList(start,number);
    }

    @Override
    public int count() {
        return adminMapper.count();
    }

    @Override
    public List<Admin> fuzzyQuery(String info) {
        return adminMapper.fuzzyQuery(info);
    }

    @Override
    public Admin selectByName(String admin_name) {
        return adminMapper.selectByName(admin_name);
    }

}
