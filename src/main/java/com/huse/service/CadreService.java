package com.huse.service;

import com.huse.pojo.Cadre;

import java.util.List;

public interface CadreService {
    int deleteByPrimaryKey(Integer id);

    int insert(Cadre record);

    int insertSelective(Cadre record);

    Cadre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cadre record);

    int updateByPrimaryKey(Cadre record);

    List<Cadre> getCadreList(int start, int number);

    int count();

    List<Cadre> fuzzyQuery(String info);

    Cadre selectByName(String name);

    Cadre selectByNamePassword(String name, String id);

    String selectRoleByName(String name);
    //按投票别名查找
    List<Cadre> selectByAlias(String alias);

    int updatePwd(String cadreName,String newPassword);//修改密码

    List<Cadre> selectFirAndSecCategory(String alias);//获取第一类和第二类干部角色

    List<Cadre> selectThirdCategory(String alias,String role);//获取第三类干部角色
}
