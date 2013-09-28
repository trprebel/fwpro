<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
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
  function registe()
  {
  	var username=document.getElementById("username").value;
  	var password=document.getElementById("password").value;
  	var repassword=document.getElementById("repassword").value;
  	var email=document.getElementById("email").value;
  	//alert(email);
  	var cpassword=/^[0-9]{1,20}$/;
  	var cusername=/^[A-Za-z0-9]+$/;
  	var cemail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  	if(password!=repassword)
  	{
  		alert("两次输入的密码不相同！");
  		return;
  	}
  	if(username=="")
  	{
  		alert("用户名不能为空！");
  		return ;
  	}
  	else if(!cusername.exec(username))
  	{
  		alert("用户名由20位以内数字和字母组成！");
  		return;
  	}
  	if(password=="")
  	{
  		alert("密码不能为空！");
  		return ;
  	}
  	else if(!cpassword.exec(password))
  	{
  		alert("密码由6位以内数字组成！");
  		return ;
  	}
  	if(email=="")
  	{
  		alert("邮箱不能为空！");
  		return ;
  	}
  	else if(!cemail.exec(email))
  	{
  		alert("邮箱格式不正确！");
  		return;
  	}
  	
  	var f1=document.getElementById("registeform");
  	f1.action="registe.action";
  	f1.submit();
  }
  function reset()
  {
  	document.getElementById("username").value="";
  	document.getElementById("password").value="";
  	document.getElementById("repassword").value="";
  	document.getElementById("email").value="";
  	document.getElementById("info").value="";
  }
  </script>
  <body>
  <br>
  <form action="" name="registeform" id="registeform" method="post">
  <table width="474" border="0" align="center">
    <tr>
      <td width="87">用户名：</td>
      <td width="377"><input type="text" name="username" id="username" maxlength="20"/>
      （用户名为字母和数字组成）</td>
    </tr>
    <tr>
      <td>密码：</td>
      <td><input type="password" name="password" id="password" maxlength="6">
      （密码为6位数字）</td>
    </tr>
    <tr>
      <td>重复密码：</td>
      <td><input type="password" name="repassword" id="repassword" maxlength="6"></td>
    </tr>
    <tr>
      <td>邮箱：</td>
      <td><input type="text" name="email" id="email" maxlength="20"></td>
    </tr>
    <tr>
      <td height="85">介绍：</td>
      <td><input type="text" name="userinfo" id="userinfo" maxlength="500" width="360" height="80" ></td>
    </tr>
	<tr>
	  <td><input type="button" onClick="registe()" value="注册"></td>
	  <td><input type="button" onClick="reset()" value="重置">
  </table>
  </form>
  </body>
</html>
