<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/resources/css/jquery.mobile-1.0.min.css'/>" rel="stylesheet" type="text/css"/>
<script src="<c:url value='/resources/js/jquery-1.6.4.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/jquery.mobile-1.0.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div data-role="page" id="page">
	<div data-role="header">
		<h1>第 1 页 &nbsp;&nbsp;目录</h1>
	</div>
	<div data-role="content">
<form:form modelAttribute="user" action="/doume/register/user_a"
		method="POST">
		<div>
			用户帐号：<form:input path="userName"></form:input>
			<form:errors path="userName" cssClass="errorClass"></form:errors>
		</div>
		<div>
			用户密码：<form:password path="password"></form:password>
			<form:errors path="password" cssClass="errorClass"></form:errors>
		</div>
		<div>
			记住密码：<form:checkbox path="rememberpassword"/>自动登陆：<form:checkbox path="autologin"/>
		</div>
		<div>
			<form:button>注册</form:button><input type="submit" value="登陆">
		</div>
	</form:form>
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>
</body>
</html>