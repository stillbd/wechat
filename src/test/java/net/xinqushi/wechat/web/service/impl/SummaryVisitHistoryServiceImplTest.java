package net.xinqushi.wechat.web.service.impl;

import net.xinqushi.wechat.pojo.SummaryVisitHistory;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.SummaryVisitHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SummaryVisitHistoryServiceImplTest {

    @Autowired
    private SummaryVisitHistoryService historyService;

    @Test
    public void checkReaded() {
    }

    @Test
    public void addVisitHistory() {
        SummaryVisitHistory history = new SummaryVisitHistory();
        history.setOpenId("ccc");
        history.setSummaryId(1234567);
        Result result = historyService.addVisitHistory(history);
        System.out.println(result.getMsg());

    }

    @Test
    public void updateAdmiration() {
    }

    @Test
    public void getAdmiration() {
    }
}