package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public Result<?> login(String loginName,String password,HttpServletRequest request,HttpServletResponse response){
        String openId = (String) request.getSession().getAttribute("OPENID");
        //前端需要使用md5加密密码，后台再加验,但实际没这样做
        Result result=userService.login(loginName,password,openId,request,response);
         return result;
    }

    @RequestMapping("/getopenid")
    public String getOpenId(HttpSession session, HttpServletRequest request, HttpServletResponse response,String code){
        Result result= userService.getOpenId(session,response,request,code);
        if(result.getCode()==0){
            return "qediter1";
        }else if(result.getCode()==5000505) {
            return "login";
        }
        return null;
    }

    //微信端调用解绑openid接口
    @GetMapping("/toUnbind")
    public String toUnbindOpenId(){
        //获取code的微信接口
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5271c0fec72093db&redirect_uri=http%3A%2F%2Fyuanfei666.top%2Fwechat7%2Fuser%2Funbind&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        return "redirect:"+url;
    }

    //获取openid的转发接口
    @RequestMapping("/unbind")
    public String unbindOpenId(HttpSession session, HttpServletRequest request, HttpServletResponse response,String code){
        Result result = userService.unbindOpenId(session, response, request, code);
        //注销session方式一
        /*User user = (User) session.getAttribute(ConstUtil.SESSION_USER_INFO);
        if (null!=user){
            session.removeAttribute(ConstUtil.SESSION_USER_INFO);
        }*/
        //方式二
        session.invalidate();
        if (result.getCode()==0){
            return "unbind_success";
        }else {
            return "unbind_failed";
        }
    }
}
