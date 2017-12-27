<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
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

<style type="text/css" >
body{
	background-color: #edf8f896;
	overflow:visible;
	margin: 0px;
	padding: 0px;
	text-align: center;
	font-family: Calibri,verdana,arial,sans-serif;
}
a{
	text-decoration: none;
	color:inherit;
	cursor: pointer;
}
button{
	border:none;
	background:none;
	cursor: pointer;
	height:21px;
}
h1{
	color:#5E5E5E;
}


.header{
	margin:auto;
	overflow:hidden;
	padding-top: 10px;
	margin-bottom: 3px;
}
.header #title{
	float: left;
	clear: left;
	padding-left:18px;
}
.header #text{
	float: right;
	padding-right:10px;
	padding-top:15px;
}
.header #title #titleh1{
	color: #4B4B4B;
	text-transform: uppercase;
	font-size : 41px;
	margin: 0px;
}
.header #title #titleh1:HOVER{
	color: #00aba9;
}
.header #title #titleh1 #u{
	font-size:55px;
	color:  #00aba9;
}
.header #text #textp{
	letter-spacing:3px;
	font-size: 14px;
	font-family: verdana;
}

.menubar{
	margin:auto;
	overflow: visible;
}
.fix{
    position:fixed;
    top:0;
    left:0;
    right:0;
    margin:0;
}
.menubar ul{
	display : inline-block;
	background-color: #5E5E5E;
	width: 100%;
	margin: 0px;
	padding:0px;
	padding-left:10px;
	text-align: center;
	font-size: 17px;
	letter-spacing: 1px;
	font-family: verdana;
	box-shadow: -2px 3px 4px -2px #5E5E5E;
}
.menubar li{
	height:33px;
	padding-top:11px;
	padding-left:15px;
	padding-right:15px;
	float:left;
	display: inherit;
	color: white;
}
.menubar #current{
	background: #00aba9;
}
.menubar li:HOVER{
	background: black !important;
}
</style>

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
<li><a href="customer.jsp" >CUSTOMERS</a></li>
</ul>
</div>


</body>
</html>