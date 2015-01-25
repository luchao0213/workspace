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
	<!-- -->
	<div data-role="page" id="admin-goods" data-theme="a" data-title="Goods">
		<div data-role="header">
			<a href="getPutGoods/0" data-role="button" class="ui-control-active">UploadGoods</a>
			<h3>Goods</h3>
			<form action="getGoods" method="GET">
			<label for="admin-gsearch" class="ui-hidden-accessible">Search:</label>
			<input type="text" id="admin-gsearch" name="goodsSearch" placeholder="search...">
			</form>
		</div>
		<div data-role="content" data-theme="c">
			<ul data-role="listview">
				<c:forEach var="item" items="${ObjectModel}">
				<li>
					<a href="getPutGoods/${item.goodsId}" data-role="button">
					<img alt="" src="${resourceRoot}/images/${item.mediaId}">
						<h1>${item.goodsName}</h1>
						<p>${item.description}</p>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div data-role="footer" data-position="fixed" class="tabbar"
			data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="getHome" data-icon="home">Home</a></li>
					<li><a href="getGoods" data-icon="search" class="ui-btn-active ui-state-persist">Goods</a></li>
					<li><a href="getNews" data-icon="star">News</a></li>
					<li><a href="getMore" data-icon="grid">More</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
