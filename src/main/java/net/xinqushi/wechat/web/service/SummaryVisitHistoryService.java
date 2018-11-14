package net.xinqushi.wechat.web.service;

import net.xinqushi.wechat.pojo.SummaryVisitHistory;
import net.xinqushi.wechat.web.result.Result;

public interface SummaryVisitHistoryService {
    Result checkReaded(Integer summaryId,String openId);
    Result addVisitHistory(SummaryVisitHistory visitHistory);
    Result updateAdmiration(Integer summaryId,String openId);
    Result getAdmiration(Integer summaryId,String openId);
}
