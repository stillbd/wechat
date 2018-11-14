package net.xinqushi.wechat.web.service.impl;

import com.sun.tools.javac.jvm.Code;
import net.xinqushi.wechat.common.ConstUtil;
import net.xinqushi.wechat.common.utils.CookieUtils;
import net.xinqushi.wechat.common.utils.MD5SaltUtils;
import net.xinqushi.wechat.common.utils.WeChatUtil;
import net.xinqushi.wechat.exception.GlobalException;
import net.xinqushi.wechat.pojo.User;
import net.xinqushi.wechat.web.dao.UserDao;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    HttpSession session;
    @Autowired
    UserDao userDao;

    @Override
    public Result login(String loginName, String password,String openId,HttpServletRequest request,HttpServletResponse response) {
        //用户登陆,注意密码是加了salt的
        User user = userDao.selectByName(loginName);
        if (null == user) {
            throw new GlobalException(CodeMsg.MSG_USER_NOT_EXISTS);
        }

        String pwd = MD5SaltUtils.encode(password, user.getSalt());
        if (!pwd.equals(user.getPwd())) {
            throw new GlobalException(CodeMsg.MSG_USER_WRONG_PASSWORD);
        }
        session.setAttribute(ConstUtil.SESSION_USER_INFO, user);
        System.out.println("目前只考虑  user为vip登录，其余身份暂时不考虑");
        //给用户绑定openid
        if(null!=openId&&openId!="") {
            user.setOpenId(openId);
            userDao.updateSelective(user);
        }
        return Result.success(true);
    }

    /**
     * 给用户绑定微信openId（没有在用）
     *
     * @param session
     * @param response
     * @param request
     * @return
     */
    @Transactional
    @Override
    public Result bindOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request,String code) {
        //1,获取openid
        try {
            String openId = WeChatUtil.wxOpenId(request, response,code);
            if (!StringUtils.isEmpty(openId)) {
                User user = (User) session.getAttribute(ConstUtil.SESSION_USER_INFO);
                user.setOpenId(openId);
                userDao.updateSelective(user);
                return Result.success("绑定成功");
            }
            return Result.error(CodeMsg.MSG_USER_BIND_FAILED);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //2,获取成功,将openId 提交到数据库后回调到主页

        return null;
    }

    /**
     * 获取openid并进行相关操作验证
     * @param session
     * @param response
     * @param request
     * @param code
     * @return
     */
    @Override
    public Result getOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request, String code) {
        try {
            String openId = WeChatUtil.wxOpenId(request,response,code);
            System.out.println(openId);
            if (!StringUtils.isEmpty(openId)) {
                //1.查看数据库中是否有用户绑定该openid，
                Result result = selectUserByOpenId(session, openId);
                if(result.getCode()!=0){
                    session.setAttribute("OPENID",openId);
                }
                return result;
            }
            throw new GlobalException(CodeMsg.MSG_USER_BIND_FAILED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过微信openid登录
     *
     * @param session
     * @param openId
     * @return
     */
    @Override
    public Result selectUserByOpenId(HttpSession session, String openId) {
        User user = userDao.selectByOpenId(openId);
        if (null != user) {
            session.setAttribute(ConstUtil.SESSION_USER_INFO, user);
            return Result.success(true);
        } else {
            return Result.error(CodeMsg.MSG_USER_NO_OPENID);
        }

    }


    @Override
    public Result unbindOpenId(HttpSession session, HttpServletResponse response, HttpServletRequest request, String code) {
        try {
            String openId = WeChatUtil.wxOpenId(request,response,code);
            if (!StringUtils.isEmpty(openId)) {
                //1.查看数据库中是否有用户绑定该openid，
                User user = userDao.selectByOpenId(openId);
                if(null!=user){
                    userDao.deleteOpenId(openId);
                    return Result.success(true);
                }else {
                    return Result.error(CodeMsg.MSG_USER_NO_OPENID);
                }
            }
            throw new GlobalException(CodeMsg.MSG_USER_BIND_FAILED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
