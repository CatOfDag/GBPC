package com.huse.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.pojo.Vote;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import com.huse.utils.ExcelFileGenerator;
import com.huse.utils.Laytable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*参与者控制器,管理参与者相关的业务*/
@Controller
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private VoteService voteService;
    @Autowired
    private CadreService cadreService;

    @RequestMapping("participant/participantlist")
    public String participantPage() {
        return "participantPage/participant";
    }

    @RequestMapping("participant/add")
    public String addParticipant(ModelMap mmp) {
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
        return "participantPage/participant-add";
    }

    @GetMapping("participant/volume_add")
    public String volume_add(ModelMap mmp) {
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes",votes);
        return "participantPage/participant-volume-add";
    }

    @RequestMapping("participant/edit")
    public String editParticipant(int id, ModelMap mp) {
        //根据id查询对象
        Participant participant = participantService.selectByPrimaryKey(id);
        mp.addAttribute("participant", participant);
        //查询所有的投票项目
        List<Vote> votes = voteService.selectAllVote();
        mp.addAttribute("votes",votes);
        return "participantPage/participant-edit";
    }

    @RequestMapping("participant/getParticipantList")
    @ResponseBody
    public Laytable getParticipantList() {
        int all = participantService.count();
        Laytable laytable = new Laytable();
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(participantService.count());
        laytable.setData(participantService.getParticipantLists(0, all));
        return laytable;
    }

    @RequestMapping("participant/addParticipant")
    @ResponseBody
    public AjaxResult addParticipant(Participant participant) {
        int i = participantService.insert(participant);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping(value = "participant/volumeAddParticipant")
    @ResponseBody
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public AjaxResult volumeAddParticipant(@RequestBody Map<String,String> data) throws IOException {
        Gson gson = new Gson();
        final String[] SIGN = {"spcn","rawn","omcn","smcn","crn"};  /*五个投票类别人数的KEY*/
        final String[] CATEGORY = {"校党委委员", "各界代表", "普通中层干部", "特殊中层干部", "经济与管理学院", "人文与社会科学学院", "马克思主义学院", "体育学院", "外国语学院",
                "传媒学院", "理学院", "电子与信息工程学院", "土木与环境工程学院", "化学与生物工程学院", "音乐与舞蹈学院", "美术与艺术设计学院", "旅游与文化产业学院", "国学院"};
        System.out.println(data.toString());
        String voteAlias = (String) data.get("voteAlias");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endtime = new Date();
        try {
            endtime = simpleDateFormat.parse(data.get("endtime"));
            System.out.println(endtime);
        } catch (ParseException e){
            ajaxResult.setRes(false);
            ajaxResult.setInfo("时间转换错误");
        }

        List<Participant> participantList = new LinkedList<>();

        for (int i = 0; i < SIGN.length - 1; i++) {/*先创建4个特殊类别*/
            int n = Integer.parseInt((String) data.get(SIGN[i]));
            for (int j = 0; j < n; j++) {
                Participant participant = new Participant();
                participant.setRole(CATEGORY[i]);
                participant.setState(true);
                participant.setVoteAlias(voteAlias);
                participant.setEndtime(endtime);
                participant.setPin(UUID.randomUUID().toString().substring(0, 8));
                participantList.add(participant);
            }
        }

        int n = Integer.parseInt((String) data.get(SIGN[4]));/*各学院代表人数*/
        for (int i = 4; i < CATEGORY.length; i++) {/*13个学院*/
            for (int j = 0; j < n; j++) {
                Participant participant = new Participant();
                participant.setRole(CATEGORY[i]);
                participant.setState(true);
                participant.setVoteAlias(voteAlias);
                participant.setEndtime(endtime);
                participant.setPin(UUID.randomUUID().toString().substring(0, 8));
                participantList.add(participant);
            }
        }

        List<String> values = new ArrayList<>();
        for (Participant participant : participantList){
            values.add(gson.toJson(participant));
            participantService.insert(participant);
        }
        /*对于创建的PIN码，生成模板文件*/
        ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(Participant.class,CATEGORY,values);
        try {
            excelFileGenerator.create();
        }catch (IOException e){
            ajaxResult.setRes(true);
            ajaxResult.setInfo("生成Excel文件失败，请手动导出");
            return ajaxResult;
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }

    @RequestMapping("participant/deleteByPK")
    @ResponseBody
    public AjaxResult deleteParticipant(int id) {
        int i = participantService.deleteByPrimaryKey(id);
        System.out.println("-----删除的id是" + id+"-----");
        boolean flag = i > 0;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("participant/updateByPK")
    @ResponseBody
    public AjaxResult updateByPK(Participant participant) {
        final int i = participantService.updateByPrimaryKey(participant);
        boolean flag = i > 0;
        ajaxResult.setRes(true);
        return ajaxResult;
    }

    @RequestMapping("participant/selectToParticipant")
    @ResponseBody
    public Laytable selectToParticipant(String info, String role){
        Laytable laytable = new Laytable();
        List<Participant> participants = new ArrayList<>() ;
        if (!info.equals(""))
            participants.add(participantService.selectByPIN(info));
        else if (!role.equals("")){
            participants = participantService.selectByRole(role);
        }
        else {
            int all = participantService.count();
            participants = participantService.getParticipantLists(0,all);
        }
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(participants.size());
        laytable.setData(participants);
        return laytable;
    }

    /*@RequestMapping("participant/batchImport")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file) throws IOException, MyException {
        String fileName = file.getOriginalFilename();
        List<Participant> participants = new LinkedList<>();
        //验证上传的文件格式
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("文件格式错误");
        }
        InputStream inputStream = file.getInputStream();
        Workbook wb = null;
        //验证上传excel的格式xlsx为word2007格式,xls为2003格式
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        if (isExcel2003) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            wb = new XSSFWorkbook(inputStream);
        }

        Sheet sheet = wb.getSheetAt(0);;
        ajaxResult.setRes(false);
        for (int i = 2; i <= sheet.getLastRowNum(); i++){
            try {
                Row row = sheet.getRow(i);//获取索引为i的行，以0开i
                String role = row.getCell(0).getStringCellValue();
                String pin = UUID.randomUUID().toString().substring(0, 8);
                Date endtime = row.getCell(1).getDateCellValue();
                int tempstate = (int) row.getCell(2).getNumericCellValue();
                String votealias = row.getCell(3).getStringCellValue();
                boolean state = tempstate==1 ? true :false;
                Participant participant = new Participant();
                participant.setEndtime(endtime);
                participant.setPin(pin);
                participant.setRole(role);
                participant.setState(state);
                participant.setVoteAlias(votealias);
                participants.add(participant);
            }catch (NullPointerException e){
                ajaxResult.setInfo("有单元格为空!");
                return ajaxResult;
            }catch (IllegalStateException e){
                ajaxResult.setInfo("无法从数字单元格中获取文本值!");
                return ajaxResult;
            }
        }
        for (Participant ptt: participants) {
            participantService.insert(ptt);
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }*/

    @RequestMapping("participant/checkPIN")
    @ResponseBody
    public AjaxResult checkPIN(String PIN){
        boolean flag = false;
        Subject subject = SecurityUtils.getSubject();
        // 自己创建一个令牌，输入用户名和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(PIN, "",false);
        try{
            subject.login(usernamePasswordToken);
            flag=true;
            ajaxResult.setInfo("participant/parVote");
        }catch (IncorrectCredentialsException e) {
            ajaxResult.setInfo("帐号不存在!");
            flag=false;

        } catch (LockedAccountException e) {
            ajaxResult.setInfo(e.getMessage());
            flag=false;
        }
        ajaxResult.setRes(flag);
        return ajaxResult;
    }
    //参与者登录成功跳转的页面
    @RequestMapping("participant/parVote")
    public String parVote(ModelMap mmp){
        //获取当前登录用户的信息
        Subject sub = SecurityUtils.getSubject();
        Participant obj = (Participant) sub.getPrincipal();
        List<Cadre> cadres = null;
        String role = obj.getRole();
        mmp.addAttribute("participant",obj);
        if("校党委委员".equals(role) || "特殊中层干部".equals(role)){//获取所有类干部角色
            //查询与当前用户关联的信息
            cadres = cadreService.selectByAlias(obj.getVoteAlias());
        }
        else if("普通中层干部".equals(role) || "各界代表".equals(role)){//获取第一类和第二类干部角色
            cadres = cadreService.selectFirAndSecCategory(obj.getVoteAlias());
        }
        else{//获取第三类干部角色
            cadres = cadreService.selectThirdCategory(obj.getVoteAlias(),obj.getRole());
        }
        mmp.addAttribute("cadre",cadres);
        return "participantPage/participant-vote";
    }



    //批量删除参与者
    @RequestMapping("participant/batchDeletion")
    @ResponseBody
    @Transactional
    public AjaxResult batchDeletion(@RequestBody(required = false)List<Integer> ids){
        for (Integer id : ids) {
            participantService.deleteByPrimaryKey(id);
        }
        ajaxResult.setRes(true);
        ajaxResult.setInfo("删除成功");
        return ajaxResult;
    }

}
