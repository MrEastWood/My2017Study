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
		<!-- 查询条件输入 -->
		<div style="height:60px;margin:10px">
			<input class="easyui-textbox" id="classifyName" name="classifyName" style="width:300px" data-options="label:'分类名:'">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchRec()" style="width:80px">查询</a>
		</div>
		<!-- 记录DataGrid -->
		<div style="width:1000px;margin:10px">
			<table id="classifyGrid"></table>
		</div>
  		<!-- 编辑窗口 -->
		<div id="w_edit" class="easyui-window" title="编辑分类" data-options="modal: true,closed:true" style="width:500px;height:250px;padding:10px;">
			<form id="editClassify" class="easyui-form" method="post" data-options="novalidate:true">
				<input type="hidden" id="cID" name="classifyId">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" id="cName" name="classifyName" style="width:80%" data-options="label:'分类名:',required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" id="cDesc" name="description" style="width:80%;height:60px" data-options="label:'分类说明:',multiline:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
		</div>
		<!-- 删除确认窗口 -->
		<div id="w_delete" class="easyui-window" title="删除分类" data-options="modal: true,closed:true" style="width:500px;height:200px;padding:10px;">
			The window content.
		</div>
	</div>

	<script charset="UTF-8">
		//通过js代码生成Datagrid
		$('#classifyGrid').datagrid({
			title : '书籍分类列表',
			//设置为空，避免首次调用
			url: '',
			method: 'post',
			rownumbers: true,
			singleSelect: true,
			queryParams: { classifyName : "" },
			
			toolbar:[{
				text: '编辑',
				iconCls: "icon-edit" ,
				handler: function(){editClassify();}
				
			},{
				text: '删除',
				iconCls: "icon-remove",
				handler: function(){removeClassify();}
			}],
			columns: [[
				{ field: 'classifyId',hidden: true },
				{ field: 'classifyName', title: '分类名称', width: '20%', align: 'left' }, 
				{ field: 'description', title: '分类描述', width: '40%', align: 'left' }, 
				{ field: 'createDate', title: '创建日期', width: '19%', align: 'left' }, 
				{ field: 'lastModifyDate', title: '最后修改日期', width: '19%', align: 'left' },
			]],
			pagination: true,
			pageSize: 20,
			onBeforeLoad: function (param) {
				param.classifyName = $("#classifyName").val();
		    }
		});
		//设置分页的信息
		$('#classifyGrid').datagrid('getPager').pagination({  
            pageSize: 20,  
            pageNumber: 1,
            pageList: [20, 30, 40, 50],  
            beforePageText: '第',//页数文本框前显示的汉字   
            afterPageText: '页    共 {pages} 页',  
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        });
		//按条件查询
		function searchRec(){
			//设置url
			$('#classifyGrid').datagrid("options").url = 'Classify/queryClassifyPage.do';
			$("#classifyGrid").datagrid("reload");
		};
		
		//编辑分类 
		function editClassify(){
			//获取选中的分类 
			var row = $("#classifyGrid").datagrid("getSelected");
			if(!row){
				$.messager.alert("提示","请选择一条记录",'info');
			}else{
				//alert(row.classifyId + row.classifyName + row.description);
				$('#cID').val(row.classifyId)
				$('#cName').textbox('setValue',row.classifyName);
				$('#cDesc').textbox('setValue',row.description);
				$("#w_edit").window("open");
			}
		};
		//提交修改表单
		function submitForm(){
			// 以ajax方式提交表达
			$('#editClassify').form('submit',{
				url: "Classify/modifyClassify.do",
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					//将返回字符串转换为JSON对象
					try{
						var msg = JSON.parse(data);
						if(msg.success){
							$.messager.alert("修改成功",msg.messageData,'info');
							$("#w_edit").window("close");
							$("#classifyGrid").datagrid("reload");
						}else{
							$.messager.alert("修改失败",msg.messageData,'error');
						}
					}catch(e){
						$.messager.alert("修改失败","未知异常，修改分类失败",'error');
					}
				}

			})
		};
		//删除分类
		function removeClassify(){
			//获取选中的分类 
			var row = $("#classifyGrid").datagrid("getSelected");
			if(!row){
				$.messager.alert("提示","请选择一条记录",'info');
			}else{
				$.messager.confirm('确认','您确认想要删除记录吗？分类名  : ' + row.classifyName ,function(r){    
				    if(r){    
				        $.getJSON("Classify/deleteClassify.do",{classifyId : row.classifyId},function(msg){
				        	$.messager.alert("删除结果",msg.messageData);
				        	if(msg.success){
				        		$("#classifyGrid").datagrid("reload");
				        	}
				        }) 
				    }    
				});  
			}
		};
		//清除form栏位值
		function clearForm(){
			$('#editClassify').form('clear');
		}
	</script>
</body>
</html>