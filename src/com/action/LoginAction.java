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
	 * ��¼����
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
	//��¼ģ��
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
			//1.��֤�벻��ȷ
			if(validatecode==null)
			{
				messages="��֤�벻���ڣ�";
				return "login";
			}
			if(!validatecode.equals(getAuthcode()))
			{
				messages="��֤�벻��ȷ��";
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
				messages="�û��������ڣ�";
				//System.out.println("�û���������");
				return "login";
			}
			else if(user.getPassword().equals(getPassword()))
			{
				if(user.getType()==1) //��������Ա
				{
					List<User> alluser=userdao.findAllUser();
					sessions.setAttribute("user", alluser);
					
					List<Message> message=userdao.findAllMessage();
					sessions.setAttribute("message",message);
					return "admin";
				}
				else if(user.getType()==2)//��ͨ����Ա
				{
					List<User> genuser=userdao.findGenUser();
					sessions.setAttribute("user", genuser);
					return "admin";
				}
				else if(user.getType()==3)//��ͨ�û�
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
				//System.out.println("�������!");
				messages="�������";
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
	//ע�Ṧ��
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
			messages="ע��ɹ������¼��";
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}

	}

	
}
