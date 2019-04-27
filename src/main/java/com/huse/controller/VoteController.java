package com.huse.controller;

import com.huse.pojo.Vote;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/*投票控制器,管理投票相关的业务*/
@Controller
public class VoteController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private VoteService voteService;

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

//    @RequestMapping("vote/getVoteList")
//    @ResponseBody
//    public Vote getVoteList(){
//    }
}
