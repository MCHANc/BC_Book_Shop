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
	.main-orderM{
		
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
	}
	.search-main{
	   	
	   background-color:#f5f5f5;
	   	position:relative;
	   	top:10px;
	   	height:50px;
	   }
.search{
	   
	   	position:relative;
	  	width:600px;
	  	height:36px;
	   	left:300px;
	   	top:5px;
	   }
	   .input-1{
	   	 width:300px;
	   	 height:30px;
	   	 font-size: 18px;
	   	
	   }
	   .bt-1{
	   	cursor: pointer;
	   	 width:86px;
	   	 height:35px;
	   
	   }
	   .st-1{
	   	width:86px;
	   	 height:35px;
	   	 cursor: pointer;
	   }
	   .manage-main{
	   	position:relative;
	   	top:20px;
	   	min-height: 300px;
	   	height: auto;
	   	overflow: auto;
	   }
	   .manage-main table{
			position:relative;
			width:1000px;
			text-align: center;
		}
		.manage-main input{
			position:relative;
			text-align: center;
			border: none;
			
		}
		.manage-main button{
			position:relative;
			width: 100px;
			cursor: pointer;
		}
		.manage-main select{
			
			cursor: pointer;
		}
		.main-change{
			position:relative;
		   
		   	top:20px;
		}
		.main-change table{
			position:relative;
			width:1000px;
			text-align: center;
		}
		.main-change input{
			position:relative;
			text-align: center;
			margin: 0px;
			border: none;
		}
		.main-change button{
			position:relative;
			cursor: pointer;
			width: 100px;
		}
		.bottom{
			position:relative;
		   	
		   	top:80px;
		}
		.bottom a{
			position:relative;
		   	text-decoration: none;
		   	color: black;
		   	font-size: 20px;
		   	left:400px;
		   	margin-left: 18px;
		}
		.bottom a:HOVER {
		 	color:red;
		}
		.p-rs{
			position:relative;
		    font-size: 20px;
		    color:red;
		    width:150px;
		   	height:30px;
		   	left:400px;
		}  
</style>
</head>
<body>
<div class="main-orderM">
<jsp:include page="admin.jsp"/>
	<div class="search-main">
		<form action="orderSerachServlet" method="post">
		<div class="search">
			<input class="input-1" type="text" name="search"  placeholder="??????????????????"/>
			<button class="bt-1">??????</button>
			<select class="st-1" name="select">
			<option value="1" selected="selected">?????????</option>
			<option value="2">??????ID</option>
			</select>
		</div>
		</form><br>
	</div>

<div class="manage-main">
<table border="1">
		<tr>
			<th>OrderId</th>
			<th>UserID</th>
			<th>Sum</th>
			<th>State</th>
			<th>????????????</th>
			<th>????????????</th>
			<th>??????</th>
		</tr>
		<c:forEach items="${orders}" var="o">
		
			<tr>
				<td>${o.orderId }</td>
				<td>${o.userId }</td>
				<td>${o.sum }</td>
				<td>${o.state }</td>
				<td><select id="select2" name="select2">
				<option value="1" selected="selected">?????????</option>
				<option value="2">?????????</option>
				<option value="3">?????????</option>
				</select></td>
				<td><button name="bt" value="2" onclick="state(${o.orderId })">????????????</button></td>
				<td><button name="bt" value="1" onclick="myorder(${o.orderId })">????????????</button></td>
			</tr>
		
		</c:forEach>
	</table>
	<p class="p-rs">${requestScope.msg }</p>
</div>

<div class="main-change">

<c:if test="${requestScope.tag!=null }">
<table border="1" >
		<tr>
			<th>??????</th>
			<th>OrderId</th>
			<th>UserID</th>
			<th>BookName</th>
			<th>BookPrice</th>
			<th>number</th>
		</tr>
		<c:forEach items="${ols}" var="ol">
		
			<tr>
				<td><a><img src="${ol.bookFile }" width="80px" height="80px"/></a></td>
				<td>${ol.orderId }</td>
				<td>${ol.userId }</td>
				<td>${ol.bookName }</td>
				<td>${ol.bookPrice }</td>
				<td>${ol.number }</td>
			</tr>
		
		</c:forEach>
</table>
</c:if>

<div class="bottom">
	<a href="admin.jsp">??????????????????</a>
	</div>
</div>
</div>
</body>
</html>
<script>
function myorder(orderId) {
	var id=orderId
	window.location.href="orderManageServlet?orderId="+id+"&bt="+1;
}
function state(orderId){
	var val=document.getElementById("select2").value;
	var id=orderId
	window.location.href="orderChangeServlet?orderId="+id+"&select="+val;
}
</script>