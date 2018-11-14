package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.pojo.SummaryComment;
import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ViewSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
   @Autowired
    ViewSumService viewSumService;

    /**
     *
     * @param summaryId
     * @return
     * 根据周报id获取评论
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<List<SummaryComment>> getSummaryComment(@RequestParam int summaryId){
        Result result=viewSumService.getCommentsBySummaryId(summaryId);

        return result;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result  addComment(ViewSum comment, HttpSession session){

     Result result=   viewSumService.addComment(comment,session);
     return result;
    }
}
