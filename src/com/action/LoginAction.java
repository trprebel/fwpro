package com.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Message;
import com.bean.User;
import com.dao.impl.UserDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	/**
	 * 登录功能
	 * @author trprebel
	 * 2013-09-16
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	private String userinfo;
	private User user;
	private UserDao userdao;
	public String messages;
	private String authcode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	//登录模块
	public String login()
	{
		//System.out.println("login action");
		userdao=new UserDao();
		//user=new User();
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sessions = request.getSession();
			
			String  validatecode=(String)sessions.getAttribute("rand");
			//System.out.println(validatecode);
			//1.验证码不正确
			if(validatecode==null)
			{
				messages="验证码不存在！";
				return "login";
			}
			if(!validatecode.equals(getAuthcode()))
			{
				messages="验证码不正确！";
				return "login";
			}
			//ActionContext ctx = ActionContext.getContext();
			//HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			//System.out.println("username:"+getUsername());
			//System.out.println("password:"+getPassword());
			//String userna=getUsername();
			//if(userna==null||userna==""||userna.equals("")) return "error";
			user=this.userdao.findUserByName(getUsername());
			if(user==null)
			{
				messages="用户名不存在！";
				//System.out.println("用户名不存在");
				return "login";
			}
			else if(user.getPassword().equals(getPassword()))
			{
				if(user.getType()==1) //超级管理员
				{
					List<User> alluser=userdao.findAllUser();
					sessions.setAttribute("user", alluser);
					
					List<Message> message=userdao.findAllMessage();
					sessions.setAttribute("message",message);
					return "admin";
				}
				else if(user.getType()==2)//普通管理员
				{
					List<User> genuser=userdao.findGenUser();
					sessions.setAttribute("user", genuser);
					return "admin";
				}
				else if(user.getType()==3)//普通用户
				{

					sessions.setAttribute("user",user);
					return "user";
				}
				else
				{
					return "error";
				}

				
			}
			else
			{
				//System.out.println("密码错误!");
				messages="密码错误！";
				return "login";
			}
			//System.out.println(user.getUsername());
			//System.out.println(user.getPassword());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	//注册功能
	public String registe()
	{
		userdao=new UserDao();
		user=new User();
		try
		{
			user.setUsername(getUsername());
			user.setPassword(getPassword());
			user.setEmail(getEmail());
			user.setInfo(getUserinfo());
			userdao.createUser(user);
			messages="注册成功，请登录！";
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}

	}

	
}
