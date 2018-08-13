<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/stock.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>	
<!-- <script  type="text/javascript" src="scripts/angular.js"></script> -->
<!-- <script  type="text/javascript" src="scripts/stockAG.js"></script>	 -->
<script type="text/javascript">
var list;
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : 'item?ch=itemStock',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			list = $.parseJSON(x);
			if(list.length>0){
				var tb = document.getElementById("tb");
	            tb.style.display="";
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
		            
		            var txt = list[i].itemSrNo;
					txt = txt.split('*');
					var s = txt.length;
					var y = "";
					for(var j=1;j<=s;j++){
						var x = txt.pop();
						if(x!=""){
							y = y.concat(x);
							if(j!=s)
								y = y.concat(", ");
						}
					}
		            
		            c1.innerHTML = (i+1);
		            c2.innerHTML = list[i].itemName;
		            c3.innerHTML = y;
		            c4.innerHTML = list[i].itemDescription;
		            c5.innerHTML = list[i].itemHSN;
		            c6.innerHTML = list[i].itemGST;
		            c7.innerHTML = list[i].itemQty;
		            if(list[i].itemQty>0)
		            	c8.innerHTML = "<a href='stockPurchase.jsp?id="+list[i].id+"'>View Purchase Bill</a>";
		            else
		            	c8.innerHTML = "<a href='stockSales.jsp?id="+list[i].id+"'>View Sales Bill</a>";
	        	}
			}
			else{
				var rs = document.getElementById("rs");
				rs.innerHTML = "No Items In Stock";
			}
		}
	});
});

$(window).scroll(function() {
    if ($(this).scrollTop() > 75) {
        $(".menubar").addClass('fix');
   } else {
        $(".menubar").removeClass('fix');
   }
});
</script>
</head>
<!-- <body ng-app="stockModule" ng-controller="stockController" > -->
<body>
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
		<li id="current" ><a href="stock.jsp" >STOCK</a></li>
		<li><a href="supplier.jsp" >SUPPLIERS</a></li>
		<li><a href="customer.jsp" >CUSTOMERS</a></li>
<!-- 		<li style="float:right;margin-right:10px;" ><input type="text" ng-model="sr" placeholder="Search" style="border-radius: 4px;border:none;padding: 3px;text-transform: uppercase;padding-left: 7px;color: black;width:200px;margin-top:0px;"/></li> -->
	</ul>
</div>

<h1 id="rs" ></h1>
<table id="tb">
	<tr id="thead">
		<td colspan="8"><h3 style="text-align:center;">Items on hand</h3></td>
	</tr>
	<tr>
		<th>No.</th>
		<th>Name</th>
		<th>Sr.No.</th>
		<th>Description</th>
		<th>HSN</th>
		<th>GST</th>
		<th>Qty</th>
		<th></th>
    </tr>
    
<!--     <tr ng-repeat="i in list | filter:sr"> -->
<!--     	<td>{{ $index + 1 }}</td> -->
<!--     	<td>{{ i.itemName }}</td> -->
<!--     	<td> -->
<%--     		<c:set var="srnum" value="{{ i.itemSrNo }}" ></c:set> --%>
<%--     		<c:forEach var="i" items="${ fun:split(srnum,'*')}"> --%>
<%-- 				${i}:: --%>
<%-- 			</c:forEach>  --%>
<!--     	</td> -->
<!--     	<td>{{ i.itemDescription }}</td> -->
<!--     	<td>{{ i.itemHSN }}</td> -->
<!--     	<td>{{ i.itemGST }}</td> -->
<!--     	<td>{{ i.itemQty }}</td> -->
<!--     	<td> -->
<%--     		<c:set var="sp" value="{{ i.itemQty }}" ></c:set> --%>
<%--     		<c:if test="${sp > 0 }"> --%>
<!--     			<a ng-href='stockPurchase.jsp?id={{ i.id }}'>View Purchase Bill</a><br> -->
<%--     		</c:if> --%>
<%--     		<c:if test="${sp < 0 }"> --%>
<!--     			<a ng-href='stockSales.jsp?id={{ i.id }}'>View Sales Bill</a>	 -->
<%--     		</c:if> --%>
<!--     	</td> -->
<!--     </tr> -->
    
</table>
<span id="bottom"></span>
</body>
</html>