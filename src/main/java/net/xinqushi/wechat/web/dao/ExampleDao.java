package net.xinqushi.wechat.web.dao;

import net.xinqushi.wechat.pojo.Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//@Mapper
public interface ExampleDao {
//    @Select("select id,name from user where id=#{id}")
    Example selectById(int id);
}
