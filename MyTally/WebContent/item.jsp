<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MyTally | Items</title>
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
							var list = $.parseJSON(x);
							var n = document.getElementById("n");
							for(var i=0;i<list.length;i++){
								if(n.value.toUpperCase()==list[i].itemName){
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
				else if(g.value!="18" && g.value!="28" && g.value!='' ){
					alert("GST Rate Can Only Be 18% OR 28%");
					g.value ='';
					flag = false;
				}
				
				if(flag==true)
					return true;
				else
					return false;
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
Name : <input type="text" name="itemName" id="n"><br>
Description : <input type="text" name="itemDescription" ><br>
HSN : <input type="text" name="itemHSN" ><br>
GST : <input type="number" name="itemGST" id="g" ><br>
PurchasePrice : <input type="number" name="itemPurchasePrice" step='any' ><br>
SalePrice : <input type="number" name="itemSalesPrice" step='any' ><br>
<input type="submit" value="ADD ITEM">
<input type="hidden" name="ch" value="itemEntry">
</form>

</body>
</html>