package net.xinqushi.wechat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class ViewSum {

    private Integer summaryId;//周报表Id，用来查找周报具体内容

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;//发布周报时间

    private String title;//周报的具体日期

    private String content;//周报内容

    private int isCheck;//是否审核？0：否；1：是,

    private int recommend;//是否推荐？0：否；1：是

    private String name;//会员姓名
    private String userName;//会员编号
    private int memberId;//会员id
    private int pId;//pId指被评论的周报id

    private int readNum;//阅读数
    private int admirationNum;//点赞数


}
