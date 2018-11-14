package net.xinqushi.wechat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
*@author wangwei
*@created 0:12 2018/8/23
 *@classname SummaryComment
*@classdescription 周报评论数据封装
*
*/
@Data
public class SummaryComment {
    private int commentId;//评价记录id
    private String observerName;//评论者姓名
    private String content;//评论内容
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date publishTime;//评论发表时间
}
