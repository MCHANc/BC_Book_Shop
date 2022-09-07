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
.main-order{
		border:1px solid red;
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
}
.main-order table{
	position:relative;
	width:1000px;
	text-align: center;
}
 .main-order th{
	width:60px;
	height:30px;
}
.main-order button{
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
</style>
</head>
<body>

<div class="main-order">
<jsp:include page="index.jsp"/><br/>
<a class="a1" href="book_view.jsp">返回主页</a>
 <table border="1">
		<tr>
			<th>OrderId</th>
			<th>UserID</th>
			<th>Sum</th>
			<th>State</th>
			<th>详情</th>
		</tr>
		<c:forEach items="${orders}" var="o">
			<tr>
				<td>${o.orderId }</td>
				<td>${o.userId }</td>
				<td>${o.sum }</td>
				<td>${o.state }</td>
				<td><button name="bt" value="1" onclick="myorder(${o.orderId })">查看详情</button></td>
			</tr>
		
		</c:forEach>
	</table>
	<p class="p1">${requestScope.msg }</p> 
</div>
</body>
</html>
<script>
function myorder(orderId) {
	var id=orderId
	window.location.href="orderListServlet?orderId="+id;
}
</script>