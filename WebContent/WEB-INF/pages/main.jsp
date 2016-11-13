<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="path" value="${pageContext.request.contextPath}" />
<title>ShiroDemo2</title>
</head>
<body>
	<div align="center">
		<h2>ShiroDemo2</h2>
		<div align="right">
			<a href="${path}/user/user/logout.html">退出登录</a>
		</div>
		Hello <b>${user.username}</b>,welcome to user main page!
	</div>
</body>
</html>