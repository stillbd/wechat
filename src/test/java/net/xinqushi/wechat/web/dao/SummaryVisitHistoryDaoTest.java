package net.xinqushi.wechat.web.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SummaryVisitHistoryDaoTest {
    @Autowired
    private SummaryVisitHistoryDao historyDao;

    @Test
    public void selectAdmirationNum() {
        int i = historyDao.selectAdmirationNum(1234567);
        System.out.println(i);
    }

    @Test
    public void doAdmiration() {
        Integer summaryId = 1234567;
        String openid = "qwd";
        int i = historyDao.doAdmiration(summaryId, openid);
        Assert.assertEquals(1,i);
    }

    @Test
    public void cancelAdmiration() {
        Integer summaryId = 1234567;
        String openid = "qwd";
        int i = historyDao.cancelAdmiration(summaryId, openid);
        Assert.assertEquals(1,i);
    }

    @Test
    public void selectAdmiration() {
        Integer summaryId = 1234567;
        String openid = "qwd";
        int i = historyDao.selectAdmiration(summaryId, openid);
        System.out.println(i);

    }
}