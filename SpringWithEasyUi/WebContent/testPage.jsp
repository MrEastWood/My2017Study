<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/easyui.css">
	<!--  
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <style type="text/css">
    	table {border-collapse: collapse; border: none; width: 500px; margin:auto;}
    	td {border:1px solid #000; height: 30px ; text-align: center;}
    	th {border:1px solid #000;}
    	input{width: 400px;}
    </style>
	<title>新增分类</title>
</head>
<body>
	<div style="margin: 10px;text-align: center;">
		<label>请求url:</label>
		<input id="posturl" type="text" />
	</div>
	
	<div style="margin: 10px;text-align: center;">
		<table id="postdate" border="1px">
			<tr><th>输入：参数名=参数值</th></tr>
		</table>
	</div>
	
	<div style="margin: 10px;text-align: center;">
		<button id="addParm" onclick="addParm()">添加参数</button>
		<button id="sendReq" onclick="sendReq()">提交请求</button>
	</div>
	
	<div id="returnMsg" style="margin: 10px;text-align: center; width: 50%">
		<p id="msg"></p>
	</div>
	
	<script charset="UTF-8">
		function addParm(){
			$("#postdate").append("<tr><td><input type='text' /></td></tr>");
		}
		
		function sendReq(){
			var url=$("#posturl").val();
			var postdata=$("#postdate input").map(function(){
				 return $(this).val();
			}).get().join("&");
			
			$.ajax({
				type: "POST",
				url: url,
				data: postdata,
				dataType: "text",
				success: function(msg){
					$("#msg").text(msg);	
				}
			});

		}
	</script>
</body>
</html>