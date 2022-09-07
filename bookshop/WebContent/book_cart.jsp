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
.main-cart{
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
}
.main-cart table{
	position:relative;
	width:1000px;
}
.main-cart th{
	width:80px;
	height:50px;
}
.main-cart span{
	
	width:100%;
	height:120px;
	display: inline-block;
	
}
.main-cart span p{
	position:relative;
	left:20px;
	top:30px;
	font-size: 20px;
}
.cart-bottom{
	position:relative;
	bottom:0px;
}
.cart-bottom p{
	position:relative;
	font-size: 20px;
	left:10px;
}
.cart-bottom button{
	position:relative;
	font-size: 20px;
	left: 10px;
}
.cart-bottom a{
	position:relative;
	font-size: 20px;
	text-decoration: none;
	left:30px;
}
.bt-4{
	position:relative;
	width: 50px;
	height:30px;
	font-size: 20px;
	left:35px;
	top:5px;
}
.main-cart input{
	position:relative;
	width: 50px;
	height:30px;
	text-align:center;
	left:25px;
	top:5px;
	font-size: 18px;
}
.bt-5{
	position:relative;
	width: 50px;
	height:30px;
	left:30px;
	top:5px;
}

</style>
</head>
<body>
<div class="main-cart">
<jsp:include page="index.jsp"/><br/>
	<table border="1">
		<tr>
			<th>图片</th>
			<th>BookName</th>
			<th>price</th>
			<th></th>
			<th>Number</th>
			<th></th>
			<th>总价</th>
			<th>删除</th>
		</tr>
		
		<c:set var="sum" value="0.00"/>
		<c:forEach items="${carts}" var="c">
		
			<tr>
				<td><a><img src="${c.bookFile }" width="80px" height="80px"/></a></td>
				<td><span><p>${c.bookName }</p></span></td>
				<td><span><p>${c.bookPrice }</p></span></td>
				<td><button class="bt-4" onclick="sub(${c.bookid})">-</button></td>
				<td><input  type="text" onkeydown="change(event,${c.bookid})" id="${c.bookid}" name="num" value="${c.bookNumber }" /></td>
				<td><button class="bt-4" onclick="add(${c.bookid})">+</button></td>
				<td><span><p>${c.bookPrice*c.bookNumber }</p></span></td>
				<td><button class="bt-5" onclick="move(${c.bookid})">删除</button></td>
			</tr>
			
		
		</c:forEach>
	</table>
	<div class="cart-bottom">
		<p>合计：
		<%-- <%for(Cart c:carts){double s=c.getBookPrice()*c.getBookNumber();sum+=s;} %><%=sum %> --%>
		<c:forEach items="${carts}" var="c">
		<c:set var="s" value="${c.bookPrice*c.bookNumber }"/>
		<c:set var="sum" value="${sum+s }"/>
		</c:forEach>
		<c:out value="${sum }"/>
		</p>
		<button onclick="order(${sum})">立即购买</button>
		<a href="book_view.jsp">返回主页</a>
		<p>
		
		${requestScope.msg }
		</p>
	</div>
</div>
	
</body>
</html>
<script type="text/javascript">
	function add(id) {
		var bookId=id;
		var num=document.getElementById(id).value;
		num++;
		document.getElementById(id).value=num;
		var num=document.getElementById(id).value;
		window.location.href="cartServlet?bookId="+bookId+"&num="+num+"&flag="+1;
	}
	function sub(id) {
		var bookId=id;
		var num=document.getElementById(id).value;
		if(num>1){
			num--;
		}
		document.getElementById(id).value=num;
		var num=document.getElementById(id).value;
		window.location.href="cartServlet?bookId="+bookId+"&num="+num+"&flag="+1;
	}
	function change(e,id){
		var evt=window.event ||e;
		var num=document.getElementById(id).value;
		var bookId=id;
		if(evt.keyCode==13){
			document.getElementById(id).value=num;
			var num=document.getElementById(id).value;
			window.location.href="cartServlet?bookId="+bookId+"&num="+num+"&flag="+1;
		}
	}
	function move(id) {
		var bookId=id;
		window.location.href="cartServlet?bookId="+bookId+"&flag="+2;
	}
	function order(sum){
		var sum=sum;
		window.location.href="orderServlet?sum="+sum;
	}
</script>