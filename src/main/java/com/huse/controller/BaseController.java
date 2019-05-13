package com.huse.controller;

import com.huse.pojo.Admin;
import com.huse.pojo.Cadre;
import com.huse.service.AdminService;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import com.huse.service.VoteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    public String loginPage(HttpSession session,ModelMap mmp) throws UnsupportedEncodingException {
        Admin admin = (Admin) session.getAttribute("admin");
        Cadre cadre = (Cadre) session.getAttribute("cadre");
        if (admin!=null){
            mmp.addAttribute("admin",admin);
            return "index";
        }
        if (cadre!=null){
            mmp.addAttribute("cadre",cadre);
            return "redirect:/cadre/cadreInfo?id="+cadre.getId()+"&username="+ URLEncoder.encode(cadre.getCadreName(),"UTF-8");
        }
        return "login";
    }

    //退出系统
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("cadre");
        session.removeAttribute("admin");
        return "logout";
    }

    //    用户中心
    @GetMapping("userCenter")
    public String userCenterPage() {
        return "user";
    }

    //    index页面
//    @RequiresRoles({"su","user"})
    @RequestMapping({"index","/"})
    public String indexPage() {
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
        ;
        try {
            subject.login(usernamePasswordToken);
            Admin admin = adminService.selectByName(username);
            Cadre cadre = cadreService.selectByName(username);
            if (admin!=null && (admin.getRole().equals("user") || admin.getRole().equals("su"))){
                session.setAttribute("admin",admin);
                return "redirect:/index";
            }
            if(cadre!=null && !(cadre.getRole().equals("user") || cadre.getRole().equals("su"))){
                session.setAttribute("cadre",cadre);
                //解决Springboot重定向参数乱码问题.
                return "redirect:/cadre/cadreInfo?id="+cadre.getId()+"&username="+ URLEncoder.encode(cadre.getCadreName(),"UTF-8");
            }
        } catch (UnknownAccountException e) {
            mmp.addAttribute("state","账号不存在！");

        } catch (LockedAccountException e) {
            mmp.addAttribute("state","账号被锁定！");

        } catch (DisabledAccountException e) {
            mmp.addAttribute("state","账号被禁用！");

        } catch (IncorrectCredentialsException e) {
            mmp.addAttribute("state","密码错误！");

        } catch (ExpiredCredentialsException e) {
            mmp.addAttribute("state","密码过期！");

        } catch (ExcessiveAttemptsException e) {
            mmp.addAttribute("state","登录失败次数过多！");
        }catch (AuthenticationException e){
            mmp.addAttribute("state","请输入正确的帐号和信息！");
        }
        return "login";
    }

}
