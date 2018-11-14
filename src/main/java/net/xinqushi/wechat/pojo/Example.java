package net.xinqushi.wechat.pojo;

import org.apache.ibatis.annotations.Mapper;


public class Example {
    private int id;
    private int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
