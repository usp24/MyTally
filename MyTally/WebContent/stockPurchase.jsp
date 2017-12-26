<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/fa.css">
<title>MyTally | Stock</title>
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">
var list;
var name = "<c:out value='${param.name}' />";
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : 'item?ch=stockPurchase&name='+name+'',
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
		            
		            c1.innerHTML = (i+1);
		            c2.innerHTML = list[i].purchaseInvoiceNo;
		            c3.innerHTML = list[i].purchaseInvoiceDate;
		            c4.innerHTML = list[i].extra;
		            c5.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/print?ch=purchase&printInvoiceNo="+list[i].purchaseInvoiceNo+"'><button  class='fa fa-eye' aria-hidden='true'></button>"; 
		            c6.innerHTML = "<a href='purchaseedit.jsp?ivn="+list[i].purchaseInvoiceNo+"'><i  class='fa fa-pencil' aria-hidden='true' ></i></a>";
		            c7.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/purchase?ch=delete&n="+list[i].purchaseInvoiceNo+"'><button class='fa fa-times' aria-hidden='true' ></button></form>";
				}
			}
			else{
				var rs = document.getElementById("rs");
				rs.innerHTML = "No Record Found !!!";
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


#btn{
	border:none;
	margin:auto;
	background: #00aba9;
	color: white;
	padding:10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	margin-top: 50px;
	height:50px;
	width: 280px;
	font-size: 20px;
}
#btn:HOVER{
	box-shadow: 1px 2px 9px 0px #5E5E5E;
}
#form{
	margin:auto;
	text-align: center;
	margin-top:70px;
	margin-bottom:100px;
}
input[type=submit]{
	border:none;
	margin:auto;
	background:  #5E5E5E;
	color: white;
	padding:10px;
	letter-spacing: 1px;
	text-transform: uppercase;
	margin-top: 30px;
	height:45px;
	width: 210px;
	font-size: 19px;
}
input[type=submit]:HOVER{
	box-shadow: 1px 2px 9px 0px #5E5E5E;
}
input[type=text],input[type=number],input[type=email]{
	width:250px;
	margin-bottom:17px;
	padding:3px;
}
.boxName{
	width:260px;
	margin: auto;
	padding: 0px;
	text-align: left;
	font-size:18px;
	padding-bottom:3px;
}


#tb{
	margin: auto;
	margin-top:50px;
	margin-bottom:50px;
	white-space:nowrap;
	border-collapse: collapse; 
	border:2px solid black;
}
#tb #thead td{
	height:20px;
	min-height:20px;
	max-height:20px;
	margin:0px;
	padding:0px;
}
#tb #thead h3{
	color: #5E5E5E;
	text-transform: uppercase;
	margin:0px;
	padding:5px;
	letter-spacing: 1px;
	font-size:22px;
}
#tb #thead h3:HOVER{
	color: #5E5E5E;
	background: #efefef !important;
}
#tb tr{
	border:2px solid black;
}
#tb th{
	font-size:21px;
	text-transform: capitalize;
	padding:10px;
	background: #5E5E5E;
	color:white;
	font-weight: normal;
}
#tb td{
	border-bottom:none;
	border-top:none;
	background: #efefef;
	padding:10px;
	font-size: 17px;
}
#tb td:nth-child(2),th:nth-child(2),td:nth-child(3),th:nth-child(3),td:nth-child(4),th:nth-child(4),td:nth-child(5),th:nth-child(5){
	text-align: left;
}
#tb #bedit,#bview,#bdel{
	 background-color: Transparent;
	 border: none;
	 cursor: pointer;
	 height:21px;
}
#tb td:nth-last-child(-n+3){
	border:none;
	padding-right:5px;
	padding-left: 5px;
	font-size: 18px;
	width:30px;
}
#tb td:nth-last-child(-n+3):HOVER{
	background-color: #00aba9;
}
#tb td:nth-child(2),td:nth-child(5){
	max-width:140px;
     word-wrap: break-word;       
     word-break: break-all;
     white-space: normal
}
#tb td:nth-child(4){
	max-width:400px;
     word-wrap: break-word;       
     word-break: break-all;
     white-space: normal
}

#n{
	text-transform: uppercase;
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
<li id="current" ><a href="stock.jsp" >STOCK</a></li>
<li><a href="supplier.jsp" >SUPPLIERS</a></li>
<li><a href="customer.jsp" >CUSTOMERS</a></li>
</ul>
</div>


<h1 id="rs" ></h1>

<table id="tb" style="display:none;">
<tr id="thead">
		<td colspan="7"><h3>Purchase Invoices of &nbsp;<i style="color:#00aba9" >'${param.name}'</i></h3></td>
	</tr>
	<tr>
		<th>Sr No.</th>
		<th>Purchase Invoice No.</th>
		<th>Purchase Invoice Date</th>
		<th>Supplier's Name</th>
		<th></th>
		<th></th>
		<th></th>
    </tr>
</table>

</body>
</html>