<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/editItem.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">		
$(document).ready(function(){
	$("#n").blur(function(){
		$.ajax({
			type : 'POST',
			url : 'item?ch=itemEntryBefore',
			headres : {
				Accept : "application/json; charset=utf-8",
				"Content-Type" : "application/json; charset=utf-8"
			},
			success : function(x){
				var list1 = $.parseJSON(x);
				var n = document.getElementById("n");
				for(var i=0;i<list1.length;i++){
					if(n.value.toUpperCase()==list1[i].itemName){
						alert("This Item Is Already Registered");
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
		alert("Please Enter Item Name");
		flag = false;
	}
	else if(g.value!="18" && g.value!="28" && g.value!=''){
		alert("GST Rate Can Only Be 18% OR 28%");
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
<li id="current" ><a href="item.jsp" >ITEM MASTER</a></li>
<li><a href="stock.jsp" >STOCK</a></li>
<li><a href="supplier.jsp" >SUPPLIERS</a></li>
<li><a href="customer.jsp" >CUSTOMERS</a></li>
</ul>
</div>


<div id="form">
<h1 style="padding-bottom:20px;">Update Details Of Item</h1>

<form  method="post" action="<%=request.getContextPath()%>/item" onsubmit="return fn()" >
<div class="boxName" >Name*</div>
<input type="text" name="itemName" id="n" value="${sessionScope.obj.itemName}"  autocomplete='off' >
<div class="boxName" >Description</div>
<input type="text" name="itemDescription" value="${sessionScope.obj.itemDescription}" autocomplete='off' >
<div class="boxName" >HSN</div>
<input type="text" name="itemHSN"  value="${sessionScope.obj.itemHSN}"  autocomplete='off' >
<div class="boxName" >GST</div>
<input type="number" name="itemGST" id="g"  value="${sessionScope.obj.itemGST}"  autocomplete='off' >
<div class="boxName" >Purchase Price</div>
<input type="number" name="itemPurchasePrice" step='any'  value="${sessionScope.obj.itemPurchasePrice}"  autocomplete='off' >
<div class="boxName" >Sale Price</div>
<input type="number" name="itemSalesPrice" step='any'  value="${sessionScope.obj.itemSalesPrice}"  autocomplete='off' ><br>

<input type="submit" value="UPDATE ITEM">
<input type="hidden" name="ch" value="editItem">
<input type="hidden" name="on" value="${sessionScope.obj.id}"> 
</form>
</div>


</body>
</html>