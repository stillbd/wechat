package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.common.ConstUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/util")
public class UtilController {

    /**
     * 获取周报列表当前页
     * 使其从周报详情页返回时，还能停留在当前页
     * @param session
     * @return
     */
    @GetMapping("/getpagenum")
    public String getSessionPageNum(HttpSession session){
        String pageNum = (String) session.getAttribute(ConstUtil.SESSION_SUMMARY_LIST_PAGENUM);
        System.out.println("util-------------"+pageNum);
        if (!StringUtils.isEmpty(pageNum)) {
            return pageNum;
        }else {
            return "1";
        }
    }

    @GetMapping("/getredirect")
    public String getRedirectUrl(HttpSession session){
        String redirectUrl = (String) session.getAttribute("REDIRECTURL");
        if (!StringUtils.isEmpty(redirectUrl)){
            return redirectUrl;
        }
        return "";
    }

}
