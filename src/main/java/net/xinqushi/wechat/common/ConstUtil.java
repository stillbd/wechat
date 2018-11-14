package net.xinqushi.wechat.common;

/**
 * 此类集中定义相关常量
 */
public class ConstUtil {



    //session中key,SESSION_
    public static final String SESSION_USER_INFO = "USERINFO";//登陆后保存的登陆用户信息
    public static final String SESSION_SUMMARY_TITLE_SHOWING = "SUMMARYTITLESHOWING";//正在查看的周报标题
    public static final String SESSION_SUMMARY_LIST_PAGENUM = "SUMMARYPAGENUM";

    //前端提示信息相关MSG_


    //当前周，上一周，下一周
    public static final String CURRENT_WEEK="currentWeek";
    public static final String PREVIOUS_WEEK="previousWeek";
    public static final String NEXT_WEEK="nextWeek";

   //数据库相关状态常量
       // 1,周报相关
    public static final int  STATUS_SUMMARY_IS_RECOMMENDED = 1;//被推荐
    public static final int  STATUS_SUMMARY_NORMAL = -1;//所有，包含被推荐和不被推荐
    public static final int  STATUS_SUMMARY_IS_CHECKED = 1;//已审核
    public static final int  STATUS_SUMMARY_NOT_CHECKED = 0;//未审核
    public static final int STATUS_SUMMARY_IS_SUMMARY = 0;//表示非评论
}
