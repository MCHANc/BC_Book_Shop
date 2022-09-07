<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

	.index{		
		position:relative;
		text-align: center;
	}
	.index h1{
		position:relative;
		
	}
	.index p{
		position:relative;
		top:10px;
		
		font-size: 20px;
	}
	.index p a{
		text-decoration: none;
		position:relative;
		
	}
	.dh{
		
		height:43px;
		position:relative;
		background-color:#fc5531;
		
		
	}
	.dh ul{
		padding:0px;
		margin:0px;
		position:relative;
		
	}
	#nav{
		
		width:750px;
		height:40px;
		margin-left:-375px;
		left:50%;
	}
	.dh li{
		list-style-type:none;
		float:left;
		margin-left: 30px;
		line-height: 40px;
		
	}
	.dh a{
		font-size: 25px;
		color:white;
		text-decoration: none;
	}
	.dh a:HOVER {
		color:black;
	}
</style>
</head>
<body>
<div class="index">
<h1 >图书销售网站</h1>
<p>欢迎您：
<c:choose>
	<c:when test="${sessionScope.id !=null }">
		<c:out value="${sessionScope.userName}"/>
		<a href="login.jsp">切换用户</a>
	</c:when>
	<c:when test="${sessionScope.id ==null }">
		<a href="login.jsp">登录</a>
	</c:when>
</c:choose>
 </p>
<div class="dh">
		<ul id="nav" >
			<li><a href="register.jsp">注册</a></li>
			<li><a href="bookServlet">浏览图书</a></li>
			<li><a href="cart_showServlet">购物车</a></li>
			<li><a href="myOrderServlet">我的订单</a></li>
			<li><a href="admin.jsp">后台管理</a></li>
			<li><a href="exit.jsp">退出登录</a></li>
		</ul>
	</div>
</div>
</body>
</html>