package com.dao.impl;



import java.util.List;

import com.bean.Message;
import com.bean.User;




public class UserDao {
	
	//空构造函数
	public UserDao()
	{
		//u1="userdao!";
	}
	
	//通过用户名查询信息
	public User findUserByName(String username) throws Exception {
		//System.out.println(username);
		return (User)SqlMap.getSqlMapClient().queryForObject("findUserByName",username);
	}
	//注册用户
	public void createUser(User user) throws Exception {
		 SqlMap.getSqlMapClient().insert("insertUser",user);
	}
	//查找所有用户
	public List<User> findAllUser() throws Exception {
		return (List<User>) SqlMap.getSqlMapClient().queryForList("findAllUser");
	}
	//查找普通用户
	public List<User> findGenUser() throws Exception{
		return (List<User>) SqlMap.getSqlMapClient().queryForList("findGenUser");
	}
	public void alterUserData(User user) throws Exception{
		SqlMap.getSqlMapClient().update("alterUserData",user);
	}
	public void insertMessage(Message message) throws Exception{
		//System.out.println(message.getUsername());
		SqlMap.getSqlMapClient().insert("insertMessage",message);
	}
	public List<Message> findAllMessage() throws Exception{
		return (List<Message>)SqlMap.getSqlMapClient().queryForList("findAllMessage");
	}
	public void replyMessage(Message message) throws Exception{
		SqlMap.getSqlMapClient().update("replyMessage", message);
	}
}
