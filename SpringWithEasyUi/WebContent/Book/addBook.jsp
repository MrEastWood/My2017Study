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
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    
	<title>新增书籍</title>
</head>
<body>
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<div class="easyui-panel" title="添加书籍" style="width:100%;max-width:400px;padding:30px 60px;">
			<form id="addClassify" class="easyui-form" action="Book/addBook.action" method="post" data-options="novalidate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookId" style="width:100%" data-options="label:'书籍编号:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookId" style="width:100%" data-options="label:'书籍编号:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookName" style="width:100%;height:60px" data-options="label:'书名:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookDescription" style="width:100%;height:60px" data-options="label:'简述:',multiline:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookPublish" style="width:100%;height:60px" data-options="label:'出版商:'">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookAuthor" style="width:100%;height:60px" data-options="label:'作者:'">	
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="bookPrice" style="width:100%;height:60px" data-options="label:'价格:'">	
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
		</div>
	</div>
	
	<script charset="UTF-8">
		function submitForm(){
			//不使用ajax提交表单
			$('#addClassify').form({
				ajax: false
			});
			
			if($('#addClassify').form('enableValidation').form('validate')){
				$('#addClassify').submit();
			}
		}
		function clearForm(){
			$('#addClassify').form('clear');
		}
	</script>
</body>
</html>