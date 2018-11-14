package net.xinqushi.wechat.web.controller;

import com.github.pagehelper.PageInfo;
import net.xinqushi.wechat.common.ConstUtil;
import net.xinqushi.wechat.common.utils.SummaryTitleUtil;
import net.xinqushi.wechat.model.PageResult;
import net.xinqushi.wechat.pojo.SummaryComment;
import net.xinqushi.wechat.pojo.ViewSum;

import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ViewSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/summary")
public class ViewSumController {


    @Autowired
    private ViewSumService viewSumService;

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param operation
     * @param session
     * @return
     * 获取周报分页数据
     */
    @GetMapping("/list")
    public PageResult getView(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize" ,defaultValue = "20") Integer pageSize,
                                      @RequestParam(value = "op" ) String operation,
                                      String sortField,
                                      String sortWay,
                                      HttpSession session
    ) {


       PageResult result=viewSumService.getSummaryWeekPage(operation,pageNum,pageSize,sortField,sortWay,session);

        return result;
    }

    /**
     *
     * @param id
     * @return
     * 根据周报id获取周报完整内容
     */

    @GetMapping("/detail/{id}")
    public Result<ViewSum> getContentById(@PathVariable("id") Integer id) {
       ViewSum viewSum = viewSumService.getContentById(id);
       Result result=null;
       if(viewSum!=null){
           result=Result.success(viewSum);
       }
       else{
           result=Result.error(CodeMsg.MSG_SUMMARY_NOT_EXIST);
       }
       return result;
    }



    /**
     *
     * @param viewSum
     * @param session
     * @return
     * 发布或者更新周报
     */
    @RequestMapping("/save")
    public Result editSummarySave(ViewSum viewSum,HttpSession session) throws ParseException {

     Result result= viewSumService.saveOrUpdateSummary(viewSum,session);
     return result;

    }

    /**
     *
     * @return  检查是否写过周报，本周
     *          如果写过则返回周报信息，
     */
    @GetMapping("/checkWritten")
    public Result checkWritten(HttpSession session){
       Result<ViewSum>  result=viewSumService.getSelfSummaryCurrentWeek(session);

       return result;
    }


}
