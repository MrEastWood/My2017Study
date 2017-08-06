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
    
	<title>新增用户</title>
</head>
<body>
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<div class="easyui-panel" title="添加用户" style="width:100%;max-width:600px;padding:30px 60px;">
			<form id="addReader" class="easyui-form"  data-options="novalidate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="readerId" style="width:100%" data-options="label:'用户ID:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="name" style="width:100%" data-options="label:'用户名:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<select id="permission" class="easyui-combobox" name="permission" style="width:100%;" data-options="label:'状态:'">   
						<option value="1">普通用户</option> 
    					<option value="2">初级用户</option>   
    					<option value="3">中级用户</option>  
    					<option value="4">高级用户</option>  
    					<option value="5">管理员</option>  
					</select>
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" name="remark" style="width:100%;height:60px" data-options="label:'备注:',multiline:true">
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
			// 以ajax方式提交表达
			$('#addReader').form('submit',{
				url: "Reader/addReader.do",
				method: "post",
				
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				
				success:function(data){
					//将返回字符串转换为JSON对象
					var msg = JSON.parse(data)
					if(msg.success){
						$.messager.alert("添加成功",msg.messageData,'info');
						$('#addReader').form('clear');
					}else{
						$.messager.alert("添加失败",msg.messageData,'error');
					}
				}
			});
		}
		function clearForm(){
			$('#addReader').form('clear');
		}
	</script>
</body>
</html>