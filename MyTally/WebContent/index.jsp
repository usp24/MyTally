<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UAccounting</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/fa.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript" >
function fn(){
		
	var u = document.getElementById("username");
	var p = document.getElementById("password");
	var flag = true;
	
	if(u.value==""){
		alert("Please Enter Username");
		flag = false;
	}
	else if(p.value==""){
		alert("Please Enter Password");
		flag = false;
	}
	
	if(flag==true)
		return true;
	else 
		return false;
}
</script>
<style type="text/css" >
body{
	background-color: #edf8f896;
	overflow:visible;
	margin: 0px;
	padding: 0px;
	text-align: center;
	font-family: Calibri,verdana,arial,sans-serif;
}
a{
	text-decoration: none;
	color:inherit;
	cursor: pointer;
}


.header{
	margin:auto;
	overflow:hidden;
	padding-top: 10px;
	margin-bottom: 3px;
}
.header #title{
	padding-left:18px;
}
.header #text{
}
.header #title #titleh1{
	color: #4B4B4B;
	text-transform: uppercase;
	font-size : 50px;
	margin: 0px;
}
.header #title #titleh1 #u{
	font-size:65px;
	color:  #00aba9;
}
.header #text #textp{
	letter-spacing:3px;
	font-size: 14px;
	font-family: verdana;
}

#bg{
	
	margin: auto;
	width:354px;
	border:none;
	border-radius: 5px;
}
fieldset{
	border:none;
	border-radius: 5px;
}
#box{
	width: 330px;
	height:420px;
 	background-color: rgba(13,11,11,0.85);
	box-shadow: 15px 15px 25px 3px;
	margin:auto;
	margin-top: 80px;
}
#u1{
	margin-top:25px;
	margin-bottom: 20px;
	width:100px;
	height:100px;
}
#username,#password{
	width:80%;
	height: 20px;
	margin-left: 28px;
	margin-bottom: 35px;
	border:1px solid white;
	border-radius: 2px;
	background: none;
	padding-left: 8px;
	color:white;
	margin-top: 5px;
}
#submit{
	background-color: #4B993C;
	border:none;
	color:white;
	text-align: center;
	height:30px;
	width:115px;
	margin:auto;
	margin-top: 30px;
	font-size:15px;
	border-radius: 3px;
	cursor: pointer;
}
#box a{
	color:white;
	text-decoration: none;
	font-weight: lighter;
}
#box i{
	color:#c1bdbd;
	position: relative;
	top: 2px;
	right: 22.5px;
}
#lck{
	color:#c1bdbd;
	position: relative;
	top: 3.5px;
	right: 24px;
	width: 15.4px;
	height: 15.4px;	
}
</style>

</head>
<body>

<div class="header">
	<div id="title" ><h1 id="titleh1" ><span id="u">U</span>Accounting</h1></div>
	<div id="text" ><p id="textp" >THE WORLD'S BEST ACCOUNTING SOFTWARE</p></div>
</div>


<c:if test="${ sessionScope.login_error ne null }">
<h3 style="color: red;letter-spacing: 1px;" >${sessionScope.login_error}</h3>
<c:set scope="session" var="login_error" value=""></c:set>
<c:remove var="login_error" scope="session"/>
</c:if>


<div id="bg" >
<fieldset id="box">
<form class="form" id="loginform" method="post" action="<%=request.getContextPath()%>/userinfo"  onsubmit="return fn()" >
	<center><img id="u1" src="img/usr.png"></center><br>
		<input type="text" id="username" name="username" placeholder="Username">
	<i class="fa fa-user"></i>
		<input type="password" id="password" name="password" placeholder="Password">
	<img id="lck" src="img/lock.png"><br>
		<input type="submit" value="LOGIN" id="submit">
	
	<input type="hidden" name="choice" value="login" >
</form>	
</fieldset>
</div>

</body>
</html>