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
		border:1px solid red;
		position:absolute;
		width:1000px;
	 	height:1000px;
	  	margin-left:-500px;
	   	left:50%;
	   }
	   #img{
	   position: relative;
	   float:left;
	   left:0px;
	   	top:0px;
	   }
	   .show-main{
	    position: relative;
	    left:50px;
	    top:20px;
	    font-size:30px;
	   
	   }
	   .show-bt{
		   position: relative;
		   top:40px;
		   left:460px;
		   width: 400px;
	   }
	   .bt-2{
	   		width: 50px;
	   		height: 50px;
	   		cursor: pointer;
	   		font-size: 30px;
	   }
	   .input-2{
	   		width: 200px;
	   		height: 50px;
	   		font-size: 30px;
	   		
	   		text-align: center;
	   }
	   .show-main textarea{
	   		width:400px;
	   		height: 100px;
	   		font-size: 20px;
	   		cursor: default;
	   }
	   .bt-3{
	    position: relative;
	   		top:60px;
	   	 	left:520px;
	   	 	width: 200px;
	   	 	height: 50px;
	   	 	cursor: pointer;
	   	 	font-size: 20px;
	   }
</style>
</head>
<body>

<div class="main">
<jsp:include page="index.jsp"/>
		<c:forEach items="${books}" var="b">
		
		 <img src="${b.bookFile }" width="400px" height="400px" id="img"/> 	
		<div class="show-main">
			
			<p>书名：${b.bookName }</p>
			<p>作者：${b.bookAuthor }</p>
			<p>类别：${b.bookType }</p>
			<p>ISBN：${b.bookIsbn }</p>
			<p>${b.bookPrice }元</p>	
			<p>简介：<textarea style="resize:none" readonly>${b.bookDetail }</textarea></p>
		</div>
			<div class="show-bt">
				<button class="bt-2" onclick="sub(${b.bookId})">-</button>
				<input class="input-2" type="text"  id="${b.bookId}" name="num" value="1" />
				<button class="bt-2" onclick="add(${b.bookId})">+</button>			
			</div>
			<button class="bt-3" onclick="jump(${b.bookId})">加入购物车</button>
			
		
		</c:forEach>
	
	
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
		window.location.href="cartServlet?bookId="+bookId+"&num="+num+"&flag="+0;
	}
</script>