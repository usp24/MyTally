<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Purchase</title>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/purchase">
Purchase Invoice Date : <input type="date" name="purchaseInvoiceDate">		<br>
Purchase Invoice Number : <input type="text" name="purchaseInvoiceNumber" value="${sessionScope.max}">		<br>
Supplier's Name : <input type="text" name="supplierName" >		<br>
Supplier's Address-1 : <input type="text" name="supplierAddress1" >	<br>
Supplier's Address-1 : <input type="text" name="supplierAddress2" >	<br>
Supplier's GSTNo. : <input type="text" name="supplierGSTNo" >	<br>
Number Of Items : <input type="text" name="purchaseNumOfItems" >	<br>
<input type="hidden" name="ch" value="purchasebill">
<input type="submit" value="Next">
</form>

</body>
</html>