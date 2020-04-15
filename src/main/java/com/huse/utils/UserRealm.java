package com.huse.utils;

import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.pojo.Vote;
import com.huse.service.AdminService;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import com.huse.service.VoteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;
import java.util.Date;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private AdminService adminService;
    @Autowired
    @Lazy
    private CadreService cadreService;
    @Autowired
    @Lazy
    private ParticipantService participantService;
    @Autowired
    @Lazy
    private VoteService voteService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //System.out.println("principals:"+principals);
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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions){
            String CurrentImf = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            try{
                String sub1 = CurrentImf.substring(CurrentImf.indexOf("\'")+1);
                String sub2 = sub1.substring(0,sub1.indexOf("\'"));
                if(sub2.equals(token.getUsername())){
                    throw new AccountException("该用户已登录");
                }
            }
            catch (StringIndexOutOfBoundsException e){}
        }

        Admin admin;
        admin = adminService.selectByName(token.getUsername());
        Cadre cadre;
        cadre = cadreService.selectByNamePassword(token.getUsername(),String.valueOf(token.getPassword()));
        Participant participant;
        participant = participantService.selectByPIN(token.getUsername());
            if (admin == null) {
                //如果cadre为空
                if (cadre==null){
                    //返回一个null,验证失败
                    if (participant==null){
                        return null;
                    }else{
                        Vote vote;
                        vote = voteService.selectByAlisa(participant.getVoteAlias());
                        Date nowTime = new Date();
                        if(!vote.getBeginTime().after(nowTime)&&vote.getState()){//如果未到投票时间或投票被禁止则无法登录
                            if(vote.getEndTime().after(nowTime)){//如果已超过投票时间则无法登录
                                //如果超过截止日期或者账户被锁定
                                if (participant.getState()&& !participant.getEndtime().before(nowTime)){
                                    return new SimpleAuthenticationInfo(participant,"",getName());
                                }else {
                                    throw new LockedAccountException("投票码已使用或已过截止期");
                                }
                            }else {
                                throw new LockedAccountException("投票项目已过期");
                            }

                        }else{
                            throw new LockedAccountException("投票项目未开始");
                        }

                    }
                }else {
                    return new SimpleAuthenticationInfo(cadre, cadre.getPassword().toCharArray(), getName());
                }
            }else {
                if (!admin.getState()){
                    //如果数据表中是停用状态,则抛出异常.
                    throw new LockedAccountException("帐号被停用,联系超级管理员开启.");
                }
                return new SimpleAuthenticationInfo(admin, admin.getPassword().toCharArray(), getName());
            }
    }
}
