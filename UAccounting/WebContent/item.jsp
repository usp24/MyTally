<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<html>
	<head>
		<link rel="stylesheet" href="css/fa.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Items</title>
		<link rel="icon" type="image/png" href="img/fi1.ico">
		<link rel="stylesheet" href="css/item.css">
		<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
		<script  type="text/javascript" src="scripts/angular.js"></script>
		<script  type="text/javascript" src="scripts/itemAG.js"></script>		
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
<body ng-app="itemModule" ng-controller="itemController">
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
		<li id="current" ><a href="item.jsp" >ITEM MASTER</a></li>
		<li><a href="stock.jsp" >STOCK</a></li>
		<li><a href="supplier.jsp" >SUPPLIERS</a></li>
		<li><a href="customer.jsp" >CUSTOMERS</a></li>
		<li style="float:right;" ><input type="text" ng-model="sr" placeholder="Search" style="width:200px;margin-right:10px;border-radius: 4px;border:none;padding: 3px;text-transform: uppercase;padding-left: 7px;color: black;margin-top:0px;"/></li>
	</ul>
</div>


<button id="btn" onclick="f2()">ADD NEW ITEM</button>
<div id="form" style="display:none;" >
<h1  style="padding-bottom:20px;">Enter Details Of New Item</h1>

<form  method="post" action="<%=request.getContextPath()%>/item" onsubmit="return fn()" >
<div class="boxName" >Name*</div>
<input type="text" name="itemName" id="n" placeholder1="Enter Name..." autocomplete='off' >
<div class="boxName" >Description</div>
<input type="text" name="itemDescription" placeholder1="Enter Description..."  autocomplete='off' >
<div class="boxName" >HSN</div>
<input type="text" name="itemHSN"  placeholder1="Enter HSN..." autocomplete='off' >
<div class="boxName" >GST</div>
<input type="number" name="itemGST" id="g"  placeholder1="Enter GST..." autocomplete='off' >
<div class="boxName" >Purchase Price</div>
<input type="number" name="itemPurchasePrice" step='any'  placeholder1="Enter Purchase Price..." autocomplete='off' >
<div class="boxName" >Sale Price</div>
<input type="number" name="itemSalesPrice" step='any'  placeholder1="Enter Sale Price..." autocomplete='off' ><br>
<input type="submit" value="SAVE ITEM">
<input type="hidden" name="ch" value="itemEntry">
</form>
</div>

<table id="tb">
	<tr id="thead">
		<td colspan="9"><h3>Items</h3></td>
	</tr>
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
	<tr ng-repeat="i in list | filter:sr" >
		<td>{{ $index + 1 }}</td>
		<td>{{ i.itemName }}</td>
		<td>{{ i.itemDescription }}</td>
		<td>{{ i.itemHSN }}</td>
		<td>{{ i.itemGST }}</td>
		<td>{{ i.extra }}</td>
		<td>{{ i.itemSrNo }}</td>
		<td>
			<a ng-href='<%=request.getContextPath()%>/item?ch=edit&n={{ i.id }}'><img id='pencil' src='img/edit.png' /></a>
		</td>
		<td>
			<a ng-href='<%=request.getContextPath()%>/item?ch=delete&n={{ i.id }}' onclick='return dz()'><img id='cross' src='img/cancle.png' /></a>
		</td>
	</tr>
</table>
<span id="bottom"></span>
</body>
</html>