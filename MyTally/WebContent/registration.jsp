<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Registration</title>
</head>
<body>

<p>Welcome To MyTally</p>
<p>Register Your Self</p>

<form class="form" id="regform" method="post" action="<%=request.getContextPath()%>/userinfo">
Business Name : <input type="text" name="businessName" placeholder="Enter Name Of Your Business">	<br>
Username : <input type="text" name="username" placeholder="Choose Username">	<br>
Password : <input type="password" name="password" placeholder="Choose Password">	<br>
Email : <input type="text" name="email" placeholder="Enter Email" >	<br>
<input type="hidden" name="choice" value="registration" >
<input type="submit" value="Register">
</form>


</body>
</html>