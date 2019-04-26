package com.huse.pojo;

public class Cadre {
    private Integer id;

    private String cadreName;

    private String job;


    private Boolean state;

    private String password;

    private String role;

    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "id=" + id +
                ", cadreName='" + cadreName + '\'' +
                ", job='" + job + '\'' +
                ", state=" + state +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}