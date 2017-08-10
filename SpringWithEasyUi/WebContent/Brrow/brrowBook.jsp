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
		<!-- 借书 -->
		<div class="easyui-panel" title="借书" style="width:100%;max-width:1000px;padding:20px 30px;">
			<form id="brrowBook" class="easyui-form" data-options="novalidate:true">
				<input class="easyui-textbox" id="readerId" name="readerId" style="width:300px" data-options="label:'用户ID:', required:true">&nbsp;&nbsp;&nbsp;&nbsp;
				<input class="easyui-textbox" id="bookId" name="bookId" style="width:300px" data-options="label:'书籍编号:', required:true">&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">借书</a>
			</form>
		</div>
		<!-- 已借书籍DataGrid -->
		<div style="width:1000px;margin-top: 10px">
			<table id="brrowedGrid"></table>
		</div>
	</div>

	<script charset="UTF-8">
		//通过js代码生成Datagrid
		$('#brrowedGrid').datagrid({
			title : '已借书籍',
			//设置为空，避免首次调用
			url: '',
			method: 'post',
			rownumbers: true,
			singleSelect: true,
			queryParams: { readerId : "" },
			columns: [[
				{ field: 'bookId',title: '书籍编号', width: '30%', align: 'left',
					formatter:function(val, row, index){  
				        return  row.book.bookId;  
				    }
				},
				{ field: 'bookName', title: '书名', width: '30%', align: 'left',
					formatter:function(val, row, index){  
						return  row.book.bookName; 
					}
 				}, 
				{ field: 'brrowDate', title: '借书日期', width: '19%', align: 'left' }, 
				{ field: 'expDate', title: '到期日', width: '19%', align: 'left' }, 
			]],
			onBeforeLoad: function (param) {
				param.readerId = $("#readerId").val();
		    }
		});
		
		//输入用户ID时，自动获取已借书籍
		$("#readerId").textbox({
			onChange: function(value){
				$.get("Reader/findById.do?readerId=" + value,function(message){
					if(message.success){
						$('#brrowedGrid').datagrid("options").url = 'Brrow/queryInBrrow.do';
						$("#brrowedGrid").datagrid("reload");
						
					}else{
						$.messager.alert("错误",message.messageData,'error');
					}
				});
				
			}
		});
		
		//提交修改表单
		function submitForm(){
			// 以ajax方式提交表达
			$('#brrowBook').form('submit',{
				url: "Brrow/brrowBook.do",
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
						$.messager.alert("失败","未知异常，借书失败",'error');
					}
				}
			})
		}
	</script>
</body>
</html>