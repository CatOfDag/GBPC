package com.huse.controller;

import com.huse.pojo.Score;
import com.huse.pojo.Vote;
import com.huse.pojo.VoteResult;
import com.huse.service.CadreService;
import com.huse.service.ScoreResultService;
import com.huse.service.ScoreService;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import com.huse.utils.ScoreCountFuncation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/*投票结果控制器,管理投票结果相关的业务*/
@Controller
public class ResultController implements ScoreCountFuncation {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private ScoreResultService scoreResultService;
    @Autowired
    private CadreService cadreService;

    @GetMapping("result/Resultlist")
    public String resultPage(ModelMap mmp){
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
        return "resultPage/result";
    }
    @GetMapping("result/LeaderResultlist")
    public String resultPage1(ModelMap mmp){
        List<Vote> votes = voteService.selectAllVote();
        System.out.println(votes.get(0).getAlias());
        mmp.addAttribute("votes",votes);
        return "resultPage/LeaderResult";
    }

    @GetMapping("result/OtherResultlist")
    public String resultPage2(ModelMap mmp){
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
        return "resultPage/OtherResultlist";
    }
    @RequestMapping("result/getScore")
    @ResponseBody
    public Laytable getScore(String alias,String role,String cadreName){
        Laytable laytable = new Laytable();
        List<String> names = null;
        if(!(cadreName==null || cadreName.equals(""))){
            names = scoreService.fuzzyQueryRS(cadreName,alias);
        }
        else if(!(role==null || role.equals(""))){
            names = scoreService.selectByRole(role);
        }
        else {
            names = scoreService.selectAllCadreName(alias);
        }
        List<VoteResult> results = new LinkedList<>();
        for (String name : names) {
            List<Score> scores = scoreService.selectByCadreName(name);
            VoteResult vtrs = new VoteResult();
            for (Score score : scores) {
                vtrs = ScoreCountFuncation.ScoreHandleInit(score,vtrs);
            }
            double ls = scoreResultService.selectLVScoreByname(name);
            double os = scoreResultService.selectOVScoreByname(name);
            if(cadreService.selectRoleByName(name).equals("行政与教辅单位"))//总成绩的更新
                scoreResultService.updateFVScore(name,ls*0.5+os*0.5);
            else {
                scoreResultService.updateFVScore(name,ls*0.4+os*0.6);
            }
            vtrs.setScore((scoreResultService.selectFVScoreByname(name)*1000)/1000.0);
            results.add(vtrs);
        }
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(results.size());
        laytable.setData(results);
        return laytable;
    }
    //算出校领导给出的成绩
    @RequestMapping("result/AcountLeaderVoteNum")
    @ResponseBody
    public Laytable AcountLeaderVoteNum(String alias) {
        List<String> names = null;
        Laytable laytable = new Laytable();
        names = scoreService.selectAllCadreName(alias);
        List<VoteResult> results = new LinkedList<>();
        for (String name : names) {
            List<Score> scores = scoreService.selectLeaderVoteNumByName(name);
            if(scores.isEmpty()) continue;
            VoteResult vtrs = new VoteResult();
            for (Score score : scores) {
                vtrs = ScoreCountFuncation.ScoreHandleInit(score,vtrs);
            }
            /*vtrs.setScore((int)(scoreResultService.selectLVScoreByname(name)*1000+5)/1000.0);*/
            double s = ScoreCountFuncation.ScoreHandle(vtrs);
            scoreResultService.updateLVScore(name,s);
            vtrs.setScore((int)((s+0.0005)*1000)/1000.0);
            results.add(vtrs);
        }
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(results.size());
        laytable.setData(results);
        return laytable;
    }
    //算出其他投票人员的给出的成绩
    @RequestMapping("result/AcountOtherVoteNum")
    @ResponseBody
    public Laytable AcountOtherVoteNum(String alias){
        List<String> names = null;
        Laytable laytable = new Laytable();
        names = scoreService.selectAllCadreName(alias);
        List<VoteResult> results = new LinkedList<>();
        for (String name : names) {
            List<Score> scores = scoreService.selectOtherVoteNumByName(name);
            if(scores.isEmpty()) continue;
            VoteResult vtrs = new VoteResult();
            for (Score score : scores) {
                vtrs = ScoreCountFuncation.ScoreHandleInit(score,vtrs);
            }
            /*vtrs.setScore((scoreResultService.selectOVScoreByname(name)*1000)/1000.0);*/
            double s = ScoreCountFuncation.ScoreHandle(vtrs);
            scoreResultService.updateOVScore(name,s);
            vtrs.setScore((int)((s+0.0005)*1000)/1000.0);
            results.add(vtrs);
        }
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(results.size());
        laytable.setData(results);
        return laytable;
    }

    //查找当前Pin投出的成绩
    @RequestMapping("result/PintoScore")
    @ResponseBody
    public Laytable PintoSoccer(String pin){
        Laytable laytable = new Laytable();
        List<Score> scores = scoreService.selectByPin(pin);
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(scores.size());
        laytable.setData(scores);
        return laytable;
    }
    @RequestMapping("result/deleteToScoreByPin")
    @ResponseBody
    public AjaxResult deleteToScoreByPin(@RequestBody(required = false)List<Integer> ids){
        AjaxResult ajaxResult = new AjaxResult();
        for (Integer id : ids)
            scoreService.deleteByPrimaryKey(id);
        ajaxResult.setRes(true);
        return ajaxResult;
    }
}
