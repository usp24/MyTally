<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Supplier</title>
<link rel="stylesheet" href="css/fa.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var dr = "<c:out value="${param.dr}"/>";
if(dr=="t"){
	alert("Deleted Successfully");
	dr="z";
	window.open("supplier.jsp", "_self");
}
else if(dr=="f"){
	alert("Cant Delete");
	dr="z";
	window.open("supplier.jsp", "_self");
}
else{dr="z";}
$(document).ready(function(){
	
	$("#n").blur(function(){
		$.ajax({
			type : 'POST',
			url : 'supplier?ch=supplierEntryBefore',
			headres : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(x){
				var list1 = $.parseJSON(x);
				var n = document.getElementById("n");
				for(var i=0;i<list1.length;i++){
					if(n.value.toUpperCase()==list1[i].supplierName){
						alert("Supplier Already Registered");
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
			url : 'supplier?ch=supplierView',
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
				         var c10 = r.insertCell(9);
				         var c11 = r.insertCell(10);
				          
				         c1.innerHTML = (i+1);
				         c2.innerHTML = list[i].supplierName;
				         c3.innerHTML = list[i].supplierAddress1;
				         c4.innerHTML = list[i].supplierAddress2;
				         c5.innerHTML = list[i].supplierCity;
				         c6.innerHTML = list[i].supplierStatecode;
				         c7.innerHTML = list[i].supplierGSTNo;
				         c8.innerHTML = list[i].supplierMobileNo;
				         c9.innerHTML = list[i].supplierEmail;
				         c10.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/supplier?ch=edit&n="+list[i].supplierName+"'><button>Edit</button></form>";
				         c11.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/supplier?ch=delete&n="+list[i].supplierName+"'><button class='fa fa-trash-o' aria-hidden='true' ></button></form>";
			    	}
				}
				else{
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
		alert("Please Enter Supplier Name");
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

<h1>Supplier's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/supplier" onsubmit="return fn()" >
Name : <input type="text" name="supplierName" id="n">	<br>
Address-1 : <input type="text" name="supplierAddress1">	<br>
Address-2 : <input type="text" name="supplierAddress2">	<br>
City : <input type="text" name="supplierCity">	<br>
State & Code : <input type="text" name="supplierStatecode" value="Gujarat, Code : 24" >	<br>
Mobile No. : <input type="number" name="supplierMobileNo">	<br>
GST NO. : <input type="text" name="supplierGSTNo" id="g" >	<br>
Email : <input type="email" name="supplierEmail">	<br>
<input type="hidden" name="ch" value="newSupplier" >
<input type="submit" value="Save" >
</form>

<button id="btn" onclick="f2()" >View All Supplier</button>
<h1 id="rs" style="display:none;" ></h1>
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