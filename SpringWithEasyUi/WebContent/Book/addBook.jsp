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
		<div class="easyui-panel" title="添加书籍" style="width:100%;max-width:600px;padding:30px 60px;">
			<form id="addBook" class="easyui-form" action="Book/addBook.action" method="post" enctype="multipart/form-data" data-options="novalidate:true">
				<div style="margin-bottom:10px">
					<input id="cls" name="bookClassify" style="width:80%">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookId" style="width:100%" data-options="label:'书籍编号:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookName" style="width:100%" data-options="label:'书名:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookDescription" style="width:100%;height:60px" data-options="label:'简述:',multiline:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookPublish" style="width:100%" data-options="label:'出版商:'">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookAuthor" style="width:100%" data-options="label:'作者:'">	
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" name="bookPrice" labelPosition="left" precision="2" style="width:60%" data-options="label:'价格:'">	
				</div>
				<div id="imgshow" style="margin-bottom:10px">
					<input class="easyui-filebox" id="bookCover" name="bookCover" data-options="prompt:'请选择...', label:'封面图片',buttonText:'浏览',accept:['image/jpeg','image/png']" style="width:80%; float:left;">
					<img id="coverimg" src="" alt="img" width="90px" height="120px" style="float: right;">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0;margin-top: 100px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
			</div>
		</div>
	</div>
	
	<script charset="UTF-8">
		
		$("#cls").combobox({
			url: 'Classify/listClassify.do',
			valueField:'classifyId',    
		    textField:'classifyName',
		    label:'书籍分类:',
		    required:true,
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) >= 0;
			}
		});
		
		function submitForm(){
			//不使用ajax提交表单
			$('#addBook').form({
				ajax: false
			});
			
			if($('#addBook').form('enableValidation').form('validate')){
				$('#addBook').submit();
			}
		}
		function clearForm(){
			$('#addBook').form('clear');
		}
		
		$("#coverimg").hide();
		$("#bookCover").filebox({
			onChange: function(){
				console.log("aaaaa");
				//var files = $("#filebox_file_id_2");  
				//var files = document.getElementById('filebox_file_id_2').files;
				//var files = $(this).next().find('input[id^="filebox_file_id_"]');
				var files = $("#imgshow input[type='file']")[0].files;
				if(files.length){
					var file = files[0];
					var reader = new FileReader();
					reader.onload = function(e){
						//$("#imgshow").append('<img src="'+e.target.result+'" alt="img" width="90px" height="120px" style="float: right">');
						$("#coverimg").attr({"src" : e.target.result});
						$("#coverimg").show();
					}
					reader.readAsDataURL(file);
				}
			}
		});


	</script>
</body>
</html>