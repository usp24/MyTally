<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/fa.css">
<title>Purchase</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/purchase.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script  type="text/javascript" src="scripts/angular.js"></script>
<script  type="text/javascript" src="scripts/purchaseAG.js"></script>		
<script type="text/javascript">
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
<body ng-app="purchaseModule" ng-controller="purchaseController">
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
		<li id="current" ><a href="purchase.jsp" >PURCHASE</a></li>
		<li><a href="item.jsp" >ITEM MASTER</a></li>
		<li><a href="stock.jsp" >STOCK</a></li>
		<li><a href="supplier.jsp" >SUPPLIERS</a></li>
		<li><a href="customer.jsp" >CUSTOMERS</a></li>
		<li style="float:right;margin-right:10px;" ><input type="text" ng-model="sr" placeholder="Search" style="border-radius: 4px;border:none;padding: 3px;text-transform: uppercase;padding-left: 7px;color: black;width:200px;"/></li>
	</ul>
</div>

<div id="link" ><a href="purchaseitem.jsp">New Purchase Bill</a></div>

<table id="tb">
	<tr id="thead">
		<td colspan="8"><h3>Purchase Invoices</h3></td>
	</tr>
	<tr>
		<th>No.</th>
		<th>Invoice No.</th>
		<th>Invoice Date</th>
		<th>Supplier</th>
		<th>Total Amount</th>
		<th></th>
		<th></th>
		<th></th>
    </tr>
    <tr ng-repeat="i in list | filter:sr" ng-init="l=list.length" >
	    <td>{{ l - $index }}</td>
    	<td>{{ i.purchaseInvoiceNo }}</td>
    	<td>{{ i.purchaseInvoiceDate }}</td>
    	<td>{{ i.extra }}</td>
    	<td>{{ i.extra2 }}</td>
		<td>
	    	<a ng-href='<%=request.getContextPath()%>/print?ch=purchase&printInvoiceNo={{ i.purchaseInvoiceNo }}'><img id='eye' src='img/eye.png' /></a>
	    </td>
	    <td>
	    	<a ng-href='purchaseedit.jsp?ivn={{ i.purchaseInvoiceNo }}'><img id='pencil' src='img/edit.png' /></a>		
	    </td>
	    <td>
	    	<a ng-href='<%=request.getContextPath()%>/purchase?ch=delete&n={{ i.purchaseInvoiceNo }}' onclick='return dz()'><img id='cross' src='img/cancle.png' /></a>
	    </td>
    </tr>
</table>
<span id="bottom"></span>
</body>
</html>