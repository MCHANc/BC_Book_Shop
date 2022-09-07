<%@page import="java.util.*,mybean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.main-userM{
		
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
		   	left:330px;
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
<div class="main-userM">
<jsp:include page="admin.jsp"/>

	<div class="search-main">
		<form action="user_searchServlet" method="post">
		<div class="search">
			<input class="input-1" type="text" name="search" " placeholder="请输入关键字"/>
			<button class="bt-1">查询</button>
			<select class="st-1" name="select">
			<option value="1" selected="selected">用户名</option>
			<option value="2">ID</option>
			</select>
		</div>
		</form><br>
	</div>
	<c:set var="i" value="0"/>
	<div class="manage-main">
	<c:forEach items="${users}" var="u">
		
		<form action="userChangeServlet"  method="post" >
			<table border="1">
				<c:if test="${i==0 }" >
					<tr>
						<th>UserID</th>
						<th>UserName</th>
						<th>password</th>
						<th>ban</th>
						<th>权限设置</th>
						<th>修改操作</th>
						<th>删除操作</th>
						
					</tr>
			</c:if>
				<tr>
						<td><input type="text"  name="id" value="${u.id }"  readonly/></td>
						<td><input type="text"  name="userName" value="${u.userName }"/></td>
						<td><input type="text"  name="password" value="${u.password }"/></td>
						<td><input type="text"  name="ban" value="${u.ban }" readonly/></td>
						<c:choose>
							<c:when test="${u.ban==0 }">			
								<td><button name="bt" value="1">禁止访问</button></td>
							</c:when>
							<c:when test="${u.ban==1 }">	
								<td><button name="bt" value="2">开启权限</button></td>
							</c:when>
						</c:choose>	
						<td><button name="bt" value="3">修改信息</button></td>	
						<td><button name="bt" value="4">删除用户</button></td>	
					</tr>
			</table>
			</form>
			<c:set var="i" value="${i+1 }"/>
			</c:forEach>
			<p class="p-rs">${requestScope.msg }</p>
	</div>
<br/>
	<div class="main-change">
			<form action="userChangeServlet"  method="post" onsubmit="return check();">
			<table border="1">
			<tr>
				<th>UserID</th>
				<th>UserName</th>
				<th>password</th>
				<th>ban</th>
				<th>添加用户</th>
				</tr>
				<tr>
					<td><input type="text" id="id" name="id" /></td>
					<td><input type="text" id="name" name="userName" /></td>
					<td><input type="text" id="pwd" name="password" /></td>
					<td><input type="text" name="ban" value="0" readonly/></td>
					<td><button name="bt" value="5">添加用户</button></td>
				</tr>
			</table>
			</form>
	</div>
		<div class="bottom">
			<a href="admin.jsp">返回管理主页</a>
			<a href="userManageServlet">返回上一页</a>
			
		</div>
</div>
</body>
</html>
<script type="text/javascript">

function check() {
	var id=document.getElementById("id").value;
	var name=document.getElementById("name").value;
	var pwd=document.getElementById("pwd").value;
	if(id==null||id==""){
		alert("id不能为空");
		return false;
	}
	if(name==null||name==""){
		alert("用户名不能为空");
		return false;
	}
	if(pwd==null||pwd==""){
		alert("密码不能为空");
		return false;
	}
	return true;
}
</script>