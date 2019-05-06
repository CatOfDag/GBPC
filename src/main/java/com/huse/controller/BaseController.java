package com.huse.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 基础控制器--控制如登录类的基础功能
 */
@Controller
public class BaseController {
    //    登录页面
    @GetMapping("adminLogin")
    public String loginPage() {
        return "admin_login";
    }

    //    用户中心
    @GetMapping("userCenter")
    public String userCenterPage() {
        return "user";
    }

    //    index页面
    @GetMapping("index")
    public String indexPage() {
        return "index";
    }

    //    welcome
    @GetMapping("welcome")
    public String welcomePage() {
        return "welcome";
    }

    //403page
    @RequestMapping("403")
    public String noAuthority() {
        return "errorPage/403";
    }

    //登录认证 https://blog.csdn.net/qq_28988969/article/details/78190869
    @RequestMapping("verification")
    public String loginCheck(String username, String password, boolean rememberMe, ModelMap mmp) {
        System.out.println("传入的用户名是:" + username + "--传入的密码是" + password);
        Subject subject = SecurityUtils.getSubject();
        // 自己创建一个令牌，输入用户名和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password,rememberMe);
        try {
            subject.login(usernamePasswordToken);
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            mmp.addAttribute("state","账号不存在！");
            System.out.println("账号不存在！");

        } catch (LockedAccountException e) {
            e.printStackTrace();
            mmp.addAttribute("state","账号被锁定！");
            System.out.println("账号被锁定！");

        } catch (DisabledAccountException e) {
            e.printStackTrace();
            mmp.addAttribute("state","账号被禁用！");
            System.out.println("账号被禁用！");

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            mmp.addAttribute("state","密码错误！");
            System.out.println("密码错误！");

        } catch (ExpiredCredentialsException e) {
            e.printStackTrace();
            mmp.addAttribute("state","密码过期！");
            System.out.println("密码过期！");

        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
            mmp.addAttribute("state","登录失败次数过多！");
            System.out.println("登录失败次数过多！");

        }
        return "admin_login";
    }
}
