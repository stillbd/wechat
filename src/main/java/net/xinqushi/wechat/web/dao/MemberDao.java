package net.xinqushi.wechat.web.dao;

import net.xinqushi.wechat.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * Member表相关操作
 */
public interface MemberDao {
    Member getByUserId(@Param("uid") int userId);
}
