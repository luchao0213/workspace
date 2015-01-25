<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>User register</title>
</head>
<body>
	User Info:
	<form:form modelAttribute="user" action="/doume/register/user_b"
		method="POST">
		<div>
			用户帐号：<form:input path="userName"></form:input>
			<form:errors path="userName" cssClass="errorClass"></form:errors>
		</div>		<div>
			用户密码：<form:password path="password"></form:password>
			<form:errors path="password" cssClass="errorClass"></form:errors>
		</div>
		<div>
			确认密码：<input type="password" name="password2" onkeydown="twicecheck()"></input>
		</div>
		<form:hidden path="userType" value="2"/>
		<div>
			<input type="submit" value="注册">
		</div>
	</form:form>
</body>
</html>