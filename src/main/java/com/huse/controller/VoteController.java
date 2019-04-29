package com.huse.controller;

import com.huse.pojo.Score;
import com.huse.pojo.Vote;
import com.huse.service.ScoreService;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @Autowired
    private ScoreService scoreService;

    @GetMapping("vote/votelist")
    public String voteListPage() {
        return "votePage/vote";
    }

    @GetMapping("vote/add")
    public String addVote() {
        return "votePage/vote-add";
    }

    @GetMapping("vote/edit")
    public String adminVote(int id, ModelMap mp) {
        Vote vote = voteService.selectByPrimaryKey(id);
        mp.addAttribute("vote", vote);
        System.out.println(vote.toString());
        return "votePage/vote-edit";
    }

    @GetMapping("vote/score")
    public String voteScore() {
        return "votePage/vote-score";
    }

    @RequestMapping("vote/getVoteList")
    @ResponseBody
    public Laytable getVoteList(int page, int limit) {
        int startRows = (page - 1) * limit;
        Laytable laytable = new Laytable();
        laytable.setCode(0);
        laytable.setMsg("");
        int count = voteService.count();
        laytable.setCount(count);
        List<Vote> voteList = voteService.getVoteList(startRows, limit);
        laytable.setData(voteList);
        return laytable;
    }

    @RequestMapping("vote/addVote")
    @ResponseBody
    public AjaxResult addVote(Vote vote) {
        int i = voteService.insert(vote);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("vote/deleteByPK")
    @ResponseBody
    public AjaxResult deleteByPK(int id) {
        int i = voteService.deleteByPrimaryKey(id);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("vote/updateByPK")
    @ResponseBody
    public AjaxResult updateByPK(Vote vote) {
        int i = voteService.updateByPrimaryKey(vote);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("vote/getScoreList")
    @ResponseBody
    public Laytable score(int page, int limit, String info) {
        Laytable laytable = new Laytable();
        laytable.setMsg("");
        laytable.setCode(0);
        if (info==null || info.equals("")){
            int startRows = (page-1)*limit;
            List<Score> scoreList = scoreService.getScoreList(startRows, limit);
            laytable.setCount(scoreService.count());
            laytable.setData(scoreList);
        }else {
            System.out.println(info);
            List<Score> scores = scoreService.fuzzyQuery(info);
            laytable.setData(scores);
        }
        return laytable;
    }
}
