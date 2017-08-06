/**
 * 此JS文件作为左侧导航菜单的数据来源
 */
var menu = [
    {
	"menuid"   : "10",
	"icon"     : "icon-sys",
	"menuname" : "分类管理",
	"menus"    : [{
	    "menuid" : "101",
	    "menuname" : "添加分类",
	    "icon" : "icon-nav",
	    "url" : "Classify/addClassify.jsp"}, 
	    {
		"menuid" : "102",
		"menuname" : "管理分类",
		"icon" : "icon-nav",
		"url" : "Classify/manageClassify.jsp"}]
    },
	{
	"menuid"   : "21",
	"icon"     : "icon-sys",
	"menuname" : "书籍管理",
	"menus"    : [{
		"menuid" : "211",
		"menuname" : "添加书籍",
		"icon" : "icon-nav",
		"url" : "Book/addBook.jsp"}, 
		{
		"menuid" : "212",
		"menuname" : "管理书籍",
		"icon" : "icon-nav",
		"url" : "Book/manageBook.jsp"
		}]
	},
	{
		"menuid"   : "31",
		"icon"     : "icon-sys",
		"menuname" : "用户管理",
		"menus"    : [{
			"menuid" : "311",
			"menuname" : "添加用户",
			"icon" : "icon-nav",
			"url" : "Reader/addReader.jsp"}, 
			{
			"menuid" : "312",
			"menuname" : "管理用户",
			"icon" : "icon-nav",
			"url" : "Reader/manageReader.jsp"
			}]
	},
	{
		"menuid"   : "41",
		"icon"     : "icon-sys",
		"menuname" : "借书还书",
		"menus"    : [{
			"menuid" : "411",
			"menuname" : "借书",
			"icon" : "icon-nav",
			"url" : "Brrow/BrrowBook.jsp"}, 
			{
			"menuid" : "412",
			"menuname" : "还书",
			"icon" : "icon-nav",
			"url" : "Brrow/returnBook.jsp"
			},
			{
			"menuid" : "413",
			"menuname" : "已借书籍管理",
			"icon" : "icon-nav",
			"url" : "Brrow/brrowedBook.jsp"
			},
			{
			"menuid" : "414",
			"menuname" : "借还记录管理",
			"icon" : "icon-nav",
			"url" : "Brrow/brrowReturnRec.jsp"
			}]
	}
];