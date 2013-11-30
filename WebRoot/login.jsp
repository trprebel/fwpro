<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登陆</title>
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
  function f_check_number()
  {           
	var obj=username=document.getElementById("username").value;
    var patrn=/^[A-Za-z0-9]+$/;

	if (!patrn.exec(obj)) alert("false");
	else alert("true");
   
  }
  function check()
  {
  	
  	var username=document.getElementById("username").value;
  	var password=document.getElementById("password").value;
  	var authcode=document.getElementById("authcode").value;
  	var cpassword=/^[0-9]{1,20}$/;
  	var cusername=/^[A-Za-z0-9]+$/;
  	//alert(username);
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

  	if(authcode=="")
  	{
  		alert("验证码不能为空！");
  		return;
  	}
  	var f1=document.getElementById("loginform");
	f1.action="login.action";
	f1.submit();
  		
  }
  function changepic(){
        document.getElementById("pic").innerHTML="<img src='authcode.jsp?ra"+Math.random()+"'  onclick='changepic()'  style='cursor: hand' />";
  }

  </script>
  <body>
  <form action="" name="loginform" id="loginform" method="post" >
  <table width="256" height="102" border="0" align="center">
    <tr>
      <td width="71">用户名：</td>
      <td width="175"><input type="text" name="username" id="username" maxlength="20"/></td>
    </tr>
    <tr>
      <td>密&nbsp;&nbsp;码：</td>
      <td><input type="password" name="password" id="password" maxlength="6"/></td>
    </tr>
    <tr>
      <td>验证码：</td>
      <td>
      <input type="text" name="authcode" id="authcode" maxlength="4" width="50">
      <span id="pic"><img  src="authcode.jsp" onClick="changepic();"></span>
      <a href="javascript:changepic()">换一个</a>	
      </td>
    </tr>
	<tr>
	  <td><input type="button" onClick="check()" value="登陆"></td>
	  <td><input align="middle" type="button" onClick="window.location.href='registe.jsp'" value="注册"></td>
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
