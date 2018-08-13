<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/fa.css">
<link rel="stylesheet" href="css/stockSales.css">
<link rel="icon" type="image/png" href="img/fi1.ico">
<title>Stock</title>
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">
var list;
var id = "<c:out value='${param.id}'/>";
$(document).ready(function(){
	$.ajax({
		type : 'POST',
		url : 'item?ch=stockSales&id='+id+'',
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
		            
		            c1.innerHTML = i+1;
		            c2.innerHTML = list[i].salesInvoiceNo;
		            c3.innerHTML = list[i].salesInvoiceDate;
		            c4.innerHTML = list[i].extra;
		            c5.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/print?ch=sales&printInvoiceNo="+list[i].salesInvoiceNo+"'><button><img id='eye' src='img/eye.png' /></button>"; 
		            c6.innerHTML = "<a href='salesedit.jsp?ivn="+list[i].salesInvoiceNo+"'><img id='pencil' src='img/edit.png' /></a>";
		            c7.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/sales?ch=delete&n="+list[i].salesInvoiceNo+"'><button onclick='return dz()' ><img id='cross' src='img/cancle.png' /></button></form>";
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
function dz(){
	if (confirm("Are You Sure You Want To Delete ?") == true)
		return true;
	else
		return false;
}
</script>

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
		<td colspan="7"><h3>Sales Invoices</h3></td>
	</tr>

	<tr>
		<th>Sr No.</th>
		<th>Sales Invoice No.</th>
		<th>Sales Invoice Date</th>
		<th>Customer's Name</th>
		<th></th>
		<th></th>
		<th></th>
    </tr>
</table>

</body>
</html>