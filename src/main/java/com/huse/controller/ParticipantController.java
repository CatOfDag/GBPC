package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.pojo.Participant;
import com.huse.service.ParticipantService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import com.huse.utils.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/*参与者控制器,管理参与者相关的业务*/
@Controller
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private AjaxResult ajaxResult;

    @RequestMapping("participant/participantlist")
    public String participantPage() {
        return "participantPage/participant";
    }

    @RequestMapping("participant/add")
    public String addParticipant() {
        return "participantPage/participant-add";
    }

    @RequestMapping("participant/edit")
    public String editParticipant(int id, ModelMap mp) {
        Participant participant = participantService.selectByPrimaryKey(id);
        mp.addAttribute("participant", participant);
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
        List<Cadre> cadresLists = new LinkedList<>();
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

//        Sheet sheet = wb.getSheetAt(0);
//        for (int i = 1; i <= sheet.getLastRowNum(); i++)
//        {
//            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
//            String cadreName= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
//            String password = row.getCell(1).getStringCellValue();
//            String job = row.getCell(2).getStringCellValue();
//            int tempstate = (int) row.getCell(4).getNumericCellValue();
//            String desc = row.getCell(6).getStringCellValue();
//            String role = row.getCell(3).getStringCellValue();
//            String avoteLias = row.getCell(5).getStringCellValue();
//            boolean state = tempstate==1 ? true :false;
//            Cadre cadre = new Cadre();
//            cadre.setAvoteLias(avoteLias);
//            cadre.setCadreName(cadreName);
//            cadre.setDesc(desc);
//            cadre.setJob(job);
//            cadre.setState(state);
//            cadre.setRole(role);
//            cadre.setPassword(password);
//            cadresLists.add(cadre);
//            System.out.println("第"+i+"条的信息"+cadre.toString());
//        }
//        for (Cadre import_cadre: cadresLists) {
//            participantService.insert(import_cadre);
//        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }

}
