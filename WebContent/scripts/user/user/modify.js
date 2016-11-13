var airlineSaveUrl = _CONTEXT_PATH + "/bd/airline/save.json"; // 保存

$().ready(function() {
	$("#airline_form").validate({
		submitHandler : function(form) {
			$.ligerDialog.waitting($.i18n.prop("bd.com.butsum"));
			$(form).ajaxSubmit({
				// beforeSubmit : showRequest,
				success : showResponse,
				url : airlineSaveUrl,
				dataType : 'json',
				type : 'post',
				timeout : 10000
			});
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
		rules : {
			id : {

			},
			s_departureAirport : {
				required : true,
				isAirportCode : true
			},
			s_arrivalAirport : {
				required : true,
				isAirportCode : true
			},
			s_relDepartureAirport : {
				isAirportCode : true
			},
			s_relArrivalAirport : {
				isAirportCode : true
			},
			s_popStatus : {

			},
			s_status : {

			}
		},
		messages : {
			s_departureAirport : {
				required : $.i18n.prop("bd.air.inputcode"),
				isAirportCode : $.i18n.prop("bd.airl.isAirportCode")
			},
			s_arrivalAirport : {
				required : $.i18n.prop("bd.air.inputcode"),
				isAirportCode : $.i18n.prop("bd.airl.isAirportCode")
			},
			s_relDepartureAirport : {
				isAirportCode : $.i18n.prop("bd.airl.isAirportCode")
			},
			s_relArrivalAirport : {
				isAirportCode : $.i18n.prop("bd.airl.isAirportCode")
			}
		}

	});
	$("#s_representation").change(function() {
		if ($("#s_representation").get(0).selectedIndex == 0) {
			$("#s_relDepartureAirport").removeAttr("disabled");
			$("#s_relArrivalAirport").removeAttr("disabled");
		} else {
			$("#s_relDepartureAirport").attr("value", "");
			$("#s_relArrivalAirport").attr("value", "");
			$("#s_relDepartureAirport").attr("disabled", "true");
			$("#s_relArrivalAirport").attr("disabled", "true");
		}

	});

});

// pre-submit callback
function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	return true;
}

function showResponse(response, statusText) {
	$.ligerDialog.closeWaitting();
	if (statusText == 'success') {
		if (response.result == 'ok') {
			parent.$.ligerDialog.success($.i18n.prop("common.msg.save"),
					function() {
						window.location.replace(_CONTEXT_PATH
								+ "/bd/airline/list.htm");
					});
		} else {
			if (response.error == 'empty') {
				$.ligerDialog.error($.i18n.prop("bd.airl.empty"));
			} else if (response.error == 'samecode') {
				$.ligerDialog.error($.i18n.prop("bd.airl.samecode"));
			} else if (response.error == 'samecode1') {
				$.ligerDialog.error($.i18n.prop("bd.airl.samecode1"));
			} else if (response.error1 != null) {
				// 使用jQuery的国际化
				alert($.i18n.prop('bd.airl.sameline1', response.error1,
						response.error2));
			} else if (response.error3 != null) {
				alert($.i18n.prop('bd.airl.sameline2', response.error3,
						response.error4));
			}
		}
	} else {
		alert(response.error);
	}

}