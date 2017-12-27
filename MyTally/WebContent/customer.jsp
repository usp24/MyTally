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
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
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
				          
				         c1.innerHTML = (i+1);
				         c2.innerHTML = list[i].customerName;
				         c3.innerHTML = list[i].customerGSTNo;
				         if(list[i].customerAddress1!="")
					         c4.innerHTML += list[i].customerAddress1 + "<br>";
					     if(list[i].customerAddress2!="")
						     c4.innerHTML += list[i].customerAddress2 + "<br>";
						 if(list[i].customerCity!="")
						     c4.innerHTML += list[i].customerCity + "<br>";
						 if(list[i].customerStatecode!="")
						     c4.innerHTML += list[i].customerStatecode;
				         c5.innerHTML = list[i].customerMobileNo;
				         c6.innerHTML = list[i].customerEmail;
				         c7.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/customer?ch=edit&n="+list[i].customerName+"'><button class='fa fa-pencil' aria-hidden='true'></button></form>";
				         c8.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/customer?ch=delete&n="+list[i].customerName+"'><button class='fa fa-times' aria-hidden='true' onclick='return dz()'></button></form>";
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

<style type="text/css" >
body{
	background-color: #edf8f896;
	overflow:visible;
	margin: 0px;
	padding: 0px;
	text-align: center;
	font-family: Calibri,verdana,arial,sans-serif;
}
a{
	text-decoration: none;
	color:inherit;
	cursor: pointer;
}
button{
	border:none;
	background:none;
	cursor: pointer;
	height:21px;
}
h1{
	color:#5E5E5E;
}


.header{
	margin:auto;
	overflow:hidden;
	padding-top: 10px;
	margin-bottom: 3px;
}
.header #title{
	float: left;
	clear: left;
	padding-left:18px;
}
.header #text{
	float: right;
	padding-right:10px;
	padding-top:15px;
}
.header #title #titleh1{
	color: #4B4B4B;
	text-transform: uppercase;
	font-size : 41px;
	margin: 0px;
}
.header #title #titleh1:HOVER{
	color: #00aba9;
}
.header #title #titleh1 #u{
	font-size:55px;
	color:  #00aba9;
}
.header #text #textp{
	letter-spacing:3px;
	font-size: 14px;
	font-family: verdana;
}

.menubar{
	margin:auto;
	overflow: visible;
}
.fix{
    position:fixed;
    top:0;
    left:0;
    right:0;
    margin:0;
}
.menubar ul{
	display : inline-block;
	background-color: #5E5E5E;
	width: 100%;
	margin: 0px;
	padding:0px;
	padding-left:10px;
	text-align: center;
	font-size: 17px;
	letter-spacing: 1px;
	font-family: verdana;
	box-shadow: -2px 3px 4px -2px #5E5E5E;
}
.menubar li{
	height:33px;
	padding-top:11px;
	padding-left:15px;
	padding-right:15px;
	float:left;
	display: inherit;
	color: white;
}
.menubar #current{
	background: #00aba9;
}
.menubar li:HOVER{
	background: black !important;
}


#btn{
	border:none;
	margin:auto;
	background: #00aba9;
	color: white;
	padding:10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	margin-top: 50px;
	height:50px;
	width: 300px;
	font-size: 20px;
}
#btn:HOVER{
	box-shadow: 1px 2px 9px 0px #5E5E5E;
}
#form{
	margin:auto;
	text-align: center;
	margin-top:70px;
	margin-bottom:100px;
}
input[type=submit]{
	border:none;
	margin:auto;
	background:  #5E5E5E;
	color: white;
	padding:10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	margin-top: 30px;
	height:45px;
	width: 210px;
	font-size: 19px;
}
input[type=submit]:HOVER{
	box-shadow: 1px 2px 9px 0px #5E5E5E;
}
input[type=text],input[type=number],input[type=email]{
	width:250px;
	padding:3px;
	margin-top: -26px;
    margin-bottom: 25px;
    margin-right: -90px;
}
.boxName{
	width:260px;
	margin: auto;
	padding: 0px;
	font-size:18px;
	padding-bottom:3px;
	text-align: right;
    padding-right: 448px;
}


#tb{
	margin: auto;
	margin-top:50px;
	margin-bottom:50px;
	white-space:nowrap;
	border-collapse: collapse; 
	border:2px solid black;
}
#tb #thead td{
	height:20px;
	margin:0px;
	padding:0px;
}
#tb #thead h3{
	color: #5E5E5E;
	text-transform: uppercase;
	margin:0px;
	padding:5px;
	letter-spacing: 1px;
	font-size:22px;
}
#tb #thead h3:HOVER{
	color: #5E5E5E;
	background: #efefef !important;
}
#tb tr{
	border:2px solid black;
}
#tb th{
	text-align:left;
	font-size:21px;
	text-transform: capitalize;
	padding:10px;
	background: #5E5E5E;
	color:white;
	font-weight: normal;
}
#tb td{
	text-align:left;
	border-bottom:none;
	border-top:none;
	background: #efefef;
	padding:10px;
	font-size: 17px;
}
#tb td:FIRST-CHILD{
	text-align:center;
}
#tb td:nth-last-child(-n+2):HOVER{
	background-color: #00aba9;
}
#tb td:nth-child(2){
	 max-width:220px;
     word-wrap: break-word;       
     word-break: break-all;
     white-space: normal;
}
#tb td:nth-child(4){
	 max-width:270px;
     word-wrap: break-word;       
     word-break: break-all;
     white-space: normal;
}

#n{
	text-transform: uppercase;
}
</style>

</head>
<body>

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
</ul>
</div>


<button id="btn" onclick="f2()">ADD NEW CUSTOMER</button>
<div id="form" style="display:none;" >
<h1 style="padding-bottom:20px;">Enter Details Of New Customer</h1>

<form method="post" action="<%=request.getContextPath()%>/customer" onsubmit="return fn()" >
<div class="boxName">Name*</div>
<input type="text" name="customerName" id="n">
<div class="boxName">GST Number*</div>
<input type="text" name="customerGSTNo" id="g" >	 
<div class="boxName">Address-1</div>	 
<input type="text" name="customerAddress1">
<div class="boxName">Address-2</div>	 
<input type="text" name="customerAddress2">	 
<div class="boxName">City</div>
<input type="text" name="customerCity">
<div class="boxName">State & Code</div>	 
<input type="text" name="customerStatecode" value="Gujarat, Code : 24" >	 
<div class="boxName">Contact Number</div>
<input type="number" name="customerMobileNo">	 
<div class="boxName">Email</div>
<input type="email" name="customerEmail"><br>	 
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
</table>

</body>
</html>