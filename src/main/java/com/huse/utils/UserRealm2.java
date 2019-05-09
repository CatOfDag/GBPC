package com.huse.utils;
import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.service.CadreService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm2 extends AuthorizingRealm {

    @Autowired
    private CadreService cadreService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Cadre cadre = cadreService.selectByName(token.getUsername());
        if (cadre == null) {
            return null;
        }
        //第二项是来自数据库中的密码
        return new SimpleAuthenticationInfo(cadre, cadre.getPassword().toCharArray(), getName());
    }

}
