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
	<div data-role="page" id="news-upload">
		<div data-role="header">
		<a href="/doume/admin/getNews" data-role="button" data-icon="arrow-l">Back</a>
			<h3>News</h3>
		</div>
		<div data-role="content">
			<form>
				<label for="newsType">NewsType:</label>
				<select name="newsType"
					id="newsType" data-native-menu="false" multiple="multiple">
					<option value="0" selected>All</option>
					<option value="2">Drink</option>
					<option value="4">Play</option>
				</select>
				<label for="title">Title:</label>
				<input type="text" name="title" id="title" />
				<label for="content">Content:</label>
				<textarea rows="" cols="" name="content" id="content"></textarea>
				<label for="file">Picture:</label>
				<input type="file" name="file" id="file" />
				<input type="submit" value="Send"/>
			</form>
		</div>
	</div>
</body>
</html>
