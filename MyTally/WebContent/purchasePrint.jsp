<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="my.NumberToWord" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/print.css" type="text/css">
<title>MyTally | Print</title>
</head>
<body>
<c:set var="nul" value="" ></c:set>
<div id="container"><center>
<div class="header1">
	<div id="copy">(DUPLICATE COPY)</div>
	<div id="head">TAX INVOICE</div>
</div>

<div id="header2">
	<div class="header21">
		<div id="buyer">
			<b>Supplier :</b><br>
			<c:forEach var="s" items="${sessionScope.supplier}" >
			<div id="name"><b>${s.supplierName}</b></div>
			<div>
				<c:if test="${ s.supplierAddress1 ne nul }">${s.supplierAddress1}<br></c:if>
				<c:if test="${ s.supplierAddress2 ne nul }">${s.supplierAddress2}<br></c:if>
				<c:if test="${ s.supplierCity ne nul }">${s.supplierCity}<br></c:if>
				<c:if test="${ s.supplierStatecode ne nul }">${s.supplierStatecode}</c:if>
			</div>
			<div id="gst" ><b>GSTIN : ${s.supplierGSTNo}</b></div>
			</c:forEach>
		</div>
		<div id="invoiceInfo">
			<c:forEach var="b" items="${sessionScope.bill}" >
			<div>Invoice No. : ${b.purchaseInvoiceNo}</div>
			<div>Invoice Date : ${b.purchaseInvoiceDate} </div>
			</c:forEach>
		</div>
	</div>
		
	<div class="header22">
		<div><img id="logo" src="img/logo2.jpg"></div>
		<div class="salerInfo">
			<div id="add">9, 'Shanti', Darbari Bagicha Society,<br>Bavla Road, Sanand - Ahmedabad <br>State  Name : Gujarat  Code : 24 </div>
			<div id="gst"><b>GSTIN : 24APSPP7299B2ZB</b></div>
		</div>
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
		<td  id="tdsr" ><div id="srno"><%=z%></div></td>
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
			<c:set var="p" value="${i.itemPurchasePrice}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</div></td>
		<td><div id="dis">
			<c:if test="${ i.itemSalesPrice ne 0 }">
				<c:set var="p" value="${i.itemSalesPrice}"></c:set>
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
			<c:set var="p" value="${b2.purchaseTotalAmount}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	
	
	<c:if test="${ b2.purchaseGST9 ne 0 }">
	<tr class="right" >
		<td></td>
		<td id="gst">SGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt">
			<c:set var="p" value="${b2.purchaseGST9}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	<tr class="right" >
		<td></td>
		<td id="gst">CGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt">
			<c:set var="p" value="${b2.purchaseGST9}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>				
	</c:if>
				
	<c:if test="${ b2.purchaseGST14 ne 0 }">
	<tr class="right" >
		<td></td>
		<td  id="gst">SGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" >
			<c:set var="p" value="${b2.purchaseGST14}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	<tr class="right" >
		<td></td>
		<td id="gst" >CGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" >
			<c:set var="p" value="${b2.purchaseGST14}"></c:set>
			<% out.print(String.format(("%,.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>				
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
			<c:set var="x" value="${b2.purchaseTotalRoundOffAmount - b2.purchaseTotalAmountGST }"></c:set>
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
	<c:set var="p1" value="${b2.purchaseTotalRoundOffAmount}" ></c:set>
	</c:forEach>
</table>
</div>

<div id="size">
<table class="item2" cellspacing="0">
	<tr id="lastrow" >
		<td id="r1"></td>
		<td id="lastrowtot">Total</td>
		<td id="r3"></td>
		<td id="r4"></td>
		<td id="lastrowqty" >${tq}</td>
		<td id="r6"></td>
		<td id="r7"></td>
		<td id="lastrowamt" >
		&#8377; <%	
			Long aa = new Long((Long)pageContext.getAttribute("p1"));
			out.print(String.format(("%,.2f"),Double.parseDouble(aa.toString())));
		%>
		</td>
	</tr>
</table>
</div>


<div id="wordrow" >
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
	<div id="tnc" >
		<div id="tnchead" >Terms & Conditions</div>
		<div id="tnctext" >
			1) Goods Once Sold Will Not Be Taken Back.<br>
			2) Warranty Avoid For Physical Damage & Burning Parts.<br>
			3) Warranty As Per Company's Rules.<br>
			4) All Parts Warranty Direct Company's Service Station Our Company Not Responsible.
		</div>
	</div>
	<div id="sign" >
		<div id="signline1" >for,AMBA INFOTECH</div>
		<br>
		<br>
		<div id="signline2" >AUTHORISED SIGNATORY</div>
	</div>
</div>



<div id="footertd">
	<div id="line1" >SUBJECT TO SANAND JURISDICTION</div>
	<div id="line2" >This is a Computer Generated Invoice</div>
</div>
</center>
</div>
</body>
</html>