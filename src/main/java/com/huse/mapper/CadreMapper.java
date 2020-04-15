package com.huse.mapper;

import com.huse.pojo.Cadre;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CadreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cadre record);

    int insertSelective(Cadre record);

    Cadre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cadre record);

    int updateByPrimaryKey(Cadre record);

    List<Cadre> getCadreList(int start, int number);

    int count();

    List<Cadre> fuzzyQuery(@Param("info") String info);

    Cadre selectByName(String name);

    Cadre selectByNameID(String name, String id);

    String selectRoleByName(String name);

    List<Cadre> selectByAlias(String alias);

    int updatePwd(String cadreName,String newPassword);//修改密码

    /*
     * 第一类：行政与教辅单位正职（含主持工作德副职）
     * 第二类：教学学院正职（院长、书记）
     * 第三类：教学学院副职（副院长、副书记）
     * */
    List<Cadre> selectFirAndSecCategory(String alias);//获取第一类和第二类干部角色

    List<Cadre> selectThirdCategory(String alias,String role);//获取第三类干部角色
}