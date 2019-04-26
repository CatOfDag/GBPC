package com.huse.controller;

import com.huse.pojo.Participant;
import com.huse.service.ParticipantService;
import com.huse.utils.AjaxResult;
import com.huse.utils.Laytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String editParticipant() {
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

    @RequestMapping("participant/deleteParticipant")
    @ResponseBody
    public AjaxResult deleteParticipant(int id) {
        int i = participantService.deleteByPrimaryKey(id);
        boolean flag = i > 0 ? true : false;
        ajaxResult.setRes(flag);
        return ajaxResult;
    }

}
