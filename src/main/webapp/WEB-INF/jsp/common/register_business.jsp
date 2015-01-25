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
			<input type="submit" value="注册">
		</div>
	</form:form>
	<form:form modelAttribute="business" action="/doume/register/business"
		method="POST">
		<div>
			店名：
			<form:input path="bName"></form:input>
			<form:errors path="bName" cssClass="errorClass"></form:errors>
		</div>
		<div>
			详细信息：
			<form:input path="information"></form:input>
			<form:errors path="information" cssClass="errorClass"></form:errors>
		</div>
		<div>
			电话：
			<form:input path="phoneno"></form:input>
			<form:errors path="phoneno" cssClass="errorClass"></form:errors>
		</div>
		<div>
			地址：
			<form:input path="addr"></form:input>
			<form:errors path="addr" cssClass="errorClass"></form:errors>
		</div>
		<div>
			地理坐标:<br/>
			经度：<form:input path="location.lng"></form:input>
			<form:errors path="information" cssClass="errorClass"></form:errors>
			纬度：<form:input path="location.lat"></form:input>
			<form:errors path="information" cssClass="errorClass"></form:errors>
		</div>
		<div>
			商家类型:<form:input path="bType"/>
		</div>
		<div>
			租金:<form:input path="ppm" ></form:input>元/月
		</div>
		<div>
			余额:<form:input path="balance" ></form:input>元
		</div>
		<div>
			使用量／容量:<form:input path="used" ></form:input>/<form:input path="capacity" ></form:input>
		</div>
		<div>
			<input type="submit" value="修改">
		</div>
	</form:form>
</body>
</html>