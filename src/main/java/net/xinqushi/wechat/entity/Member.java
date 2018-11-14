package net.xinqushi.wechat.entity;

import java.util.Date;

public class Member {
    private Integer id;

    private Integer uid;

    private String name;

    private String sex;

    private String school;

    private String company;

    private String mobile;

    private Boolean student;

    private Date graduatedate;

    private Date time;

    private Boolean abnormal;

    private Integer provid;

    private Boolean flag;

    private Integer alog;

    private Float restamount;

    private Float restinterest;

    private Boolean fee;

    private Integer aid;

    private Integer eid;

    private Boolean summaryflag;

    private Integer seatProvid;

    private Integer periodstatus;

    private Integer schproid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public Date getGraduatedate() {
        return graduatedate;
    }

    public void setGraduatedate(Date graduatedate) {
        this.graduatedate = graduatedate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(Boolean abnormal) {
        this.abnormal = abnormal;
    }

    public Integer getProvid() {
        return provid;
    }

    public void setProvid(Integer provid) {
        this.provid = provid;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getAlog() {
        return alog;
    }

    public void setAlog(Integer alog) {
        this.alog = alog;
    }

    public Float getRestamount() {
        return restamount;
    }

    public void setRestamount(Float restamount) {
        this.restamount = restamount;
    }

    public Float getRestinterest() {
        return restinterest;
    }

    public void setRestinterest(Float restinterest) {
        this.restinterest = restinterest;
    }

    public Boolean getFee() {
        return fee;
    }

    public void setFee(Boolean fee) {
        this.fee = fee;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Boolean getSummaryflag() {
        return summaryflag;
    }

    public void setSummaryflag(Boolean summaryflag) {
        this.summaryflag = summaryflag;
    }

    public Integer getSeatProvid() {
        return seatProvid;
    }

    public void setSeatProvid(Integer seatProvid) {
        this.seatProvid = seatProvid;
    }

    public Integer getPeriodstatus() {
        return periodstatus;
    }

    public void setPeriodstatus(Integer periodstatus) {
        this.periodstatus = periodstatus;
    }

    public Integer getSchproid() {
        return schproid;
    }

    public void setSchproid(Integer schproid) {
        this.schproid = schproid;
    }
}