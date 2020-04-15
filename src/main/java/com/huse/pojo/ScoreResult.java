package com.huse.pojo;
/*对于cadreName得数总成绩的数据类*/
public class ScoreResult {
    private Integer id;

    private String vote_alia;

    private String cadreName;

    private String job;

    private String role;

    private Double leadervotescore; //领导人投票成绩的总分

    private Double othervotescore; //除领导人之外的人员投票成绩的总分

    private Double finalyvotescore; //总成绩

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getLeadervotescore() {
        return leadervotescore;
    }

    public void setLeadervotescore(Double leadervotescore) {
        this.leadervotescore = leadervotescore;
    }

    public Double getOthervotescore() {
        return othervotescore;
    }

    public void setOthervotescore(Double othervotescore) {
        this.othervotescore = othervotescore;
    }

    public Double getFinalyvotescore() {
        return finalyvotescore;
    }

    public void setFinalyvotescore(Double finalyvotescore) {
        this.finalyvotescore = finalyvotescore;
    }

    public ScoreResult(Integer id, String vote_alia, String cadreName, String job, String role, Double leadervotescore, Double othervotescore, Double finalyvotescore) {
        this.id = id;
        this.vote_alia = vote_alia;
        this.cadreName = cadreName;
        this.job = job;
        this.role = role;
        this.leadervotescore = leadervotescore;
        this.othervotescore = othervotescore;
        this.finalyvotescore = finalyvotescore;
    }

    public ScoreResult(Integer id, String vote_alia, String cadreName, String job, String role) {
        this.id = id;
        this.vote_alia = vote_alia;
        this.cadreName = cadreName;
        this.job = job;
        this.role = role;
        this.leadervotescore = 0.0;
        this.othervotescore = 0.0;
        this.finalyvotescore = 0.0;
    }

    @Override
    public String toString() {
        return "ScoreResult{" +
                "id=" + id +
                ", vote_alia='" + vote_alia + '\'' +
                ", cadreName='" + cadreName + '\'' +
                ", job='" + job + '\'' +
                ", role='" + role + '\'' +
                ", leadervotescore=" + leadervotescore +
                ", othervotescore=" + othervotescore +
                ", finalyvotescore=" + finalyvotescore +
                '}';
    }
}