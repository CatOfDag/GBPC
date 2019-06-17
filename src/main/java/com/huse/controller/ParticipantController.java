package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.pojo.Vote;
import com.huse.service.CadreService;
import com.huse.service.ParticipantService;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import com.huse.utils.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public Laytable getParticipantList(int page, int limit) {
        int startRows = (page - 1) * limit;
        Laytable laytable = new Laytable();
        laytable.setMsg("");
        laytable.setCode(0);
        laytable.setCount(participantService.count());
        laytable.setData(participantService.getParticipantLists(startRows, limit));
        return laytable;
    }

    @RequestMapping("participant/addParticipant")
    @ResponseBody
    public AjaxResult addParticipant(Participant participant) {
        System.out.println(participant.toString());
        int i = participantService.insert(participant);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("participant/deleteByPK")
    @ResponseBody
    public AjaxResult deleteParticipant(int id) {
        int i = participantService.deleteByPrimaryKey(id);
        System.out.println("要删除的id是" + id);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("participant/updateByPK")
    @ResponseBody
    public AjaxResult updateByPK(Participant participant) {
        System.out.println(participant.toString());
        final int i = participantService.updateByPrimaryKey(participant);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(true);
        return ajaxResult;
    }

    @RequestMapping("participant/batchImport")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file) throws IOException, MyException {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
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

        Sheet sheet = wb.getSheetAt(0);
        for (int i = 2; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            String pin= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            String role = row.getCell(1).getStringCellValue();
            Date endtime = row.getCell(2).getDateCellValue();
            int tempstate = (int) row.getCell(3).getNumericCellValue();
            String votealias = row.getCell(4).getStringCellValue();
            boolean state = tempstate==1 ? true :false;
            Participant participant = new Participant();
            participant.setEndtime(endtime);
            participant.setPin(pin);
            participant.setRole(role);
            participant.setState(state);
            participant.setVoteAlias(votealias);
            participants.add(participant);
            System.out.println("第"+i+"条的信息"+participant.toString());
        }
        for (Participant ptt: participants) {
            participantService.insert(ptt);
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }

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
        }catch (UnknownAccountException e) {
            ajaxResult.setInfo("帐号不存在!");
            flag=false;

        } catch (LockedAccountException e) {
            ajaxResult.setInfo("帐号已失效!");
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
        System.out.println(obj.getVoteAlias());
        mmp.addAttribute("participant",obj);
        //查询与当前用户关联的信息
        List<Cadre> cadres = cadreService.selectByAlias(obj.getVoteAlias());
        mmp.addAttribute("cadreByAlias",cadres);
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
