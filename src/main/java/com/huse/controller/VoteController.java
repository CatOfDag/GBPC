package com.huse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*投票控制器,管理投票相关的业务*/
@Controller
public class VoteController {
    @GetMapping("vote/votelist")
    public String voteListPage(){
        return "votePage/vote";
    }
    @GetMapping("vote/add")
    public String addVote(){
        return "votePage/vote-add";
    }
    @GetMapping("vote/edit")
    public String adminVote(){
        return "votePage/vote-edit";
    }
    @GetMapping("vote/score")
    public String voteScore(){
        return "votePage/vote-score";
    }
}
