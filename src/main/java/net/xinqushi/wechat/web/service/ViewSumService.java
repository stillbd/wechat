package net.xinqushi.wechat.web.service;

import com.github.pagehelper.PageInfo;
import net.xinqushi.wechat.model.PageResult;
import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.result.Result;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

public interface ViewSumService {

    //获取周报列表
    PageInfo<ViewSum> getSumLis(Integer pageNum, Integer pageSize);
    //获取周报内容
    ViewSum getContentById(Integer id);
    //获取某一周的周报

    /**
     *
     * @param week   周报名
     * @param pageNum  页码
     * @param pageSize  页大小
     * @param sortField  排序字段
     * @param sortWay    排序方式
     * @param session
     * @return
     */
   PageResult getSummaryWeekPage(String week, int pageNum, int pageSize, String sortField, String sortWay, HttpSession session);
   //获取周报评论列表
    Result getCommentsBySummaryId(int summaryId);
    // 添加评论
    Result addComment( ViewSum comment, HttpSession session);
    //添加或者更新周报
    Result saveOrUpdateSummary(ViewSum viewSum,HttpSession session) throws ParseException;
    //获取被推荐的周报
    Result  getSummaryRecommend(String title,HttpSession session);
   /**
    *@created   23:58 2018/8/23
    *@author    wangwei
    *@params    
    *@return    根据审核状态获取周报列表

   */
    Result  getSummaryChecked(String title,int isCheck);

    /**
     *
     * @param session
     * @return
     * 获取当前登录用户本周周报
     */
    Result<ViewSum> getSelfSummaryCurrentWeek(HttpSession session);
}
