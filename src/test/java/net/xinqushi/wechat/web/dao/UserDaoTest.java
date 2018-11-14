package net.xinqushi.wechat.web.dao;

import lombok.extern.slf4j.Slf4j;
import net.xinqushi.wechat.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    public void selectByName() {
      User user=  userDao.selectByName("243");
      log.info("info:{}",user);
    }
    @Test
    public void deleteOpenId() {
      int i =  userDao.deleteOpenId("okYSy0qhKyqPi1uXj_pBShQdixLU");
      log.info("info:{}",i);
    }
//    @Test
    public void bindOpenId(){
        User user=  userDao.selectByName("243");
//        user.setOpenId("123");
        userDao.updateSelective(user);
    }
}