<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Customer</title>
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var on = "<c:out value='${param.n}' />";
$(document).ready(function(){
	$("#n").blur(function(){
		$.ajax({
			type : 'POST',
			url : 'customer?ch=customerEntryBefore',
			headres : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(x){
				var list1 = $.parseJSON(x);
				var n = document.getElementById("n");
				for(var i=0;i<list1.length;i++){
					if(n.value.toUpperCase()==list1[i].customerName && n.value.toUpperCase()!=on){
						alert("Customer Already Registered");
						n.value = '';
						break;
					}		
				}				
			}
		});
	});
});
function fn(){
	
	var n = document.getElementById("n");
	var g = document.getElementById("g");
	var flag = true;
	
	if(n.value==""){
		alert("Please Enter Customer Name");
		flag = false;
	}
	else if(g.value==""){
		alert("Please Enter GST No.");
		g.value ='';
		flag = false;
	}
	
	if(flag==true)
		return true;
	else
		return false;
}

function f2(){
	var rs = document.getElementById("rs");
	rs.style.display = "";
	tb = document.getElementById("tb");
	tb.style.display = "";
}
</script>
<style type="text/css" >
#n{
	text-transform: uppercase;
}
</style>
</head>
<body>

<h1>Edit Customer's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/customer" onsubmit="return fn()" >
Name : <input type="text" name="customerName" id="n" value="${param.name}">	<br>
Address-1 : <input type="text" name="customerAddress1" value="${param.a1}">	<br>
Address-2 : <input type="text" name="customerAddress2"  value="${param.a2}">	<br>
City : <input type="text" name="customerCity"  value="${param.c}">	<br>
State & Code : <input type="text" name="customerStatecode"  value="${param.s}">	<br>
Mobile No. : <input type="number" name="customerMobileNo"  value="${param.m}">	<br>
GST NO. : <input type="text" name="customerGSTNo" id="g" value="${param.g}" >	<br>
Email : <input type="email" name="customerEmail" value="${param.e}">	<br>
<input type="hidden" name="on" value="${param.n}">
<input type="hidden" name="ch" value="editCustomer" >
<input type="submit" value="Update" >
</form>

</body>
</html>