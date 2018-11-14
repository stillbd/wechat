package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.common.ConstUtil;
import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ViewSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/summary/detail")
public class SumDetailController {

    @Autowired
    private ViewSumService viewSumService;

    @GetMapping("/get/{id}")
    public Object getContentById(@PathVariable("id") Integer id, Model model,HttpSession session){
        ViewSum viewSum = viewSumService.getContentById(id);
        Result result;
        if(viewSum!=null){
            String isWx = (String)session.getAttribute("isWx");
            model.addAttribute("summary",viewSum);
            model.addAttribute("isWx",isWx);
            return "summary_detail";
        }else{
            result=Result.error(CodeMsg.MSG_SUMMARY_NOT_EXIST);
            return result;
        }

    }

}
