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
	<link rel="stylesheet" type="text/css" href="css/icon.css" />
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    
	<title>新增分类</title>
</head>
<body style="overflow-y: hidden" scroll="no">
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<!-- 还书 -->
		<div class="easyui-panel" title="借书" style="width:100%;max-width:1000px;padding:20px 30px;">
			<form id="brrowBook" class="easyui-form" data-options="novalidate:true">
				<input class="easyui-textbox" id="bookId" name="bookId" style="width:300px" data-options="label:'书籍编号:', required:true">&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">还书</a>
			</form>
		</div>
	</div>

	<script charset="UTF-8">
		
		//提交借书表单
		function submitForm(){
			// 以ajax方式提交表达
			$('#brrowBook').form('submit',{
				url: "Brrow/returnBook.do",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					//将返回字符串转换为JSON对象
					try{
						var msg = JSON.parse(data);
						if(msg.success){
							$.messager.alert("成功",msg.messageData,'info');
							$("#brrowedGrid").datagrid("reload");
						}else{
							$.messager.alert("失败",msg.messageData,'error');
						}
					}catch(e){
						$.messager.alert("失败","未知异常，还书失败",'error');
					}
				}
			})
		}
	</script>
</body>
</html>