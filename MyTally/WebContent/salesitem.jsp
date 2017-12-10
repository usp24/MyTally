<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="vo.itemVO" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Sales</title>

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
				div.innerHTML = div.innerHTML + "Sr. No."+(i+1)+" : <input type='text' id='srno${"+row+"}"+i+"' name='ItemSrNo"+row+""+(i+1)+"' readonly><br>";
			}
		 }
		else{
			div.style.display = 'none';
			div.innerHTML="";
		}
	}
	
	function fn3(row){
		var name = document.getElementById("item"+row);
		var hsn = document.getElementById("hsn"+row);
		var gst = document.getElementById("gst"+row);
		var disc = document.getElementById("disc"+row);
		var price = document.getElementById("p"+row);
		var qty = document.getElementById("qty"+row);
		var num = document.getElementById("num");
		for(var i=0;i<num.value;i++){
			var dbname = document.getElementById("nm"+i);
			if(dbname.value == name.value){
				hsn.value = document.getElementById("hn"+i).value;
				gst.value = document.getElementById("gt"+i).value;
				disc.value = document.getElementById("ds"+i).value;
				price.value = document.getElementById("pr"+i).value;
				qty.value = document.getElementById("qt"+i).value;
				break;
			}
		}
	}

</script>

</head>
<body>

<datalist id="l">
<%
	List<itemVO> list = (List)session.getAttribute("salesItemList");
	int i=0;
	for(i=0;i<list.size();i++){
		itemVO ob = new itemVO((itemVO)list.get(i));
%>
<option value="<%=ob.getItemName()%>"></option>
<input type="hidden" id="nm<%=i%>" value="<%=ob.getItemName()  %>">
<input type="hidden" id="hn<%=i%>" value="<%= ob.getItemHSN() %>">
<input type="hidden" id="gt<%=i%>" value="<%= ob.getItemGST() %>">
<input type="hidden" id="ds<%=i%>" value="<%= ob.getItemDiscription() %>">
<input type="hidden" id="sr<%=i%>" value="<%= ob.getItemSrNo() %>">
<input type="hidden" id="qt<%=i%>" value="<%= ob.getSalesItemQty() %>">
<input type="hidden" id="pr<%=i%>" value="<%= ob.getPurchaseItemUnitPrice()%>">
<%}%>
<input type="hidden" id="num" value="<%=i%>">
</datalist>

<datalist id="ls">
<%
	for(i=0;i<list.size();i++){
		itemVO ob = new itemVO((itemVO)list.get(i));
		if(ob.getItemSrNo()!=null){
%>
<option value="<%=ob.getItemSrNo()%>"></option>
<%}}%>
</datalist>

<form action="<%=request.getContextPath()%>/item" method="post" >
<input type="hidden" name="n" value="">
<input type="submit" value="Add Item">
</form>

<form action="<%=request.getContextPath()%>/item" method="post">
<c:forEach var="rows" begin="1" end="${param.n}" >
Item : <input type="text" name="ItemName${rows}" list="l" oninput="fn3(${rows})" id="item${rows}">  <br>
Qty : <input type="text" name="salesItemQty${rows}" id="q${rows}" onkeyup="fn(${rows})" oninput="fn2(${rows})" >  <br>
Sr. No. : <input type="text" name="ItemSrNo${rows}" list="ls" id="srno${rows}1" readonly>  <br>
	<div id="sn${rows}" style="display:none;"></div>
Description : <input type="text" name="ItemDiscription${rows}" id="disc${rows}">  <br>
HSN : <input type="text" name="ItemHSN${rows}" id="hsn${rows}" readonly>  <br>
GST : <input type="text" name="ItemGST${rows}" id="gst${rows}" readonly>  <br>
Unit Price : <input type="text" name="salesItemUnitPrice${rows}" id="p${rows}" onkeyup="fn(${rows})">  <br>
Discount : <input type="text" name="salesItemDiscount${rows}" value="0">%	<br>
Amount : <input type="text" name="salesItemAmount${rows}" id="amt${rows}" readonly>	<br>
</c:forEach>

<input type="hidden" name="ch" value="sales">
<input type="hidden" name="s" value="${param.s}">
<input type="hidden" name="g" value="${param.g}">
<input type="hidden" name="i" value="${param.i}">
<input type="hidden" name="n" value="${param.n}">
<input type="submit" value="Save">
</form>

<%
	for(i=0;i<list.size();i++){
	itemVO ob = new itemVO((itemVO)list.get(i));
		out.println(ob.getItemName());
		out.println(ob.getItemDiscription());
		out.println(ob.getItemGST());
		out.println(ob.getItemHSN());
		out.println(ob.getItemSrNo());
	}
%>
</body>
</html>