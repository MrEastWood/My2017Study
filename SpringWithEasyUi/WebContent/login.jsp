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
	<div class="easyui-panel" title="登录" style="width:100%;max-width:450px;padding:30px 60px;">
		<form id="login" class="easyui-form" action="Login/login.action" method="post" data-options="novalidate:true">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="username" style="width:100%" data-options="label:'用户名:',required:true">
			</div>
			
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="password" style="width:100%" data-options="type:'password', label:'密码:',required:true">
			</div>
			
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登录</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
			</div>
		</form>
	</div>
	<script charset="UTF-8">
		function submitForm(){
			//不使用ajax提交表单
			$('#login').form({
				url: "Login/login.action" ,
				ajax: false
			});
			
			if($('#login').form('enableValidation').form('validate')){
				$('#login').submit();
			}
		}
		function clearForm(){
			$('#login').form('clear');
		}
	</script>
</body>
</html>