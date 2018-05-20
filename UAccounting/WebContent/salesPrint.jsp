<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="my.NumberToWord" %>
<html>
<head>
<title>Print</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/salesPrint.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>
<script type="text/javascript" >
window.onafterprint = function() {
	document.getElementById("l1").style.display='';
	document.getElementById("l2").style.display='';
	document.getElementById("prto").style.display='';
	document.getElementById("prtd").style.display='';
	document.getElementById("backIMG").style.display='none';
	document.getElementById("body").style.marginLeft='340px';
	document.body.style.backgroundColor = "#edf8f896";
};
function p(x){
	var div = document.getElementById("copy").innerHTML = x;
	document.getElementById("body").style.marginLeft='0px';
	document.getElementById("l1").style.display='none';
	document.getElementById("l2").style.display='none';
	document.getElementById("prto").style.display='none';
	document.getElementById("prtd").style.display='none';
	document.body.style.backgroundColor = "white";
	document.getElementById("backIMG").style.display='';
	window.print();
}

</script>
<title>Print</title>
</head>
<body>

<div class="header" id="l1" >
	<div id="title" ><h1 id="titleh1" ><a href="menu.jsp" ><span id="u">U</span>Accounting</a></h1></div>
	<div id="text" ><p id="textp" >THE WORLD'S BEST ACCOUNTING SOFTWARE</p></div>
</div>
<div class="menubar" id="l2" >
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

<button onclick="p('(ORIGINAL COPY)')" id="prto" style="margin-top:20px;height:33px;width:160px;background-color:#00aba9;font-size: 17px;color:white;border-radius:5px;">ORIGINAL</button>
<button onclick="p('(DUPLICATE COPY)')" id="prtd" style="margin-top:20px;height:33px;width:160px;background-color:#00aba9;font-size: 17px;color:white;margin-left:100px;border-radius:5px;" >DUPLICATE</button>

<div id="body" style="background-color: white;margin-left:340px;" >


<img src="img/back.jpg" id="backIMG" style="display: none;">
<c:set var="nul" value="" ></c:set>
<c:forEach var="u" items="${sessionScope.user}">
<div id="container">
<center>
<div class="header1">
	<div id="copy"></div>
	<div id="head">TAX INVOICE</div>
</div>

<div id="header2">
	<div class="header21">
		<div id="buyer">
			<b>Buyer :</b><br>
			<c:forEach var="s" items="${sessionScope.customer}" >
			<div id="name"><b>${s.customerName}</b></div>
			<div>
				<c:if test="${ s.customerAddress1 ne nul }">${s.customerAddress1}<br></c:if>
				<c:if test="${ s.customerAddress2 ne nul }">${s.customerAddress2}<br></c:if>
				<c:if test="${ s.customerCity ne nul }">${s.customerCity}<br></c:if>
				<c:if test="${ s.customerStatecode ne nul }">${s.customerStatecode}</c:if>
			</div>
			<div id="gst" >
				<b>Mobile No. : ${s.customerMobileNo}</b><br>
				<b>GSTIN : ${s.customerGSTNo}</b>
			</div>
			</c:forEach>
		</div>
		<div id="invoiceInfo">
			<c:forEach var="b" items="${sessionScope.bill}">
			<c:set var="IGSTvar" value="${b.salesIGST}" scope="page"></c:set>
			<c:set var="vehi" value="${b.vehical}" scope="page"></c:set>
			<c:set var="vehino" value="${b.vehicalNo}" scope="page"></c:set>
			<table border="0" >
					<tr>
						<td>Invoice No. </td>
						<td> : ${b.salesInvoiceNo}</td>
					</tr>
					<tr>
						<td>Invoice Date </td>
						<td> : ${b.salesInvoiceDate}</td>
					</tr>
					<tr>
						<td>Payment Terms</td>
						<td> : ${b.paymentMode}</td>
					</tr>
					<tr>
						<td>Challan Number</td>
						<td> : ${b.challanNumber}</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
		
	<div class="header22">
<!-- 		<div><img src="img/logo.jpg" id="logoIMG"></div> -->
		<div class="salerInfo">
			<div id="add" style="font-size:43px;margin-top:2.3cm;font-weight: bold;" >UAccounting</div>
			<div id="gst"></div>
		</div>
	</div>

</div>

<div id="header3">
	<div style="float: left;clear: left;text-align:left; border-right:1px solid black;padding:4px;width:97.8mm;max-width: 97.8mm;min-width: 97.8mm;overflow:hidden;">
		<b>Vehicle : ${vehi}</b>
	</div>
	<div style="float: left;text-align: left;padding:4px;width:92mm;max-width:92mm;min-width:92mm;overflow:hidden;border-right:1px solid black;">
		<b>Vehicle No. : ${vehino}</b>
	</div>
</div>


<div id="tb1">
<table class="item" cellspacing="0">

	<tr id="itemHeader">
		<th><div id="srno" >Sr No.</div></th>
		<th><div id="goods">Description of Goods</div></th>
		<th><div id="hsn">HSN/SAC</div></th>
		<th><div id="gt">GST Rate</div></th>
		<th><div id="qy">Qty.</div></th>
		<th><div id="price" >Unit Price</div></th>
		<th><div id="dis" >Disc. % </div></th>
		<th><div id="totamt" >Amount</div></th>
	</tr>
	
	<% int z=1; %>			
	<c:forEach var="i" items="${sessionScope.item}">
	<tr id="data" >
		<td id="tdsr" ><div id="srno"><%=z%></div></td>
		<td><div id="goods">
			<div id="name">${i.itemName}<br></div>
			<div id="dn">
				<c:if test="${ i.itemDescription ne nul }">${i.itemDescription}<br></c:if>
				<c:set var="srn" value="${i.itemSrNo}" ></c:set>
				<i><%
					String sr[] = ((String)pageContext.getAttribute("srn")).split("\\*");
					for(int i=0;i<sr.length;i++){
						if(!(sr[i].equals(""))){
							out.print(" "+sr[i]);
							if(i!=(sr.length-1))
								out.print(",");	
						}
					}
				%></i>
			</div>
		</div></td>
		<td><div id="hsn">${i.itemHSN}</div></td>
		<td><div id="gt" >${i.itemGST}%</div></td>
		<td><div id="qy">${i.itemQty}</div></td>
		<td><div id="price">
			<c:set var="p" value="${i.itemSalesPrice}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</div></td>
		<td><div id="dis">
			<c:if test="${ i.itemPurchasePrice ne 0 }">
				<c:set var="p" value="${i.itemPurchasePrice}"></c:set>
				<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
		</div></td>
		<td><div id="totamt" >
			<c:set var="p" value="${i.extrad}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</div></td>
	</tr><%z++;%>
	<c:set var="tq" value="${i.extra}" ></c:set>
	</c:forEach>
	
	<c:forEach var="b2" items="${sessionScope.bill}" >
	<tr class="right" id="subtotalrow">
		<td><div id="srno" ></div></td>
		<td><div id="goods"></div></td>
		<td><div id="hsn"></div></td>
		<td><div id="gt"></div></td>
		<td><div id="qy"></div></td>
		<td><div id="price" ></div></td>
		<td><div id="dis" ></div></td>
		<td id="subtotal">
			<c:set var="p" value="${b2.salesTotalAmount}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	
	
	<c:if test="${ b2.salesGST9 ne 0 }">
	<tr class="right">
		<td></td>
		<td id="gst"> 
			<c:if test="${ IGSTvar eq 'YES' }">IGST 18%</c:if>
			<c:if test="${ IGSTvar eq 'NO' }">SGST 9%</c:if>
		</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt">
			<c:if test="${ IGSTvar eq 'YES' }">
				<c:set var="p" value="${2 * b2.salesGST9}"></c:set>
				<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
			<c:if test="${ IGSTvar eq 'NO' }">
				<c:set var="p" value="${b2.salesGST9}"></c:set>
				<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
		</td>
	</tr>
	
	<c:if test="${ IGSTvar eq 'NO' }">
	<tr class="right" >
		<td></td>
		<td id="gst">CGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt">
			<c:set var="p" value="${b2.salesGST9}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	</c:if>
	</c:if>
				
	<c:if test="${ b2.salesGST14 ne 0 }">
	<tr class="right" >
		<td></td>
		<td id="gst"> 
			<c:if test="${ IGSTvar eq 'YES' }">IGST 28%</c:if>
			<c:if test="${ IGSTvar eq 'NO' }">SGST 14%</c:if>
		</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" >
			<c:if test="${ IGSTvar eq 'YES' }">
				<c:set var="p" value="${2 * b2.salesGST14}"></c:set>
				<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
			<c:if test="${ IGSTvar eq 'NO' }">
				<c:set var="p" value="${b2.salesGST14}"></c:set>
				<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
		</td>
	</tr>
	
	<c:if test="${ IGSTvar eq 'NO' }">
	<tr class="right" >
		<td></td>
		<td id="gst" >CGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" >
			<c:set var="p" value="${b2.salesGST14}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>				
	</c:if>
	</c:if>
	
	<tr class="right" >
		<td></td>
		<td id="roundoff" >Rounding Off</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="roundoffamt" >
			<c:set var="x" value="${b2.salesTotalRoundOffAmount - b2.salesTotalAmountGST }"></c:set>
			<% Double num = (Double)pageContext.getAttribute("x");
				String s = String.format(("%,.2f"),num);
				if(num>=0)
					{%>+(<%= s %>)
				<%}else{ 
					%>(<%= s %>)<%} %>
		</td>
	</tr>
	
	<%for(int i=0;i<200;i++){ %>
	<tr id="data"> 
		<td id="srno"></td>
		<td id="goods"></td>
		<td id="hsn"></td>
		<td id="gst" ></td>
		<td id="qy" ></td>
		<td id="price" ></td>
		<td id="dis" ></td>
		<td id="totamt2"></td>
	</tr>
	<%} %>
	<c:set var="p1" value="${b2.salesTotalRoundOffAmount}" ></c:set>
	</c:forEach>
</table>
</div>

<div id="size">
<table class="item2" cellspacing="0">
	<tr id="lastrow" >
		<td></td>
		<td id="lastrowtot">Total</td>
		<td></td>
		<td></td>
		<td id="lastrowqty" >${tq}</td>
		<td></td>
		<td></td>
		<td id="lastrowamt" >
		&#8377; <%	
			Long aa = new Long((Long)pageContext.getAttribute("p1")); 
			out.print(String.format(("%,.2f"),Double.parseDouble(aa.toString())));
		%>
		</td>
	</tr>
</table>
</div>


<div id="wordrow">
	<div id="wordhead" >Amount Chargeable(in words)</div>
	<div id="word" >INR 
		<%
			NumberToWord yy = new NumberToWord();
			String s = yy.call(aa.toString());
		%>
		<%=s %>
	</div>
</div>

<div class="footer1" >
	<div id="bank" >
		<div style="border: 1px sloid black;overflow: hidden;" >
			<table style="clear: left;float: left;" >
				<tr>
					<th>Bank Name</th>
					<td><b> : </b>${u.bankName }</td>
				</tr>
				<tr>
					<th>A/C No.</th>
					<td><b> : </b>${u.actNo }</td>
				</tr>
			</table>
			<table style="float: left;padding-left: 200px;">
				<tr>
					<th>Branch</th>
					<td><b> : </b>${u.bankBranch}</td>
				</tr>
				<tr>
					<th>IFSC No.</th>
					<td><b> : </b>${u.IFSCNo }</td>
				</tr>
			</table>
		</div>
		<div id="extra">
			<b>${u.tnc6}</b>
		</div>
	</div>
	<div id="tnc" >
		<div id="tnchead" >Terms & Conditions</div>
		<div id="tnctext" >
			${u.tnc1}<br>
			${u.tnc2}<br>
			${u.tnc3}<br>
			${u.tnc4}<br>
			${u.tnc5}
		</div>
	</div>
	<div id="sign" >
		<div id="signline1" ></div>
		<br>
		<br>
		<div id="signline2" >AUTHORISED SIGNATORY</div>
	</div>
</div>



<div id="footertd">
	<div id="line1" >${u.subTo}</div>
	<div id="line2" >This is a Computer Generated Invoice</div>
</div>
</center>
</div>
</c:forEach>


</div>
</body>
</html>