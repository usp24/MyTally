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
	
	function fn2(row){
		var qty = document.getElementById("q"+row);
		var div = document.getElementById("sn"+row);
		div.innerHTML="";
		if(qty.value>1){
			div.style.display = '';
			for(var i=1;i<qty.value;i++){
				div.innerHTML = div.innerHTML + "Sr. No."+(i+1)+" : <input type='text' name='ItemSrNo"+row+""+(i+1)+"'><br>";
			}
		 }
		else{
			div.style.display = 'none';
			div.innerHTML="";
		}
	}
</script>

</head>
<body>

<form action="<%=request.getContextPath()%>/item" method="post">
<c:forEach var="rows" begin="1" end="${param.n}" >
Item : <input type="text" name="ItemName${rows}" >  <br>
Sr. No. : <input type="text" name="ItemSrNo${rows}1" >  <br>
<div id="sn${rows}" style="display:none;"></div>
Description : <input type="text" name="ItemDiscription${rows}" >  <br>
HSN : <input type="text" name="ItemHSN${rows}" >  <br>
GST : <input type="text" name="ItemGST${rows}" id="g${rows}" >%  <br>
Qty : <input type="text" name="purchaseItemQty${rows}" id="q${rows}" onkeyup="fn(${rows})" oninput="fn2(${rows})">  <br>
Unit Price : <input type="text" name="purchaseItemUnitPrice${rows}" id="p${rows}" onkeyup="fn(${rows})">  <br>
Discount : <input type="text" name="purchaseItemDiscount${rows}" value="0">%	<br>
Amount : <input type="text" name="purchaseItemAmount${rows}" id="amt${rows}" readonly>	<br>
</c:forEach>
<input type="hidden" name="ch" value="purchase">
<input type="hidden" name="s" value="${param.s}">
<input type="hidden" name="g" value="${param.g}">
<input type="hidden" name="i" value="${param.i}">
<input type="hidden" name="n" value="${param.n}">
<input type="submit" value="Save">
</form>

</body>
</html>