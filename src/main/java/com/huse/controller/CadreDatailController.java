package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.pojo.CadreDatail;
import com.huse.service.CadreDatailService;
import com.huse.service.CadreService;
import com.huse.service.InfoService;
import com.huse.service.VoteService;
import com.huse.utils.AjaxResult;
import com.huse.utils.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CadreDatailController {
    @Autowired
    private CadreDatailService cadreDatailService;
    @Autowired
    private CadreService cadreService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private InfoService infoService;
    @Autowired
    private VoteService voteService;

    @GetMapping("cadre/cadreInformation")
    public String cadreInformation(){
        return "cadrePage/cadreInformation";
    }

    @GetMapping("cadre/modify")
    public String modify(int id, ModelMap mp){
        CadreDatail cadreDatail = cadreDatailService.selectByPrimaryKey(id);
        mp.addAttribute("cadreDatail", cadreDatail);
        System.out.println(mp);
        if(cadreDatail == null){
            cadreDatail = cadreDatailService.selectByIdFoundName(id);
            System.out.println(cadreDatail);
            mp.addAttribute("cadreDatail",cadreDatail);
            System.out.println("cadre-detail_all"+mp);
            return "cadrePage/cadre-detail_add";
        }
        return "cadrePage/cadre-modify";
    }

    @RequestMapping("cadre/detailUpdateByPK")
    @ResponseBody
    public AjaxResult updateByPK(CadreDatail cadreDatail) {
        System.out.println("updateByPK");
        int i = cadreDatailService.updateByPrimaryKey(cadreDatail);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/addUpdateByPK")
    @ResponseBody
    public AjaxResult addUpdateByPK(CadreDatail cadreDatail){
        System.out.println("add " + cadreDatail);
        final int insert = cadreDatailService.insert(cadreDatail);
        boolean flag = insert > 0 ? true : false;//控制是否输入错误
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/fileimprot")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file, ModelMap mp) throws IOException, MyException {
        System.out.println("fileImport");
        String fileName = file.getOriginalFilename();
        List<CadreDatail> cadresLists = new LinkedList<>();
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
            Row row9 = sheet.getRow(9);
            Row row10 = sheet.getRow(10);
            Row row11 = sheet.getRow(11);
            Row row12 = sheet.getRow(12);
            Row row13 = sheet.getRow(13);
            String name  = row1.getCell(1).getStringCellValue();//第一行
            String sex = row1.getCell(4).getStringCellValue();
            String birth = row1.getCell(6).getStringCellValue();

            String nation = row2.getCell(1).getStringCellValue();//第二行
            String nativeplace = row2.getCell(4).getStringCellValue();
            String brithplace = row2.getCell(6).getStringCellValue();

            String nativetytime = row3.getCell(1).getStringCellValue();//第三行
            String worktime = row3.getCell(4).getStringCellValue();
            String health = row3.getCell(6).getStringCellValue();

            String majorpost = row4.getCell(1).getStringCellValue();
            String expertise = row4.getCell(4).getStringCellValue();

            String seducation = row5.getCell(2).getStringCellValue();
            String seducationdetail = row5.getCell(5).getStringCellValue();

            String weducation = row6.getCell(2).getStringCellValue();
            String weducationdetail = row6.getCell(5).getStringCellValue();

            String newpost = row7.getCell(1).getStringCellValue();
            String wantpost = row8.getCell(1).getStringCellValue();
            String falsepost = row9.getCell(1).getStringCellValue();
            String resume = row10.getCell(1).getStringCellValue();
            String punishaward = row11.getCell(1).getStringCellValue();
            String annualass = row12.getCell(1).getStringCellValue();
            String reason = row13.getCell(1).getStringCellValue();

            CadreDatail cadreDatail = new CadreDatail();
            cadreDatail.setCadre_name(name);
            cadreDatail.setBirth(birth);
            cadreDatail.setSex(sex);
            cadreDatail.setNation(nation);
            cadreDatail.setNativeplace(nativeplace);
            cadreDatail.setBrithplace(brithplace);
            cadreDatail.setNativetytime(nativetytime);
            cadreDatail.setWorktime(worktime);
            cadreDatail.setHealth(health);
            cadreDatail.setMajorpost(majorpost);
            cadreDatail.setExpertise(expertise);
            cadreDatail.setSeducation(seducation);
            cadreDatail.setSeducationdetail(seducationdetail);
            cadreDatail.setWeducation(weducation);
            cadreDatail.setWeducationdetail(weducationdetail);
            cadreDatail.setNewpost(newpost);
            cadreDatail.setWantpost(wantpost);
            cadreDatail.setFalsepost(falsepost);
            cadreDatail.setResume(resume);
            cadreDatail.setPunishaward(punishaward);
            cadreDatail.setAnnualass(annualass);
            cadreDatail.setReason(reason);

            cadreDatailService.updateByNameJob(cadreDatail);
            cadreService.updateHuseCadreJob(cadreDatail.getNewpost(),cadreDatail.getCadre_name());
        ajaxResult.setRes(true);
        return ajaxResult;
    }
}
