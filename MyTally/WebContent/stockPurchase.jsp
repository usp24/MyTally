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
		            var c8 = r.insertCell(7);
		            
		            c1.innerHTML = (i+1);
		            c2.innerHTML = list[i].purchaseInvoiceNo;
		            c3.innerHTML = list[i].purchaseInvoiceDate;
		            c4.innerHTML = list[i].extra;
		            c5.innerHTML = list[i].purchaseTotalAmountGST;
		            c6.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/print?ch=purchase&printInvoiceNo="+list[i].purchaseInvoiceNo+"'><button>View</button>"; 
		            c7.innerHTML = "<a href='purchaseedit.jsp?ivn="+list[i].purchaseInvoiceNo+"'>Edit</a>";
		            c8.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/purchase?ch=delete&n="+list[i].purchaseInvoiceNo+"'><button class='fa fa-trash-o' aria-hidden='true' ></button></form>";
				}
			}
			else{
				var rs = document.getElementById("rs");
				rs.innerHTML = "No Record Found !!!";
			}
		}
	});
});
</script>
</head>
<body>

<h1 id="rs" ></h1>

<table id="tb" style="display:none;">
	<tr>
		<th>Sr No.</th>
		<th>Purchase Invoice No.</th>
		<th>Purchase Invoice Date</th>
		<th>Supplier's Name</th>
		<th>Total Amount</th>
		<th></th>
		<th></th>
		<th></th>
    </tr>
</table>

</body>
</html>