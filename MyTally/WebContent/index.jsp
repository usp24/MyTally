<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Login</title>

<script type="text/javascript" >
	function fn(){
		
		var u = document.getElementById("u");
		var p = document.getElementById("p");
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
<body>

<c:if test="${ sessionScope.login_error ne null }">
${sessionScope.login_error}
<c:set scope="session" var="login_error" value=""></c:set>
<c:remove var="login_error" scope="session"/>
</c:if>

<p>
Welcome To MyTally
</p>

<form class="form" id="loginform" method="post" action="<%=request.getContextPath()%>/userinfo"  onsubmit="return fn()" >
Username : <input type="text" name="username" id="u" >	<br>
Password : <input type="password" name="password" id="p" >	<br>
<input type="submit" value="Login">
<input type="hidden" name="choice" value="login" >
<p><a href="#">Forgot Password ?</a></p>
</form>

</body>
</html>