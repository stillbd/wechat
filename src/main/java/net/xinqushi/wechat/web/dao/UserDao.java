package net.xinqushi.wechat.web.dao;

import net.xinqushi.wechat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//
//@Mapper
public interface UserDao {
    @Select("select * from user where name=#{name}")
    User selectByName(String name);

    @Update("update user set openId=#{openId} where id=#{id}")
    int updateSelective(User user);

    @Update("update user set openId=null where openId=#{openId}")
    int deleteOpenId(String openId);

    @Select("select * from user where openId=#{openId}")
    User selectByOpenId(String openId);
}
