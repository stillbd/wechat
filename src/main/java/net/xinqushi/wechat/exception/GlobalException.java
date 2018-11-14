package net.xinqushi.wechat.exception;

import lombok.Data;
import net.xinqushi.wechat.web.result.CodeMsg;

/**
*@author wangwei
*@created 15:41 2018/8/25
 *@classname GlobalException
*@classdescription 全局异常类 ,查询返回结果都是Result类对象时，出现的任何不正常的情况，比如未登录，可以直接抛
 *    new GlobalException(CodeMsg),而不用在controller更多的去判断CodeMsg的code，减少不必要的代码
*
*/
@Data
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg=codeMsg;

    }
}
