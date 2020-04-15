package com.huse.pojo;

import java.io.Serializable;


public class CadreDatail implements Serializable {
    private Integer id;
    private String vote_alia;
    private String cadre_name;
    private String sex;
    private String birth;
    private String nation;
    private String nativeplace;
    private String health;
    private String politicalface;
    private String worktime;
    private String majorpost;
    private String seducation;
    private String seducationdetail;
    private String weducation;
    private String weducationdetail;
    private String newpost;
    private String appointtime;
    private String resume;
    private String punishaward;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVote_alia() {
        return vote_alia;
    }

    public void setVote_alia(String vote_alia) {
        this.vote_alia = vote_alia;
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

    public String  getBirth() {
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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPoliticalface() {
        return politicalface;
    }

    public void setPoliticalface(String politicalface) {
        this.politicalface = politicalface;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getMajorpost() {
        return majorpost;
    }

    public void setMajorpost(String majorpost) {
        this.majorpost = majorpost;
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

    public String getAppointtime() {
        return appointtime;
    }

    public void setAppointtime(String appointtime) {
        this.appointtime = appointtime;
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

    public CadreDatail(Integer id,String vote_alia, String cadre_name, String newpost) {
        this.id = id;
        this.vote_alia = vote_alia;
        this.cadre_name = cadre_name;
        this.newpost = newpost;
        this.sex = null;
        this.birth = null;
        this.nation = null;
        this.nativeplace = null;
        this.health = null;
        this.politicalface = null;
        this.worktime = null;
        this.majorpost = null;
        this.seducation = null;
        this.seducationdetail = null;
        this.weducation = null;
        this.weducationdetail = null;
        this.appointtime = null;
        this.resume = null;
        this.punishaward = null;
    }
    public CadreDatail() {
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
                ", health='" + health + '\'' +
                ", politicalface='" + politicalface + '\'' +
                ", worktime='" + worktime + '\'' +
                ", majorpost='" + majorpost + '\'' +
                ", seducation='" + seducation + '\'' +
                ", seducationdetail='" + seducationdetail + '\'' +
                ", weducation='" + weducation + '\'' +
                ", weducationdetail='" + weducationdetail + '\'' +
                ", newpost='" + newpost + '\'' +
                ", appointtime='" + appointtime + '\'' +
                ", resume='" + resume + '\'' +
                ", punishaward='" + punishaward + '\'' +
                '}';
    }
}