package com.huse.pojo;

public class Admin {
    private Integer id;
    private String adminName;
    private String password;
    private String phone;
    private Boolean state;
    private String desc;
    private String role;

    public Admin() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getState() {
        return this.state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String toString() {
        return "Admin{id=" + this.id + ", adminName='" + this.adminName + '\'' + ", password='" + this.password + '\''
                + ", phone='" + this.phone + '\'' + ", state=" + this.state + ", desc='" + this.desc + '\'' + ", role='"
                + this.role + '\'' + '}';
    }
}