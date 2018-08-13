<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/fa.css">
<link rel="stylesheet" href="css/customer.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script  type="text/javascript" src="scripts/angular.js"></script>
<script  type="text/javascript" src="scripts/customerAG.js"></script>
<script  type="text/javascript">
var dr = "<c:out value="${param.dr}"/>";
if(dr=="t"){
	alert("Deleted Successfully");
	dr="z";
	window.open("customer.jsp", "_self");
}
else if(dr=="f"){
	alert("Cant Delete");
	dr="z";
	window.open("customer.jsp", "_self");
}
else{dr="z";}
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
});
function fn(){
	
	var n = document.getElementById("n");
	var g = document.getElementById("g");
	var flag = true;
	
	if(n.value==""){
		alert("Please Enter Customer Name");
		flag = false;
	}
	
	if(flag==true)
		return true;
	else
		return false;
}
function f2(){
	var form = document.getElementById("form");
	form.style.display="";
	
	var btn = document.getElementById("btn");
	btn.style.display="none";
}
$(window).scroll(function() {
    if ($(this).scrollTop() > 75) {
         $(".menubar").addClass('fix');
    } else {
         $(".menubar").removeClass('fix');
    }
});
function dz(){
	if (confirm("Are You Sure You Want To Delete ?") == true)
		return true;
	else
		return false;
}
</script>
</head>
<body ng-app="customerModule" ng-controller="customerController" >
<a href="#bottom" id='dwn' style="position: fixed;top:660px;right:35px;"><img src="img/down.png" style="width:60px;" onclick="document.getElementById('up').style.display='';document.getElementById('dwn').style.display='none'" ></a>
<a href="#top" id='up' style="position: fixed;top:660px;right:35px;display:none;"><img src="img/up.png" style="width:60px;" onclick="document.getElementById('up').style.display='none';document.getElementById('dwn').style.display=''"></a>
<span id="top"></span>

<div class="header">
<div id="title" ><h1 id="titleh1" ><a href="menu.jsp" ><span id="u">U</span>Accounting</a></h1></div>
<div id="text" ><p id="textp" >THE WORLD'S BEST ACCOUNTING SOFTWARE</p></div>
</div>
<div class="menubar">
<ul>
<li><a href="menu.jsp" >HOME</a></li>
<li><a href="sales.jsp" >SALE</a></li>
<li><a href="purchase.jsp" >PURCHASE</a></li>
<li><a href="item.jsp" >ITEM MASTER</a></li>
<li><a href="stock.jsp" >STOCK</a></li>
<li><a href="supplier.jsp" >SUPPLIERS</a></li>
<li id="current" ><a href="customer.jsp" >CUSTOMERS</a></li>
<li style="float:right;margin-right:10px;" ><input type="text" ng-model="sr" placeholder="Search" style="border-radius: 4px;border:none;padding: 3px;text-transform: uppercase;padding-left: 7px;color: black;width:200px;margin-top:0px;margin-right: 0px;"/></li>
</ul>
</div>


<button id="btn" onclick="f2()">ADD NEW CUSTOMER</button>
<div id="form" style="display:none;" >
<h1 style="padding-bottom:20px;">Enter Details Of New Customer</h1>

<form method="post" action="<%=request.getContextPath()%>/customer" onsubmit="return fn()" >
<div class="boxName">Name*</div>
<input type="text" name="customerName" id="n" autocomplete='off' >
<div class="boxName">GST Number*</div>
<input type="text" name="customerGSTNo" id="g"  autocomplete='off' >	 
<div class="boxName">Address-1</div>	 
<input type="text" name="customerAddress1" autocomplete='off' >
<div class="boxName">Address-2</div>	 
<input type="text" name="customerAddress2" autocomplete='off' >	 
<div class="boxName">City</div>
<input type="text" name="customerCity" autocomplete='off' >
<div class="boxName">State & Code</div>	 
<input type="text" name="customerStatecode" value="Gujarat, Code : 24" autocomplete='off'  >	 
<div class="boxName">Contact Number</div>
<input type="number" name="customerMobileNo" autocomplete='off' >	 
<div class="boxName">Email</div>
<input type="email" name="customerEmail" autocomplete='off' ><br>	 
<input type="hidden" name="ch" value="newCustomer" >
<input type="submit" value="Save" >
</form>
</div>

<table id="tb">
<tr id="thead">
		<td colspan="8"><h3>Customers</h3></td>
	</tr>
<tr>
	<th>No.</th>
	<th>Name</th>
	<th>GST No.</th>
	<th>Address</th>
	<th>Mobile No.</th>
	<th>Email</th>
	<th></th>
	<th></th>
</tr>
<tr ng-repeat="i in list | filter:sr">
	<td>{{ $index + 1 }}</td>
	<td>{{ i.customerName }}</td>
	<td>{{ i.customerGSTNo }}</td>
	<td>{{ i.customerAddress1 }}<br>{{ i.customerAddress2 }}<br>{{ i.customerCity }}<br>{{ i.customerStatecode }}</td>
	<td>{{ i.customerMobileNo }}</td>	
	<td>{{ i.customerEmail }}</td>
	<td><a ng-href='<%=request.getContextPath()%>/customer?ch=edit&n={{ i.id }}'><img id='pencil' src='img/edit.png'/></a></td>
	<td><a ng-href='<%=request.getContextPath()%>/customer?ch=delete&n={{ i.id }}' onclick='return dz()'><img id='cross' src='img/cancle.png' /></td>
</tr>
</table>
<span id="bottom"></span>
</body>
</html>