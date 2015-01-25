<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Administrator</title>
<meta content="width=device-width,initial-scale=1" name="viewport">
<link href="${resourceRoot}/css/jquery.mobile.min.css" rel="stylesheet" type="text/css"/>
<script src="${resourceRoot}/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/jquery.mobile.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/admin.js" type="text/javascript"></script>
<link href="${resourceRoot}/css/style.css" rel="stylesheet"/>
<style type="text/css">.errorClass{color:red}</style>
</head>
<body>
	<div data-role="page" id="login">
		<div data-role="header">
			<h1>Login</h1>
		</div>
		<div data-role="content">
			帐号信息:
			<form:form id="formAdminLogin" action="/doume/admin/login" method="POST" modelAttribute="user">
				<div>
					<label for="userName">用户帐号：</label>
					<form:input id="userName" name="userName" value="doume" path="userName" pattern="[A-Za-z0-9_.]{4,31}" type="text" maxlength="30"></form:input>
					<form:errors path="userName" cssClass="errorClass"></form:errors>
				</div>
				<div>
					<label for="password">用户密码：</label>
					<form:password id="password" path="password" value="123456"  minlength="6" maxlength="31"></form:password>
					<form:errors path="password" cssClass="errorClass"></form:errors>
				</div>
				<div>
					<input type="submit" value="Login"/>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
