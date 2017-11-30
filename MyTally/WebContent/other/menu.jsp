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
<%
	purchaseDAO purchaseDAO = new purchaseDAO();
	int max = purchaseDAO.maxInvoiceNo();
	session.setAttribute("max", max+1);
	
	salesDAO s = new salesDAO();
	int maxs = s.maxInvoiceNo();
	session.setAttribute("maxs", maxs+1);
	
%>
<a href="supplier/supplier.jsp">Supplier</a>
<a href="customer/customer.jsp">Customer</a>
<form method="post" action="<%=request.getContextPath()%>/sales">
<input type="submit" value="sale" >
<input type="hidden" name="ch" value="salesbillbefore">
</form>
<a href="sales/salesbillbofore.jsp">Sales</a>
<a href="purchase/purchasebill.jsp">Purchase</a>
<a href="stock/viewStock.jsp">View Stock</a>
<a href="payments.jsp">Payments</a>
<a href="other.jsp">Other</a>
<a href="settings.jsp">Settings</a>
</body>
</html>