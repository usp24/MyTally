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

<form action="<%=request.getContextPath()%>/purchase" method="post">
<c:forEach var="rows" begin="1" end="${sessionScope.n}" >
Item : <input type="text" name="purchaseItemName${rows}" >  <br>
Discription : <input type="text" name="purchaseItemDiscription${rows}" >  <br>
HSN : <input type="text" name="purchaseItemHSN${rows}" >  <br>
GST : <input type="text" name="purchaseItemGST${rows}" id="g${rows}" >  <br>
Qty : <input type="text" name="purchaseItemQty${rows}" id="q${rows}" onkeyup="fn(${rows})">  <br>
Unit Price : <input type="text" name="purchaseItemUnitPrice${rows}" id="p${rows}" onkeyup="fn(${rows})">  <br>
Discount : <input type="text" name="purchaseItemDiscount${rows}" value="0">%	<br>
Amount : <input type="text" name="purchaseItemAmount${rows}" id="amt${rows}" readonly>	<br>
</c:forEach>
<input type="hidden" name="ch" value="purchaseitem">
<input type="submit" value="Save">
</form>

</body>
</html>