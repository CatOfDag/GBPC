package com.huse.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.Integer;


public class CadreDatail implements Serializable {
    private Integer id;

    private String cadre_name;

    private String sex;

    private String birth;

    private String nation;

    private String nativeplace;

    private String brithplace;

    private String nativetytime;

    private String worktime;

    private String health;

    private String majorpost;

    private String expertise;

    private String seducation;

    private String seducationdetail;

    private String weducation;

    private String weducationdetail;

    private String newpost;

    private String wantpost;

    private String falsepost;

    private String resume;

    private String punishaward;

    private String annualass;

    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCadre_name() {
        return cadre_name;
    }

    public void setCadre_name(String cadre_name) {
        this.cadre_name = cadre_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getBrithplace() {
        return brithplace;
    }

    public void setBrithplace(String brithplace) {
        this.brithplace = brithplace;
    }

    public String getNativetytime() {
        return nativetytime;
    }

    public void setNativetytime(String nativetytime) {
        this.nativetytime = nativetytime;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getMajorpost() {
        return majorpost;
    }

    public void setMajorpost(String majorpost) {
        this.majorpost = majorpost;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getSeducation() {
        return seducation;
    }

    public void setSeducation(String seducation) {
        this.seducation = seducation;
    }

    public String getSeducationdetail() {
        return seducationdetail;
    }

    public void setSeducationdetail(String seducationdetail) {
        this.seducationdetail = seducationdetail;
    }

    public String getWeducation() {
        return weducation;
    }

    public void setWeducation(String weducation) {
        this.weducation = weducation;
    }

    public String getWeducationdetail() {
        return weducationdetail;
    }

    public void setWeducationdetail(String weducationdetail) {
        this.weducationdetail = weducationdetail;
    }

    public String getNewpost() {
        return newpost;
    }

    public void setNewpost(String newpost) {
        this.newpost = newpost;
    }

    public String getWantpost() {
        return wantpost;
    }

    public void setWantpost(String wantpost) {
        this.wantpost = wantpost;
    }

    public String getFalsepost() {
        return falsepost;
    }

    public void setFalsepost(String falsepost) {
        this.falsepost = falsepost;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPunishaward() {
        return punishaward;
    }

    public void setPunishaward(String punishaward) {
        this.punishaward = punishaward;
    }

    public String getAnnualass() {
        return annualass;
    }

    public void setAnnualass(String annualass) {
        this.annualass = annualass;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CadreDatail{" +
                "id=" + id +
                ", cadre_name='" + cadre_name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", nation='" + nation + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", brithplace='" + brithplace + '\'' +
                ", nativetytime='" + nativetytime + '\'' +
                ", worktime='" + worktime + '\'' +
                ", health='" + health + '\'' +
                ", majorpost='" + majorpost + '\'' +
                ", expertise='" + expertise + '\'' +
                ", seducation='" + seducation + '\'' +
                ", seducationdetail='" + seducationdetail + '\'' +
                ", weducation='" + weducation + '\'' +
                ", weducationdetail='" + weducationdetail + '\'' +
                ", newpost='" + newpost + '\'' +
                ", wantpost='" + wantpost + '\'' +
                ", falsepost='" + falsepost + '\'' +
                ", resume='" + resume + '\'' +
                ", punishaward='" + punishaward + '\'' +
                ", annualass='" + annualass + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}