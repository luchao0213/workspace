<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Administrator</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1" name="viewport">
<link href="${resourceRoot}/css/jquery.mobile.min.css" rel="stylesheet" type="text/css"/>
<script src="${resourceRoot}/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/customer.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/jquery.mobile.min.js" type="text/javascript"></script>
<link href="${resourceRoot}/css/style.css" rel="stylesheet"/>
</head>
<body>
	<!-- -->
	<div data-role="page" id="customer-home" data-theme="a"
		data-title="Home">
		<div data-role="header" data-position="fixed"
			class="segmented-control">
			<div data-role="controlgroup" data-type="horizontal">
				<a href="#login" data-role="button" class="ui-control-active">Login</a>
				<a href="#logout" data-role="button" class="ui-control-inactive">Logout</a>
			</div>
		</div>
		<div data-role="content" data-theme="c">
			<h4>New Message</h4>
			<fieldset>
			<a href="#news">
			<h3>Title</h3>
			<img alt="" src="images/news.jpg">
			</a>
			</fieldset>
			
			<h4>Goods</h4>
			<div class="ui-grid-c" style="text-align: center;">
				<div class="ui-block-a">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-b">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-c">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-d">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-a">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-b">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-c">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
				<div class="ui-block-d">
					<a href="#search" data-role="button" width="50">Meat</a>
				</div>
			</div>

		</div>
		<div data-role="footer" data-position="fixed" class="tabbar"
			data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="#customer-home" data-icon="home"
						class="ui-btn-active ui-state-persist"></a></li>
					<li><a href="#search" data-icon="search"></a></li>
					<li><a href="#customer-private" data-icon="star"></a></li>
					<li><a href="#customer-more" data-icon="grid"></a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- -->
	<div data-role="page" id="search" data-title="Search">
		<div data-role="header" class="segmented-control">
			<label for="search" class="ui-hidden-accessible">Search...</label> 
			<input type="search" id="search" placeholder="Search" />
			<fieldset data-role="controlgroup" data-type="horizontal">
				<select name="business-type" id="business-type"
					data-native-menu="false" data-theme="a">
					<option value="65535">全部</option>
					<option value="1">Eating</option>
					<option value="8">Dress</option>
					<option value="2">Entertainment</option>
					<option value="4">Use</option>
				</select> <select name="business-type" id="goods-type"
					data-native-menu="false" data-theme="a">
					<option value="1">住宿</option>
					<option value="8">餐厅</option>
					<option value="2">丽人</option>
				</select>
			</fieldset>
		</div>
		<div data-role="content">
			<ul data-role="listview">
				<li><a href="#customer-goods">
					<img src="images/goods.jpg">
						<h2>Goods</h2>
						<p>Google Chrome 很好的</p>
				</a></li>
				<li><a href="#customer-business"> 
					<img src="images/goods.jpg">
						<h2>Business</h2>
						<p>Google Chrome 不错的</p>
				</a></li>
			</ul>
		</div>

		<div data-role="footer" data-position="fixed" class="tabbar"
			data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="#customer-home" data-icon="home"></a></li>
					<li><a href="#search" data-icon="search"
						class="ui-btn-active ui-state-persist"></a></li>
					<li><a href="#customer-private" data-icon="star"></a></li>
					<li><a href="#customer-more" data-icon="grid"></a></li>
				</ul>
			</div>
		</div>
	</div>

	<!--  -->
	<div data-role="dialog" id="login">
		<div data-role="header">
			<h2>Customer Register</h2>
		</div>
		<div data-role="content">
			<form>
				<label for="userName">UserName:</label>
				<input type="text" name="userName" id="userName" />
				<label for="password">Password:</label>
				<input type="password" name="password" id="password" />
				<label for="confirm">Confirm:</label> 
				<input type="text" name="confirm" id="confirm" />
				<a id="btn-register" href="#customer-register" data-role="button" data-inline="true">Register</a>
				<a id="btn-login" href="#" data-role="button" data-rel="back" data-inline="true">Login</a>
			</form>
		</div>
	</div>
	
	<!--  -->
	<div data-role="page" id="customer-register">
		<div data-role="header">
			<h2>Customer Register</h2>
		</div>
		<div data-role="content">
			<form>
				<label for="userName">UserName:</label>
				<input type="text" name="userName" id="userName" />
				<label for="password">Password:</label>
				<input type="password" name="password" id="password" />
				<label for="password">Confirm:</label> 
				<input type="password" name="password2" id="password2" />
				<a id="btn-register" href="#" data-role="button" data-inline="true">Register</a>
				<a id="btn-login" href="#" data-role="button" data-rel="back" data-inline="true">Cancel</a>
			</form>
		</div>
		<div data-role="footer"></div>
	</div>

	<!-- -->
	<div data-role="page" id="customer-private" data-title="Private">
		<div data-role="header">
			<h1>Private</h1>
		</div>
		
		<div data-role="content">
			<div data-role="collapsible-set">	
				<div data-role="collapsible">
					<h1>Private Infomation</h1>
					<div data-role="collapsible">
						<h1>User Infomation</h1>
						<form>
							<label for="userName">UserName:</label>
							<input type="text" name="userName" id="userName" />
							<label for="password">Password:</label>
							<input type="password" name="password" id="password" />
							<label for="password">Confirm:</label>
							<input type="password" name="password2" id="password2" />
							<a id="user-update" href="#" data-role="button">Update</a>
						</form>
					</div>
					<div data-role="collapsible">
						<h1>Customer Infomation</h1>
						<form>
							<div data-role="fieldcontain">
								<label for="sex">Sex:</label>
								<select name="sex" id="sex" data-role="slider">
									<option value="0">Female</option>
									<option value="1" selected>Male</option>
								</select>
								<label for="birthday">Birthday:</label>
								<input type="date" name="birthday" id="birthday" />
								<label for="phoneno">tel:</label>
								<input type="tel" name="phoneno" id="phoneno" />
								<a id="customer-update" href="#" data-role="button">Update</a>
							</div>
						</form>
					</div>
				</div>
				
				<div data-role="collapsible">
					<h1>Credit</h1>
					<ul data-role="listview" data-filter="true"
						data-filter-placeholder="商家过滤">
						<li>
							<a href="#customer-business?userId=1">
								<img src="images/business.jpg">
								<h2>bName</h2>
								<p>creditValue:</p>
							</a> 
						</li>
						<li>
							<a href="#customer-business?userId=1">
								<img src="images/business.jpg"/>
								<h2>bName</h2>
								<p>creditValue:</p>
							</a>
						</li>
					</ul>
				</div>

				<div data-role="collapsible">
					<h1>Orders</h1>
					<ul data-role="listview">
						<li data-role="list-divider">
							<a href="#orders?ordersId=1">ordersInfo</a> 
							<a href="orders?ordersId=1&option=Confrim">Confrim</a> 
							<a href="orders?ordersId=1&option=Refuse">Refuse</a>
							<span class="ui-li-count">$25</span>
						</li>
						<li data-role="list-divider">
							<a href="#orders?ordersId=1">ordersInfo</a> 
							<a href="orders?ordersId=1&option=Confrim">Confrim</a> 
							<a href="orders?ordersId=1&option=Refuse">Refuse</a>
							<span class="ui-li-count">$25</span>
						</li>
					</ul>

				</div>

				<div data-role="collapsible">
					<h1>Message</h1>
					<ul data-role="listview" data-filter="true">
						<li>
							<h1>Time:
							<a href="#">Sender</a>
							<a href="#message-send">Reply</a>
							</h1>
							<h2>subject:</h3>
							<h3>Content:How are you?</h2>
						</li>
					</ul>
				</div>
				
				<div data-role="collapsible">
					<h1>Enshrine</h1>
					<ul data-role="listview" data-filter="true">
						<li>
							<a href="#customer-goods">
							<img src="images/goods.jpg">
								<h2>Goods</h2>
								<p>Google Chrome 很好啦</p>
							</a>
						</li>
						<li><a href="#customer-business">
							<img src="images/goods.jpg">
								<h2>Business</h2>
								<p>Google Chrome 不错呢</p>
							</a>
						</li>
					</ul>
				</div>
				<div data-role="collapsible">
					<h1>Comment</h1>
					<ul data-role="listview">
						<li>
							<h1>Orders#goods1:
							<a href="#comment">comment</a>
							</h1>
						</li>
						<li>
							<h1>Orders#goods2:
							<a href="#comment">comment</a>
							</h1>
						</li>
					</ul>
				</div>
				<div data-role="collapsible">
					<h1>Deal</h1>
					<label for="months">Months:</label>
					<input id="months" name="months" type="date">
					<ol data-role="listview" data-filter="true">
						<li>
							<h1>Time|seller|buyer|dealMsg|value</h1>
						</li>
						<li>
							<h1>Time|seller|buyer|dealMsg|value</h1>
						</li>
					</ol>
				</div>
			</div>
		</div>
		
		<div data-role="footer" data-position="fixed" class="tabbar"
			data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="#customer-home" data-icon="home"></a></li>
					<li><a href="#search" data-icon="search"></a></li>
					<li><a href="#customer-private" data-icon="star" class="ui-btn-active ui-state-persist"></a></li>
					<li><a href="#customer-more" data-icon="grid"></a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 	-->
	<div data-role="page" id="orders" data-theme="a" data-title="Home">
		<div data-role="header" data-position="fixed">
			<h1>Orders</h1>
		</div>
		<div data-role="content" data-theme="c">
			<div data-role="collapsible">
					<h1>OrdersId|total<span class="ui-li-count">$100</span></h1>
					<ul data-role="listview" data-inset="true">
						<li>
							<a href="#goods?goodsId=1">
							<img src="images/goods.jpg">
							<h2>goodsName </h2>
							<span class="ui-li-count">1</span>
							<p>Price | Creidt</p>
							<p>infomation</p>
							</a>
						</li>	
						<li>
							<a href="#goods?goodsId=1">
							<img src="images/goods.jpg">
							<h2>goodsName </h2>
							<span class="ui-li-count">1</span>
							<p>Price | Creidt</p>
							<p>infomation</p>
							</a>
						</li>
					</ul>
			</div>
		</div>

		<div data-role="footer" data-position="fixed" class="tabbar" data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="#customer-home" data-icon="home"></a></li>
					<li><a href="#search" data-icon="search"></a></li>
					<li><a href="#customer-private" data-icon="star" class="ui-btn-active ui-state-persist"></a></li>
					<li><a href="#customer-more" data-icon="grid"></a></li>
				</ul>
			</div>
		</div>
	</div>

	
	<!-- -->
	<div data-role="page" id="customer-more" data-theme="a" data-title="More">
		<div data-role="header" data-position="fixed">
			<h1>More</h1>
		</div>
		<div data-role="content" data-theme="c">
			<div data-role="collapsible-set">
				<div data-role="collapsible">
					<h1>About</h1>
					<p>Maidou Company</p>
				</div>
			</div>
		</div>
		<div data-role="footer" data-position="fixed" class="tabbar" data-id="main-tabbar">
			<div data-role="navbar" class="tabbar">
				<ul>
					<li><a href="#customer-home" data-icon="home"></a></li>
					<li><a href="#search" data-icon="search"></a></li>
					<li><a href="#customer-private" data-icon="star"></a></li>
					<li><a href="#customer-more" data-icon="grid" class="ui-btn-active ui-state-persist"></a></li>
				</ul>
			</div>
		</div>
	</div>

	<!--  -->
	<div data-role="page" id="customer-business">
		<div data-role="header">
			<a href="#customer-home" data-role="button" data-rel="back" data-icon="back">Back</a>
			<h2>Business Name</h2>
			<a href="#customer-menu" data-role="button" data-icon="star">Menu</a>
		</div>
		<div data-role="content">
			<h1>Business HomePage</h1>
			<div data-role="collapsible">
				<h2>Fruit</h2>
				<ul data-role="listview">
					<li>
						<a href="#customer-goods">
						<img src="images/goods.jpg">
						<h3>Banana | SellCount | Score</h3>
						<p>Price:$10.0&nbsp;nbsp;&nbsp;nbsp; Credit: 10/100</p>
						<p>It's so big!and you must be like!</p>
						</a>
					</li>
				</ul>
			</div>

			<div data-role="collapsible">
				<h2>Meat</h2>
				<ul data-role="listview">
					<li>
						<a href="#customer-goods">
						<img src="images/goods.jpg">
						<h3>Banana | SellCount | Score</h3>
						<p>Price:$10.0&nbsp;nbsp;&nbsp;nbsp; Credit: 10/100</p>
						<p>It's so big!and you must be like!</p>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<!--  -->
	<div data-role="page" id="customer-goods">
		<div data-role="header">
			<a href="#customer-home" data-role="button" data-rel="back" data-icon="back">Back</a>
			<h1>goodsName</h1>
			<a href="#customer-menu" data-role="button" data-icon="star">Menu</a>
		</div>
		<div data-role="content">
			<div data-role="fieldcontain">	
				<h1>GoodsName</h1>
				<img src="images/goods.jpg">
				<h2>Price|Credit</h2>
				<a href="#" data-role="button" data-inline="true">Add2Menu</a>
				<a href="#" data-role="button" data-inline="true">Enshrine</a>
				<p>infomation:very good goods.very good goods.very good goods.very good goods.</p>
			</div>
			<div data-role="collapsible">
			<h1>Comment</h1>
			<ul data-role="listview">
				<li>Coment1:</li>
				<li>Coment2:</li>
				<li>Coment3:</li>
			</ul>
			</div>
		</div>
		<div data-role="footer">
			<h3>footer</h3>
		</div>
	</div>
	<div data-role="page" id="customer-menu">
		<div data-role="header">
			
			<a href="#customer-home" data-role="button" data-rel="back" data-icon="back">Back</a>
			<h1>Menu</h1>
			<a href="#customer-delete" data-role="button" data-icon="delete">Delete</a>
		</div>
		<div data-role="content">
			<ol>
				<li>
					<div data-role="controlgroup" data-type="horizontal">
						<a href="#customer-goods">Name</a>&nbsp;nbsp;Count:<count>10</count>&nbsp;nbsp;$:<count>10</count>
					</div>
					<div data-role="ui-grid-d">
						<div class="ui-block-a">
							<select data-role="slider">
								<option value="0">Credit</option>
								<option value="1" selected="true">Cash</option>
							</select>
						</div>
						<div class="ui-block-b">
							<button data-icon="plus" data-iconpos="notext"></button>
						</div>
						<div class="ui-block-c">
							<button data-icon="minus" data-iconpos="notext"></button>
						</div>
						<div class="ui-block-e">
							<button data-icon="delete" data-iconpos="notext"></button>
						</div>
					</div>
				</li>
			</ol>
		</div>
		<div data-role="footer">
			<h1>
			<a href="#addOrders" data-role="button">Submit</a>
			</h1>
		</div>
	</div>
	
	<!-- -->
	<div data-role="page" id="news">
		<div data-role="header">
			<a href="#customer-home" data-role="button" data-rel="back" data-icon="back">Back</a>
			<h1>News Title</h1>
		</div>
		<div data-role="content">
			<h3>Title|Time</h3>
			<img alt="" src="images/news.jpg">
			<p>content of news.content of news.content of news.content of news.content of news.content of news.</p>
		</div>
	</div>
	
	<!--    dialog    -->
	<div data-role="dialog" id="login">
		<div data-role="header">
			<h1>Login</h1>
		</div>
		<div data-role="content">
			<form>
				<label for="userName">UserName:</label> <input type="text" name="userName" id="userName" /> <label for="password">Password:</label>
				<input type="password" name="password" id="password" />
			</form>
			<a id="btn-register" href="#customer-register" data-role="button" data-inline="true">Register</a>
			<a id="btn-login" href="#" data-role="button" data-inline="true">Login</a>
		</div>
	</div>
	
	<!--  -->
	<div data-role="dialog" id="comment">
		<div data-role="header">
			<h3>Comment</h3>
		</div>
		<div data-role="content">
			<form method="post" action="demoform.html">
				<div data-role="fieldcontain">
					<input type="text" class="ui-hidden-accessible" name="tag" id="tag">
					<label for="points">Points:</label>
					<input type="range" name="points" id="points" value="5" min="0" max="10">
					<label for="content">content:</label>
					<textarea name="content" id="content"></textarea>
				</div>
			</form>
			<a id="btn-register" href="#" data-role="button" data-inline="true">Submit</a>
			<a id="btn-login" href="#customer-home" data-role="button"
				data-rel="back" data-inline="true">Cancel</a>
		</div>
	</div>
		
	<!--  -->
	<div data-role="dialog" id="message-send">
		<div data-role="header">
			<h3>Message</h3>
		</div>
		<div data-role="content">
			<form method="post" action="demoform.html">
				<div data-role="fieldcontain">
					<input type="text" class="ui-hidden-accessible"  name="senderId" id="senderId">
					<input type="text" class="ui-hidden-accessible"  name="receiverId" id="receiverId">
					<input type="text" class="ui-hidden-accessible"  name="msgType" id="msgType">
					<label for="content">content:</label>
					<textarea name="content" id="content"></textarea>
				</div>
			</form>
			<a id="btn-register" href="#" data-role="button" data-inline="true">Submit</a>
			<a id="btn-login" href="#customer-home" data-role="button"
				data-rel="back" data-inline="true">Cancel</a>
		</div>
	</div>
</body>
</html>