package net.xinqushi.wechat.model;

import com.github.pagehelper.PageInfo;

public class PageResult  {
    private  String title;
    private PageInfo pageInfo;

    public PageResult(String title, PageInfo pageInfo) {
        this.title = title;
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
