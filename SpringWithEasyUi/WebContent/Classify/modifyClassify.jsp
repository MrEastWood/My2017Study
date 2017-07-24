<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
	
	<script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    
	<title>新增分类</title>
</head>
<body style="overflow-y: hidden" scroll="no">
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<div style="height:60px;margin:10px">
			<input class="easyui-textbox" id="classifyName" name="classifyName" style="width:300px" data-options="label:'分类名:'">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="searchRec()" style="width:80px">查询</a>
		</div>
		<div style="width:1000px;margin:10px">
			<table id="classifyGrid"></table>
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
			queryParams: { classifyName : "" },
			columns: [[
				{ field: 'classifyId',hidden: true },
				{ field: 'classifyName', title: '分类名称', width: '20%', align: 'left' }, 
				{ field: 'description', title: '分类描述', width: '40%', align: 'left' }, 
				{ field: 'createDate', title: '创建日期', width: '19%', align: 'left' }, 
				{ field: 'lastModifyDate', title: '最后修改日期', width: '19%', align: 'left' },
			]],
			pagination: true,
			onBeforeLoad: function (param) {
				param.classifyName = $("#classifyName").val();
		    },
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
			$('#classifyGrid').datagrid("options").url = 'queryClassifyPage.do';
			$("#classifyGrid").datagrid("reload");
		}
	</script>
</body>
</html>