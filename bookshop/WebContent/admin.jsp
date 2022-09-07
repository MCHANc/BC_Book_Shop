<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.admin{
		
		position:relative;
		text-align: center;
	}
	.admin h1{
		position:relative;
		
	}
	.admin p{
		position:relative;
		top:10px;
		
		font-size: 20px;
	}
	.admin p a{
		text-decoration: none;
		position:relative;
		
	}
	.dh{
		
		height:43px;
		position:relative;
		background-color: #f5f5f5;
		
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
		color:black;
		text-decoration: none;
	}
	.dh a:HOVER {
		color:red;
	}
</style>
</head>
<body>
<div class="admin">
<h1 >管理界面</h1>
<p>欢迎您：
<c:choose>
	<c:when test="${sessionScope.level !=null }">
		<c:out value="${sessionScope.userName}"/>
		<a href="login.jsp">切换用户</a>
	</c:when>
	<c:when test="${sessionScope.level ==null }">
		<c:redirect url="login.jsp"/>
	</c:when>
</c:choose>
 </p>
	<div class="dh">
		<ul id="nav">
			<li><a href="userManageServlet">用户管理</a></li>
			<li><a href="bookManageServlet">图书管理</a></li>
			<li><a href="orderManageServlet">订单管理</a></li>
			<li><a href="book_view.jsp">返回主页</a></li>
			<li><a href="exit.jsp">退出登录</a></li>
		</ul>
	</div>
</div>
</body>

</html>