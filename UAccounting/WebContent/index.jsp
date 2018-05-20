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
<link rel="stylesheet" href="css/index.css">
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

</head>
<body onload="document.getElementById('username').focus()" >

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
	<center><img id="u1" src="img/usr2.png"></center><br>
		<input type="text" id="username" name="username" placeholder="Username" autocomplete="off">
	<img id="usr" src="img/usr2.png"><br>
		<input type="password" id="password" name="password" placeholder="Password"  autocomplete="off">
	<img id="lck" src="img/lock.png"><br>
		<input type="submit" value="LOGIN" id="submit">
	<input type="hidden" name="choice" value="login" >
</form>	
</fieldset>
</div>

</body>
</html>