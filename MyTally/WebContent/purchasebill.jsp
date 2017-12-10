<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="vo.supplierVO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Purchase</title>

<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">		
$(document).ready(function(){
		
		var list1;
		var list2;
		
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
</script>
<style type="text/css">
#name{
	text-transform: uppercase;
}
</style>
</head>
<body id="bd">

<form method="post" action="<%=request.getContextPath()%>/purchase">
Purchase Invoice Date : <input type="date" name="purchaseInvoiceDate" >		<br>
Purchase Invoice Number : <input type="text" name="purchaseInvoiceNumber" value="${sessionScope.maxp}" id="iv">		<br>
Supplier's Name : <input type="text" name="supplierName" list="dl" id="name">	<br>
	<datalist id="dl" ></datalist>
Supplier's Address-1 : <input type="text" name="supplierAddress1" id="add1" >	<br>
Supplier's Address-2 : <input type="text" name="supplierAddress2" id="add2" >	<br>
Area and City : <input type="text" name="supplierCity" id="city">	<br>
State Code : <input type="text" name="supplierStatecode" value="Gujarat - 24" id="statecode">	<br>
Supplier's GSTNo. : <input type="text" name="supplierGSTNo" id="gst">	<br>
Number Of Items : <input type="text" name="purchaseNumOfItems" >	<br>
<input type="hidden" name="ch" value="purchaseBill">
<input type="submit" value="Next">
</form>

</body>
</html>	