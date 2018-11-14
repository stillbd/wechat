package net.xinqushi.wechat.web.service.impl;

import net.xinqushi.wechat.pojo.SummaryVisitHistory;
import net.xinqushi.wechat.web.dao.SummaryVisitHistoryDao;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.SummaryVisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *周报访问记录操作
 */
@Service
public class SummaryVisitHistoryServiceImpl implements SummaryVisitHistoryService {

    @Autowired
    private SummaryVisitHistoryDao visitHistoryDao;

    @Override
    public Result checkReaded(Integer summaryId, String openId) {
        List<SummaryVisitHistory> list = visitHistoryDao.selectHistoryBySummaryIdAndOpenId(summaryId, openId);
        if (list.size()>0){
            return Result.error(CodeMsg.MSG_VISIT_IS_EXISTS);
        }else {
            return Result.success(true);
        }
    }

    @Override
    public Result addVisitHistory(SummaryVisitHistory visitHistory) {
        //初始点赞状态为未点赞
        visitHistory.setAdmiration(0);
        int i = visitHistoryDao.insertVisitHistory(visitHistory);
        if(i==1){
            return Result.success(true);
        }else{
            return Result.error(CodeMsg.MSG_VISIT_ADD_ERROR);
        }
    }

    @Override
    public Result updateAdmiration(Integer summaryId, String openId) {
        int admiration = visitHistoryDao.selectAdmiration(summaryId, openId);
        if(admiration==1){
            int i = visitHistoryDao.cancelAdmiration(summaryId, openId);
            return i>0?Result.success(0):Result.error(CodeMsg.MSG_VISIT_ADD_ERROR);
        }else if (admiration==0){
            int i = visitHistoryDao.doAdmiration(summaryId, openId);
            return i>0?Result.success(1):Result.error(CodeMsg.MSG_VISIT_ADD_ERROR);
        }else {
            return Result.error(CodeMsg.MSG_USER_NO_OPENID);
        }
    }

    @Override
    public Result getAdmiration(Integer summaryId, String openId) {
        int i = visitHistoryDao.selectAdmiration(summaryId, openId);
        if(i==0){
            //0是未点赞
            return Result.success(0);
        }else if (i==1){
            //1是已点赞
            return Result.success(1);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
