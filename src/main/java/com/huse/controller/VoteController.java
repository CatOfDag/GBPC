package com.huse.controller;

import com.huse.pojo.*;
import com.huse.service.*;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import com.huse.utils.ScoreCountFuncation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/*投票控制器,管理投票相关的业务*/
@Controller
public class VoteController implements ScoreCountFuncation {
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
    private ScoreResultService scoreResultService;
    @Autowired
    private CadreService cadreService;

    static boolean token = true;//对提交成绩的读取控制

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
        String operationVote = voteService.countOperationVote();
        if(operationVote==null || vote.getState() == false){
            ajaxResult.setRes(voteService.insert(vote) == 0?false: true);
            return ajaxResult;
        }
        ajaxResult.setRes(false);
        ajaxResult.setInfo("添加失败！当前已有一个活动项目!");
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
        String operationVote = voteService.countOperationVote();
        if(operationVote==null || vote.getState() == false || (vote.getState()==true&&operationVote.equals(vote.getAlias()))){
            int i = voteService.updateByPrimaryKey(vote);
            boolean flag = i > 0 ? true : false;
            ajaxResult.setRes(flag);
            return ajaxResult;
        }
        ajaxResult.setRes(false);
        ajaxResult.setInfo("添加失败！当前已有一个活动项目!");
        return ajaxResult;
    }

    @RequestMapping("vote/getScoreList")
    @ResponseBody
    public Laytable score(int page, int limit, String info,String alias,String role) {
        Laytable laytable = new Laytable();
        laytable.setMsg("");
        laytable.setCode(0);
        int startRows = (page-1)*limit;
        List<ScoreResult> scoreResults = null;
        if(!(role==null||role.equals(""))){
            scoreResults = scoreResultService.selectByRole(role);
            laytable.setData(scoreResults);
        }else if ((info==null||info.equals("")) && (alias==null||alias.equals(""))){
            scoreResults = scoreResultService.getScoreList(startRows, limit);
            laytable.setCount(scoreResultService.count());
            laytable.setData(scoreResults);
        }else {
            scoreResults = scoreResultService.fuzzyQuery(info,alias);
            laytable.setData(scoreResults);
        }
        return laytable;
    }
    //投票页面数据恢复
    @RequestMapping("vote/dataRecover")
    @ResponseBody
    @Transactional
    public List<String[]> dataRecover(@RequestBody(required = false) String FileName){
        String Recoverpath = "H:\\scoreSave\\"+FileName+".xls";
        File file = new File(Recoverpath);
        List<String[]> scoreList = new LinkedList<>();
        if(file.exists()){
            ajaxResult.setRes(false);
            try {
                FileInputStream inputStream = new FileInputStream(file);
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
                Sheet sheet = hssfWorkbook.getSheetAt((int) 0);
                int cols = sheet.getRow(0).getPhysicalNumberOfCells();
                int rows = sheet.getLastRowNum();
                try{
                    for(int row = 1; row <= rows; row++){
                        Row rowData = sheet.getRow(row);
                        String[] score = new String[5];
                        for(int i = 0; i < cols;i++){
                            score[i] = rowData.getCell(i).getStringCellValue();
                        }
                        scoreList.add(score);
                        }
                }catch (Exception e){
                    System.out.println("Exception");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
        return scoreList;
    }
    /*投票页面数据备份*/
    @RequestMapping("vote/dataSave")
    @ResponseBody
    @Transactional
    public AjaxResult dataSave(@RequestBody(required = false) List<Score> scoreList){
        String sheetName = "投票成绩";
        String Savepath = "H:\\scoreSave\\";
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setRes(true);//设置默认值为true
        String SaveFileName = scoreList.get(0).getPin()+".xls";
        String[] heardList = {"virtue","ability","diligence","feats","honest"};
        int rows = 0;//开始行

        HSSFCell cell = null;
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);
        HSSFRow hssfRowh = hssfSheet.createRow((int) rows++);//表头行
        //设置表头
        for(int i = 0; i < heardList.length; i++) {//virtue ability diligence feats honest
            cell = hssfRowh.createCell(i);
            cell.setCellValue(heardList[i]);
        }
        //设置表内数据
        for(Score score : scoreList){
            HSSFRow hssfRowd = hssfSheet.createRow((int) rows++);
            int[] fs;
            fs = score.getScore();
            for(int i = 0; i < heardList.length; i++){
                cell = hssfRowd.createCell(i);
                cell.setCellValue(String.valueOf(fs[i]));
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Savepath+SaveFileName);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ajaxResult.setRes(false);
        } catch (IOException e) {
            e.printStackTrace();
            ajaxResult.setRes(false);
        }
        return ajaxResult;
    }

    @RequestMapping("vote/insertVote")
    @ResponseBody
    @Transactional
    public AjaxResult insertVote(@RequestBody(required = false) List<Score> score){
        System.out.println("----->进入投票成绩提交流程<-----");
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
        if(cadreDatail == null){
            return "errorPage/Unedited_pages";
        }
        Info cadreInfo = infoService.selectByCadreName(name);
        mmp.addAttribute("cadreInfo", cadreInfo);
        return "cadrePage/cadre-info2";
    }
}
