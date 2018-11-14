package net.xinqushi.wechat.web.result;

import com.sun.tools.javac.jvm.Code;

public class CodeMsg {

	private int code;
	private String msg;
	
	//通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg SERVER_BIND_ERROR = new CodeMsg(500101, "服务端绑定异常:%s");


	//周报模块 5002XX
	public static CodeMsg MSG_SUMMARY_NOT_EXIST=new CodeMsg(5000201,"周报不存在");
	public static CodeMsg MSG_SUMMARY_COMMENT_NOT_EXISTS=new CodeMsg(5000202,"评论不存在");
	public static CodeMsg MSG_SUMMARY_RECOMMEND_NOT_EXIST=new CodeMsg(5000203,"无推荐周报");
	public static CodeMsg MSG_SUMMARY_THE_STATUS_NOT_EXIST=new CodeMsg(5000204,"不存在此状态周报");
	public static CodeMsg MSG_SUMMARY_NOT_ALLOWED_TO_MODIFY=new CodeMsg(5000205,"已审核周报不允许被修改");
	public static CodeMsg 	MSG_SUMMARY_ONLY_OWNER_CAN_MODIFY=new CodeMsg(5000206,"只能作者本人修改周报！");
	public static CodeMsg 	MSG_PIC_UPLOAD_FAILED=new CodeMsg(5000207,"图片上传失败");
    public static CodeMsg 	MSG_SUMMART_MODIFY_SUCCESS=new CodeMsg(5000208,"周报修改成功");
    public static CodeMsg 	MSG_SUMMART_POST_SUCCESS=new CodeMsg(5000209,"周报发布成功");
	public static CodeMsg 	MSG_SUMMARY_COMMENT_IS_EMPTY=new CodeMsg(5000210,"评论内容不能为空！");


	public static CodeMsg 	MSG_SUMMARY_CONTENT_IS_EMPTY=new CodeMsg(5000210,"周报内容不能为空");

	//聊天模块 5003XX
	
	//费用模块 5004XX
	
    //登陆模块  5005XX
	 public static CodeMsg MSG_USER_NOT_EXISTS =new CodeMsg(500501,"账号不存在");
	 public static CodeMsg MSG_USER_WRONG_PASSWORD =new CodeMsg(500502,"密码错误") ;
	public static CodeMsg MSG_USER_BIND_FAILED = new CodeMsg(500503,"openId获取失败");
	public static CodeMsg MSG_USER_NOT_ONLINE=new CodeMsg(5000504,"当前用户未登录");
	public static CodeMsg MSG_USER_NO_OPENID=new CodeMsg(5000505,"无用户绑定该openId");

	//阅读记录  5006XX
	public static CodeMsg MSG_VISIT_IS_EXISTS = new CodeMsg(500601,"该用户已阅读过该周报");
	public static CodeMsg MSG_VISIT_ADD_ERROR = new CodeMsg(500602,"阅读记录插入出错");

	//调试专用
    public static CodeMsg MSG_UNKNOW = new CodeMsg(500700,"未知错误");


//	public static final Object MSG_LOGIN_SUCCESS = "登陆成功";
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	/**
	 *@created   23:03 2018/8/24
	 *@author    wangwei
	 *@params
	 *@return     异常CodeMsg 对象生成方法
	 */

	public CodeMsg fillArgs(Object ... args){
		int code=this.code;
		String message=String.format(msg,args);
		return new CodeMsg(code,message);
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
