<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="vo.itemVO" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Purchase</title>

<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">

var list;
var num;
var list1;
var list2;
var cnt = 2;
var nr = document.getElementById("nr");
nr=1;

$(document).ready(function(){
	
	$.ajax({
		type : 'POST',
		url : 'purchase?ch=purchaseBillBefore',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			var dl = document.getElementById("dl");
			list1 = $.parseJSON(x);
			dl.innerHTML = "";
			for(var i=0;i<list1.length;i++){
				dl.innerHTML += "<option value='"+list1[i].supplierName+"'/>";
			}
		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'purchase?ch=purchaseBillBefore2',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			list2 = $.parseJSON(x);
		}
	});
	
	
	$.ajax({
		type : 'POST',
		url : 'item?ch=itemDetail',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			list = $.parseJSON(x);
			num = list.length;
			var dl = document.getElementById("dl_item");
			dl.innerHTML = "";
			for(var i=0;i<num;i++){
				dl.innerHTML += "<option value='"+list[i].itemName+"'/>";
			}
		}
	});
	
	$("#iv").blur(function(){
		var no = document.getElementById("iv");
		for(var i=0;i<list2.length;i++){
			if(no.value == list2[i].purchaseInvoiceNo){
				alert("Purchase Bill With Invoice No : '"+iv.value+"' Is Already Generated, Please Enter Different Invoice No");
				iv.value = '';
				break;
			}
		}
	});
	
			
	$("#name").on("input",function(){
		
		var name = document.getElementById("name");
		var add1 = document.getElementById("add1");
		var add2 = document.getElementById("add2");
		var city = document.getElementById("city");
		var statecode = document.getElementById("statecode");
		var gst = document.getElementById("gst");
		
		for(var i=0;i<list1.length;i++){
			if(name.value==list1[i].supplierName){
				add1.value = list1[i].supplierAddress1;
				add2.value = list1[i].supplierAddress2;
				city.value = list1[i].supplierCity;
				statecode.value = list1[i].supplierStatecode;
				gst.value = list1[i].supplierGSTNo;
				break;
			}		
		}	
	});
});

	function fn(row){
	 var qty = document.getElementById("q"+row);
	 var u = document.getElementById("p"+row);
	 var at = document.getElementById("amt"+row);
	 at.value = (qty.value*u.value);
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
	
	function fn3(row){
	var name = document.getElementById("item"+row);
	var disc = document.getElementById("disc"+row);
	var hsn = document.getElementById("hsn"+row);
	var gst = document.getElementById("gst"+row);
	var unitPrice = document.getElementById("p"+row);
	
	for(var i=0;i<num;i++){
		if(list[i].itemName == name.value){
			hsn.value = list[i].itemHSN;
			gst.value = list[i].itemGST;
			disc.value = list[i].itemDescription;
			unitPrice.value = list[i].itemPurchasePrice;
			break;
				/*		
					var x = list[i].itemSrNo.split("*");
					for(var j=0;j<x.length;j++){
						var srno = document.getElementById("ItemSrNo"+row+""+(j+1));			
					}		
				*/
		}
	}
}
	
	function fb(){
	var tb = document.getElementById("tb1");
	
	var r = tb.insertRow(-1);
	var c1 = r.insertCell(0);
	var c2 = r.insertCell(1);
	var c3 = r.insertCell(2);
	var c4 = r.insertCell(3);
	var c5 = r.insertCell(4);
	var c6 = r.insertCell(5);
	var c7 = r.insertCell(6);
	var c8 = r.insertCell(7);
	var c9 = r.insertCell(8);
	var c10 = r.insertCell(9);
	var c11 = r.insertCell(10);
	
	c1.innerHTML = cnt;
	c2.innerHTML = "<input type='text' list='dl_item' name='ItemName"+cnt+"' id='item"+cnt+"' onblur='fn3("+cnt+")' >";
	c3.innerHTML = "<input type='text' name='ItemDescription"+cnt+"' id='disc"+cnt+"'>";
	c4.innerHTML = "<input type='text' name='purchaseItemQty"+cnt+"' id='q"+cnt+"' onkeyup='fn("+cnt+")' onblur='fn2("+cnt+")'>";
	c5.innerHTML = "<input type='text' name='ItemSrNo"+cnt+"1'>";
	c6.innerHTML = "<input type='text' name='ItemHSN"+cnt+"' id='hsn"+cnt+"'>";
	c7.innerHTML = "<input type='text' name='ItemGST"+cnt+"' id='gst"+cnt+"' value='18' >%";
	c8.innerHTML = "<input type='text' name='purchaseItemUnitPrice"+cnt+"' id='p"+cnt+"' onkeyup='fn("+cnt+")'>";
	c9.innerHTML = "<input type='text' name='purchaseItemDiscount"+cnt+"' value='0' onblur='fn("+cnt+")' id='d"+cnt+"'>%";
	c10.innerHTML = "<input type='text' name='purchaseItemTotalAmount"+cnt+"' id='amt"+cnt+"' readonly>";
	c11.innerHTML = "<input type='button' value='next' onclick='fb()'>";
	
	nr = cnt;
	cnt++;
}
	
</script>

<style type="text/css" >
table{
	border-collapse : collapse;
	border : 1px solid black;
}
th,td{
	border : 1px solid black;
}
input{
	border:none;
}
#name{
text-transform: uppercase;
}
</style>
</head>
<body>

<datalist id="dl" ></datalist>
<datalist id="dl_item" ></datalist>

<form action="<%=request.getContextPath()%>/purchase" method="post">
Purchase Invoice Date : <input type="date" name="purchaseInvoiceDate" >		<br>
Purchase Invoice Number : <input type="text" name="purchaseInvoiceNumber" value="${sessionScope.maxp}" id="iv">		<br>
Supplier's Name : <input type="text" name="supplierName" list="dl" id="name">	<br>
Supplier's Address-1 : <input type="text" name="supplierAddress1" id="add1" >	<br>
Supplier's Address-2 : <input type="text" name="supplierAddress2" id="add2" >	<br>
Area and City : <input type="text" name="supplierCity" id="city">	<br>
State Code : <input type="text" name="supplierStatecode" value="Gujarat - 24" id="statecode">	<br>
Supplier's GSTNo. : <input type="text" name="supplierGSTNo" id="gst">	<br>

<hr>
<table id="tb1">
<tr>
	<th>No.</th>
	<th>Name</th>
	<th>Description</th>
	<th>Qty</th>
	<th>Sr. No.</th>
	<th>HSN</th>
	<th>GST</th>
	<th>UnitPrice</th>
	<th>Discount</th>
	<th>Amount</th>
	<th></th>
</tr>
<tr>
	<td>1</td>
	<td><input type="text" list="dl_item" name="ItemName1" id="item1" onblur="fn3('1')"></td>
	<td><input type="text" name="ItemDescription1" id="disc1"></td>
	<td><input type="text" name="purchaseItemQty1" id="q1" onkeyup="fn('1')" onblur="fn2('1')"></td>
	<td><input type="text" name="ItemSrNo11" ></td>
	<td><input type="text" name="ItemHSN1" id="hsn1"></td>
	<td><input type="text" name="ItemGST1" id="gst1" value="18" >%</td>
	<td><input type="text" name="purchaseItemUnitPrice1" id="p1" onkeyup="fn('1')"></td>
	<td><input type="text" name="purchaseItemDiscount1" value="0" onblur="fn('1')" id="d1">%</td>
	<td><input type="text" name="purchaseItemTotalAmount1" id="amt1" readonly></td>
	<td><input type="button" value="next" onclick="fb()"></td>
</tr>
</table>

<input type="hidden" name="ch" value="purchase">
<input type="hidden" name="n" id="nr" value="1">
<input type="submit" value="Save">

</form>
</body>
</html>