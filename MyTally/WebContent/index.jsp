<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Login</title>
</head>
<body>

<p>
Welcome To MyTally
</p>

<form class="form" id="loginform" method="post" action="<%=request.getContextPath()%>/userinfo">
Username : <input type="text" name="username">	<br>
Password : <input type="password" name="password">	<br>
<input type="submit" value="Login">
<input type="hidden" name="choice" value="login" >
<p><a href="#">Forgot Password ?</a></p>
</form>

</body>
</html>