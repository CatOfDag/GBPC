package com.huse.controller;

import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.service.AdminService;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import com.huse.service.VoteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/*
 * 基础控制器--控制如登录类的基础功能
 */
@Controller
public class BaseController {

    @Autowired
    private CadreService cadreService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private AdminService adminService;
    //    登录页面
    @GetMapping("login")
    public String loginPage(ModelMap mmp) throws UnsupportedEncodingException {
        Subject sub = SecurityUtils.getSubject();
        Object obj = sub.getPrincipal();
        if (obj==null){
            return "login";
        }
        try{
            Admin admin = (Admin) obj;
            mmp.addAttribute("admin",admin);
            return "index";
        }catch (ClassCastException e){
            try{
                Cadre cadre = (Cadre) obj;
                return "redirect:/cadre/cadreInfo";
            }catch (ClassCastException f){
                Participant par = (Participant) obj;
                return "login";
            }
        }
    }

    //退出系统
    @GetMapping("/log/out")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    //    用户中心
    @GetMapping("userCenter")
    public String userCenterPage(ModelMap mmp) {
        Subject sub = SecurityUtils.getSubject();
        Admin obj = (Admin) sub.getPrincipal();
        mmp.addAttribute("userInfo",obj);
        return "user";
    }

    //index页面
    @RequestMapping({"index","/"})
    public String indexPage(ModelMap mmp) {
        //获得当前登录的用户信息
        Subject sub = SecurityUtils.getSubject();
        Admin obj = (Admin) sub.getPrincipal();
        mmp.addAttribute("admin",obj);
        return "index";
    }

    //    welcome
    @GetMapping("welcome")
    public String welcomePage(ModelMap mp) {
        int cadreCount = cadreService.count();
        int parCount = participantService.count();
        int fbCount = participantService.forbidden(0);
        int voteCount = voteService.count();
        mp.addAttribute("cadreCount",cadreCount);
        mp.addAttribute("parCount",parCount);
        mp.addAttribute("fbCount",fbCount);
        mp.addAttribute("voteCount",voteCount);
        return "welcome";
    }

    //403page
    @RequestMapping("403")
    public String noAuthority() {
        return "errorPage/403";
    }

    //管理员的登录认证
    @RequestMapping("verification")
    public String loginCheck(String username, String password, boolean rememberMe, ModelMap mmp,HttpSession session ) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        // 自己创建一个令牌，输入用户名和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password,rememberMe);
        try {
            System.out.println("------登录用户：" + usernamePasswordToken.getUsername()+"------");
            subject.getSession().setAttribute(username,subject.getSession());
            subject.login(usernamePasswordToken);
            Admin admin = adminService.selectByName(username);
            Cadre cadre = cadreService.selectByNamePassword(username,password);
            if (admin!=null && (admin.getRole().equals("user") || admin.getRole().equals("su"))){
                return "redirect:/index";
            }
            if(cadre!=null && !(cadre.getRole().equals("user") || cadre.getRole().equals("su"))){
                //解决Springboot重定向参数乱码问题.
                return "redirect:cadre/cadreInfo";
            }
        } catch (IncorrectCredentialsException e) {
            mmp.addAttribute("state","密码错误！");
        } catch (UnknownAccountException e) {
            mmp.addAttribute("state","账号不存在！");
        } catch (LockedAccountException e) {
            mmp.addAttribute("state","账号被锁定，联系超级管理员开启！");
        } catch (DisabledAccountException e) {
            mmp.addAttribute("state","账号被禁用！");
        } catch (ExpiredCredentialsException e) {
            mmp.addAttribute("state","密码过期！");
        } catch (ExcessiveAttemptsException e) {
            mmp.addAttribute("state","登录失败次数过多！");
        } catch (AccountException e){
            mmp.addAttribute("state","账户已登录！");
        } catch (AuthenticationException e){
            mmp.addAttribute("state","请输入正确的帐号和信息！");
        }
        return "login";
    }

}
