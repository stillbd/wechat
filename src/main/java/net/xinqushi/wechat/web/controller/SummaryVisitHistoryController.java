package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.common.utils.WeChatUtil;
import net.xinqushi.wechat.config.WechatAccountConfig;
import net.xinqushi.wechat.exception.GlobalException;
import net.xinqushi.wechat.pojo.SummaryVisitHistory;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.SummaryVisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/visit")
public class SummaryVisitHistoryController {
    @Autowired
    private SummaryVisitHistoryService historyService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @RequestMapping("/add")
    public String addVisitHistory(HttpServletRequest request, HttpServletResponse response,String code){
        try {
            String openId = WeChatUtil.wxOpenId(request, response, code);
            request.getSession().setAttribute("OPENID",openId);
            Integer summaryId = (Integer) request.getSession().getAttribute("SUMMARYID");
            //先查询该用户是否已经阅读过该周报
            Result checkResult = historyService.checkReaded(summaryId, openId);
            String contextPath = request.getContextPath();
            //请求路径域名
            String yuming = wechatAccountConfig.getYuming();
            if (checkResult.getCode()==0){
                SummaryVisitHistory visitHistory = new SummaryVisitHistory();
                visitHistory.setSummaryId(summaryId);
                visitHistory.setOpenId(openId);
                Result addResult = historyService.addVisitHistory(visitHistory);
                if (addResult.getCode()==0){
                    return "redirect:"+yuming+"/wechat7/summary/detail/get/"+summaryId;
                }else {
                    throw new GlobalException(CodeMsg.MSG_VISIT_ADD_ERROR);
                }
            }else{
                return "redirect:"+yuming+"/wechat7/summary/detail/get/"+summaryId;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 修改点赞状态 */
    @GetMapping("/updateAdmiration")
    @ResponseBody
    public Result updateAdmiration(Integer summaryId, HttpSession session){
        String openid = (String)session.getAttribute("OPENID");
        Result result = historyService.updateAdmiration(summaryId, openid);
        return result;
    }

    /** 页面加载时获取当前点赞状态 */
    @GetMapping("/getAdmiration")
    @ResponseBody
    public Result getAdmiration(Integer summaryId,HttpSession session){
        String openid = (String)session.getAttribute("OPENID");
        Result result = historyService.getAdmiration(summaryId, openid);
        return result;
    }

}
