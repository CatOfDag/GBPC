package com.huse.controller;

import com.huse.pojo.CadreDatail;
import com.huse.pojo.ScoreResult;
import com.huse.service.CadreDatailService;
import com.huse.service.CadreService;
import com.huse.service.ScoreResultService;
import com.huse.utils.AjaxResult;
import com.huse.utils.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@Service
public class CadreDatailController {
    @Autowired
    private CadreDatailService cadreDatailService;
    @Autowired
    private CadreService cadreService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private ScoreResultService scoreResultService;

    @GetMapping("cadre/cadreInformation")
    public String cadreInformation(){
        return "cadrePage/cadreInformation";
    }

    @GetMapping("cadre/modify")
    public String modify(int id, ModelMap mp){
        CadreDatail cadreDatail = cadreDatailService.selectByPrimaryKey(id);
        mp.addAttribute("cadreDatail", cadreDatail);
        return "cadrePage/cadre-modify";
    }

    @RequestMapping("cadre/detailUpdateByPK")
    @ResponseBody
    public AjaxResult updateByPK(CadreDatail cadreDatail) {
        System.out.println("this");
        int i = cadreDatailService.updateByPrimaryKey(cadreDatail);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/fileimprot")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file) throws IOException, MyException {
        String fileName = file.getOriginalFilename();
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
            Row row1 = sheet.getRow(1);
            Row row2 = sheet.getRow(2);
            Row row3 = sheet.getRow(3);
            Row row4 = sheet.getRow(4);
            Row row5 = sheet.getRow(5);
            Row row6 = sheet.getRow(6);
            Row row7 = sheet.getRow(7);
            Row row8 = sheet.getRow(8);
        CadreDatail cadreDatail = new CadreDatail();
            try {
                String name = row1.getCell(1).getStringCellValue();//第一行
                String sex = row1.getCell(3).getStringCellValue();
                String birth = row1.getCell(5).getStringCellValue();

                String nation = row2.getCell(1).getStringCellValue();//第二行
                String nativeplace = row2.getCell(3).getStringCellValue();
                String health = row2.getCell(5).getStringCellValue();

                String politicalface = row3.getCell(1).getStringCellValue();//第三行
                String worktime = row3.getCell(3).getStringCellValue();
                String majorpost = row3.getCell(5).getStringCellValue();

                String seducation = row4.getCell(2).getStringCellValue();
                String seducationdetail = row4.getCell(5).getStringCellValue();
                String weducation = row5.getCell(2).getStringCellValue();
                String weducationdetail = row5.getCell(5).getStringCellValue();

                String newpost = row6.getCell(2).getStringCellValue();
                String appointtime = row6.getCell(5).getStringCellValue();

                String resume = row7.getCell(1).getStringCellValue();
                String punishaward = row8.getCell(1).getStringCellValue();
                cadreDatail.setCadre_name(name);
                cadreDatail.setBirth(birth);
                cadreDatail.setSex(sex);

                cadreDatail.setNation(nation);
                cadreDatail.setNativeplace(nativeplace);
                cadreDatail.setHealth(health);

                cadreDatail.setPoliticalface(politicalface);
                cadreDatail.setWorktime(worktime);
                cadreDatail.setMajorpost(majorpost);

                cadreDatail.setSeducation(seducation);
                cadreDatail.setSeducationdetail(seducationdetail);
                cadreDatail.setWeducation(weducation);
                cadreDatail.setWeducationdetail(weducationdetail);

                cadreDatail.setNewpost(newpost);
                cadreDatail.setAppointtime(appointtime);

                cadreDatail.setResume(resume);
                cadreDatail.setPunishaward(punishaward);
                cadreDatail.toString();
            }catch (NullPointerException e){
                ajaxResult.setInfo("有单元格为空!");
                return ajaxResult;
            }
            boolean flag = cadreDatailService.updateByNameJob(cadreDatail)==1?true:false;
            if(!flag) ajaxResult.setInfo("修改失败！");
            ajaxResult.setRes(flag);
            return ajaxResult;
    }

    public void addUpdateByPK(CadreDatail cadreDatail,String role){
        cadreDatailService.insert(cadreDatail);
        ScoreResult scoreResult = new ScoreResult(cadreDatail.getId(),cadreDatail.getVote_alia(),cadreDatail.getCadre_name(),cadreDatail.getNewpost(),role);
        scoreResultService.insert(scoreResult);
    }
}
