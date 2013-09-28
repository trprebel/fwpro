package com.action;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Message;
import com.bean.User;
import com.dao.impl.UserDao;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;
	private String uemail;
	private String uinfo;
	private String utype;
	private String ustate;
	private String reply;
	private String seq;
	private String msgid;
	
	private UserDao userdao;
	//public String messages;
	private String result;

	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUinfo() {
		return uinfo;
	}
	public void setUinfo(String uinfo) {
		this.uinfo = uinfo;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String ustate) {
		this.ustate = ustate;
	}

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	//修改用户数据
	public String modify()
	{
		//System.out.println(uname);
		try
		{
//			System.out.println(uname);
//			System.out.println(uemail);
//			System.out.println(uinfo);
//			System.out.println(utype);
//			System.out.println(ustate);
			//HttpServletRequest request = ServletActionContext.getRequest();
			
			Pattern p=Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
			Matcher m=p.matcher(uemail);
			if(!m.find())
			{
				result="邮箱格式不正确！";
				return "success";
			}
			//System.out.println(id);
			User auser=new User();
			auser.setUsername(uname);
			auser.setEmail(uemail);
			auser.setInfo(uinfo);
			auser.setType(Integer.parseInt(utype));
			auser.setState(Integer.parseInt(ustate));
			
			
			userdao=new UserDao();
			userdao.alterUserData(auser);

			
			
			result="修改成功！";
			return "success";
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		//result="修改成功！";
		//return "success";
	}
	//答复用户的申请
	public String replymsg()
	{
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sessions = request.getSession();
			//System.out.println("replymsg");
			//System.out.println(uname);
			Message message=new Message();
			//message.setUsername(uname);
			message.setState(Integer.parseInt(reply));
			message.setId(Integer.parseInt(msgid));
			userdao=new UserDao();
			userdao.replyMessage(message);
			User user=userdao.findUserByName(uname);
			user.setType(2);
			userdao.alterUserData(user);
			if(Integer.parseInt(reply)==1)
				result=uname+"已成为管理员！";
			else result="已驳回请求！";
			//删除session中对应的消息
			List<Message> messagess = (List<Message>) sessions.getAttribute("message");
			messagess.remove(Integer.parseInt(seq));
			sessions.setAttribute("message", messagess);
			return "success";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}



	

}
