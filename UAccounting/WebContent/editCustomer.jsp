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
<link rel="stylesheet" href="css/editCustomer.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var on = "<c:out value='${sessionScope.obj.customerName}' />";
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
$(window).scroll(function() {
    if ($(this).scrollTop() > 75) {
         $(".menubar").addClass('fix');
    } else {
         $(".menubar").removeClass('fix');
    }
});
</script>

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

<div id="form">
<h1 style="padding-bottom:20px;" >Update Customer's Details</h1>
<form method="post" action="<%=request.getContextPath()%>/customer" onsubmit="return fn()" >
<div class="boxName">Name*</div>
<input type="text" name="customerName" id="n" value="${sessionScope.obj.customerName}" autocomplete='off' >
<div class="boxName">GST Number*</div>
<input type="text" name="customerGSTNo" id="g" value="${sessionScope.obj.customerGSTNo}"  autocomplete='off' >
<div class="boxName">Address-1</div>
<input type="text" name="customerAddress1" value="${sessionScope.obj.customerAddress1}" autocomplete='off' >
<div class="boxName">Address-2</div>
<input type="text" name="customerAddress2"  value="${sessionScope.obj.customerAddress2}" autocomplete='off' >
<div class="boxName">City</div>
<input type="text" name="customerCity"  value="${sessionScope.obj.customerCity}" autocomplete='off' >
<div class="boxName">State & Code</div>	
<input type="text" name="customerStatecode"  value="${sessionScope.obj.customerStatecode}" autocomplete='off' >
<div class="boxName">Contact Number</div>
<input type="number" name="customerMobileNo"  value="${sessionScope.obj.customerMobileNo}" autocomplete='off' >
<div class="boxName">Email</div>
<input type="email" name="customerEmail" value="${sessionScope.obj.customerEmail}"><br>

<input type="hidden" name="on" value="${sessionScope.obj.id}">
<input type="hidden" name="ch" value="editCustomer" >
<input type="submit" value="Update" >
</form>
</div>


</body>
</html>