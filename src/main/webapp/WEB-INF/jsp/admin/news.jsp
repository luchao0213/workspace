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
	<!-- news -->
	<div data-role="page" id="admin-news">
		<div data-role="header" data-position="fixed" class="segmented-control">
			<div data-role="controlgroup" data-type="horizontal">
				<a href="sendMessage" data-role="button" class="ui-control-active">Send Message</a>
				<a href="getPutNews/0" data-role="button" class="ui-control-inactive">Upload News</a>
			</div>
		</div>
		<div data-role="content">
			<div data-role="collapsible">
				<h3>Message</h3>
				<ul data-role="listview" data-filter="true">
					<li>
						<h3>
							Sender|Receiver|Time|<a href="#">Delete</a>
						</h3>
						<p>Content</p>
					</li>
				</ul>
			</div>
			<div data-role="collapsible">
				<h3>News</h3>
				<ul data-role="listview" data-filter="true">
					<li>
						<h3>
							Sender|Receiver|Time|<a href="#">Delete</a>
						</h3>
						<p>Content</p>
					</li>
				</ul>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" class="tabbar"
			data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="getHome" data-icon="home">Home</a></li>
					<li><a href="getGoods" data-icon="search">Goods</a></li>
					<li><a href="getNews" data-icon="star" class="ui-btn-active ui-state-persist">News</a></li>
					<li><a href="getMore" data-icon="grid">More</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
