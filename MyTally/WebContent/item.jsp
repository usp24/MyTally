<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<html>
	<head>
		<link rel="stylesheet" href="css/fa.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>MyTally | Items</title>
		<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
		<script type="text/javascript">
		var dr = "<c:out value="${param.dr}" />";
		if(dr=="t"){
			alert("Deleted Successfully");
			dr="z";
			window.open("item.jsp", "_self");
		}
		else if(dr=="f"){
			alert("Cant Delete");
			dr="z";
			window.open("item.jsp", "_self");
		}
		else{dr="z";}
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
			
			var list;
				$.ajax({
					type : 'POST',
					url : 'item?ch=itemView',
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
					            c2.innerHTML = list[i].itemName;
					            c3.innerHTML = list[i].itemDescription;
					            c4.innerHTML = list[i].itemHSN;
					            c5.innerHTML = list[i].itemGST;
					            c6.innerHTML = list[i].itemPurchasePrice;
					            c7.innerHTML = list[i].itemSalesPrice;
					            c8.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/item?ch=edit&n="+list[i].itemName+"'><button>Edit</button></form>";
						        c9.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/item?ch=delete&n="+list[i].itemName+"'><button class='fa fa-trash-o' aria-hidden='true' ></button></form>";
				        	}
						}
						else{
							rs.innerHTML = "No Items Found !!!";
						}
					}
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
Name : <input type="text" name="itemName" id="n"><br>
Description : <input type="text" name="itemDescription" ><br>
HSN : <input type="text" name="itemHSN" ><br>
GST : <input type="number" name="itemGST" id="g" ><br>
PurchasePrice : <input type="number" name="itemPurchasePrice" step='any' ><br>
SalePrice : <input type="number" name="itemSalesPrice" step='any' ><br>
<input type="submit" value="ADD ITEM">
<input type="hidden" name="ch" value="itemEntry">
</form>

<button id="btn" onclick="f2()" >View All Items</button>
<h1 id="rs" style="display:none;"></h1>
<table id="tb" style="display:none;" >
<tr>
	<th>No.</th>
	<th>Name</th>
	<th>Description</th>
	<th>HSN</th>
	<th>GST</th>
	<th>Purchase Price</th>
	<th>Sales Price</th>
	<th></th>
	<th></th>
</tr>
</table>

</body>
</html>