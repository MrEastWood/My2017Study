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
    
	<title>管理用户</title>
</head>
<body style="overflow-y: hidden" scroll="no">
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<!-- 查询条件输入 -->
		<div class="easyui-panel" title="筛选条件" style="width:100%;max-width:800px;padding:30px 60px;">
			<form id="queryParms" class="easyui-form">
				<div style="margin-bottom:10px">
					<input id="q_id" class="easyui-textbox" name="readerId" style="width:300px" data-options="label:'用户ID:'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="q_name" class="easyui-textbox" name="name" style="width:300px" data-options="label:'用户名:'">
				</div>
				<div style="margin-bottom:10px">
					<select id="q_permission" class="easyui-combobox" name="permission" style="width:300px;" data-options="label:'权限:'">   
						<option value=" ">全部</option> 
    					<option value="1">普通用户</option> 
    					<option value="2">初级用户</option>   
    					<option value="3">中级用户</option>  
    					<option value="4">高级用户</option>  
    					<option value="5">管理员</option>  
					</select>
				</div>
			</form>
			<div style="text-align:center;padding:10px 0;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitQuery()" style="width:80px">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearQuery()" style="width:80px">清空</a>
			</div>
		</div>
		<!-- 记录DataGrid -->
		<div style="width:1000px;margin: 10px 0px">
			<table id="readerGrid"></table>
		</div>
	</div>
	<!-- 编辑用户窗口 -->
		<div id="w_edit" class="easyui-window" title="编辑用户" data-options="modal: true,closed:true" style="width:600px;height:400px;padding:10px;">
			<form id="editReader" class="easyui-form" method="post" data-options="novalidate:true">
				<div style="margin:10px 20px">
					<input class="easyui-textbox" name="readerId" style="width:100%" data-options="label:'用户ID:',required:true, readonly:true">
				</div>
				<div style="margin:10px 20px">
					<input class="easyui-textbox" name="name" style="width:100%" data-options="label:'用户名:',required:true, readonly:true">
				</div>
				<div style="margin:10px 20px">
					<select id="permission" class="easyui-combobox" name="permission" style="width:100%;" data-options="label:'权限:'">   
						<option value="1">普通用户</option> 
    					<option value="2">初级用户</option>   
    					<option value="3">中级用户</option>  
    					<option value="4">高级用户</option>  
    					<option value="5">管理员</option>  
					</select>
				</div>
				<div style="margin:10px 20px">
					<select id="status" class="easyui-combobox" name="status" style="width:100%;" data-options="label:'状态:'">   
						<option value="N">正常</option> 
    					<option value="F">冻结</option>   
    					<option value="E">过期</option>  
					</select>
				</div>
				<div style="margin:10px 20px">
					<input class="easyui-textbox" name="remark" style="width:100%;height:60px" data-options="label:'备注:',multiline:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0;margin: 10px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()" style="width:80px">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearEdit()" style="width:80px">清空</a>
			</div>
		</div>

	<script charset="UTF-8">
		//通过js代码生成Datagrid
		$('#readerGrid').datagrid({
			title : '用户列表',
			//设置为空，避免首次调用
			url: '',
			method: 'post',
			rownumbers: true,
			singleSelect: true,
			queryParams: { 
				readerId : "",
				name : "",
				permission : ""},
			columns: [[
				{ field: 'readerId', title: '用户ID', width: '20%', align: 'left' }, 
				{ field: 'name', title: '用户名', width: '20%', align: 'left' }, 
				{ field: 'permission', title: '权限', width: '15%', align: 'left',
					formatter: function(value,row,index){
						switch(value){
						case "1":
							return "普通用户" ;
						case "2":
							return "初级用户" ;
						case "3":
							return "中级用户" ;
						case "4":
							return "高级用户" ;
						case "5":
							return "管理员" ;
						}
					}
				},
				{ field: 'createDate', title: '创建日期', width: '20%', align: 'left' }, 
				{ field: 'opreation', title: '操作', width: '25%', align: 'left',
					formatter: function(value,row,index){
						return '<a href="javascript:void(0)" onclick="modifyReader(\'' + row.readerId +'\')">详情/编辑</a>' + '&nbsp&nbsp&nbsp&nbsp' + 
							   '<a href="javascript:void(0)" onclick="deleteReader(\'' + row.readerId +'\')">删除</a>' ;
					}
				}
			]],
			pagination: true,
			pageSize: 20
		});
		//设置分页的信息
		$('#readerGrid').datagrid('getPager').pagination({  
            pageSize: 20,  
            pageNumber: 1,
            pageList: [20, 30, 40, 50],  
            beforePageText: '第',//页数文本框前显示的汉字   
            afterPageText: '页    共 {pages} 页',  
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
        });
		
		//按条件查询
		function submitQuery(){
			//设置url
			$('#readerGrid').datagrid("options").url = 'Reader/queryReaderPage.do';
			var parms = $('#readerGrid').datagrid("options").queryParams;
			parms.readerId = $("#q_id").textbox("getValue");
			parms.name = $("#q_name").textbox("getValue");
			parms.permission = $("#q_permission").combobox("getValue");
			$("#readerGrid").datagrid("reload");
		};
		//清空查询条件 
		function clearQuery(){
			$('#queryParms').form('clear');
		};
		
		//详情和编辑书籍 
		function modifyReader(id){
			$.get("Reader/findById.do?readerId=" + id,function(message){
				if(message.success){
					data = message.messageData;
					$("#editReader").form("load",data);
					$("#w_edit").window("open");
				}else{
					$.messager.alert("错误",message.messageData,'error');
				}
			});
			
		}
		//清空编辑书籍表单
		function clearEdit(){
			$('#editReader').form('clear');
		}
		//提交编辑书籍表单
		function submitEdit(){
			// 以ajax方式提交表达
			$('#editReader').form('submit',{
				url: "Reader/editReader.do",
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
							$("#readerGrid").datagrid("reload");
						}else{
							$.messager.alert("修改失败",msg.messageData,'error');
						}
					}catch(e){
						$.messager.alert("修改失败","未知异常，修改分类失败",'error');
					}
				}
			})
		};
		//删除书籍
		function deleteReader(id){
			$.messager.confirm('确认','您确认想要删除记录吗？用户ID  : ' + id ,function(r){    
			    if(r){    
			        $.getJSON("Reader/deleteReader.do",{readerId : id},function(msg){
			        	$.messager.alert("删除结果",msg.messageData);
			        	if(msg.success){
			        		$("#readerGrid").datagrid("reload");
			        	}
			        }) 
			    }    
			}); 
		}
	</script>
</body>
</html>