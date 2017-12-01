<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="dao.purchaseDAO" %>
<%@ page import="dao.salesDAO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Menu</title>
</head>
<body>
<a href="supplier/supplier.jsp">Supplier</a>
<a href="customer/customer.jsp">Customer</a>

<form method="post" action="<%=request.getContextPath()%>/sales">
<input type="submit" value="sales" >
<input type="hidden" name="ch" value="salesbillbefore">
</form>

<form method="post" action="<%=request.getContextPath()%>/purchase">
<input type="submit" value="Purchase" >
<input type="hidden" name="ch" value="purchasebillbefore">
</form>

<a href="stock/viewStock.jsp">View Stock</a>
<a href="payments.jsp">Payments</a>
<a href="other.jsp">Other</a>
<a href="settings.jsp">Settings</a>
</body>
</html>