package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ViewSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ViewSumService viewSumService;
    @RequestMapping("/getSummaryList")
    @ResponseBody
    public Result<ViewSum> getSummary(String title,int isCheck){
     Result<ViewSum> viewSumResult=viewSumService.getSummaryChecked(title,isCheck);

      return viewSumResult;
    }

}
