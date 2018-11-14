package net.xinqushi.wechat.pojo;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String pwd;
    private String salt;
    //注册时间
    private Date time;
    //公告查看时间（为空则提示有新公告）
    private Date noticeTime;
    private int root;
    //微信openId
    private String openId;

    public int getId() {
        return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", time=" + time +
                ", noticeTime=" + noticeTime +
                ", root=" + root +
                ", openId='" + openId + '\'' +
                '}';
    }
}
