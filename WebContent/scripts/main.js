var tab = null;
var accordion = null;
var tree = null;
var tabItems = [];
$(function() {
    $.ligerDefaults.TabString = {
            closeMessage: '关闭当前页',
            closeOtherMessage: '关闭其他',
            closeAllMessage: '关闭所有',
            reloadMessage: '刷新'
    };
	// 布局
	$("#main_layout").ligerLayout({
		leftWidth : 190,
		height : '100%',
		heightDiff : -34,
		space : 4,
		onHeightChanged : f_heightChanged
	});

	var height = $(".l-layout-center").height();

	// Tab
	$("#framecenter").ligerTab({
		height : height,
		showSwitchInTab : true,
		showSwitch : true,
		onAfterAddTabItem : function(tabdata) {
			tabItems.push(tabdata);
			saveTabStatus();
		},
		onAfterRemoveTabItem : function(tabid) {
			for (var i = 0; i < tabItems.length; i++) {
				var o = tabItems[i];
				if (o.tabid == tabid) {
					tabItems.splice(i, 1);
					saveTabStatus();
					break;
				}
			}
		},
		onReload : function(tabdata) {
			var tabid = tabdata.tabid;
		}
	});

	// 面板
	$("#accordion1").ligerAccordion({
		height : height - 24,
		speed : null
	});

	$(".l-link").hover(function() {
		$(this).addClass("l-link-over");
	}, function() {
		$(this).removeClass("l-link-over");
	});

	tab = liger.get("framecenter");
	accordion = liger.get("accordion1");
	tree = liger.get("tree1");
	$("#pageloading").hide();

	pages_init();

	// 把没有子菜单sndMenu隐藏起来
/*	$(".sndMenu").each(function(idx, el) {
		var jqElm = $(el);
		if ($.trim(jqElm.html()) == "") {
			var prev = jqElm.prev();
			jqElm.remove();
			prev.remove();
		}
	});*/
});
function f_heightChanged(options) {
	if (tab)
		tab.addHeight(options.diff);
	if (accordion && options.middleHeight - 24 > 0)
		accordion.setHeight(options.middleHeight - 24);
}
function f_closeTab(tabid) {
	if (tab.isTabItemExist(tabid)) {
		tab.removeTabItem(tabid);
	}
}
function f_addTab(tabid, text, url, reload) {
	var turl = url;
	if (url.indexOf("?") <= 0) {
		turl = turl + "?random=" + Math.random();
	} else {
		turl = turl + "&random=" + Math.random();
	}
	if (tab.isTabItemExist(tabid)) {
		tab.overrideTabItem(tabid, {
			tabid : tabid,
			text : text,
			url : turl
		});
		tab.selectTabItem(tabid);
		if (!reload) {
			var obj = $(".l-tab-content-item[tabid=" + tabid + "] iframe[id=" + tabid + "]");
			obj.attr("src", turl);
			$("li[tabid=" + tabid + "] a:first-child").html(text);
		}
	} else {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : turl
		});
	}
}

function pages_init() {
	var tabJson = $.cookie('liger-home-tab');
	if (tabJson) {
		var tabitems = JSON2.parse(tabJson);
		for (var i = 0; tabitems && tabitems[i]; i++) {
			f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
		}
	}
}
function saveTabStatus() {
	// $.cookie('liger-home-tab', JSON2.stringify(tabItems));
}

function getQueryString(name) {
	var now_url = document.location.search.slice(1), q_array = now_url.split('&');
	for (var i = 0; i < q_array.length; i++) {
		var v_array = q_array[i].split('=');
		if (v_array[0] == name) {
			return v_array[1];
		}
	}
	return false;
}