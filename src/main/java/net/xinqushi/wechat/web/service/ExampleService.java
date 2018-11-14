package net.xinqushi.wechat.web.service;

import net.xinqushi.wechat.pojo.Example;
import net.xinqushi.wechat.web.result.Result;

public interface ExampleService {
    Result<Example> getUserById(int id);
}
