package net.xinqushi.wechat.web.dao;

import net.xinqushi.wechat.pojo.SummaryComment;
import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.result.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.List;

//@Mapper

public interface ViewSumDao {

    List<ViewSum> selectViewSummeryAll(@Param("orderByClause") String orderByClause );

    ViewSum getViewSummeryById(Integer id);

    List<ViewSum> selectViewSummeryLike(@Param("likeField") String likeField, @Param("likeValue") String likeValue, @Param("orderByClause") String orderByClause);

    List<ViewSum> selectForWeek(@Param("week") String week,@Param("orderByClause") String orderByClause);
    //
    List<SummaryComment> selectCommentBySummaryId(@Param("summaryId") int summaryId);
    /**
     *@created   22:08 2018/8/23
     *@author    wangwei
     *@params
     *@return
     *@todo       发表评论

    */
    int  addComment(ViewSum comment);
    /**
     *@created   22:09 2018/8/23
     *@author    wangwei
     *@params
     *@return
     *@todo      发表周报

    */
    int  saveSummary(ViewSum viewSum);
    /**
     *@created   22:09 2018/8/23
     *@author    wangwei
     *@params    
     *@return    
     *@todo    更新周报
     
    */
    int  updateSummary(ViewSum origin);
  /**
   *@created   22:08 2018/8/23
   *@author    wangwei
   *@params    
   *@return    
   *@todo    根据是否被推荐获取周报
   
  */
    List<ViewSum> getSummaryByTitleRecommend(@Param("title") String title,@Param("isRecommened") int isRecommended);
/**
 *@created   22:08 2018/8/23
 *@author    wangwei
 *@params    
 *@return    
 *@todo    根据是否被审核获取周报
 
*/
    List<ViewSum> getSummaryByTitleChecked(@Param("title") String title,@Param("isCheck") int isCheck);
/**
 *@created   0:15 2018/8/24
 *@author    wangwei
 *@params    
 *@return    根据会员id和标题获取周报
*/
    List<ViewSum> getByMemberIdAndTitle(@Param("title") String title, @Param("memberId") int memberId);
}
