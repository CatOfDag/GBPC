package com.huse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
* 基础控制器--控制如登录类的基础功能
*/
@Controller
public class BaseController {
//    登录页面
    @GetMapping("adminLogin")
    public String loginPage(){
        return "admin_login";
    }

//    用户中心
    @GetMapping("userCenter")
    public String userCenterPage(){
        return "user";
    }
//    index页面
    @GetMapping("index")
    public String indexPage(){
        return "index";
    }
//    welcome
    @GetMapping("welcome")
    public String welcomePage(){
        return "welcome";
    }
}
