<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Sale</title>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/sales">
Sales Invoice Date : <input type="date" name="salesInvoiceDate">		<br>
Sales Invoice Number : <input type="text" name="salesInvoiceNumber" >		<br>
Customer's Name : <input type="text" name="customerName" >		<br>
Customer's Address : <input type="text" name="customerAddress" >	<br>
Customer's GSTNo. : <input type="text" name="customerGSTNo" >		<br>
Item : <input type="text" name="salesItemName" >  <br>
Description : <input type="text" name="salesItemDescription" >  <br>
HSN : <input type="text" name="salesItemHSN" >  <br>
Qty : <input type="text" name="salesItemQty" >  <br>
Unit Price : <input type="text" name="salesItemPrice" >  <br>
GST : <input type="text" name="salesItemGST" >  <br>
Amount : <div id="salesItemAmount" ></div>  <br>
Total Amount : <div id="salesTotalAmount"></div>  <br>
Total RoundOff Amount : <div id="salesTotalRoundOffAmount"></div>  <br>
Discount : <input type="text" name="salesDiscount">	<br>
<input type="submit" value="Save">
</form>

</body>
</html>