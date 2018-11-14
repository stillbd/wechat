package net.xinqushi.wechat.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.xinqushi.wechat.config.WechatAccountConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
*@author wangwei
*@created 22:31 2018/8/19
 *@classname WeChatUtil
*@classdescription  微信工具，获取openid
*
*/
@Slf4j
public class WeChatUtil {

    @Autowired
    static WechatAccountConfig wechatAccountConfig;

    public static String wxOpenId(HttpServletRequest request, HttpServletResponse response,String code) throws UnsupportedEncodingException {
        response.setContentType("text/html");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map params = new HashMap();
        params.put("secret", wechatAccountConfig.getMpAppSecret());
        params.put("appid", wechatAccountConfig.getMpAppId());
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        String result = HttpGetUtil.httpRequestToString(
                "https://api.weixin.qq.com/sns/oauth2/access_token", params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = jsonObject.get("openid").toString();
       log.info("得到的openid为:{}",openid);
       return openid;

    }
}
