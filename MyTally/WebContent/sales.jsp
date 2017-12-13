<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/fa.css">
<title>MyTally | Sales</title>

<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
<script type="text/javascript">
$(document).ready(function(){
		
		var list;
		var id=1;
		
		$.ajax({
			type : 'POST',
			url : 'sales?ch=salesBillShow',
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
			            
			            c1.innerHTML = id;
			            c2.innerHTML = list[i].salesInvoiceNo;
			            c3.innerHTML = list[i].salesInvoiceDate;
			            c4.innerHTML = list[i].extra;
			            c5.innerHTML = list[i].salesTotalRoundOffAmount;
			            c6.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/print?ch=sales&printInvoiceNo="+list[i].salesInvoiceNo+"'><button>View</button>"; 
			            c7.innerHTML = "<a href='salesedit.jsp?ivn="+list[i].salesInvoiceNo+"'>Edit</a>";
			            c8.innerHTML = "<form method='post' action='<%=request.getContextPath()%>/sales?ch=delete&n="+list[i].salesInvoiceNo+"'><button><i class='fa fa-trash-o' aria-hidden='true'></i></button></form>";
			            
			            id++;
					}
				}
			}
		});
});
</script>
</head>
<body>

<a href="salesitem.jsp" >New Sales Bill</a>

<table id="tb" style="display:none;">
	<tr>
		<th>Sr No.</th>
		<th>Sales Invoice No.</th>
		<th>Sales Invoice Date</th>
		<th>Customer's Name</th>
		<th>Total Amount</th>
		<th></th>
		<th></th>
		<th></th>
    </tr>
</table>

</body>
</html>