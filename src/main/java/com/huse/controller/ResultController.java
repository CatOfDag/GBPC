package com.huse.controller;

import com.huse.mapper.ScoreMapper;
import com.huse.pojo.Score;
import com.huse.pojo.Vote;
import com.huse.pojo.VoteResult;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*投票结果控制器,管理投票结果相关的业务*/
@Controller
public class ResultController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private VoteService voteService;

    @GetMapping("result/resultlist")
    public String resultPage(ModelMap mmp){
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
        return "resultPage/result";
    }

    @RequestMapping("result/getScore")
    @ResponseBody
    public Laytable getScore(String alias){
        System.out.println("投票的别名是+="+alias);
        Laytable laytable = new Laytable();
        //查询到所有的领导
        List<String> strings = null;
        strings = scoreService.selectAllCadreName(alias);
        System.out.println(strings.toString());
        List<VoteResult> results = new LinkedList<>();
        for (String tempString : strings) {
            int v1=0,v2=0,v3=0,v4=0;
            int a1=0,a2=0,a3=0,a4=0;
            int d1=0,d2=0,d3=0,d4=0;
            int f1=0,f2=0,f3=0,f4=0;
            int h1=0,h2=0,h3=0,h4=0;
            List<Score> scores = scoreService.selectByCadreName(tempString);
            VoteResult vtrs = new VoteResult();
            //ability
            vtrs.setA1count(a1);
            vtrs.setA2count(a2);
            vtrs.setA3count(a3);
            vtrs.setA4count(a4);
            //virtua
            vtrs.setV1count(v1);
            vtrs.setV2count(v2);
            vtrs.setV3count(v3);
            vtrs.setV4count(v4);
            //honest
            vtrs.setH1count(h1);
            vtrs.setH2count(h2);
            vtrs.setH4count(h3);
            vtrs.setH4count(h4);
            //diligence
            vtrs.setD1count(d1);
            vtrs.setD2count(d2);
            vtrs.setD3count(d3);
            vtrs.setD4count(d4);
            //feats
            vtrs.setF1count(f1);
            vtrs.setF2count(f2);
            vtrs.setF3count(f3);
            vtrs.setF4count(f4);
            for (Score score : scores) {
//                System.out.println(score.toString());
                vtrs.setName(score.getCadreName());
                vtrs.setVotealias(score.getAlias());
                switch (score.getAbility()){
                    case 1:
                        vtrs.setA1count(vtrs.getA1count()+1);
                        break;
                    case 2:
                        vtrs.setA2count(vtrs.getA2count()+1);
                        break;
                    case 3:
                        vtrs.setA3count(vtrs.getA3count()+1);
                        break;
                    case 4:
                        vtrs.setA4count(vtrs.getA4count()+1);
                        break;
                }
                switch (score.getHonest()){
                    case 1:
                        vtrs.setH1count(vtrs.getH1count()+1);
                        break;
                    case 2:
                        vtrs.setH2count(vtrs.getH2count()+1);
                        break;
                    case 3:
                        vtrs.setH3count(vtrs.getH3count()+1);
                        break;
                    case 4:
                        vtrs.setH4count(vtrs.getH4count()+1);
                        break;
                }
                switch (score.getFeats()){
                    case 1:
                        vtrs.setF1count(vtrs.getF1count()+1);
                        break;
                    case 2:
                        vtrs.setF2count(vtrs.getF2count()+1);
                        break;
                    case 3:
                        vtrs.setF3count(vtrs.getF3count()+1);
                        break;
                    case 4:
                        vtrs.setF4count(vtrs.getF4count()+1);
                        break;
                }
                switch (score.getVirtue()){
                    case 1:
                        vtrs.setV1count(vtrs.getV1count()+1);
                        break;
                    case 2:
                        vtrs.setV2count(vtrs.getV2count()+1);
                        break;
                    case 3:
                        vtrs.setV3count(vtrs.getV3count()+1);
                        break;
                    case 4:
                        vtrs.setV4count(vtrs.getV4count()+1);
                        break;
                }
                switch (score.getDiligence()){
                    case 1:
                        vtrs.setD1count(vtrs.getD1count()+1);
                        break;
                    case 2:
                        vtrs.setD2count(vtrs.getD2count()+1);
                        break;
                    case 3:
                        vtrs.setD3count(vtrs.getD3count()+1);
                        break;
                    case 4:
                        vtrs.setD4count(vtrs.getD4count()+1);
                        break;
                }
            }
            //计算结果
            double vRes =(vtrs.getV1count()*100+vtrs.getV2count()*85+vtrs.getV3count()*70+vtrs.getV4count()*50)
                    / (vtrs.getV1count()+vtrs.getV2count()+vtrs.getV3count()+vtrs.getV4count());
            double fRes =(vtrs.getF1count()*100+vtrs.getF2count()*85+vtrs.getF3count()*70+vtrs.getF4count()*50)
                    / (vtrs.getF1count()+vtrs.getF2count()+vtrs.getF3count()+vtrs.getF4count());
            double hRes =(vtrs.getH1count()*100+vtrs.getH2count()*85+vtrs.getH3count()*70+vtrs.getH4count()*50)
                    / (vtrs.getH1count()+vtrs.getH2count()+vtrs.getH3count()+vtrs.getH4count());
            double dRes =(vtrs.getD1count()*100+vtrs.getD2count()*85+vtrs.getD3count()*70+vtrs.getD4count()*50)
                    / (vtrs.getD1count()+vtrs.getD2count()+vtrs.getD3count()+vtrs.getD4count());
            double aRes =(vtrs.getA1count()*100+vtrs.getA2count()*85+vtrs.getA3count()*70+vtrs.getA4count()*50)
                    / (vtrs.getA1count()+vtrs.getA2count()+vtrs.getA3count()+vtrs.getA4count());
            vtrs.setScore(vRes*0.2+hRes*0.2+fRes*0.2+dRes*0.2+aRes*0.2);
            results.add(vtrs);
        }
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(results.size());
        laytable.setData(results);
        return laytable;
    }
}
