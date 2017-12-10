<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="my.NumberToWord" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="salesPrintCSS.css" type="text/css">
<title>MyTally | Print</title>
</head>
<body>
<div id="container"><center>
<%
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
	Statement st1 = con.createStatement();
	Statement st2 = con.createStatement();
	Statement st3 = con.createStatement();
	ResultSet c = st1.executeQuery("select * from customer where name='OMKAR ENGINEERING'");c.next();
	ResultSet b = st2.executeQuery("select * from salesbill where invoiceNo='14'");b.next();
	ResultSet it = st3.executeQuery("select * from salesitem  where invoiceNo='14'");
	DecimalFormat df = new DecimalFormat();
	df.setMinimumFractionDigits(2);
	df.setMaximumFractionDigits(2);
%>
<div class="header1">
	<div id="copy">(ORIGINAL COPY)</div>
	<div id="head">TAX INVOICE</div>
</div>

<div id="header2">
	<div class="header21">
		<div id="buyer">
			<b>Buyer :</b><br>
			<div id="name"><b><%=c.getString("name")%></b></div>
			<div>Plot / Survey No. 113/23<br>Opp. Krishna Rice Mill Bh. Rathi Rice Mill<br>Sanand - Ahmedabad<br>State Code : Gujarat - 24</div>
			<div id="gst" ><b>GSTIN : <%=c.getString("gstNo") %></b></div>
		</div>
		<div id="invoiceInfo">
			<div>Invoice No. : <%=b.getInt("invoiceNo") %></div>
			<div>Invoice Date : <% Date ob = b.getDate("invoiceDate");%><%=ob.getDate()%>/<%=ob.getMonth() %>/<%=ob.getYear()+1900%></div>
		</div>
	</div>
		
	<div class="header22">
		<div><img id="logo" src="../logo2.jpg"></div>
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
				
	<% for(int i=1;i<=b.getInt("numOfItems");i++){ if(it.next()){ %>
	<tr id="data" >
		<td><div id="srno"><%=i%></div></td>
		<td><div id="goods">
			<div id="name"><%=it.getString("name") %><br></div>
			<div id="dn"><%=it.getString("discription") %></div>
		</div></td>
		<td><div id="hsn"><%=it.getString("HSN") %></div></td>
		<td><div id="gt" ><%=it.getString("GST") %> %</div></td>
		<td><div id="qy"><%=it.getInt("qty") %></div></td>
		<td><div id="price"><%= df.format(it.getDouble("unitPrice"))%></div></td>
		<td><div id="dis" >
			<% if(it.getDouble("discount")!=0){%>
			<%=it.getDouble("discount")%><%} %>
		</div></td>
		<td><div id="totamt" ><%=df.format(it.getDouble("totalAmount"))%></div></td>
	</tr>
	<%}}%>
	
	<tr class="right" id="subtotalrow">
		<td><div id="srno" ></div></td>
		<td><div id="goods"></div></td>
		<td><div id="hsn"></div></td>
		<td><div id="gt"></div></td>
		<td><div id="qy"></div></td>
		<td><div id="price" ></div></td>
		<td><div id="dis" ></div></td>
		<td id="subtotal"><%= df.format(b.getDouble("totalAmount")) %></td>
	</tr>
	
	
	<% if(b.getDouble("GST9")!=0){ %>
	<tr class="right" >
		<td></td>
		<td id="gst">SGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt"><%= df.format(b.getDouble("GST9")) %></td>
	</tr>
	<tr class="right" >
		<td></td>
		<td id="gst">CGST 9%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt"><%=df.format(b.getDouble("GST9"))%></td>
	</tr>				
	<%}%>
				
	<% if(b.getDouble("GST14")!=0){ %>
	<tr class="right" >
		<td></td>
		<td  id="gst">SGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" ><%=df.format(b.getDouble("GST14"))%></td>
	</tr>
	<tr class="right" >
		<td></td>
		<td id="gst" >CGST 14%</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="gstamt" ><%=df.format(b.getDouble("GST14"))%></td>
	</tr>				
	<%}%>
	
	<tr class="right" >
		<td></td>
		<td id="roundoff" >Rounding Off</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td id="roundoffamt" >
			<% 
				double x = b.getDouble("totalRoundOffAmount") - b.getDouble("totalAmountGST");
				if(x>=0){%>
					+(<%=df.format(x) %>)
				<% }else{%>
					(<%=df.format(x)  %>)
				<%} %>
		</td>
	</tr>
	
	<%for(int i=0;i<50;i++){ %>
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
		
</table>
</div>

<div id="size">
<table class="item2" cellspacing="0">
	<tr id="lastrow" >
		<td></td>
		<td id="lastrowtot">Total</td>
		<td></td>
		<td></td>
		<td id="lastrowqty" >
			<%
			Statement st4 = con.createStatement();
			ResultSet temp = st4.executeQuery("select SUM(qty)'sum' from salesitem where invoiceNo='14'");temp.next();
			int q = temp.getInt("sum");%>
			<%=q%>
		</td>
		<td></td>
		<td></td>
		<td id="lastrowamt" >&#8377; <%=df.format(b.getDouble("totalRoundOffAmount"))%></td>
	</tr>
</table>
</div>



<div id="wordrow" >
	<div id="wordhead" >Amount Chargeable(in words)</div>
	<div id="word" >INR 
		<%
			NumberToWord yy = new NumberToWord();
			Long aa = b.getLong("totalRoundOffAmount");
			String a = aa.toString();
			String s = yy.call(a);
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