package com.huse.utils;

import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.service.AdminService;
import com.huse.service.CadreService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CadreService cadreService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            Admin user = (Admin) principals.getPrimaryPrincipal();
            authorizationInfo.addRole("su");
            authorizationInfo.addRole("user");
        }catch (ClassCastException e){
            Cadre cadre = (Cadre) principals.getPrimaryPrincipal();
            authorizationInfo.addRole("cadre");
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
        cadre = cadreService.selectByName(token.getUsername());

        if (admin == null) {
            if (cadre==null){
                return null;
            }else {
                return new SimpleAuthenticationInfo(cadre, cadre.getPassword().toCharArray(), getName());
            }
        }
        return new SimpleAuthenticationInfo(admin, admin.getPassword().toCharArray(), getName());
    }

}
