<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
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
function modefn(){
	var cash = document.getElementById("divCash");
	var cheque = document.getElementById("divCheque");
	var online = document.getElementById("divOnline");
	var mode = document.getElementById("mode");
	var modeid = document.getElementById("modeid");
	modeid.value = mode.options[mode.selectedIndex].value;
	
	if(mode.options[mode.selectedIndex].value=="cash"){
		cash.style.display="";
		cheque.style.display="none";
		online.style.display="none";
	}
	else if(mode.options[mode.selectedIndex].value=="cheque"){
		cash.style.display="none";
		cheque.style.display="";
		online.style.display="none";
	}
	else if(mode.options[mode.selectedIndex].value=="online"){
		cash.style.display="none";
		cheque.style.display="none";
		online.style.display="";
	}
	else{
		cash.style.display="none";
		cheque.style.display="none";
		online.style.display="none";
	}
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


<c:set var="d" value="${param.date}" ></c:set>
<%
	String dt[] = ((String)pageContext.getAttribute("d")).split("-");
	String date = "";
	for(int i=2;i>=0;i--){
		date = date.concat(dt[i]);
		if(i!=0)
			date = date.concat("-");
	}
%>
<table id="tb" >
	<tr>
		<th>Invoice No.</th>
		<th>Invoice Date</th>
		<th>Total Amount On Bill</th>
		<th>Customer Name</th>
	</tr>
	<tr>
		<td>${param.no}</td>
		<td><%=date%></td>
		<td>${param.amt}</td>
		<td>${param.name}</td>
	</tr>
</table>

<datalist id="bank" ></datalist>
<datalist id="branch" ></datalist>

<h1 style="padding-bottom:20px;" >Enter Payment Details</h1>
<form method="post" action="<%=request.getContextPath()%>/payment">
	<div class="boxName">Payment Mode</div>
	<select name="payMode" id="mode" onchange="modefn()" >
		<option value="none">- SELECT PAYMENT MODE -</option>
		<option value="cash">CASH</option>
		<option value="cheque">CHEQUE</option>
		<option value="online">ONLINE</option>
	</select>

	<div id="divCash" style="display:none;" >
		<div class="boxName">Received Cash Amount</div>
		<input type="number" name="amount" autocomplete='off' step="any">
		<div class="boxName">Discount On Final Amount</div>
		<input type="number" name="discount" autocomplete='off' step="any">
		<div class="boxName">Date Of Payment</div>
		<input type="date" name="paymentDate" autocomplete='off'>
		<div class="boxName">Other Information</div>
		<input type="text" name="other" autocomplete='off'>
	</div>
	<div id="divCheque" style="display:none;">
		<div class="boxName">Bank Name</div>
		<input type="text" name="bankName" autocomplete='off' list="bank">
		<div class="boxName">Branch</div>
		<input type="text" name="brachName" autocomplete='off' list="branch">
		<div class="boxName">Cheque No.</div>
		<input type="text" name="chequeNo" autocomplete='off'>
		<div class="boxName">Date On Cheque</div>
		<input type="date" name="chequeDate" autocomplete='off'>
		<div class="boxName">Amount</div>
		<input type="number" name="amount" autocomplete='off' step="any">
		<div class="boxName">Date Of Payment</div>
		<input type="date" name="paymentDate" autocomplete='off'>
		<div class="boxName">Discount On Final Amount</div>
		<input type="number" name="discount" autocomplete='off' step="any">
		<div class="boxName">Other Information</div>
		<input type="text" name="other" autocomplete='off'>
	</div>
	<div id="divOnline" style="display:none;">
		<div class="boxName">Transaction id</div>
		<input type="text" name="tid" autocomplete='off'>
		<div class="boxName">Bank Name</div>
		<input type="text" name="bankName" autocomplete='off' list="bank">
		<div class="boxName">Date Of Payment</div>
		<input type="date" name="paymentDate" autocomplete='off'>
		<div class="boxName">Amount</div>
		<input type="number" name="amount" autocomplete='off' step="any">
		<div class="boxName">Discount On Final Amount</div>
		<input type="number" name="discount" autocomplete='off' step="any">
		<div class="boxName">Other Information</div>
		<input type="text" name="other" autocomplete='off'>
	</div>
	<br>
	<input type="submit" value="SAVE">
	<input type="hidden" name="ch" value="directSales">
	<input type="hidden" name="no" value="${param.no}">
	<input type="hidden" name="name" value="${param.name}">
	<input type="hidden" name="date" value="${param.date}">
	<input type="hidden" name="amt" value="${param.amt}">
	<input type="hidden" name="mode" id="modeid">
</form>
</body>
</html>