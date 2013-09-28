<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.dao.impl.UserDao"/>
<jsp:directive.page import="com.bean.User"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    User user=(User) session.getAttribute("user");
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  function modify()
  {
    
	var f1=document.getElementById("alterform");
	f1.action="user.action";
	f1.submit();
  }
  function apply()
  {
  	var f1=document.getElementById("alterform");
	f1.action="apply.action";
	f1.submit();
	
  }
  </script>
  <body>
  <form action="" name="alterform" id="alterform" method="post">
  <table align="center">
  <tr>
  	<td>用户名</td>
  	<td>邮箱</td>
  	<td>用户介绍</td>
  	<td>用户身份</td>
  	<td>操作</td>
  </tr>

  <tr>
  	<td><input type="text" name="uname" id="uname" border="0" readonly value="<%=user.getUsername() %>"></td>
  	<td><input type="text" name="uemail" id="uemail" value="<%=user.getEmail() %>"></td>
  	<td><input type="text" name="uinfo" id="uinfo" value="<%=user.getInfo() %>"></td>
  	<td>用户</td>
  	<td>
  		<a href="javascript:modify()">修改</a>
  		&nbsp;&nbsp;&nbsp;&nbsp;
  		<a href="javascript:apply()">申请成为管理员</a>
  	</td>
  </tr>
  
  </table>
  </form>
  </body>
  <script type="text/javascript">
  if('${messages}'!="")
  {
  	alert('${messages}');
  
  }
 
  </script>
</html>
