package net.xinqushi.wechat.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.xinqushi.wechat.common.ConstUtil;
import net.xinqushi.wechat.common.utils.EmptyUtil;
import net.xinqushi.wechat.common.utils.SummaryTitleUtil;
import net.xinqushi.wechat.entity.Member;
import net.xinqushi.wechat.exception.GlobalException;
import net.xinqushi.wechat.model.PageResult;
import net.xinqushi.wechat.pojo.SummaryComment;
import net.xinqushi.wechat.pojo.User;
import net.xinqushi.wechat.pojo.ViewSum;
import net.xinqushi.wechat.web.dao.MemberDao;
import net.xinqushi.wechat.web.dao.SummaryVisitHistoryDao;
import net.xinqushi.wechat.web.dao.ViewSumDao;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ViewSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static net.xinqushi.wechat.common.ConstUtil.SESSION_SUMMARY_TITLE_SHOWING;

@Service
public  class ViewSumServiceImpl implements ViewSumService {

    @Autowired
    private ViewSumDao viewSumDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SummaryVisitHistoryDao historyDao;
    @Autowired
    HttpSession session;

    @Override
    public PageInfo<ViewSum> getSumLis(Integer pageNum, Integer pageSize) {

        //分页
        PageHelper.startPage(pageNum,pageSize);
        //

        String orderByClause=null;
        List<ViewSum> list = viewSumDao.selectViewSummeryAll(orderByClause);
        PageInfo<ViewSum> pageInfo = new PageInfo<ViewSum>(list);
        return pageInfo;
    }

    @Override
    public ViewSum getContentById(Integer id) {

        ViewSum viewSum = viewSumDao.getViewSummeryById(id);
        String isWx = (String)session.getAttribute("isWx");
        session.setAttribute("intercept","0");
        if ("1".equals(isWx)){
            int readNum = historyDao.selectReadNum(id);
            int admirationNum = historyDao.selectAdmirationNum(id);
            viewSum.setReadNum(readNum);
            viewSum.setAdmirationNum(admirationNum);
        }
        return viewSum;
    }

    @Override
    public PageResult getSummaryWeekPage(String operation, int pageNum, int pageSize, String sortField, String sortWay, HttpSession session) {
        //1,获取当前显示周报标题
        //2,获取当前查询周报标题
        //3保存当前查询周报标题
        String title= SummaryTitleUtil.getWriteTitle();
        String titleShowing= (String) session.getAttribute(SESSION_SUMMARY_TITLE_SHOWING);


        if(null!=titleShowing) {
             //处理   operation操作获取请求的周报标题
             if (ConstUtil.PREVIOUS_WEEK.equals(operation)) {
                 title = SummaryTitleUtil.getPreWeekTitleByTitle(titleShowing);
                 pageNum = 1;
             } else if (ConstUtil.NEXT_WEEK.equals(operation)) {
                 title = SummaryTitleUtil.getNextWeekTitleByTitle(titleShowing);
                 pageNum = 1;
             } else if (ConstUtil.CURRENT_WEEK.equals(operation)) {
                 //当前周
                 title = SummaryTitleUtil.getWriteTitle();
                 pageNum = 1;
             } else {
                 title = titleShowing;
             }
        }
        String orderByClause=( StringUtils.isEmpty(sortField)||StringUtils.isEmpty(sortWay))? null:(sortField+" "+sortWay);
        System.out.println("title-------------------------------"+title);
        session.setAttribute(SESSION_SUMMARY_TITLE_SHOWING,title);

        Integer pageNumber = pageNum;
        String pageNumber1 = pageNumber.toString();
        session.setAttribute(ConstUtil.SESSION_SUMMARY_LIST_PAGENUM,pageNumber1);
        System.out.println("session-------------------------------"+session.getAttribute(ConstUtil.SESSION_SUMMARY_LIST_PAGENUM));
        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<ViewSum> list = viewSumDao.selectForWeek(title,orderByClause);
        PageInfo<ViewSum> pageInfo = new PageInfo<ViewSum>(list);

        return new PageResult(title,pageInfo);
    }

    @Override
    public Result getCommentsBySummaryId(int summaryId) {
       List<SummaryComment> comments=viewSumDao.selectCommentBySummaryId(summaryId);
       if(EmptyUtil.CollectionIsEmpty(comments)){
           throw new GlobalException(CodeMsg.MSG_SUMMARY_COMMENT_NOT_EXISTS);
        }
        return Result.success(comments);
    }

    @Override
    public Result addComment( ViewSum comment, HttpSession session) {

        User user= (User) session.getAttribute(ConstUtil.SESSION_USER_INFO);

        if(null==user){
          throw new GlobalException(CodeMsg.MSG_USER_NOT_ONLINE);
       }
       if(StringUtils.isEmpty(comment.getContent())){
            throw new GlobalException(CodeMsg.MSG_SUMMARY_COMMENT_IS_EMPTY);
       }
       Member member=memberDao.getByUserId(user.getId());
       comment.setTime(new Date());
       comment.setMemberId(member.getId());
       viewSumDao.addComment(comment);
        return Result.success(null);
    }

    @Override
    public Result saveOrUpdateSummary(ViewSum viewSum, HttpSession session) throws ParseException {
        if(null==viewSum){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        if(StringUtils.isEmpty(viewSum.getContent())){
            throw new GlobalException(CodeMsg.MSG_SUMMARY_CONTENT_IS_EMPTY);
        }
        //必须是本人才能修改
        //viewSum中id为null，表示为发表
        User user= (User) session.getAttribute(ConstUtil.SESSION_USER_INFO);
        if(null==user){
            throw new GlobalException(CodeMsg.MSG_USER_NOT_ONLINE);
        }
         Member member=memberDao.getByUserId(user.getId());
         viewSum.setPId(ConstUtil.STATUS_SUMMARY_IS_SUMMARY);
          List<ViewSum> viewSums=viewSumDao.getByMemberIdAndTitle(SummaryTitleUtil.getWriteTitle(),member.getId());
        //处理发表
          if(EmptyUtil.CollectionIsEmpty(viewSums)){
              viewSum.setTitle(SummaryTitleUtil.getWriteTitle());
              viewSum.setMemberId(member.getId());
              viewSum.setTime(new Date());
              return (viewSumDao.saveSummary(viewSum)>0)?Result.success(CodeMsg.MSG_SUMMART_POST_SUCCESS):Result.error(CodeMsg.SERVER_ERROR);
         }
        //处理修改
        ViewSum origin=viewSums.get(0);
           //历史周报,已经审核的周报不允许修改
//        if(!origin.getTitle().equals(viewSum.getTitle())){
//         return Result.error(CodeMsg.SERVER_ERROR);
//        }
        // 已审核不能修改
        if(ConstUtil.STATUS_SUMMARY_IS_CHECKED==origin.getIsCheck()){
            throw new GlobalException(CodeMsg.MSG_SUMMARY_NOT_ALLOWED_TO_MODIFY);
        }
        // 非本人不能修改
        if(origin.getMemberId()!=member.getId()){
            throw new GlobalException(CodeMsg.MSG_SUMMARY_ONLY_OWNER_CAN_MODIFY);
        }

        origin.setContent(viewSum.getContent());
        origin.setTime(new Date());
        return (viewSumDao.updateSummary(origin)>0)?Result.success(CodeMsg.MSG_SUMMART_MODIFY_SUCCESS):Result.error(CodeMsg.SERVER_ERROR);
    }

    @Override
    public Result getSummaryRecommend(String title, HttpSession session) {

      List<ViewSum> viewSums= viewSumDao.getSummaryByTitleRecommend(title,ConstUtil.STATUS_SUMMARY_IS_RECOMMENDED);
      if(EmptyUtil.CollectionIsEmpty(viewSums)){
          throw new GlobalException(CodeMsg.MSG_SUMMARY_RECOMMEND_NOT_EXIST);
      }
        return Result.success(viewSums);
    }

    @Override
    public Result getSummaryChecked(String title, int isCheck) {
       List<ViewSum> viewSums=viewSumDao.getSummaryByTitleChecked(title,isCheck);
       if(EmptyUtil.CollectionIsEmpty(viewSums)){
           throw new GlobalException(CodeMsg.MSG_SUMMARY_THE_STATUS_NOT_EXIST);
       }
        return Result.success(viewSums);
    }

    /**
     *
     * @param session
     * @return  获取当前登陆用户当前周周报
     */
    @Override
    public Result<ViewSum> getSelfSummaryCurrentWeek(HttpSession session) {
        User user= (User) session.getAttribute(ConstUtil.SESSION_USER_INFO);
        if(null==user){
            throw new GlobalException(CodeMsg.MSG_USER_NOT_ONLINE);
        }
        Member member=memberDao.getByUserId(user.getId());
        String title= SummaryTitleUtil.getWriteTitle();
       List<ViewSum>viewSums=viewSumDao.getByMemberIdAndTitle(title,member.getId());
       if(EmptyUtil.CollectionIsEmpty(viewSums)){
           throw new GlobalException(CodeMsg.MSG_SUMMARY_NOT_EXIST);
       }
        return Result.success(viewSums.get(0));
    }
}
