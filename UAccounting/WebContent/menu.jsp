<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/menu.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">
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
		<li id="current" ><a href="menu.jsp" >HOME</a></li>
		<li><a href="sales.jsp" >SALE</a></li>
		<li><a href="purchase.jsp" >PURCHASE</a></li>
		<li><a href="item.jsp" >ITEM MASTER</a></li>
		<li><a href="stock.jsp" >STOCK</a></li>
		<li><a href="supplier.jsp" >SUPPLIERS</a></li>
		<li><a href="customer.jsp" >CUSTOMERS</a></li>
	</ul>
</div>

<center>
<div id="mainbox" >

	<div id="box1">
		<a href="menu.jsp" onclick="alert('This Feature is not available in demo version');" ><div class="innerBox" >
			<div class="img"><img src="img/backup.png"></div>
			<div class="a">Backup</div>
		</div></a>
	 	<a href="supplier.jsp"><div class="innerBox" >
			<div class="img"><img src="img/supplier.png"></div>
			<div class="a">Suppliers </div>
		</div></a>
		 <a href="customer.jsp"><div class="innerBox" >
			<div class="img"><img src="img/customer.png"></div>
			<div class="a">Customers </div>
		</div></a>
		<a href="item.jsp"><div class="innerBox" >
			<div class="img"><img src="img/item.png"></div>
			<div class="a">Item Master </div>
		</div></a>	
	</div>
	
	<div id="box2" >
		<a href="sales.jsp"><div class="innerBox" >
			<div class="img"><img src="img/sale.png"></div>
			<div class="a">Sale</div>
		</div></a>
		<a href="purchase.jsp"><div class="innerBox" >
			<div class="img"><img src="img/purchase.png"></div>
			<div class="a">Purchase </div>
		</div></a>
		<a href="menu.jsp" onclick="alert('This Feature is not available in demo version');" ><div class="innerBox" >
			<div class="img"><img src="img/pay.png"></div>
			<div class="a">Payments </div>
		</div></a>
		 <a href="stock.jsp"><div class="innerBox" >
			<div class="img"><img src="img/stock.png"></div>
			<div class="a">Stock </div>
		</div></a>	
	</div>
	<div id="box3" >  
<!-- 		 <a href="other.jsp"><div class="innerBox" > -->
<!-- 			<div class="img"><img src="img/ff.png"></div> -->
<!-- 			<div class="a">Other </div> -->
<!-- 		</div></a> -->
	</div>
</div>
</center>

</body>
</html>