<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Customer</title>
</head>
<body>
<h1>Customer's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/customer">
Name : <input type="text" name="customerName">	<br>
Address-1 : <input type="text" name="customerAddress1">	<br>
Address-2 : <input type="text" name="customerAddress2">	<br>
Mobile No. : <input type="text" name="customerMobileNo">	<br>
GST NO. : <input type="text" name="customerGSTNo">	<br>
Email : <input type="text" name="customerEmail">	<br>
<input type="submit" value="Save" >
</form>

</body>
</html>