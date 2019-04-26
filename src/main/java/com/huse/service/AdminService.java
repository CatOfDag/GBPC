package com.huse.service;

import com.huse.pojo.Admin;

import java.util.List;

public interface AdminService {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAdminList(int start ,int number);

    int count();

    List<Admin> fuzzyQuery(String info);

}
