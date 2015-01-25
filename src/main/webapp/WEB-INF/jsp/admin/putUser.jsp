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
</head>
<body>
	<!--  -->
	<div id="create-user" data-role="page">
		<div data-role="header">
			<h2>UserInfo</h2>
		</div>
		<div data-role="content">
			<form:form modelAttribute="tarUser" action="/doume/admin/putUser" method="post">
				<label for="userId">UserId:</label>
				<form:input id="userId" path="userId" type="number" maxlength="20" placeholder="Zero stand for Create" />
				<form:errors path="userId" cssClass="errorClass"></form:errors>
				<label for="userName">UserName:</label>
				<form:input id="userName" path="userName" type="text" maxlength="30"/>
				<form:errors path="userName" cssClass="errorClass"></form:errors>
				<label for="password">Password:</label>
				<form:input id="password" path="password" type="text" value="123456" />
				<form:errors path="password" cssClass="errorClass"></form:errors>
				<label for="userType">UserType:</label>
				<form:select id="userType" path="userType">
					<option value="1">Admim</option>
					<option value="2" selected>Business</option>
					<option value="4">Customer</option>
				</form:select>
				<label for="lock">Status:</label>
				<select id="locked" name="locked" data-role="slider">
					<option value="1">Locked</option>
					<option value="0" selected>Unlocked</option>
				</select>
				<input type="submit" data-role="button" value="Submit"/>
			</form:form>
		</div>
	</div>
</body>
</html>
