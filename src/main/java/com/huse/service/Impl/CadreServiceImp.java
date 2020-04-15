package com.huse.service.Impl;

import com.huse.mapper.CadreMapper;
import com.huse.pojo.Cadre;
import com.huse.service.CadreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "cadrelist")
public class CadreServiceImp implements CadreService {

    @Autowired
    private CadreMapper cadreMapper;

    @Override
    @CacheEvict(allEntries = true)
    public int deleteByPrimaryKey(Integer id) {
        return cadreMapper.deleteByPrimaryKey(id);
    }

    @Override
    @CacheEvict(allEntries = true) //添加干部，清除所有的缓存集
    public int insert(Cadre record) {
        return cadreMapper.insert(record);
    }

    @Override
    public int insertSelective(Cadre record) {
        return cadreMapper.insertSelective(record);
    }

    @Override
    public Cadre selectByPrimaryKey(Integer id) {
        return cadreMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Cadre record) {
        return cadreMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int updateByPrimaryKey(Cadre record) {
        return cadreMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Cadre> getCadreList(int start, int number) {
        return cadreMapper.getCadreList(start,number);
    }

    @Override
    public int count() {
        return cadreMapper.count();
    }

    @Override
    public List<Cadre> fuzzyQuery(String info){
        return cadreMapper.fuzzyQuery(info);
    }

    @Override
    public Cadre selectByName(String name) {
        return cadreMapper.selectByName(name);
    }

    @Override
    public Cadre selectByNamePassword(String name, String id) {
        return cadreMapper.selectByNameID(name,id);
    }

    @Override
    public String selectRoleByName(String name) {
        return cadreMapper.selectRoleByName(name);
    }

    @Override
    @Cacheable(key = "#alias + 'All'")/*对于方法selectByAlias的返回值缓存*/
    public List<Cadre> selectByAlias(String alias) {
        return cadreMapper.selectByAlias(alias);
    }
    @Override
    @Cacheable(key = "#alias + 'FS'")/*对于方法selectFirAndSecCategory的返回值缓存*/
    public List<Cadre> selectFirAndSecCategory(String alias) {
        return cadreMapper.selectFirAndSecCategory(alias);
    }

    @Override
    @Cacheable(key = "#alias + #role")/*对于方法selectThirdCategory的返回值缓存*/
    public List<Cadre> selectThirdCategory(String alias, String role) {
        return cadreMapper.selectThirdCategory(alias,role);
    }
    @Override
    public int updatePwd(String cadreName, String newPassword) {
        return cadreMapper.updatePwd(cadreName,newPassword);
    }

}
