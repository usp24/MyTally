<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Supplier</title>
</head>
<body>
<h1>Supplier's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/supplier">
Name : <input type="text" name="supplierName">	<br>
Address-1 : <input type="text" name="supplierAddress1">	<br>
Address-2 : <input type="text" name="supplierAddress2">	<br>
Mobile No. : <input type="text" name="supplierMobileNo">	<br>
GST NO. : <input type="text" name="supplierGSTNo">	<br>
Email : <input type="text" name="supplierEmail">	<br>
<input type="hidden" value="newSupplier" >
<input type="submit" value="Save" >
</form>

</body>
</html>