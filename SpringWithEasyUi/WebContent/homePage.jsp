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
<head id="Head1">
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>简单图书管理系统</title>
	
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	<link rel="stylesheet" type="text/css" href="css/easyui.css" />
	<link rel="stylesheet" type="text/css" href="css/icon.css" />

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='js/menuData.js'></script>
	<script type="text/javascript" src='js/outlook.js'></script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>

	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎登录
			<a href="#" id="editpass">修改密码</a> <a href="Login/logout.action" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px; float: left;"><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			图书管理系统</span>
	</div>

	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">如有问题，请联系 liuxingyi</div>
	</div>

	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 180px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
			<!--  导航内容通过JS代码填充  -->
		</div>
	</div>

	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
			<h1>欢迎使用图书管理系统!</h1>
		</div>
	</div>
</body>
</html>