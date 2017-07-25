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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<title>简单图书管理系统</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/icon.css" />

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

</head>
<body style="overflow-y: hidden" scroll="no">
	<div id="p" class="easyui-panel" title="交易失败"
		style="width: 700px; height: 200px; padding: 10px;">
		<p style="font-size: 14px">错误码     ：${message.returnCode}</p>
		<p style="font-size: 14px">错误信息：${message.messageData}</p>
	</div>
</body>
</html>