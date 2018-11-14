package net.xinqushi.wechat.exception;

import lombok.extern.slf4j.Slf4j;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
*@author wangwei
*@created 22:59 2018/8/24
 *@classname GlobalExceptionHandler
*@classdescription 异常拦截器，所有异常都由此类拦截，向前端返回Result类对象
*
*/
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
@ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
    System.out.println(e.getMessage());
//    e.printStackTrace();
    log.error("异常打印：{}",e.getStackTrace());
    //全局异常处理逻辑
     if(e instanceof  GlobalException){
         return Result.error(((GlobalException) e).getCodeMsg());
     }
     //绑定异常处理逻辑
    else if(e instanceof BindException){
      BindException exception=(BindException) e;
         List<ObjectError> errors=exception.getAllErrors();
         String msg=errors.get(0).getDefaultMessage();
         return Result.error(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));
     }

     return Result.error(CodeMsg.MSG_UNKNOW);

}
}
