var manager; // 航线
var airlineUrl = _CONTEXT_PATH + "/bd/airline/list.json";// list页面
var airlineListUrl = _CONTEXT_PATH + "/bd/airline/list.htm";
var airlineRemoveUrl = _CONTEXT_PATH + "/bd/airline/remove/";
var airlineModifyUrl = _CONTEXT_PATH + "/bd/airline/modify.htm";

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// 查询航线
function manager_search() {
	if ($("#s_popStatus").prop('checked'))
		var s_popStatus = 1;
	else
		var s_popStatus = "";
	if ($("#s_status").prop('checked'))
		var s_status = 1;
	else
		var s_status = "";

	var s_departureAirport = $("#s_departureAirport").val();
	var s_arrivalAirport = $("#s_arrivalAirport").val();
	var s_relDepartureAirport = "";
	var s_relArrivalAirport = "";
	var isMapping = ""; // 映射
	if ($("#s_representation").prop('checked')) {
		// 获取映射机场参数
		s_relDepartureAirport = $("#s_relDepartureAirport").val();
		s_relArrivalAirport = $("#s_relArrivalAirport").val();
		isMapping = "true";
	}
	manager.setOptions({
		parms : {
			"s_departureAirport" : s_departureAirport,
			"s_arrivalAirport" : s_arrivalAirport,
			"s_relDepartureAirport" : s_relDepartureAirport,
			"s_relArrivalAirport" : s_relArrivalAirport,
			"s_popStatus" : s_popStatus,
			"s_status" : s_status,
			"isMapping" : isMapping
		}
	// 传参
	});
	manager.changePage('first');
	manager.loadData(true);// 刷新
}
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// 开始
$(function() {
	// 航线Grid
	manager = $("#content")
			.ligerGrid(
					{
						checkbox : true,
						switchPageSizeApplyComboBox : false,
						url : airlineUrl,
						enabledSort : false,// 排序
						columns : [
								{
									display : $.i18n.prop("bd.airl.departureAirport"),
									name : 'departureAirport',
									width : 100
								},
								{
									display : $.i18n.prop("bd.airl.arrivalAirport"),
									name : 'arrivalAirport',
									width : 100
								},
								{
									display : $.i18n.prop("bd.airl.relDepartureAirport"),
									name : 'relDepartureAirport',
									width : 100
								},
								{
									display : $.i18n.prop("bd.airl.relArrivalAirport"),
									name : 'relArrivalAirport',
									width : 100
								},
								{
									display : $.i18n.prop("bd.airl.popStatus"),
									name : 'popStatus',
									width : 100,
									render : function(rowdata, index, value) {
										if (value == "1") {
											return $.i18n.prop("bd.airl.airlineYes");
										} else {
											return $.i18n.prop("bd.airl.airlineNo");
										}
									}
								},
								{
									display : $.i18n.prop("bd.airl.status"),
									name : 'status',
									width : 100,
									render : function(rowdata, index, value) {
										if (value == "1") {
											return $.i18n.prop("bd.airl.airlineYes");
										} else {
											return $.i18n.prop("bd.airl.airlineNo");
										}
									}
								},
								{
									display : $.i18n.prop("bd.airl.representation"),
									name : 'representation',
									width : 100,
									render : function(rowdata, index, value) {
										if (rowdata.relDepartureAirport != null
												|| rowdata.relArrivalAirport != null) {
											return $.i18n.prop("bd.airl.airlineYes");
										} else {
											return $.i18n.prop("bd.airl.airlineNo");
										}
									}
								} ],
						data : [],
						parms : {},
						pageSize : 10,
						pageSizeOptions : [ 10, 20, 50 ],
						width : 'auto',
						height : '95%',
						onSuccess : function(data, grid) {
						},
						enabledEdit : true,// 可编辑表格 在colums中要添加 editor
						clickToEdit : false,// 点击编辑

						onSelectRow : function(rowdata, rowid, rowobj) {
							// $("#txtrowindex").val(rowid); 启用鼠标选中行 ，不要写
							// checkbox: true,
							var rows = manager.getSelectedRows();
							if (rows) {
								for (var i = 0; i < rows.length; i++) {
									if (rows[i].__id != rowid) {
										manager.unselect(rows[i].__id);
									}
								}
								var selected = manager.getSelected();
								var rows = manager.getSelecteds();
							}
						}
					});

});
// 编辑航线
var edit_airline = function() {
	var selected = manager.getSelected();
	if (selected) {
		var id = selected.id;
		window.location.replace(airlineModifyUrl + "?id=" + id);
	} else {
		$.ligerDialog.question($.i18n.prop("bd.com.selDetailView"));
	}

}
// 删除航线
var del_airline = function() {

	var selected = manager.getSelected();
	var rows = manager.getSelecteds();
	if (selected) {
		var id = selected.id;
		$.ligerDialog
				.confirm(
						$.i18n.prop("bd.com.delRecord"),
						function(yes) {
							if (yes) {
								var _url = airlineRemoveUrl;
								_url += id + ".json";
								$
										.ajax({
											type : "DELETE",
											url : _url,
											data : "{}",
											contentType : "application/json; charset=utf-8",
											dataType : "json",
											success : function(data) {
												if (data.result == 'ok') {
													$.ligerDialog
															.success(
																	$.i18n.prop("common.msg.delete"),
																	function() {
																		window.location.href = airlineListUrl;
																	});
												}
											},
											error : function(msg) {
												alert(msg);
											}
										});
							}
						});
	} else {
		$.ligerDialog.question($.i18n.prop('bd.com.selDetailDel'));
	}
}

// 控制映射出发机场和映射到达机场时候是否为可输入状态
var checkboxSelected = function() {
	if ($("#s_representation").prop('checked')) {
		$("#s_relDepartureAirport").removeAttr("disabled");
		$("#s_relArrivalAirport").removeAttr("disabled");
	} else {
		$("#s_relDepartureAirport").attr("value", "");
		$("#s_relArrivalAirport").attr("value", "");
		$("#s_relDepartureAirport").attr("disabled", "true");
		$("#s_relArrivalAirport").attr("disabled", "true");
	}
}