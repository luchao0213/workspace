<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>User register</title>
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
	帐号信息:
	<form:form modelAttribute="msg" method="POST">
		<div>
			Message：<form:input path="content"></form:input>
			<form:errors path="content" cssClass="errorClass"></form:errors>
		</div>
	</form:form>
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>
</body>
</html>