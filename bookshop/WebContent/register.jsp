<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		background: url("/bookshop/img/bg.jpg");
		background-size: 100%,100%;
   	background-attachment: fixed; 
	}
		.login{
			border:1px solid black;
			position:absolute;
			width:500px;
	 		height:500px;
	  		margin-left:-250px;
	   		left:48%;
	   		top:10%;
	   		padding:80px 40px;
	   		background: white;
	   		border-radius: 10px; 		
	   		opacity: 0.9;
	   		
		}
		h1{
			position:relative;
			display: black;
			font-size: 2em;
			text-align: center;
		    font-weight: bold;
		}
		.name{
			position:relative;
			left:15%;
			font-size: 20px;
		}
		.name input{
			position:relative;
			width:200px;
			height:20px;
			left:25px;
			font-size: 20px;
		}
		.pwd{
			position:relative;
			left:15%;
			font-size: 20px;
		}
		.pwd input{
			position:relative;
			width:200px;
			height:20px;
			left:60px;
			font-size: 20px;
		}
		.pwd2{
			position:relative;
			left:15%;
			font-size: 20px;
		}
		.pwd2 input{
			position:relative;
			width:200px;
			height:20px;
			left:20px;
			font-size: 20px;
		}
		.admin{
			position:relative;
			left:15%;
			font-size: 20px;
		}
		.bt-login{
			position:relative;
			left:35%;
			font-size: 18px;
			width: 100px;
			height: 40px;
		}
		.a1{
			position:relative;
			text-decoration: none;
			font-size: 20px;
			color:black;
			left:25%;
		}
		.a1:HOVER {
			color:red;
		}
		.a2{
			position:relative;
			text-decoration: none;
			font-size: 20px;
			color:black;
			left:40%;
		}
		.a2:HOVER {
		color:red;
		}
		.p1{
		position:relative;
	    font-size: 20px;
	    color:red;
	    width:150px;
	   	height:30px;
	   	left:180px;
		}
</style>
</head>
<body>
<div class="login">
<form action="registerSerlet" method="post">
		
		<h1>注册</h1>
		<div class="name">用户名:&nbsp;&nbsp;<input type="text" name="userName" value="${requestScope.userName }" /></div>
		<br/>
		<div class="pwd">密码:<input type="password" name="password1" /></div>
		<br/>
		<div class="pwd2">确认密码:<input type="password" name="password2"/></div>
		<br/>
		<div class="admin">管理员注册<input type="checkbox" name="admin" value="admin" /></div>
		<br/>
		<button class="bt-login">注册</button>
		<br/><br/>
		<a class="a1" href="login.jsp">返回登录</a>
		<a class="a2" href="book_view.jsp" >返回主页</a>
	</form>

	<p class="p1">${requestScope.msg }</p>
</div>
</body>
</html>