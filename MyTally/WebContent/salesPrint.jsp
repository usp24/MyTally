<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="my.NumberToWord" %>
<html>
<head>
<link rel="stylesheet" href="css/print.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Print</title>
</head>
<body>
<c:set var="nul" value="" ></c:set>
<div id="container"><center>
<div class="header1">
	<div id="copy">(ORIGINAL COPY)</div>
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
			<div id="gst" ><b>GSTIN : ${s.customerGSTNo}</b></div>
			</c:forEach>
		</div>
		<div id="invoiceInfo">
			<c:forEach var="b" items="${sessionScope.bill}" >
			<div>Invoice No. : ${b.salesInvoiceNo}</div>
			<div>Invoice Date : ${b.salesInvoiceDate} </div>
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
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
		</div></td>
		<td><div id="dis">
			<c:if test="${ i.itemPurchasePrice ne 0 }">
				<c:set var="p" value="${i.itemSalesPrice}"></c:set>
				<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
			</c:if>
		</div></td>
		<td><div id="totamt" >
			<c:set var="p" value="${i.extrad}"></c:set>
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
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
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>
	
	
	<c:if test="${ b2.salesGST9 ne 0 }">
	<tr class="right" >
		<td></td>
		<td id="gst">SGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt">
			<c:set var="p" value="${b2.salesGST9}"></c:set>
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
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
			<c:set var="p" value="${b2.salesGST9}"></c:set>
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
		</td>
	</tr>				
	</c:if>
				
	<c:if test="${ b2.salesGST14 ne 0 }">
	<tr class="right" >
		<td></td>
		<td  id="gst">SGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" >
			<c:set var="p" value="${b2.salesGST14}"></c:set>
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
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
			<c:set var="p" value="${b2.salesGST14}"></c:set>
			<% out.print(String.format(("%.2f"),(Double)pageContext.getAttribute("p"))); %>
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
			<c:set var="x" value="${b2.salesTotalRoundOffAmount - b2.salesTotalAmountGST }"></c:set>
			<% Double num = (Double)pageContext.getAttribute("x");
				String s = String.format(("%.2f"),num);
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
		<td id="lastrowamt" >&#8377; ${p1}.00</td>
	</tr>
</table>
</div>


<div id="wordrow" >
	<div id="wordhead" >Amount Chargeable(in words)</div>
	<div id="word" >INR 
		<%
			NumberToWord yy = new NumberToWord();
			Long aa = new Long((Long)pageContext.getAttribute("p1"));
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