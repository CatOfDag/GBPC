package com.huse.mapper;

import com.huse.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> getAdminList(int start, int number);

    int count();

    List<Admin> fuzzyQuery(@Param("info") String info);

    Admin selectByName(String admin_name);

}