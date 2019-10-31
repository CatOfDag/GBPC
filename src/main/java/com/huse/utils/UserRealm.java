package com.huse.utils;

import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.service.AdminService;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import javax.xml.crypto.Data;
import java.util.Date;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CadreService cadreService;
    @Autowired
    private ParticipantService participantService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            //如果当前用户是user,则赋予su和user两种权限
            Admin user = (Admin) principals.getPrimaryPrincipal();
            authorizationInfo.addRole("su");
            authorizationInfo.addRole("user");
        }catch (ClassCastException e){
            try {
                //如果当前用户是cadre则赋予cadre权限
                Cadre cadre = (Cadre) principals.getPrimaryPrincipal();
                authorizationInfo.addRole("cadre");
            }catch (ClassCastException f){
                Participant participant = (Participant) principals.getPrimaryPrincipal();
                authorizationInfo.addRole("par");
            }

        }

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Admin admin;
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        admin = adminService.selectByName(token.getUsername());
        Cadre cadre;
        cadre = cadreService.selectByNameID(token.getUsername(),String.valueOf(token.getPassword()));
        Participant participant;
        participant = participantService.selectByPIN(token.getUsername());
//        System.out.println("认证获得用户名"+token.getUsername());


        //如果admin信息为空
        if (admin == null) {
            //如果cadre为空
            if (cadre==null){
                //返回一个null,验证失败
                if (participant==null){
                    return null;
                }else{
                    Date nowTime = new Date();
                    //如果超过截止日期或者账户被锁定了
                    if (participant.getState()&& !participant.getEndtime().before(nowTime)){
                        return new SimpleAuthenticationInfo(participant,"",getName());
                    }else {
                        throw new LockedAccountException("帐号已过期");
                    }
                }
            }else {
                return new SimpleAuthenticationInfo(cadre, cadre.getPassword().toCharArray(), getName());
            }
        }
        if (!admin.getState()){
            //如果数据表中是停用状态,则抛出异常.
            throw new LockedAccountException("帐号被停用,联系超级管理员开启.");
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword().toCharArray(), getName());
    }

}
