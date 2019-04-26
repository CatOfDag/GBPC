package com.huse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*日志控制器,管理日志相关的业务*/
@Controller
public class LogController {
    @GetMapping("log/loglist")
    public String logPage(){
        return "logPage/log";
    }
}
