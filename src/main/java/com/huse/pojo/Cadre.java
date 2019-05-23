package com.huse.pojo;

import java.io.Serializable;

public class Cadre implements Serializable {
    private Integer id;

    private String cadreName;

    private String job;

    private Boolean state;

    private String desc;

    private String password;

    private String role;

    private String avoteLias;//fuck 笔误

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCadreName() {
        return cadreName;
    }

    public void setCadreName(String cadreName) {
        this.cadreName = cadreName == null ? null : cadreName.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getAvoteLias() {
        return avoteLias;
    }

    public void setAvoteLias(String avoteLias) {
        this.avoteLias = avoteLias == null ? null : avoteLias.trim();
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "id=" + id +
                ", cadreName='" + cadreName + '\'' +
                ", job='" + job + '\'' +
                ", state=" + state +
                ", desc='" + desc + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", avoteLias='" + avoteLias + '\'' +
                '}';
    }
}