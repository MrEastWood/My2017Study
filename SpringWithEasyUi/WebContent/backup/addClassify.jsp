<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	
	<script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    
	<title>新增分类</title>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<jsp:include page="master.html"></jsp:include>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
		<div class="easyui-panel" title="添加分类" style="width:100%;max-width:400px;padding:30px 60px;">
			<!--  
			<form action="addClassify.action" method="post" >
				<input type="text" name="classifyName"></input>
				<input type="text" name="description"></input>
				<input type="submit">提交</input>
			</form>
			-->

			<form id="addClassify" class="easyui-form" method="post" action="addClassify.action" data-options="novalidate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="classifyName" style="width:100%" data-options="label:'分类名:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="description" style="width:100%;height:60px" data-options="label:'分类说明:',multiline:true">
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
			$('#addClassify').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					$('#mainPanle').append(data);
					//$(location).attr('href','successaaa.jsp');
					//document.location.reload(true);
				}
			});
		}
		function clearForm(){
			$('#addClassify').form('clear');
		}
	</script>
</body>
</html>