<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}" />
<title>ShiroDemo2</title>
<script src="${path}/scripts/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#verifyImg").click(
				function() {
					$("#message").text("");
					$(this).attr(
							"src",
							"<c:url value='/user/user/verify.html'/>?"
									+ Math.floor(Math.random() * 100));
				});
		$("#verifyCode").keyup(function() {
			var verifyCodeValue = $("#verifyCode").val().replace("/\s/g", "");
			var data = {"verifyCodeValue":verifyCodeValue};
			if (verifyCodeValue.length == 5) {
				$.ajax({
					url : "<c:url value='/user/user/checkVerifyCode.json'/>",
					type : "POST",
					contentType : 'application/json; charset=utf-8',
					data : JSON.stringify(data),
					success : function(response) {
						if (response.result == "ok") {
							$("#message").text("ok");
							$("#password").focus();
						} else if(response.result == "error"){
							$("#message").text("error");
							$("#verifyImg").attr("src","<c:url value='/user/user/verify.html'/>?"
											+ Math.floor(Math.random() * 100));
						}
					},
					error : function(response){
						alert("提交失败，请重新提交");
					}
				});
			}
		});
		$("#submit").click(function(){
			var action = "<c:url value='/user/user/check.json'/>";
			var data = {"username":$("#username").val(),"password":$("#password").val()};
			$.ajax({
				url : action,
				type:"POST",
				contentType:'application/json',
				data:JSON.stringify(data),
				success:function(response){
					if(response.result == "success"){
						window.location.href ="<c:url value='/user/index.html'/>";
					}else if(response.result == "error"){
						$("#error").text("登录失败，请重新提交");
						$("#verifyImg").attr("src","<c:url value='/user/user/verify.html'/>?"
								+ Math.floor(Math.random() * 100));
					}
				},
				error:function(response){
					alert("提交失败，请重新提交");
				}
			});
		});
		
	});
</script>
<STYLE type="text/css">
#login {
	width: 400px;
	height: 280px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	margin-top: -140px;
	border: 1px;
	align: center;
}

#form {
	width: 300px;
	height: 160px;
	position: relative;
	left: 50%;
	top: 50%;
	margin-left: -150px;
	margin-top: -80px;
	text-align: center";
}
</STYLE>
</head>
<body>
	<div id="login">
		<div id="form">
			<form id="myform">
				<div>
					<input type="text" id="username" name="username" placeholder="用户名" />
					<input type="password" id="password" name="password"
						placeholder="密码" />
				</div>
				<div>
					<input type="text" id="verifyCode" name="verifyCode" maxlength="5"
						placeholder="验证码" /> <img id="verifyImg" name="verifyImg"
						title="点击刷新" src="<c:url value='/user/user/verify.html'/>"
						style="margin-bottom: -4px"><span id="message"
						style="color: red"></span>
				</div>
				<div id="submit">
					<input type="submit" value="登录" />
					<div id="error" style="color: red"></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>