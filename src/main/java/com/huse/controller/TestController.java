package com.huse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/test/{var}")
    @ResponseBody
    public String operation(@PathVariable int var){
        String[] sentence= {"I like you", "Don't be afraid of failure"};
        if (var >= sentence.length || var < 0){
            return "错误输入";
        } else {
            return sentence[var];
        }
    }
    @RequestMapping("test")
    @ResponseBody
    public String operation(){
        return "ky";
    }
    @RequestMapping("test/test1")
    @ResponseBody
    public String operation2(){
        return "ky1";
    }
}
