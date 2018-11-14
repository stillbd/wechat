package net.xinqushi.wechat.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截访问周报详情路径
 * 如果是在微信端访问，就获取openid插入访问历史表
 * 如果是在其他浏览器访问，不做操作
 */
@Component
public class ReadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        //判断是否拦截请求周报的路径，1:不拦截
        String intercept = (String)session.getAttribute("intercept");
        if(!"1".equals(intercept)) {
            //回调显示周报时，不对其进行拦截
            session.setAttribute("intercept","1");
            //判断 是否是微信浏览器
            String userAgent = httpServletRequest.getHeader("user-agent").toLowerCase();
            if (userAgent.indexOf("micromessenger") > -1) {//微信客户端
                session.setAttribute("isWx", "1");
                StringBuffer requestURL = httpServletRequest.getRequestURL();
                String url = requestURL.toString();
                Integer summaryId = Integer.valueOf(url.substring(url.lastIndexOf("/")+1));
                session.setAttribute("SUMMARYID", summaryId);
                httpServletResponse.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5271c0fec72093db&redirect_uri=http%3A%2F%2Fyuanfei666.top%2Fwechat7%2Fvisit%2Fadd&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
                return false;
            } else {
                session.setAttribute("isWx", "0");
                System.out.println("非微信端=======================================================");
                return true;
            }
        }else {
            System.out.println("没有拦截=======================================================");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
