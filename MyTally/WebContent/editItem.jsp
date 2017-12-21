<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Item</title>
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

function f2(){
	tb = document.getElementById("tb");
	tb.style.display = "";
	var rs = document.getElementById("rs");
	rs.style.display="";
}
</script>
<style type="text/css" >
#n{
	text-transform: uppercase;
}
</style>			
</head>
<body>

<form  method="post" action="<%=request.getContextPath()%>/item" onsubmit="return fn()" >
Name : <input type="text" name="itemName" id="n" value="${param.name}" ><br>
Description : <input type="text" name="itemDescription" value="${param.d}"  ><br>
HSN : <input type="text" name="itemHSN"  value="${param.h}" ><br>
GST : <input type="number" name="itemGST" id="g"  value="${param.g}" ><br>
PurchasePrice : <input type="number" name="itemPurchasePrice" step='any'  value="${param.p}" ><br>
SalePrice : <input type="number" name="itemSalesPrice" step='any'  value="${param.s}" ><br>
<input type="submit" value="UPDATE ITEM">
<input type="hidden" name="ch" value="editItem">
<input type="hidden" name="on" value="${param.n}"> 
</form>

</body>
</html>