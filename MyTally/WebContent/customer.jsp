<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Customer</title>
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var list;
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
					if(n.value.toUpperCase()==list1[i].customerName){
						alert("Customer Already Registered");
						n.value = '';
						break;
					}		
				}				
			}
		});
	});
		var list;
		$.ajax({
			type : 'POST',
			url : 'customer?ch=customerView',
			headres : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(x){
				list = $.parseJSON(x);
				if(list.length>0){
			    	for(var i=0;i<list.length;i++){    
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
				          
				         c1.innerHTML = (i+1);
				         c2.innerHTML = list[i].customerName;
				         c3.innerHTML = list[i].customerAddress1;
				         c4.innerHTML = list[i].customerAddress2;
				         c5.innerHTML = list[i].customerCity;
				         c6.innerHTML = list[i].customerStatecode;
				         c7.innerHTML = list[i].customerGSTNo;
				         c8.innerHTML = list[i].customerMobileNo;
				         c9.innerHTML = list[i].customerEmail;
			    	}
				}
				else{
					var rs = document.getElementById("rs");
					rs.innerHTML = "No Record Found !!!";
				}
			}
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

<h1>Customer's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/customer" onsubmit="return fn()" >
Name : <input type="text" name="customerName" id="n" >	<br>
Address-1 : <input type="text" name="customerAddress1">	<br>
Address-2 : <input type="text" name="customerAddress2">	<br>
City : <input type="text" name="customerCity">	<br>
State & Code : <input type="text" name="customerStatecode" value="Gujarat, Code : 24" >	<br>
Mobile No. : <input type="number" name="customerMobileNo">	<br>
GST NO. : <input type="text" name="customerGSTNo" id="g" >	<br>
Email : <input type="email" name="customerEmail">	<br>
<input type="hidden" name="ch" value="newCustomer" >
<input type="submit" value="Save" >
</form>

<h1 id="rs"></h1>
<button id="btn" onclick="f2()" >View All Customer</button>
<table id="tb" style="display:none;" >
<tr>
	<th>No.</th>
	<th>Name</th>
	<th>Address-1</th>
	<th>Address-2</th>
	<th>City</th>
	<th>State Code</th>
	<th>GST No.</th>
	<th>Mobile No.</th>
	<th>Email</th>
	<th></th>
	<th></th>
</tr>
</table>

</body>
</html>