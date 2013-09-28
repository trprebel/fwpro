<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'release.jsp' starting page</title>
    
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
  <script>
	function releasetitle()
	{
		if(document.getElementById("title").value==""||document.getElementById("title").value==null)
		{
			alert("标题不能为空！");
			return;
		}
		//alert("release");
		var speedv;
		var directionv;
		for(var i=1;i<4;i++)
		{
			if(document.getElementById("radios"+i).checked) {
				speedv=i;
				//alert(speed);
				break;
			}
		}
		//alert(speedv);
		if(document.getElementById("radiod1").checked) directionv=1;
		else directionv=2;
		//alert(speed);
		//alert(document.getElementById("direction").value);
		var titledata = {
           content :document.getElementById("title").value,
           speed : speedv,
           direction : directionv
        };
        //alert(document.getElementById("title").value);
        //alert(speedv);
        //alert(directionv);
        $.ajax({
     		type:'post',
     		data: titledata,
    		url:'title.action',
     		dataType:'text',
     		success:function(data){
     			var obj = $.parseJSON(data);        
				alert(obj.result);
     		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){ 
    			alert(errorThrown);
    		}
    	});
	}
	function filepath()
	{
		alert(document.getElementById("file").value);
	}
	var i=1;
	function addtag()
	{
		//alert("addtag");
		var p=document.createElement("p");
		p.id="p"+i;
		var input=document.createElement("<input name=\"picture\" onchange=\"checkimg(this)\">");
		input.type="file";
		//input.name="picture";
		input.id="picture"+i;
		var a=document.createElement("a");
		//alert(p.id);
		a.href="javascript:deltag("+p.id+")";
		var node=document.createTextNode("删除");
		a.appendChild(node);
		p.appendChild(input);
		p.appendChild(a);
		i++;
		var div=document.getElementById("inputdiv");
		var subp=document.getElementById("subp");
		div.insertBefore(p,subp);
	}
	function deltag(element)
	{
		element.parentNode.removeChild(element);
	}
	
	function checkimg(img) 
	{
		if( !img.value.match( /.jpg|.jpeg|.gif|.png|.bmp/i ) )
		{
			alert("图片格式不正确，请选择jpg,jpeg,gif,png或bmp格式文件！");
		}

	}
	function check()
	{
		//alert("check");
		var array=document.getElementsByName("picture");
		//alert(array.item(0).value);
		//alert(array.item(0).value);
		for(var i=0;i<array.length;i++)
		{
			var j=i+1;
			if(array.item(i).value==""||array.item(i).value==null)
			{
				
				alert("第"+j+"张图片为空！");
				return;
			}
			if( !array.item(i).value.match( /.jpg|.jpeg|.gif|.png|.bmp/i ) )
			{
				alert("第"+j+"张图片格式不正确！");
				return;
			}
		}
		var form=document.getElementById("uploadform");
		form.submit();
	}



  </script>
  <body>
  	<table id="table">
		<caption>发布标题</caption>
		<tr>
			<td>标题内容</td>
			<td><input type="text" name="title" id="title" maxlength="100"></td>
		</tr>
		<tr>
			<td>速度</td>
			<td>
			<input type="radio" name="radios" id="radios1" value="2" checked>2
			<input type="radio" name="radios" id="radios2" value="4">4
			<input type="radio" name="radios" id="radios3" value="6">6
			</td>
		</tr>
		<tr>
			<td>方向</td>
			<td>
			<input type="radio" name="radiod" id="radiod1" value="1" checked>从左往右
			<input type="radio" name="radiod" id="radiod2" value="2">从右往左
			</td>
		</tr>
		<tr>
			<td><input type="button" onClick="javascript:releasetitle()" value="发布"></td>
		</tr>
	</table>

	<form action="picture.action" name="uploadform" id="uploadform" enctype="multipart/form-data" method="post">
		<div id="inputdiv">
		<p>上传图片&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:addtag()">添加图片</a></p>
		<p><input type="file" name="picture" id="picture" onchange="checkimg(this)"/></p>
		<p id="subp">
		<input type="button" onclick="check()" value="上传">
		<input type="reset" value="重置">
		</p>
		</div>
	</form>

  </body>
  <script type="text/javascript">
  	if('${messages}'!="")
 	{
  		alert('${messages}');
  	}
  </script>
</html>
