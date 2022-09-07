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
	.main{
		
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
	   
	   }
	   .search-main{
	   
	   	background-color:#fc5531;
	   	position:relative;
	   	top:10px;
	   	height:50px;
	   }
	   .search{
	   
	   	position:relative;
	  	width:600px;
	  	height:36px;
	   	left:250px;
	   	top:5px;
	   }
	   .input-1{
	   	 width:300px;
	   	 height:30px;
	   	 font-size: 18px;
	   	
	   }
	   .st-1{
	   	width:86px;
	   	 height:35px;
	   	 cursor: pointer;
	   }
	   .bt-1{
	   
	   	 width:86px;
	   	 height:36px;
	   	
	   }
	   .main-show{
	   		border:1px solid #fc5531;
	   		position:relative;
	   		width:1000px;
	   		height:auto;
	   		overflow: auto;
	   		min-height: 300px;
	   }
	   .main-show ul{
	   position:relative;
	   	padding:0px;
		margin:0px;
	   }
	   .main-show li{
	   	position:relative;
	   	list-style-type:none;
		float:left;
		margin-left: 30px;
		margin-top:10px;
		line-height: 40px;
		box-shadow: 2px 2px 9px gray;
	   }
	   .main-show span{
	   	position:relative;
	   	font-size: 20px;
	   	left:70px;
	   	
	   }
	   .bt-2{
	   	width:25px;
	   	height:20px;
	   	cursor: pointer;
	   }
	   .bt-3{
	   	position:relative;
	   	left:55px;
	   	width:100px;
	   	height:30px;
	   	cursor: pointer;
	   }
	   .main-show p{
	   	position:relative;
	    font-size: 20px;
	    color:red;
	    width:150px;
	   	height:30px;
	   	left:400px;
	   }
	   .show-bt{
	   position:relative;
	   left:30px;
	   }
	   .input-2{
	   	position:relative;
	   	text-align: center;
	   	width:80px;
	   	font-size: 18px;
	   }
</style>
</head>
<body>

<c:if test="${empty requestScope.tag }">
	<c:redirect url="bookServlet" />
</c:if>

<div class="main">
<jsp:include page="index.jsp"/>

	<div class="search-main">
		<form action="searchServlet" method="post">
		<div class="search">
			<input class="input-1" type="text" name="search" placeholder="请输入关键字"/>
			<button class="bt-1">查询</button>
			<select class="st-1" name="select">
			<option value="1" selected="selected">书名</option>
			<option value="2">类别</option>
			</select>
		</div>
		</form>
	</div>
	<br/>
	
	<div class="main-show">
		<ul>
		<c:forEach items="${books}" var="b">
		
			<li>
				<a href="bookDetailServlet?bookid=+${b.bookId }"><img src="${b.bookFile }" width="200px" height="200px"/></a>
				<br/>
				<span>${b.bookName}</span>
				<br/>
				<span>${b.bookPrice }元</span>		
				<br/>
				<div class="show-bt">
				<button class="bt-2" onclick="sub(${b.bookId })">-</button>
				<input class="input-2" type="text"  id="${b.bookId }" name="num" value="1" />
				<button class="bt-2" onclick="add(${b.bookId })">+</button>
				<br/>
				</div>
				<button class="bt-3" onclick="jump(${b.bookId })">加入购物车</button>
			</li>
		
		</c:forEach>
		</ul>
		<p><b>${requestScope.msg }</b></p>
	</div>
	
</div>
</body>
</html>
<script type="text/javascript">
	function add(id) {
		var num=document.getElementById(id).value;
		num++;
		document.getElementById(id).value=num;
	}
	function sub(id) {
		var num=document.getElementById(id).value;
		if(num>1){
			num--;
		}
		document.getElementById(id).value=num;
	}
	function jump(id) {
		var bookId=id;
		var num=document.getElementById(id).value;
		alert("添加成功")
		window.location.href="cartServlet?bookId="+bookId+"&num="+num+"&flag="+0;
	}
</script>