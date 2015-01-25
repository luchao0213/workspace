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
	<div id="update-business" data-role="page">
		<div data-role="header">
			<h2>Business</h2>
		</div>
		<div data-role="content">
			<form:form modelAttribute="tarBusiness" action="/doume/admin/updateBusiness" method="post">
				<label for="userId">UserId:</label>
				<form:input id="userId" path="userId" type="number" maxlength="20" placeholder="Null stand for Create" />
				<form:errors path="userId" cssClass="errorClass"></form:errors>
				<div data-role="fieldcontain">
					<label for="bName">ShopName:</label>
					<form:input id="bName" path="bName" type="text"/>
					<form:errors path="bName" cssClass="errorClass"></form:errors>
					<label for="bType">BusinessType:</label>
					<form:select id="bType" path="bType" data-role="none">
						<option value="1" selected>Eat</option>
						<option value="2">Drink</option>
						<option value="4">Play</option>
						<option value="8">Dress</option>
						<option value="16">Live</option>
						<option value="32">Transport</option>
						<option value="64">Live&amp;Eat</option>
					</form:select>
					<form:errors path="bType" cssClass="errorClass"></form:errors>
					<label for="information">information:</label>
					<form:textarea id="information" path="information" rows="" cols=""></form:textarea>
					<form:errors path="information" cssClass="errorClass"></form:errors>
					<label for="phoneno">tel:</label>
					<form:input id="phoneno" path="phoneno" type="tel"/>
					<form:errors path="phoneno" cssClass="errorClass"></form:errors>
					<label for="capacity">Capacity:</label>
					<form:input id="capacity" path="capacity" type="number" value="100" />
					<form:errors path="capacity" cssClass="errorClass"></form:errors>
					<label for="balance">Balance:</label>
					<form:input id="balance" path="balance" type="number" value="30" />
					<form:errors path="balance" cssClass="errorClass"></form:errors>
					<label for="ppm">PPM:</label>
					<form:input id="ppm" path="ppm" type="number" value="30" />
					<form:errors path="ppm" cssClass="errorClass"></form:errors>
					<label for="weight">Weight:</label>
					<form:input id="weight" path="weight" type="number" value="0" /> 
					<form:errors path="weight" cssClass="errorClass"></form:errors>
					<input type="submit" data-role="button" value="Update">
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
