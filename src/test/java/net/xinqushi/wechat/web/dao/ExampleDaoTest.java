package net.xinqushi.wechat.web.dao;

import lombok.extern.slf4j.Slf4j;
import net.xinqushi.wechat.pojo.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ExampleDaoTest {
@Autowired
    ExampleDao exampleDao;
    @Test
    public void selectById() {
//        log.info("data:{}",exampleDao.selectById(99).toString());
    }
}