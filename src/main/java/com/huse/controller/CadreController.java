package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.service.CadreService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import com.huse.utils.MyException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/*参评干部控制器,管理参评干部的相关的业务*/
@Controller
public class CadreController {

    @Autowired
    private CadreService cadreService;
    @Autowired
    private AjaxResult ajaxResult;

    @GetMapping("cadre/cadrelist")
    public String cadrePage() {
        return "cadrePage/cadre";
    }

    @GetMapping("cadre/add")
    public String addCadre() {
        return "cadrePage/cadre-add";
    }

    @GetMapping("cadre/edit")
    public String editCadre(int id, ModelMap mp) {
        Cadre cadre = cadreService.selectByPrimaryKey(id);
        mp.addAttribute("cadre", cadre);
        return "cadrePage/cadre-edit";
    }

    @RequestMapping("cadre/getCadreLists")
    @ResponseBody
    public Laytable getCadreLists(int page, int limit, String info) {
        Laytable laytable = new Laytable();
        laytable.setCode(0);
        laytable.setMsg("");
        if (info == null || info.equals("")){
            int startRow = (page - 1) * limit;
            List<Cadre> cadreList = cadreService.getCadreList(startRow, limit);
            laytable.setData(cadreList);
            laytable.setCount(cadreService.count());
        }else {
            List<Cadre> cadres = cadreService.fuzzyQuery(info);
            laytable.setData(cadres);
        }

        return laytable;
    }

    @RequestMapping("cadre/addCadre")
    @ResponseBody
    public AjaxResult addCadre(Cadre cadre) {
        final int insert = cadreService.insert(cadre);
        boolean flag = insert > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;

    }

    @RequestMapping("cadre/updateByPK")
    @ResponseBody
    public AjaxResult updateByPK(Cadre cadre) {
        int i = cadreService.updateByPrimaryKeySelective(cadre);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/deleteByPK")
    @ResponseBody
    @Transactional
    public AjaxResult deleteByPK(int id) {
        System.out.println("要删除的id是"+id);
        int i = cadreService.deleteByPrimaryKey(id);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/batchImport")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file,ModelMap mp) throws IOException, MyException {
        String fileName = file.getOriginalFilename();
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

        Sheet sheet = wb.getSheetAt(0);
        for (int i = 2; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);//获取索引为i的行，以2开始
            String cadreName= row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            String password = row.getCell(1).getStringCellValue();
            String job = row.getCell(2).getStringCellValue();
            int tempstate = (int) row.getCell(4).getNumericCellValue();
            String desc = row.getCell(6).getStringCellValue();
            String role = row.getCell(3).getStringCellValue();
            String avoteLias = row.getCell(5).getStringCellValue();
            boolean state = tempstate==1 ? true :false;
            Cadre cadre = new Cadre();
            cadre.setAvoteLias(avoteLias);
            cadre.setCadreName(cadreName);
            cadre.setDesc(desc);
            cadre.setJob(job);
            cadre.setState(state);
            cadre.setRole(role);
            cadre.setPassword(password);
            cadresLists.add(cadre);
            System.out.println("第"+i+"条的信息"+cadre.toString());
        }
        for (Cadre import_cadre: cadresLists) {
            cadreService.insert(import_cadre);
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }

    @RequestMapping("cadre/cadreInfo")
    public String cadreInfo(Integer id,ModelMap mmp){
        System.out.println(id);
        Cadre cadre = cadreService.selectByPrimaryKey(id);
        mmp.addAttribute("cadre",cadre);
        return "cadrePage/cadre-info";
    }
}
