package com.huse.controller;

import com.huse.pojo.*;
import com.huse.service.*;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
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
    @Autowired
    private CadreDatailService cadreDatailService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private Participant participant;
    @Autowired
    private InfoService infoService;
    @Autowired
    private CadreService cadreService;

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
    public String voteScore(ModelMap mmp) {
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
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
    public Laytable score(int page, int limit, String info,String alisa) {
        Laytable laytable = new Laytable();
        laytable.setMsg("");
        laytable.setCode(0);
        if ((info==null||info.equals("")) && (alisa==null||alisa.equals(""))){
            int startRows = (page-1)*limit;
            List<Score> scoreList = scoreService.getScoreList(startRows, limit);
            laytable.setCount(scoreService.count());
            laytable.setData(scoreList);
        }else {
            List<Score> scores = scoreService.fuzzyQuery(info,alisa);
            laytable.setData(scores);
        }
        return laytable;
    }
    @RequestMapping("vote/insertVote")
    @ResponseBody
    @Transactional
    public AjaxResult insertVote(@RequestBody(required = false) List<Score> score){
        Score score1 = score.get(0);
        //查询投票状态
        Vote vote = voteService.selectByAlisa(score1.getAlias());
//        查询用户状态
        Participant recParticipant = participantService.selectByPIN(score1.getPin());
        Date nowTime = new Date();
//        验证用户状态
        if (!recParticipant.getState()){
            ajaxResult.setInfo("你已经投过票啦,账号已失效!!!");
            ajaxResult.setRes(false);
            return ajaxResult;
        }
//        如果在开始之前
        if (nowTime.before(vote.getBeginTime())){
            ajaxResult.setRes(false);
            ajaxResult.setInfo("投票还未开始，请开始后再投!!!");
            return ajaxResult;
        }
//        如果在开始之后
        if(vote.getEndTime().before(nowTime)){
            ajaxResult.setInfo("投票已经结束，无法投票!!!");
            ajaxResult.setRes(false);
            return ajaxResult;
        }
        //如果投票被停用了
        if(!vote.getState()){
            ajaxResult.setInfo("投票已被禁用，无法投票!!!");
            ajaxResult.setRes(false);
            return ajaxResult;
        }
//        如果处于正在投票期间
        for (Score tempScore : score) {
            scoreService.insert(tempScore);
            System.out.println(tempScore.toString());
        }

//        投票成功
        participant.setPin(score1.getPin());

        participant.setState(false);
        participantService.updateByPIN(participant);
        ajaxResult.setRes(true);
        ajaxResult.setInfo("投票成功");
        return ajaxResult;
    }

    @RequestMapping(value = "vote/showInfo")
    public String cadreInfo(ModelMap mmp,String name,Integer id) {
        //获取当前登录对象
        CadreDatail cadreDatail = cadreDatailService.selectByPrimaryKey(id);
        mmp.addAttribute("cadre", cadreDatail);
        System.out.println("1:"+mmp);
        if(cadreDatail == null){
            return "errorPage/Unedited_pages";
        }
        Info cadreInfo = infoService.selectByCadreName(name);
        mmp.addAttribute("cadreInfo", cadreInfo);
        System.out.println("2:"+cadreInfo);
        return "cadrePage/cadre-info2";
    }
}
