package net.xinqushi.wechat.web.dao;

import net.xinqushi.wechat.pojo.SummaryVisitHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SummaryVisitHistoryDao {

    /** 查找是否有过阅读记录 */
    @Select("select * from summary_visit_history where summaryId=#{summaryId} and openId=#{openId}")
    List<SummaryVisitHistory> selectHistoryBySummaryIdAndOpenId(@Param("summaryId")Integer summaryId,@Param("openId")String openId);

    /** 查找某个周报的阅读数 */
    @Select("select count(*) from summary_visit_history where summaryId=#{summaryId}")
    int selectReadNum(Integer summaryId);

    /** 插入阅读记录 */
    @Insert("insert into summary_visit_history (summaryId,openId,admiration) values(#{summaryId},#{openId},#{admiration})")
    int insertVisitHistory(SummaryVisitHistory visitHistory);

    /** 查找某个周报的点赞数 */
    @Select("select count(*) from summary_visit_history where summaryId=#{summaryId} and admiration=1")
    int selectAdmirationNum(Integer summaryId);

    /** 点赞 */
    @Update("update summary_visit_history set admiration=1 where summaryId=#{summaryId} and openId=#{openId}")
    int doAdmiration(@Param("summaryId")Integer summaryId,@Param("openId")String openId);

    /** 取消点赞 */
    @Update("update summary_visit_history set admiration=0 where summaryId=#{summaryId} and openId=#{openId}")
    int cancelAdmiration(@Param("summaryId")Integer summaryId,@Param("openId")String openId);

    /** 获取当前点赞状态 */
    @Select("select admiration from summary_visit_history where summaryId=#{summaryId} and openId=#{openId}")
    int selectAdmiration(@Param("summaryId")Integer summaryId, @Param("openId")String openId);
}
