package com.huse.controller;

import com.huse.pojo.Cadre;
import com.huse.service.CadreService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
