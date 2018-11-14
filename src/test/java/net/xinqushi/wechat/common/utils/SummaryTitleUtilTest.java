package net.xinqushi.wechat.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;
@Slf4j
public class SummaryTitleUtilTest {

    @Test
    public void getTitleSec() {
    }

    @Test
    public void getPreWeekTitle() {
        log.info("{}",SummaryTitleUtil.getPreWeekTitle());
    }

    @Test
    public void getPreWeekTitleSec() {
    }

    @Test
    public void getPreWeekTitleByTitle() {
    }

    @Test
    public void getNextWeekTitle() {
    }

    @Test
    public void getNextWeekTitleSec() {
    }

    @Test
    public void getWriteTitle() throws ParseException {
        log.info(SummaryTitleUtil.getWriteTitle());
    }

    @Test
    public void getWriteTitle1() {
    }
}