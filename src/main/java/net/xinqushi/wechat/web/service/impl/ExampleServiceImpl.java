package net.xinqushi.wechat.web.service.impl;

import net.xinqushi.wechat.pojo.Example;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.dao.ExampleDao;
import net.xinqushi.wechat.web.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleDao exampleDao;
    @Override
    public  Result<Example>  getUserById(int id) {
        Example example=exampleDao.selectById(id);
       return  Result.success(example);
    }
}
