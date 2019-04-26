package com.huse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*投票结果控制器,管理投票结果相关的业务*/
@Controller
public class ResultController {
    @GetMapping("result/resultlist")
    public String resultPage(){
        return "resultPage/result";
    }
}
