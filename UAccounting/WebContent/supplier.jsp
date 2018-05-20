<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplier</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/fa.css">
<link rel="stylesheet" href="css/supplier.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script  type="text/javascript" src="scripts/angular.js"></script>
<script  type="text/javascript" src="scripts/supplierAG.js"></script>
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
});
function fn(){
	
	var n = document.getElementById("n");
	var g = document.getElementById("g");
	var flag = true;
	
	if(n.value==""){
		alert("Please Enter Supplier Name");
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
<body ng-app="supplierModule" ng-controller="supplierController" >
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
<li id="current" ><a href="supplier.jsp" >SUPPLIERS</a></li>
<li><a href="customer.jsp" >CUSTOMERS</a></li>
<li style="float:right;margin-right:10px;" ><input type="text" ng-model="sr" placeholder="Search" style="border-radius: 4px;border:none;padding: 3px;text-transform: uppercase;padding-left: 7px;color: black;width:200px;margin-top:0px;margin-right: 0px;"/></li>
</ul>
</div>


<button id="btn" onclick="f2()">ADD NEW SUPPLIER</button>
<div id="form" style="display:none;" >
<h1 style="padding-bottom:20px;">Enter Details Of New Supplier</h1>

<form method="post" action="<%=request.getContextPath()%>/supplier" onsubmit="return fn()" >
<div class="boxName">Name*</div>
<input type="text" name="supplierName" id="n" autocomplete='off' >
<div class="boxName">GST Number*</div>
<input type="text" name="supplierGSTNo" id="g"  autocomplete='off' >
<div class="boxName">Address-1</div>
<input type="text" name="supplierAddress1" autocomplete='off' >
<div class="boxName">Address-2</div>
<input type="text" name="supplierAddress2" autocomplete='off' >
<div class="boxName">City</div>
<input type="text" name="supplierCity" autocomplete='off' >
<div class="boxName">State & Code</div>
<input type="text" name="supplierStatecode" value="Gujarat, Code : 24"  autocomplete='off' >
<div class="boxName">Contact Number</div>
<input type="number" name="supplierMobileNo" autocomplete='off' >
<div class="boxName">Email</div>
<input type="email" name="supplierEmail" autocomplete='off' ><br>
<input type="hidden" name="ch" value="newSupplier" >
<input type="submit" value="Save" >
</form>
</div>


<table id="tb">
<tr id="thead">
		<td colspan="8"><h3>Suppliers</h3></td>
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
	<td>{{ i.supplierName }}</td>
	<td>{{ i.supplierGSTNo }}</td>
	<td>{{ i.supplierAddress1 }}<br>{{ i.supplierAddress2 }}<br>{{ i.supplierCity }}<br>{{ i.supplierStatecode }}</td>
	<td>{{ i.supplierMobileNo }}</td>	
	<td>{{ i.supplierEmail }}</td>
	<td><a ng-href='<%=request.getContextPath()%>/supplier?ch=edit&n={{ i.id }}'><img id='pencil' src='img/edit.png'/></a></td>
	<td><a ng-href='<%=request.getContextPath()%>/supplier?ch=delete&n={{ i.id }}' onclick='return dz()'><img id='cross' src='img/cancle.png' /></td>
</tr>

</table>
<span id="bottom"></span>
</body>
</html>