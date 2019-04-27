package com.huse.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Participant {
    private Integer id;

    private String pin;

    private String role;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date endtime;

    private Boolean state;

    private String voteAlias;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getVoteAlias() {
        return voteAlias;
    }

    public void setVoteAlias(String voteAlias) {
        this.voteAlias = voteAlias == null ? null : voteAlias.trim();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", pin='" + pin + '\'' +
                ", role='" + role + '\'' +
                ", endtime=" + endtime +
                ", state=" + state +
                ", voteAlias='" + voteAlias + '\'' +
                '}';
    }
}