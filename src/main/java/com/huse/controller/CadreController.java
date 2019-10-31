package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.pojo.CadreDatail;
import com.huse.pojo.Info;
import com.huse.pojo.Vote;
import com.huse.service.CadreDatailService;
import com.huse.service.CadreService;
import com.huse.service.InfoService;
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
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/*参评干部控制器,管理参评干部的相关的业务*/
@Controller
public class CadreController {

    @Autowired
    private CadreService cadreService;
    @Autowired
    private CadreDatailService cadreDatailService;
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private InfoService infoService;
    @Autowired
    private VoteService voteService;

    @GetMapping("cadre/cadrelist")
    public String cadrePage() {
        return "cadrePage/cadre";
    }


    @GetMapping("cadre/add")
    public String addCadre(ModelMap mmp) {
        List<Vote> votes = voteService.selectAllVote();
        mmp.addAttribute("votes", votes);
        return "cadrePage/cadre-add";
    }

    @GetMapping("cadre/edit")
    public String editCadre(int id, ModelMap mp) {
        //根据id查询干部对象
        Cadre cadre = cadreService.selectByPrimaryKey(id);
        mp.addAttribute("cadre", cadre);
        //查询所有的投票
        List<Vote> votes = voteService.selectAllVote();
        mp.addAttribute("votes", votes);
        return "cadrePage/cadre-edit";
    }

    @RequestMapping("cadre/getCadreLists")
    @ResponseBody
    public Laytable getCadreLists(int page, int limit, String info) {
        Laytable laytable = new Laytable();
        laytable.setCode(0);
        laytable.setMsg("");
        if (info == null || info.equals("")) {
            int startRow = (page - 1) * limit;
            List<Cadre> cadreList = cadreService.getCadreList(startRow, limit);
            laytable.setData(cadreList);
            laytable.setCount(cadreService.count());
        } else {
            List<Cadre> cadres = cadreService.fuzzyQuery(info);
            laytable.setData(cadres);
        }

        return laytable;
    }

    @RequestMapping("cadre/addCadre")
    @ResponseBody
    public AjaxResult addCadre(Cadre cadre) {
        System.out.println("add");
        final int insert = cadreService.insert(cadre);
        boolean flag = insert > 0 ? true : false;//控制是否输入错误
        ajaxResult.setRes(flag);
        System.out.println(ajaxResult);
        return ajaxResult;
    }

    @RequestMapping("cadre/updateByPK")
    @ResponseBody
    public AjaxResult updateByPK(Cadre cadre) {
        System.out.println("upadateByPK");
        int i = cadreService.updateByPrimaryKeySelective(cadre);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/deleteByPK")
    @ResponseBody
    @Transactional
    public AjaxResult deleteByPK(int id) {
        System.out.println("要删除的id是" + id);
        int i = cadreService.deleteByPrimaryKey(id);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/batchImport")
    @ResponseBody
    public AjaxResult batchImport(MultipartFile file, ModelMap mp) throws IOException, MyException {
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
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以2开始
            String cadreName = row.getCell(0).getStringCellValue();//获取第i行的索引为0的单元格数据
            String password = row.getCell(1).getStringCellValue();
            String job = row.getCell(2).getStringCellValue();
            int tempstate = (int) row.getCell(4).getNumericCellValue();
            String desc = row.getCell(6).getStringCellValue();
            String role = row.getCell(3).getStringCellValue();
            String avoteLias = row.getCell(5).getStringCellValue();
            boolean state = tempstate == 1 ? true : false;
            Cadre cadre = new Cadre();
            cadre.setAvoteLias(avoteLias);
            cadre.setCadreName(cadreName);
            cadre.setDesc(desc);
            cadre.setJob(job);
            cadre.setState(state);
            cadre.setRole(role);
            cadre.setPassword(password);
            cadresLists.add(cadre);
            System.out.println("第" + i + "条的信息" + cadre.toString());
        }
        for (Cadre import_cadre : cadresLists) {
            cadreService.insert(import_cadre);
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }

    @RequestMapping(value = "cadre/cadreInfo")
    public String cadreInfo(ModelMap mmp) {
        //获取当前登录对象
        Subject sub = SecurityUtils.getSubject();
        Cadre cadre = (Cadre) sub.getPrincipal();
        CadreDatail cadreDatail = cadreDatailService.selectByPrimaryKey(cadre.getId());
        mmp.addAttribute("cadre", cadreDatail);
        if(cadreDatail == null) {
            return "errorPage/Unedited_pages.html";
        }
        else{
            Info cadreInfo = infoService.selectByCadreName(cadre.getCadreName());
            System.out.println("cadreInfo:"+cadreInfo);
            if (cadreDatail == null && cadreInfo == null) {
                return "errorPage/Unedited_pages.html";
            }
            else{
                mmp.addAttribute("cadreInfo", cadreInfo);
                return "cadrePage/cadre-info";
            }
        }
    }

    @RequestMapping("cadre/cadreInfoEdit")
    public String cadreInfoEdit(String cadreName, String job, ModelMap mmp) {
        //将名字和职务传递到cadreInfoEdit页面
        mmp.addAttribute("cadreName", cadreName);
        mmp.addAttribute("job", job);
        //如果表中没有记录,新建一条记录
        Info info = infoService.selectByCadreName(cadreName);
        if (info == null) {
            infoService.insertCadreName(cadreName);
        } else {
            mmp.addAttribute("infoDesc", info.getInfo());
        }

        return "cadrePage/cadre-infoEdit";
    }

    //文件的上传
    @RequestMapping("cadre/upload")
    @ResponseBody
    @Transactional
    public AjaxResult upload(MultipartFile file, String cadreName) {
        Info info = new Info();
        info.setCardName(cadreName);
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        /*产生一个8位的UUID,将文件名称改为UUID,可以直接用姓名（不会出现报错）
        String uuid = UUID.randomUUID().toString().substring(0, 8);*/
       //最终文件名称
        String saveName = cadreName + suffix;
        //上传的如果是图片
        if (suffix.equals(".jpg") || suffix.equals(".png")) {
//            String picPath = "E:/HUSEFile/headportrait/" + saveName;//如果当前文件已经存在则会覆盖旧的.
            /*String picPath = "/root/usr/local/HUSEFile/headportrait/" + saveName;//如果当前文件已经存在则会覆盖旧的.*/
            String picPath = "E:\\Windows\\Users\\Documents\\Tencent Files\\3223646214\\FileRecv\\HUSE\\HUSE\\src\\main\\resources\\static\\headportrait\\" + saveName;//如果当前文件已经存在则会覆盖旧的.
            try {
                file.transferTo(new File(picPath));
                info.setHeaderPic(saveName);
                infoService.updateByCadreName(info);
            } catch (IOException e) {
                ajaxResult.setRes(false);
                System.out.println("上传失败");
            }
        }
        //如果上传的是doc或docx文件
        if (suffix.equals(".doc") || suffix.equals(".docx")) {
//            String docPath = "E:/HUSEFile/doc/" + saveName;
            String docPath = "/root/usr/local/HUSEFile/doc/" + saveName;
            try {
                file.transferTo(new File(docPath));
                info.setFilePath(saveName);
                infoService.updateByCadreName(info);
            } catch (IOException e) {
                ajaxResult.setRes(false);
                System.out.println("上传失败");
            }
        }
        ajaxResult.setRes(true);
        return ajaxResult;
    }


    @RequestMapping("cadre/updateInfo")
    @ResponseBody
    public AjaxResult updateInfo(String info, String cadreName) {
        Info infoDomain = new Info();
        infoDomain.setInfo(info);
        infoDomain.setCardName(cadreName);
        int i = infoService.updateByCadreName(infoDomain);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

    @RequestMapping("cadre/batchDeletion")
    @ResponseBody
    @Transactional
    public AjaxResult batchDeletion(@RequestBody(required = false)List<Integer> ids){
        System.out.println("开始执行...");
        for (Integer id : ids) {
            System.out.println(id);
            cadreService.deleteByPrimaryKey(id);
        }
        ajaxResult.setRes(true);
        ajaxResult.setInfo("删除成功");
        return ajaxResult;
    }
}
