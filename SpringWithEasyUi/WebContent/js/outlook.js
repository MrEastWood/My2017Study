$(function() {
	// 导航菜单绑定初始化
	$("#wnav").accordion( {
		animate : false
	});

	addNav(menu);
	InitLeftMenu();
});

function addNav(data) {

	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			//使用内嵌Frame来链接到目标页面
			//menulist += '<li><div><a ref="' + o.menuid + '" href="' + o.url + '" rel="'
			menulist += '<li><div><a ref="' + o.menuid + '" href="homePage.jsp#" rel="'
					+ o.url + '" ><span class="icon ' + o.icon
					+ '" >&nbsp;</span><span class="nav">' + o.menuname
					+ '</span></a></div></li> ';
		});
		menulist += '</ul>';

		$('#wnav').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});

	});

	var pp = $('#wnav').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#wnav').accordion('select', t);

}

/**
 * 为每个菜单选项添加悬停效果及点击事件
 */
function InitLeftMenu() {
	
	hoverMenuItem();
	
	$('#wnav').on('click','li a', function() {
		//点击动态产生一个frame
		var url = $(this).attr("rel");
		var frame = createFrame(url);
		$('#mainPanle').html(frame);
		//增加点击效果
		$('#wnav li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});

}

/**
 * 菜单项鼠标Hover(鼠标悬停)
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

/**
 * 获取左侧导航的图标
 */
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(menu, function(j, o) {
		$.each(o.menus, function(k, m){
			if (m.menuid == menuid) {
				icon += m.icon;
				return false;
			}
		});
	});
	return icon;
}

/**
 * 产生一个内嵌的frame
 */
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}