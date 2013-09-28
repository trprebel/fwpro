package com.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Message;
import com.bean.User;
import com.dao.impl.UserDao;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uname;
	private String uemail;
	private String uinfo;
	private UserDao userdao;
	public String messages;
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
	//用户修改个人信息
	public String alter() {
		//System.out.println("user alter");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sessions = request.getSession();
		try
		{
			
			
			User auser=new User();
			auser.setUsername(uname);
			auser.setEmail(uemail);
			auser.setInfo(uinfo);
			auser.setType(3);
			auser.setState(1);
			sessions.setAttribute("user",auser);
			Pattern p=Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
			Matcher m=p.matcher(uemail);
			if(!m.find())
			{
				messages="邮箱格式不正确！";
				return "success";
			}
			//System.out.println(id);
			
			
			
			userdao=new UserDao();
			userdao.alterUserData(auser);
			
			messages="修改成功！";
			return "success";
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	public String apply(){
		try
		{
			//System.out.println("apply");
			HttpServletRequest request = ServletActionContext.getRequest();
			User auser=new User();
			auser.setUsername(getUname());
			auser.setEmail(getUemail());
			auser.setInfo(getUinfo());
			auser.setType(3);
			auser.setState(1);
			request.setAttribute("user",auser);
			Message message=new Message();
			message.setUsername(getUname());
			message.setReason("申请成为管理员！");
			message.setState(0);
			userdao=new UserDao();
			userdao.insertMessage(message);
			messages="申请成功！";
			return "success";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
