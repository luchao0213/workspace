<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>jQuery Mobile Web 应用程序</title>
<link href="${resourceRoot}/css/jquery.mobile.min.css" rel="stylesheet" type="text/css"/>
<script src="${resourceRoot}/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/js/jquery.mobile.min.js" type="text/javascript"></script>
</head> 
<body> 

<div data-role="page" id="page">
	<div data-role="header">
		<h1>第 1 页 &nbsp;&nbsp;目录</h1>
	</div>
	<div data-role="content">	
		<ul data-role="listview">
			<li><a href="#page2">第 2 页 HTML 表单</a></li>
            <li><a href="#page3">第 3 页 page</a></li>
			<li><a href="#page4">第 4 页 listview</a></li>
			<li><a href="#page5">第 5 页 ui-grid-a</a></li>
            <li><a href="#page6">第 6 页 collapsible</a></li>
			<li><a href="#page7">第 7 页 component</a></li>
		</ul>		
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

<div data-role="page" id="page2">
	<div data-role="header">
		<h1>第 2 页</h1>
	</div>
	<div data-role="content">
    <form action="" method="get">
    账号：<input name="userName" type="text" size="20" maxlength="32">
    密码：<input name="password" type="password" size="20" maxlength="32">
    <div>
    性别：&nbsp;&nbsp;&nbsp;&nbsp;男<input name="sex" type="radio" value="1">&nbsp;&nbsp;&nbsp;&nbsp;女：<input name="sex" type="radio" value="0">
    </div>
    
    <div>
    爱好：<input name="hobby" type="checkbox" value="dota">
    <input name="hobby" type="checkbox" value="basketball" checked>
    </div>
    <div>
    文件：<input name="image" type="file" size="200000">
    </div>
    <div>picture:
    <input name="pic" type="image" src="${resourceRoot}/images/1.jpg" height="200px" hspace="3" vspace="3" border="3">
    </div>
    <div>
    <select name="using">
    	<option value="1">a</option>
    	<option value="2">b</option>
    </select>
    </div>
    
    <input name="login_submit" type="submit" value="登陆">
    </form>
	</div>
    
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

<div data-role="page" id="page3">
	<div data-role="header">
		<h1>第 3 页 page</h1>
	</div>
	<div data-role="content">
	  <div data-role="page" id="page8">
	    <div data-role="header">
	      <h1>标题</h1>
        </div>
	    <div data-role="content">内容</div>
	    <div data-role="footer">
	      <h4>脚注</h4>
        </div>
      </div>
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

<div data-role="page" id="page4">
	<div data-role="header">
		<h1>第 4 页 listview</h1>
	</div>
	<div data-role="content">
    
	  <ul data-role="listview" data-inset="true">
	    <li><a href="#">
	      <h3>页面</h3>
	      <p>Lorem ipsum</p>
	      <span class="ui-li-count">1</span></a></li>
	    <li><a href="#">
	      <h3>页面</h3>
	      <p>Lorem ipsum</p>
	      <span class="ui-li-count">1</span></a></li>
	    <li><a href="#">
	      <h3>页面</h3>
	      <p>Lorem ipsum</p>
	      <span class="ui-li-count">1</span></a></li>
      </ul>
    	
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

<div data-role="page" id="page5">
	<div data-role="header">
		<h1>第 5 页 ui-grid-a</h1>
	</div>
	<div data-role="content">
    
	  <div class="ui-grid-a">
	    <div class="ui-block-a">区块 1,1</div>
	    <div class="ui-block-b">区块 1,2</div>
	    <div class="ui-block-a">区块 2,1</div>
	    <div class="ui-block-b">区块 2,2</div>
	    <div class="ui-block-a">区块 3,1</div>
	    <div class="ui-block-b">区块 3,2</div>
      </div> 
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

<div data-role="page" id="page6">
	<div data-role="header">
		<h1>第 6 页 collapsible</h1>
	</div>
	<div data-role="content">
	  <div data-role="collapsible-set">
	    <div data-role="collapsible">
	      <h3>标题</h3>
	      <p>内容</p>
        </div>
	    <div data-role="collapsible" data-collapsed="true">
	      <h3>标题</h3>
	      <p>内容</p>
        </div>
	    <div data-role="collapsible" data-collapsed="true">
	      <h3>标题</h3>
	      <p>内容</p>
        </div>
      </div>
    
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>
<div data-role="page" id="page7">
	<div data-role="header">
		<h1>第 7 页</h1>
	</div>
	<div data-role="content">
	  <div data-role="fieldcontain">
	    <label for="textinput">文本输入:</label>
	    <input type="text" name="textinput" id="textinput" value=""  />
      </div>
	  <div data-role="fieldcontain">
	    <label for="passwordinput">密码输入:</label>
	    <input type="password" name="passwordinput" id="passwordinput" value=""  />
      </div>
	  <div data-role="fieldcontain">
	    <label for="textarea">文本区域:</label>
	    <textarea cols="40" rows="8" name="textarea" id="textarea"></textarea>
      </div>
	  <div data-role="fieldcontain">
	    <label for="selectmenu" class="select">选项:</label>
	    <select name="selectmenu" id="selectmenu">
	      <option value="option1">选项 1</option>
	      <option value="option2">选项 2</option>
	      <option value="option3">选项 3</option>
        </select>
      </div>
	  <div data-role="fieldcontain">
	    <fieldset data-role="controlgroup" data-type="horizontal">
	      <legend>选项</legend>
	      <input type="checkbox" name="checkbox1" id="checkbox1_0" class="custom" value="" />
	      <label for="checkbox1_0">选项</label>
	      <input type="checkbox" name="checkbox1" id="checkbox1_1" class="custom" value="" />
	      <label for="checkbox1_1">选项</label>
	      <input type="checkbox" name="checkbox1" id="checkbox1_2" class="custom" value="" />
	      <label for="checkbox1_2">选项</label>
        </fieldset>
      </div>
	  <div data-role="fieldcontain">
	    <fieldset data-role="controlgroup">
	      <legend>选项</legend>
	      <input type="radio" name="radio1" id="radio1_0" value="" />
	      <label for="radio1_0">选项</label>
	      <input type="radio" name="radio1" id="radio1_1" value="" />
	      <label for="radio1_1">选项</label>
	      <input type="radio" name="radio1" id="radio1_2" value="" />
	      <label for="radio1_2">选项</label>
        </fieldset>
      </div>
	  <div data-role="controlgroup">
	    <button>按钮1</button>
	    <button>按钮2</button>
      </div>
	  <div data-role="fieldcontain">
	    <label for="slider">值:</label>
	    <input type="range" name="slider" id="slider" value="0" min="0" max="100" />
      </div>
	  <div data-role="fieldcontain">
	    <label for="flipswitch">选项:</label>
	    <select name="flipswitch" id="flipswitch" data-role="slider">
	      <option value="off">关</option>
	      <option value="on">开</option>
        </select>
      </div>
	</div>
	<div data-role="footer">
		<h4>页面脚注</h4>
	</div>
</div>

</body>
</html>
