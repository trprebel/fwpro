<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:directive.page import="com.bean.Message" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//List<User> users=(List<User>)session.getAttribute("user");
	//List<Message> message=(List<Message>)request.getAttribute("message");
	//response.setAttribute("message",message);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title>清爽简单橙色后台模板 - 免费模板网 www.865171.cn</title>
		<link href="css/css.css" rel="stylesheet" type="text/css" />
	</head>
	<script type="text/javascript" src="js/jquery-1.6.js">
	</script>
	<body>

		<div class="top_img" align="right">

		</div>

		<div class="mid">
			<div class="left">


				<div class="left2">
					<div class="left2_1">
						<img src="images/left2_01.jpg" />
					</div>
					<div class="left2_2">
						<ul>
							<li>
								<span>内参研究报告</span>
							</li>
							<li>
								<a href="javascript:retouserinfo()">用户信息</a>
							</li>
							<li>
								<a href="javascript:retomsginfo()">用户消息</a>
							</li>
						</ul>

					</div>
					<div class="left2_1">
						<img src="images/left2_02.jpg" />
					</div>
				</div>
			</div>

			<div class="right">
				<div class="right1">
					现在的位置：
					<a href="#">首页</a>>>研究报告
				</div>
				<div class="right2" align="center">
					<div class="right2_tt" align="left" id="main">

					</div>
				</div>
				<div class="right3">
					<img src="images/right_b_03.jpg" />
				</div>
			</div>
		</div>

		<div style="clear: both;"></div>
		<div class="btt" align="center">
			<table width="98%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="18%">
					</td>
					<td width="82%" align="right"></td>
				</tr>
			</table>
		</div>

	</body>
	<script type="text/javascript">
	function retouserinfo()
	{
		//alert("userinfo");
		$.get('userinfo.jsp', function(data)
		{
			$('#main').html(data);
		}); 
	}
	function retomsginfo()
	{
		//alert("userinfo");
		$.get('msginfo.jsp', function(data)
		{
			$('#main').html(data);
		}); 
	}
	function modify(i)
 	{
		//var temp=document.getElementById("utype"+i).value;
		//alert(temp);
		var f1=document.getElementById("adminform");
		f1.action="admin.action?id="+i;
		f1.submit();
	}
	</script>
</html>
