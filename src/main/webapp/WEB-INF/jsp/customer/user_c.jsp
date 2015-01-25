<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>User register</title>
<link href="${resourceRoot}/css/jquery.mobile.min.css" rel="stylesheet" type="text/css"/>
<script src="${resourceRoot}/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/jquery.mobile.min.js" type="text/javascript"></script>
</head>
<body>
<div data-role="page" id="page">
	<div data-role="header">
		<h1>第 1 页 &nbsp;&nbsp;目录</h1>
	</div>
	<div data-role="content">
	帐号信息:
	<form:form modelAttribute="user" action="user_c" method="POST">
		<div>
			用户帐号：<form:input path="userName"></form:input>
			<form:errors path="userName" cssClass="errorClass"></form:errors>
		</div>
		<div>
			用户密码：<form:password path="password"></form:password>
			<form:errors path="password" cssClass="errorClass"></form:errors>
		</div>
		<div>
			确认密码：<input type="password" name="password2" onkeydown="twicecheck()"></input>
		</div>
		<form:hidden path="userType" value="4"/>
		<div>
			<input type="submit" value="注册">
		</div>
	</form:form>
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>
</body>
</html>