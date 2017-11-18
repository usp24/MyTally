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

<form class="form" id="loginform" method="post" action="<%=request.getContextPath()%>/loginform">
Username : <input type="text" name="luser">	<br>
Password : <input type="text" name="lpsw">	<br>
<input type="submit" value="Login">
<p><a href="#">Forgot Password ?</a></p>
<p><a href="registration.jsp">New User ? Register Your Self</a></p>
</form>

</body>
</html>