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
	<form:form modelAttribute="goods" action="/doume/resource/goods_save" 
	enctype="multipart/form-data" method="POST">
		<div>
			商品名称：
			<form:input path="goodsName"></form:input>
			<form:errors path="goodsName" cssClass="errorClass"></form:errors>
		</div>
		<div>
			商品类型：
			<form:input path="goodsType"></form:input>
			<form:errors path="goodsType" cssClass="errorClass"></form:errors>
		</div>
		<div>
			商品简介：
			<form:input path="discription"></form:input>
			<form:errors path="discription" cssClass="errorClass"></form:errors>
		</div>
		<div>
			商品价格：
			<form:input path="price"></form:input>
			<form:errors path="price" cssClass="errorClass"></form:errors>
			积分价格：
			<form:input path="creditPrice"></form:input>
			<form:errors path="creditPrice" cssClass="errorClass"></form:errors>
			回馈积分：
			<form:input path="retCredit"></form:input>
			<form:errors path="retCredit" cssClass="errorClass"></form:errors>
		</div>		
		<div>
			图片：<form:input type="file" path="file" />
		</div>
		<div>
			<input type="submit" value="提交">
		</div>
	</form:form>
</body>
</html>