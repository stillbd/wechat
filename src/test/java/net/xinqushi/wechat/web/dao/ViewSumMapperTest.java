package net.xinqushi.wechat.web.dao;

import lombok.extern.slf4j.Slf4j;
import net.xinqushi.wechat.pojo.ViewSum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ViewSumMapperTest {

    @Autowired
    private ViewSumDao mapper;
//
    @Test
    public void  test(){
//        List<ViewSum> list = mapper.selectViewSummeryAll();
//        System.out.println(list);
//       log.info(mapper.getViewSummeryById(14126).toString());
    }
}