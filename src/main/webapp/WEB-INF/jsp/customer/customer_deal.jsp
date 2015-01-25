<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="user" action="/doume/register/user_c"
		method="POST">
		<div>
			用户帐号：
			<form:input path="userName"></form:input>
			<form:errors path="userName" cssClass="errorClass"></form:errors>
		</div>
		<div>
			用户密码：
			<form:password path="password"></form:password>
			<form:errors path="password" cssClass="errorClass"></form:errors>
		</div>
		<div>
			确认密码：<input type="password" name="password2" onkeydown="twicecheck()"></input>
		</div>
		<form:hidden path="userType" value="4" />
		<div>
			<input type="submit" value="提交修改">
		</div>
	</form:form>
	<form:form modelAttribute="customer" action="/doume/register/customer"
		method="POST">
		<div>
			性别：<form:radiobutton path="sex" value="0"/>男
				 <form:radiobutton path="sex" value="1"/>女
		</div>
		<div>
			生日：<form:input path="birthday"></form:input>
			<form:errors path="birthday" cssClass="errorClass"></form:errors>
		</div>
		<div>
			年级：<form:input path="grade"></form:input>
			<form:errors path="grade" cssClass="errorClass"></form:errors>
		</div>
		<div>
			地址：<form:input path="addr"></form:input>
			<form:errors path="addr" cssClass="errorClass"></form:errors>
		</div>
		<div>
			联系方式：<form:input path="contact"></form:input>
			<form:errors path="contact" cssClass="errorClass"></form:errors>
		</div>
		<div>
			<input type="submit" value="提交修改">
		</div>
	</form:form>
</body>
</html>