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
    
	<title>管理书籍</title>
</head>
<body style="overflow-y: hidden" scroll="no">
	<div id="mainPanle" style="background: #eee; overflow-y:hidden">
		<!-- 查询条件输入 -->
		<div class="easyui-panel" title="筛选条件" style="width:100%;max-width:800px;padding:30px 60px;">
			<form id="queryParms" class="easyui-form">
				<div style="margin-bottom:10px">
					<input id="q_cls" name="bookClassify" style="width:295px">&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="q_name" class="easyui-textbox" name="bookName" style="width:300px" data-options="label:'书名:'">
				</div>
				<div style="margin-bottom:10px">
					<input id="q_publish" class="easyui-textbox" name="bookPublish" style="width:300px" data-options="label:'出版商:'">&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="q_author" class="easyui-textbox" name="bookAuthor" style="width:300px" data-options="label:'作者:'">
				</div>
				<div style="margin-bottom:10px">
					<input id="q_priceFr" class="easyui-numberbox" name="priceFrom" precision="2" style="width:175px" data-options="label:'价格从:'">
					<input id="q_priceTo" class="easyui-numberbox" name="priceTo" precision="2" labelWidth="30px" style="width:120px" data-options="label:'至:'">&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="q_sts" class="easyui-combobox" name="bookSts" style="width:300px;" data-options="label:'状态:'">   
						<option value=" ">全部</option> 
    					<option value="N">可借</option>   
    					<option value="B">借出</option>  
    					<option value="L">丢失</option>  
    					<option value="D">下架</option>  
    					<option value="F">冻结</option>  
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
			<table id="bookGrid"></table>
		</div>
	</div>
	<!-- 编辑书籍窗口 -->
		<div id="w_edit" class="easyui-window" title="编辑书籍" data-options="modal: true,closed:true" style="width:600px;height:500px;padding:10px;">
			<form id="editBook" class="easyui-form" action="Book/editBook.do" method="post" enctype="multipart/form-data" data-options="novalidate:true">
				<div style="margin-bottom:10px">
					<input id="cls" name="bookClassify" style="width:80%">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookId" style="width:80%" data-options="label:'书籍编号:',required:true, readonly:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookName" style="width:80%" data-options="label:'书名:',required:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookDescription" style="width:80%;height:60px" data-options="label:'简述:',multiline:true">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookPublish" style="width:80%" data-options="label:'出版商:'">
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" name="bookAuthor" style="width:80%" data-options="label:'作者:'">	
				</div>
				<div style="margin-bottom:10px">
					<input class="easyui-numberbox" name="bookPrice" labelPosition="left" precision="2" style="width:60%" data-options="label:'价格:'">	
				</div>
				<div id="imgshow" style="margin-bottom:10px">
					<input class="easyui-filebox" id="bookCover" name="bookCover" data-options="prompt:'请选择...', label:'封面图片',buttonText:'浏览',accept:['image/jpeg','image/png']" style="width:80%; float:left;">
					<img id="coverimg" src="" alt="img" width="90px" height="120px" style="float: right;">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0;margin-top: 100px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()" style="width:80px">提交</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearEdit()" style="width:80px">清空</a>
			</div>
		</div>

	<script charset="UTF-8">
	
		$("#q_cls").combobox({
			url: 'Classify/listClassify.do',
			valueField:'classifyId',    
	    	textField:'classifyName',
	    	label:'书籍分类:',
	    	filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) >= 0;
			}
		});
		$("#cls").combobox({
			url: 'Classify/listClassify.do',
			valueField:'classifyId',    
	    	textField:'classifyName',
	    	label:'书籍分类:',
	    	filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) >= 0;
			}
		});
		//通过js代码生成Datagrid
		$('#bookGrid').datagrid({
			title : '书籍分类列表',
			//设置为空，避免首次调用
			url: '',
			method: 'post',
			rownumbers: true,
			singleSelect: true,
			queryParams: { 
				bookClassify : "",
				bookName : "",
				bookAuthor : "",
				bookPublish : "",
				priceFrom : "",
				priceTo : "",
				bookSts : ""},
			columns: [[
				{ field: 'bookId', title: '书籍编号', width: '25%', align: 'left' }, 
				{ field: 'bookName', title: '书名', width: '25%', align: 'left' }, 
				{ field: 'bookAuthor', title: '作者', width: '20%', align: 'left' }, 
				{ field: 'bookStatus', title: '状态', width: '10%', align: 'left',
					formatter: function(value,row,index){
						switch(value){
						case "N":
							return "可借" ;
						case "B":
							return "借出" ;
						case "L":
							return "丢失" ;
						case "F":
							return "冻结" ;
						case "D":
							return "下架" ;
						}
					}
				},
				{ field: 'description', title: '操作', width: '40%', align: 'left',
					formatter: function(value,row,index){
						return '<a href="javascript:void(0)" onclick="modifyBook(\'' + row.bookId +'\')">详情/编辑</a>' + '&nbsp&nbsp&nbsp&nbsp' + 
							   '<a href="javascript:void(0)" onclick="deleteBook(\'' + row.bookId +'\')">删除</a>' ;
					}
				}
			]],
			pagination: true,
			pageSize: 20,
			onBeforeLoad: function(param){
				//param.bookName = $("#q_name").textbox("getValue");
				//param.bookClassify = $("#q_cls").combobox("getValue");
				//param.bookName = $("#q_name").textbox("getValue");
				//param.bookPublish = $("#q_publish").textbox("getValue");
				//param.bookAuthor = $("#q_author").textbox("getValue");
				//param.priceFrom = $("#q_priceFr").numberbox("getValue");
				//param.priceTo = $("#q_priceTo").numberbox("getValue");
				//param.bookSts = $("#q_sts").combobox("getValue");
		    }
		});
		//设置分页的信息
		$('#bookGrid').datagrid('getPager').pagination({  
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
			$('#bookGrid').datagrid("options").url = 'Book/queryBookPage.do';
			var parms = $('#bookGrid').datagrid("options").queryParams;
			parms.bookName = $("#q_name").textbox("getValue");
			parms.bookClassify = $("#q_cls").combobox("getValue");
			parms.bookName = $("#q_name").textbox("getValue");
			parms.bookPublish = $("#q_publish").textbox("getValue");
			parms.bookAuthor = $("#q_author").textbox("getValue");
			parms.priceFrom = $("#q_priceFr").numberbox("getValue");
			parms.priceTo = $("#q_priceTo").numberbox("getValue");
			parms.bookSts = $("#q_sts").combobox("getValue");
			$("#bookGrid").datagrid("reload");
		};
		//清空查询条件 
		function clearQuery(){
			$('#queryParms').form('clear');
		};
		$("#bookCover").filebox({
			onChange: function(){
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
		
		//详情和编辑书籍 
		function modifyBook(id){
			$.get("Book/loadBookForm.do?bookId=" + id,function(data){
				if(data){
					data.bookClassify = data.bookClassify.classifyId;
					data.bookCover = data.bookImageUrl
					$("#editBook").form("load",data);
					$("#coverimg").attr("src",data.bookImageUrl);
					
					$("#w_edit").window("open");
					
				}else{
					$.messager.alert("操作失败","此记录已被删除",'error');
				}
			});
			
		}
		//清空编辑书籍表单
		function clearEdit(){
			$('#editBook').form('clear');
		}
		//提交编辑书籍表单
		function submitEdit(){
			// 以ajax方式提交表达
			$('#editBook').form('submit',{
				url: "Book/editBook.do",
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
							$("#bookGrid").datagrid("reload");
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
		function deleteBook(id){
			$.messager.confirm('确认','您确认想要删除记录吗？书籍编号  : ' + id ,function(r){    
			    if(r){    
			        $.getJSON("Book/deleteBook.do",{bookId : id},function(msg){
			        	$.messager.alert("删除结果",msg.messageData);
			        	if(msg.success){
			        		$("#bookGrid").datagrid("reload");
			        	}
			        }) 
			    }    
			}); 
		}
	</script>
</body>
</html>