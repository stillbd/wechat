package net.xinqushi.wechat.web.service;

import net.xinqushi.wechat.web.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {
    Result login(String loginName,String password,String openId,HttpServletRequest request,HttpServletResponse response);

    Result bindOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request,String code);

    Result getOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request,String code);

    Result selectUserByOpenId(HttpSession session,String openId);

    Result unbindOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request,String code);
}
