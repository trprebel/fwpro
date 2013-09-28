<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.bean.Message" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Message> message = (List<Message>) session.getAttribute("message");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'msginfo.jsp' starting page</title>

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

  function modify(i,reply)
  {
  	//alert(document.getElementById("uname"+i).value);
  	var userdata = {
  		msgid :document.getElementById("id"+i).value,
  		uname :document.getElementById("uname"+i).value,
  		reply:reply,
  		seq:i
    };
  	
	$.ajax({
     type:'post',
     data:userdata,
     url:'replymsg.action',
     dataType:'text',
     success:function(data){
     	var obj = $.parseJSON(data);        
		alert(obj.result);
		var tb=document.getElementById("table");
		var tr=document.getElementById("seq"+i);
		//parent.removeChild(child);
		tb.deleteRow(tr.rowIndex);
     },
    error:function(){
       alert("修改失败");
    }

    });
  }
  </script>
	<body>
		<table id="table">
			<caption>消息列表</caption>
			<tr>
				<td>
					消息ID
				</td>
				<td>
					用户名
				</td>
				<td>
					申请内容
				</td>
				<td>
					状态
				</td>
				<td>
					操作
				</td>
			</tr>
			<%
				int i = 0;
				for (Message msg : message) {
			%>

			<tr id="<%="seq"+i %>">
				<td>
				<input type="text" name="id" id="<%="id" + i%>" border="0"
						readonly value="<%=msg.getId()%>">
				</td>
				<td>
					<input type="text" name="uname" id="<%="uname" + i%>" border="0"
						readonly value="<%=msg.getUsername()%>">
				</td>

				<td>
					<input type="text" name="umessage" id=<%="umessage" + i%>
						readonly value="<%=msg.getReason()%>">
				</td>
				<td>
					<input type="text" name="ustate" id=<%="ustate" + i%>
						value="未处理">
				</td>


				<td>
					<a href="javascript:modify(<%=i%>,1)">批准</a>
					<a href="javascript:modify(<%=i%>,2)">驳回</a>
				</td>

			</tr>

			<%
				i++;
				}
			%>

		</table>
	</body>
</html>
