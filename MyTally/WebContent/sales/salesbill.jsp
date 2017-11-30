<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="java.util.List" %>
<%@ page import="vo.customerVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Sales</title>

<script type="text/javascript">
function fn(){
	
	var num = document.getElementById("num").value;
	var name = document.getElementById("name");
	var add1 = document.getElementById("add1");
	var add2 = document.getElementById("add2");
	var city = document.getElementById("city");
	var statecode = document.getElementById("statecode");
	var gst = document.getElementById("gst");
	
	for(var i=0;i<num;i++){
		var dbname = document.getElementById("n"+i).value;
		if(name.value == dbname){
			add1.value = document.getElementById("a1"+i).value;
			add2.value = document.getElementById("a2"+i).value;
			city.value = document.getElementById("c"+i).value;
			statecode.value = document.getElementById("s"+i).value;
			gst.value = document.getElementById("g"+i).value;
			break;
		}
	}
}
</script>

</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/sales">
Sales Invoice Date : <input type="date" name="salesInvoiceDate">		<br>
Sales Invoice Number : <input type="text" name="salesInvoiceNumber" value="${sessionScope.maxs}" id="in">		<br>
Customer's Name : <input type="text" name="customerName" list="l" id="name" oninput="fn()" >
<datalist id="l">
<%
	List l1 = (List)session.getAttribute("clist");
	int i=0;
	for(i=0;i<l1.size();i++){
		customerVO ob = new customerVO((customerVO)l1.get(i));
%>
<option value="<%= ob.getCustomerName() %>"/>
<input type="hidden" id="n<%=i%>" value="<%=ob.getCustomerName()  %>">
<input type="hidden" id="a1<%=i%>" value="<%= ob.getCustomerAddress1() %>">
<input type="hidden" id="a2<%=i%>" value="<%= ob.getCustomerAddress2() %>">
<input type="hidden" id="g<%=i%>" value="<%= ob.getCustomerGSTNo() %>">
<input type="hidden" id="c<%=i%>" value="<%= ob.getCustomerCity() %>">
<input type="hidden" id="s<%=i%>" value="<%= ob.getCustomerStatecode() %>">
<%}%>
<input type="hidden" id="num" value="<%=i%>">
</datalist>	<br>
Customer's Address-1 : <input type="text" name="customerAddress1" id="add1" >	<br>
Customer's Address-2 : <input type="text" name="customerAddress2" id="add2" >	<br>
Area and City : <input type="text" name="customerCity" id="city">	<br>
State Code : <input type="text" name="customerStatecode" value="Gujarat - 24" id="statecode">	<br>
Customer's GSTNo. : <input type="text" name="customerGSTNo" id="gst">	<br>
Number Of Items : <input type="text" name="salesNumOfItems" >	<br>
<input type="hidden" name="ch" value="salesbill">
<input type="submit" value="Next">
</form>

</body>
</html>