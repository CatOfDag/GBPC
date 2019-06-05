package com.huse.controller;

import com.huse.pojo.Admin;
import com.huse.service.AdminService;
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


/*管理员控制器,管理管理员相关的业务*/
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AjaxResult ajaxResult;

    @GetMapping("admin/userlist")
    public String userListPage() {
        return "adminPage/admin";
    }

    @GetMapping("admin/addPage")
    public String addAdminPage() {
        return "adminPage/admin-add";
    }

    @GetMapping("admin/editPage")
    public String adminEditPage(int id, ModelMap mp) {
        Admin admin = adminService.selectByPrimaryKey(id);
        mp.addAttribute("admin",admin);
        return "adminPage/admin-edit";
    }

    @GetMapping("admin/getAdminList")
    @ResponseBody
    public Laytable getAdminList(int page,int limit,String info){
        Laytable laytable = new Laytable();
        laytable.setCode(0);
        laytable.setMsg("");
        int startRow = (page-1)*limit;
        if (info == null || info.equals("")){
            List<Admin> adminList = adminService.getAdminList(startRow, limit);
            laytable.setCount(adminService.count());
            laytable.setData(adminList);
        }else {
            List<Admin> admins = adminService.fuzzyQuery(info);
            laytable.setData(admins);
        }
        return laytable;
    }

    @RequestMapping(value="admin/addAdmin")
    @ResponseBody
    @Transactional
    public AjaxResult addAdmin(Admin admin){
        System.out.println(admin.toString());
        adminService.insert(admin);
        ajaxResult.setRes(true);
        return ajaxResult;
    }
    @RequestMapping(value = "admin/updateByPK")
    @ResponseBody
    @Transactional
    public AjaxResult updateByPK(Admin admin){
        System.out.println(admin.toString());
        int i = adminService.updateByPrimaryKey(admin);
        System.out.println(i);
        if (i > 0){
            ajaxResult.setRes(true);
        }else {
            ajaxResult.setRes(false);
        }
        return ajaxResult;
    }

    @RequestMapping("admin/deleteByPK")
    @ResponseBody
    @Transactional
    public AjaxResult deleteByPK(int id){
        int i = adminService.deleteByPrimaryKey(id);
        Boolean flag = i>0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }
    @RequestMapping("admin/updateInfo")
    @ResponseBody
    public AjaxResult updateInfo(Admin admin){
        System.out.println(admin);
        int i = adminService.updateByPrimaryKey(admin);
        if (i>0){
            ajaxResult.setRes(true);
            ajaxResult.setInfo("修改成功,密码将在下一次登录时更新!");
        }else {
            ajaxResult.setInfo("修改失败,请重试!");
            ajaxResult.setRes(false);
        }
        return ajaxResult;
    }

}
