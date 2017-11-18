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

<form class="form" id="regform" method="post" action="<%=request.getContextPath()%>/registrationfrom">
Business Name : <input type="text" name="busr" stackholder="Enter Name Of Your Business">	<br>
Password : <input type="password" name="bpsw" stackholder="Choose Your Password">	<br>
Email : <input type="text" name="bemail">	<br>
<input type="submit" value="Register">
</form>


</body>
</html>