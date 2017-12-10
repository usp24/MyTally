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
				alert("Item Added Successfully !!!");
			}
		</script>
<style type="text/css" >
#n{
	text-transform: uppercase;
}
</style>
	</head>
<body>

<form  method="post" action="<%=request.getContextPath()%>/item" >
Name : <input type="text" name="itemName" id="n"><br>
Description : <input type="text" name="itemDescription" ><br>
HSN : <input type="text" name="itemHSN" ><br>
GST : <input type="text" name="itemGST" value="18"><br>
PurchasePrice : <input type="text" name="itemPurchasePrice" value="0"><br>
SalePrice : <input type="text" name="itemSalesPrice" value="0"><br>
<input type="submit" value="ADD ITEM" onclick="fn()">
<input type="hidden" name="ch" value="itemEntry">
</form>

</body>
</html>