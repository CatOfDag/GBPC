package com.huse.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Score {
    private Integer id;

    private String pin;

    private String cadreName;

    private String alias;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date voteTime;

    private Integer virtue;

    private Integer ability;

    private Integer diligence;

    private Integer feats;

    private Integer honest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin == null ? null : pin.trim();
    }

    public String getCadreName() {
        return cadreName;
    }

    public void setCadreName(String cadreName) {
        this.cadreName = cadreName == null ? null : cadreName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public Integer getVirtue() {
        return virtue;
    }

    public void setVirtue(Integer virtue) {
        this.virtue = virtue;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getDiligence() {
        return diligence;
    }

    public void setDiligence(Integer diligence) {
        this.diligence = diligence;
    }

    public Integer getFeats() {
        return feats;
    }

    public void setFeats(Integer feats) {
        this.feats = feats;
    }

    public Integer getHonest() {
        return honest;
    }

    public void setHonest(Integer honest) {
        this.honest = honest;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", pin='" + pin + '\'' +
                ", cadreName='" + cadreName + '\'' +
                ", alias='" + alias + '\'' +
                ", voteTime=" + voteTime +
                ", virtue=" + virtue +
                ", ability=" + ability +
                ", diligence=" + diligence +
                ", feats=" + feats +
                ", honest=" + honest +
                '}';
    }
}