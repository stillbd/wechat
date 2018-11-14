package net.xinqushi.wechat.interceptor;

import net.xinqushi.wechat.common.ConstUtil;
import net.xinqushi.wechat.config.WechatAccountConfig;
import net.xinqushi.wechat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    WechatAccountConfig wechatAccountConfig;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute(ConstUtil.SESSION_USER_INFO);
        if(user==null){
            String requestURL = httpServletRequest.getRequestURL().toString();
            session.setAttribute("REDIRECTURL",requestURL);
            //重定向地址
            String redirect_uri = wechatAccountConfig.getYuming()+"/wechat7/user/getopenid";
            //对重定向地址进行转码
            String redirect_uri_encode = URLEncoder.encode(redirect_uri,"UTF-8");
            //httpServletResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5271c0fec72093db&redirect_uri=http%3A%2F%2Fyuanfei666.top%2Fwechat7%2Fuser%2Fgetopenid&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
            httpServletResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+wechatAccountConfig.getMpAppId()+"&redirect_uri="+redirect_uri_encode+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
