<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,mybean.*"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.main-bookM{
		
		position:absolute;
		width:1390px;
	 	height:1000px;
	  	margin-left:-700px;
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
	   	left:380px;
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
			width:1250px;
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
			height: 30px;
			cursor: pointer;
		}
		.main-change{
			position:relative;
		   
		   	top:20px;
		}
		.main-change table{
			position:relative;
			width:1390px;
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
			width: 150px;
			height:34.5px;
			left:550px;
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
		   	left:480px;
		   	margin-left: 18px;
		}
		.bottom a:HOVER {
		 	color:red;
		}
		.p-rs{
			position:relative;
		    font-size: 30px;
		    color:red;
		    width:200px;
		   	height:30px;
		   	left:500px;
		}  
</style>
</head>
<body>
<div class="main-bookM">
<jsp:include page="admin.jsp"/>

	<div class="search-main">
		<form action="bookManageSearchServlet" method="post">
		<div class="search">
			<input class="input-1" type="text" name="search" " placeholder="请输入关键字"/>
			<button class="bt-1">查询</button>
			<select class="st-1" name="select">
			<option value="1" selected="selected">书名</option>
			<option value="2">ID</option>
			<option value="3">作者</option>
			<option value="4">类别</option>
			</select>
		</div>
		</form><br>
	</div>
	<div class="manage-main">
		
		<c:set var="i" value="0"/>
		<c:forEach items="${books}" var="b">
		
		<form action="bookChangeServlet"  method="post" >
		<table border="1">
		<c:if test="${i==0 }" >
			
				<tr>
					<th>图片</th>
					<th>BookID</th>
					<th>BookName</th>
					<th>BookPrice</th>
					<th>BookAuthor</th>
					<th>BookType</th>
					<th>BookDetaile</th>
					<th>BookIsbn</th>
					<th>修改操作</th>
					<th>删除操作</th>
					
				</tr>
			</c:if>
			<tr>
					<td><a><img src="${b.bookFile }" width="80px" height="80px"/></a></td>
					<td><input type="text"  name="bookId" value="${b.bookId }" readonly/></td>
					<td><input type="text"  name="bookName" value="${b.bookName }"/></td>
					<td><input type="text"  name="bookPrice" value="${b.bookPrice }"/></td>
					<td><input type="text"  name="bookAuthor" value="${b.bookAuthor }" /></td>
					<td><input type="text"  name="bookType" value="${b.bookType }"/></td>
					<td><textarea style="resize:none" name="bookDetail" value="${b.bookDetail }">${b.bookDetail }</textarea></td>
					<td><input type="text"  name="bookIsbn" value="${b.bookIsbn }"/></td>
					<td><button name="bt" value="1">修改信息</button></td>	
					<td><button name="bt" value="2">删除图书</button></td>	
				</tr>
		</table>
	</form>
	<c:set var="i" value="${i+1 }"/>
	</c:forEach>
	<p class="p-rs">${requestScope.msg }</p>
</div>
<br/>
<div class="main-change">
		<form action="bookChangeServlet"  method="post" onsubmit="return check();" enctype="multipart/form-data">
		<table border="1">
		<tr>
			<th>BookID</th>
			<th>BookName</th>
			<th>BookPrice</th>
			<th>BookAuthor</th>
			<th>BookType</th>
			<th>BookDetail</th>
			<th>BookIsbn</th>
			<th>添加图片</th>
			</tr>
			<tr>
				<td><input type="text" id="id" name="bookId" /></td>
				<td><input type="text" id="name" name="bookName" /></td>
				<td><input type="text" id="price" name="bookPrice" /></td>
				<td><input type="text" id="author" name="bookAuthor" /></td>
				<td><input type="text" id="type" name="bookType" /></td>
				<td><input type="text" id="detail" name="bookDetail" /></td>
				<td><input type="text" id="isbn" name="bookIsbn" /></td>
				<td><input type="file" name="photo"/></td>
				
			</tr>
		</table>
		<br/><button class="bt-cg" name="bt" value="3">添加图书</button>
		</form>
	</div>
	<div class="bottom">
	<a href="admin.jsp">返回管理主页</a>
	<a href="bookManageServlet">返回上一页</a>
	
</div>
</div>
</body>
</html>
<script type="text/javascript">

function check() {
	var id=document.getElementById("id").value;
	var name=document.getElementById("name").value;
	var price=document.getElementById("price").value;
	var author=document.getElementById("author").value;
	var type=document.getElementById("type").value;
	var detail=document.getElementById("detail").value;
	var isbn=document.getElementById("isbn").value;
	if(id==null||id==""){
		alert("图书id不能为空");
		return false;
	}
	if(name==null||name==""){
		alert("图书名字不能为空");
		return false;
	}
	if(price==null||price==""){
		alert("价格不能为空");
		return false;
	}
	if(author==null||author==""){
		alert("作者不能为空");
		return false;
	}
	if(type==null||type==""){
		alert("图书类型不能为空");
		return false;
	}
	if(detail==null||detail==""){
		alert("图书详细信息不能为空");
		return false;
	}
	if(isbn==null||isbn==""){
		alert("图书Isbn码不能为空");
		return false;
	}
	return true;
}
</script>