<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="vo.supplierVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Purchase</title>

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
<form method="post" action="<%=request.getContextPath()%>/purchase">
Purchase Invoice Date : <input type="date" name="purchaseInvoiceDate">		<br>
Purchase Invoice Number : <input type="text" name="purchaseInvoiceNumber" value="${sessionScope.maxp}">		<br>
Supplier's Name : <input type="text" name="supplierName" list="l" id="name" oninput="fn()" >
<datalist id="l">
<%
	List l1 = (List)session.getAttribute("slist");
	int i=0;
	for(i=0;i<l1.size();i++){
		supplierVO ob = new supplierVO((supplierVO)l1.get(i));
%>
<option value="<%= ob.getSupplierName() %>"/>
<input type="hidden" id="n<%=i%>" value="<%=ob.getSupplierName()  %>">
<input type="hidden" id="a1<%=i%>" value="<%= ob.getSupplierAddress1() %>">
<input type="hidden" id="a2<%=i%>" value="<%= ob.getSupplierAddress2() %>">
<input type="hidden" id="g<%=i%>" value="<%= ob.getSupplierGSTNo() %>">
<input type="hidden" id="c<%=i%>" value="<%= ob.getSupplierCity() %>">
<input type="hidden" id="s<%=i%>" value="<%= ob.getSupplierStatecode() %>">
<%}%>
<input type="hidden" id="num" value="<%=i%>">
</datalist>	<br>
Supplier's Address-1 : <input type="text" name="supplierAddress1" id="add1" >	<br>
Supplier's Address-2 : <input type="text" name="supplierAddress2" id="add2" >	<br>
Area and City : <input type="text" name="supplierCity" id="city">	<br>
State Code : <input type="text" name="supplierStatecode" value="Gujarat - 24" id="statecode">	<br>
Supplier's GSTNo. : <input type="text" name="supplierGSTNo" id="gst">	<br>
Number Of Items : <input type="text" name="purchaseNumOfItems" >	<br>
<input type="hidden" name="ch" value="purchasebill">
<input type="submit" value="Next">
</form>

</body>
</html>