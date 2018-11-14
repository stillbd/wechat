package net.xinqushi.wechat.pojo;

import lombok.Data;

@Data
public class SummaryVisitHistory {
    /** 主键id */
    private Integer id;
    /** 周报id */
    private Integer summaryId;
    /** 微信openid（每个人的微信号对应公众号的openid都是唯一的） */
    private String openId;
    /** 是否点赞（0：未点赞，1：点赞） */
    private Integer admiration;

}
