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
	}
];