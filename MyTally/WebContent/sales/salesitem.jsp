<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Purchase</title>

<script type="text/javascript">
	
	function fn(row){
		 var qty = document.getElementById("q"+row);
		 var unitPrice = document.getElementById("p"+row);
		 var at = document.getElementById("amt"+row);
		 at.value = (qty.value*unitPrice.value);
	}
</script>

</head>
<body>

<form action="<%=request.getContextPath()%>/sales" method="post">
<c:forEach var="rows" begin="1" end="${sessionScope.ns}" >
Item : <input type="text" name="salesItemName${rows}" >  <br>
Discription : <input type="text" name="salesItemDiscription${rows}" >  <br>
HSN : <input type="text" name="salesItemHSN${rows}" >  <br>
GST : <input type="text" name="salesItemGST${rows}" id="g${rows}" >  <br>
Qty : <input type="text" name="salesItemQty${rows}" id="q${rows}" onkeyup="fn(${rows})">  <br>
Unit Price : <input type="text" name="salesItemUnitPrice${rows}" id="p${rows}" onkeyup="fn(${rows})">  <br>
Discount : <input type="text" name="salesItemDiscount${rows}" value="0">%	<br>
Amount : <input type="text" name="salesItemAmount${rows}" id="amt${rows}" readonly>	<br>
</c:forEach>
<input type="hidden" name="ch" value="salesitem">
<input type="submit" value="Save">
</form>

</body>
</html>