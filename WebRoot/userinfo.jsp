<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:directive.page import="com.bean.User"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    List<User> users=(List<User>)session.getAttribute("user");
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
  <script type="text/javascript" src="js/jquery-1.6.js"></script>
  <script type="text/javascript">
  
  function modify(i)
  {
  	//alert(document.getElementById("uname"+i).value);
  	var userdata = {
           uname :document.getElementById("uname"+i).value,
           uemail : document.getElementById("uemail"+i).value,
           uinfo : document.getElementById("uinfo"+i).value,
           ustate : document.getElementById("ustate"+i).value,
           utype : document.getElementById("utype"+i).value
        };
  	
	$.ajax({
     type:'post',
     data:userdata,
     url:'admin.action',
     dataType:'text',
     success:function(data){
     	var obj = $.parseJSON(data);        
		alert(obj.result);
     },
    error:function(){
       alert("修改失败");
    }

    });
  }
  </script>
  <body>

  <table align="center">
  <tr>
  	<td>用户名</td>
  	<td>邮箱</td>
  	<td>用户介绍</td>
  	<td>用户状态</td>
  	<td>用户身份</td>
  	<td>操作</td>
  </tr>
  <%
  int i=0;
  for (User user : users) { 
  	
  %>
  
  <tr>
  	<td><input type="text" name="uname" id="<%="uname"+i%>"  border="0" readonly value="<%=user.getUsername() %>"></td>
  	
  	<td><input type="text" name="uemail" id=<%="uemail"+i %> value="<%=user.getEmail() %>"></td>
  	<td><input type="text" name="uinfo" id=<%="uinfo"+i %> value="<%=user.getInfo() %>"></td>
  	<td>
  	<select name="ustate" id=<%="ustate"+i %> >
  		<option id="used" value="1"  <%if(user.getState()==1) out.print("selected"); %>>可用</option>
  		<option id="unused" value="0" <%if(user.getState()==0) out.print("selected"); %>>不可用</option>	
  	</select>
  	</td>

  	<td>
  	<select name="utype" id=<%="utype"+i %>>
  		<option value="2" <%if(user.getType()==2) out.print("selected"); %>>管理员</option>
  		<option value="3" <%if(user.getType()==3) out.print("selected"); %>>用户</option>
  	</select>
  	</td>
  	
  	<td><a href="javascript:modify(<%=i %>)">修改</a>
  	</td>
  
  </tr>

  <%i++;} %>
  
  </table>
  </body>
</html>
