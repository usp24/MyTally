<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Stock</title>
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>		
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
		            	c8.innerHTML = "<form action='<%=request.getContextPath()%>/item' method='get'><input type='submit' value='View Purchase Bill'/><input type='hidden' name='ch' value='stockPurchase'><input type='hidden' name='name' value='"+list[i].itemName+"'></form>";
		            else
		            	c8.innerHTML = "";
	        	}
			}
			else{
				var rs = document.getElementById("rs");
				rs.innerHTML = "No Items In Stock";
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
		<th>No.</th>
		<th>Name</th>
		<th>Sr.No.</th>
		<th>Description</th>
		<th>HSN</th>
		<th>GST</th>
		<th>Qty</th>
		<th></th>
    </tr>
</table>

</body>
</html>