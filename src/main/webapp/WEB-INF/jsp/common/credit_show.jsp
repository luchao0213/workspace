<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>User register</title>
</head>
<body>
	<form:form modelAttribute="business" action="/doume/register/business"
		method="POST">
		<div>
			店名<form:input path="bname"></form:input>
			<form:errors path="bname" cssClass="errorClass"></form:errors>
		</div>
		<div>
			类别：<form:radiobuttons path="bType" value="1"/>吃
				 <form:radiobuttons path="bType" value="2"/>喝
		</div>
		<div>
			详细信息：<form:input path="information"></form:input>
			<form:errors path="information" cssClass="errorClass"></form:errors>
		</div>
		<div>
			地址：<form:input path="addr"></form:input>
			<form:errors path="addr" cssClass="errorClass"></form:errors>
		</div>
		<div>
			联系方式：<form:input path="phoneno"></form:input>
			<form:errors path="contact" cssClass="errorClass"></form:errors>
		</div>
	</form:form>
</body>
</html>