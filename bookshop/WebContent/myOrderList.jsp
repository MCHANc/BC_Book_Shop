<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,mybean.*" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.main-orderlist{
		border:1px solid red;
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
}
.main-orderlist table{
	position:relative;
	width:1000px;
	text-align: center;
}

.main-orderlist button{
	width:100px;
	height:30px;
	cursor: pointer;
}
.a1{
	position:relative;
	text-decoration: none;
	left:430px;
	font-size: 20px;
	color:black;
}
.a1:HOVER {
	color:red;
}
.p1{
		position:relative;
	    font-size: 20px;
	 	 color:red;
	    width:150px;
	   	height:30px;
	   	left:455px;
}
.input-list{
	position:relative;
	text-align: center;
	width: 100px;
	height: 30px;
	cursor: default;
	border: none;
}
</style>
</head>
<body>
<div class="main-orderlist">
<jsp:include page="index.jsp"/><br/>

<c:set var="sum" value="0.00"/>
<c:set var="i" value="0"/>
<c:forEach items="${ols}" var="ol">

<form action="orderListServlet"  method="get" >
<table border="1" >
	<c:if test="${i==0 }" >
		<tr>
			<th>图片</th>
			<th>OrderId</th>
			<th>UserID</th>
			<th>BookName</th>
			<th>BookPrice</th>
			<th>number</th>
			<th>申请退款</th>
		</tr>
	</c:if>
			<tr>
				<td><a href=""><img src="${ol.bookFile }" width="80px" height="80px"/></a></td>
				<td><input class="input-list" type="text"  name="orderId" value="${ol.orderId }" readonly/></td>
				<td><input class="input-list" type="text"  name="userId" value="${ol.userId }" readonly/></td>
				<td><input class="input-list" type="text"  name="bookName" value="${ol.bookName }" readonly/></td>
				<td><input class="input-list" type="text"  name="bookPrice" value="${ol.bookPrice }" readonly/></td>
				<td><input class="input-list" type="text"  name="number" value="${ol.number }" readonly/></td>
				<td><button  name="bt" value="0">申请退款</button></td>
			</tr>
</table>
</form>	
<c:set var="i" value="${i+1 }"/>
</c:forEach>

<a class="a1" href="myOrderServlet">返回订单主页</a>
	<p class="p1">${requestScope.msg }</p>
</div>
</body>
</html>