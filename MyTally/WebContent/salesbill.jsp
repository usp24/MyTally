<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Sales</title>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/sales">
Sales Invoice Date : <input type="date" name="salesInvoiceDate">		<br>
Sales Invoice Number : <input type="text" name="salesInvoiceNumber" value="${sessionScope.maxs}">		<br>
Customer's Name : <input type="text" name="customerName" >		<br>
Customer's Address-1 : <input type="text" name="customerAddress1" >	<br>
Customer's Address-1 : <input type="text" name="customerAddress2" >	<br>
Customer's GSTNo. : <input type="text" name="customerGSTNo" >	<br>
Number Of Items : <input type="text" name="salesNumOfItems" >	<br>
<input type="hidden" name="ch" value="salesbill">
<input type="submit" value="Next">
</form>

</body>
</html>