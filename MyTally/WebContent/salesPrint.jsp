<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="vo.NumberToWord" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyTally | Print</title>
<style type="text/css" >
	
	
	body{
		margin: 0px;
    	padding :0px;
    	text-align:center;
		font-family:Arial,TimesNewRoman;
		font-size : 12.5px;
		width:210mm;	
		max-width:210mm;
		min-width:210mm;
		height:297mm;
		max-height: 297mm;
		min-height: 297mm;
	}
	#container{
		margin:auto;
		margin-top:5mm;
		height:290mm;
		max-height: 290mm;
		min-height: 290mm;
		width: 195mm;
		max-width:195mm;
		min-width:195mm;
		text-align:center;
		border-collapse: collapse;
		overflow: hidden;
		position: relative;
	}
	
	
	
	
	.header1{
		font-weight: bold;
	}
	.header1 #copy{
		font-size : 13px;
		margin-top : 5px;
		padding-left : 5px;
		float: left;
	}
	.header1 #head{
		margin-bottom:5px;
		font-size : 19px;
		text-align : center;
		padding-right : 100px;
	}
	
	
	
	
	
	#header212{
		border:1px solid black;
		overflow:hidden;
		height:41mm;
		max-height: 41mm;
		min-height: 41mm;
		width: 195mm;
		max-width:195mm;
		min-width:195mm;
		position: absolute;
	}
	.header21{
		margin:0px;
		height:41mm;
		max-height: 41mm;
		min-height: 41mm;
		width: 100mm;
		max-width:100mm;
		min-width:100mm;
		float:left;
		text-align:left;
		overflow:hidden;
	}
	.header21 #buyer{
		width:80%;
		height:29mm;
		max-height: 29mm;
		min-height: 29mm;
		padding-top:4px;
		padding-left:4px;
		padding-bottom: 4px;
		float: left;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal
	}
	.header21 #invoiceInfo{
		width:100%;
		height:12mm;
		max-height: 12mm;
		min-height: 12mm;
		font-weight: bold;
		float:left;
		border-top: 1px solid black;
		padding-left:4px;
		padding-top: 4px;
		padding-bottom: 4px;
	}
	.header22{
		border-left:1px solid black;
		border-right:1px solid black;
		float: left;
		height:41mm;
		max-height: 41mm;
		min-height: 41mm;
		width: calc(95mm - 3px);
		max-width:calc(95mm - 3px);
		min-width:calc(95mm - 3px);
	}
	.header22 #logo img{
		margin-bottom : 10px;
		margin-top : 10px;
	}
	.header22 .salerInfo #gst {
		margin-left:55px;
	}
	.header22 .salerInfo #add {
		text-align:left;
		padding-bottom:10px;
		margin-left:123px;
	}
	
	
	
	
	#tb1{
		margin:0px;
		padding:0px;
		position:absolute;
		top:184px;
		height:187mm;
		max-height: 187mm;
		min-height: 187mm;
		width: 195mm;
		max-width:195mm;
		min-width:195mm;
		overflow: hidden;
	}
	.item{
		margin:0px;
		padding:0px;
		height:41mm;
		max-height: 41mm;
		min-height: 41mm;
		width: 195mm;
		max-width:195mm;
		min-width:195mm;
		border-left: 1px solid black;
		overflow: hidden;
	}
	.item #itemHeader th{
		padding-top:4px;
		padding-bottom:5px;
		border:1px solid black;
		border-top:none;
		border-left:none;
	}
	.item #itemHeader #srno{
		width:7mm;
		max-width:7mm ;
		min-width:7mm;
	}
	.item #itemHeader #goods{
		width:82mm;
		max-width:82mm ;
		min-width:82mm;
	}
	.item #itemHeader #hsn{
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
	}
	.item #itemHeader #qy{
		width:9mm;
		max-width:9mm ;
		min-width:9mm;
	}
	.item #itemHeader #gt{
		width:12mm;
		max-width:12mm ;
		min-width:12mm;
	}
	.item #itemHeader #dis{
		width:10mm;
		max-width:10mm ;
		min-width:10mm;
	} 
	.item #itemHeader #price{
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
	}
	.item #itemHeader #totamt{
		border-right: none;
		width:23mm;
		max-width:23mm ;
		min-width:23mm;
	}
	
	
	
	.item tr td{
		border-right:1px solid black;
	
	}
	.item #data #srno{
		padding:0px;
		vertical-align: top;
		text-align:center;
		padding-top:7px;
		width:7mm;
		max-width:7mm ;
		min-width:7mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	.item #data #goods{
		text-align: left;
		padding-left:3px;
		padding-right:3px;
		width:82mm;
		max-width:82mm ;
		min-width:82mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}	
	.item #data #goods #name{
		text-transform: capitalize;
		padding-top:6px;
		font-weight: bold;
	}
	.item #data #goods #dn{
		padding-top:2px;
		pading-left:15px;
	}
	.item #data #hsn{
		text-align: left;
		padding-left:3px;
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	.item #data #qy{
		text-align:center;
		width:9mm;
		max-width:9mm ;
		min-width:9mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	.item #data #gt{
		text-align:center;
		width:12mm;
		max-width:12mm ;
		min-width:12mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	} 
	.item #data #price{
		padding-right:3px;
		text-align:right;
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	.item #data #totamt{
		padding-right:3px;
		text-align:right;
		border-right:none;
		width:23mm;
		max-width:23mm ;
		min-width:23mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	.item #data #dis{
		text-align:center;
		width:10mm;
		max-width:10mm ;
		min-width:10mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	
	.item #subtotalrow #subtotal{
		border-top:1px solid black;
		padding-top:6px;
		padding-bottom:7px;
		font-size: 15px;
		padding-right:3px;
		text-align:right;
		width:23mm;
		max-width:23mm ;
		min-width:23mm;
		white-space: -moz-pre-wrap !important;
     	white-space: -pre-wrap;      
     	white-space: -o-pre-wrap;    
     	white-space: pre-wrap;      
     	word-wrap: break-word;       
     	word-break: break-all;
     	white-space: normal;
	}
	
	.item #roundoff,#roundoffamt{
		padding-bottom: 10px;
	}
	.item .right{
		text-align: right;
	}
	.item #gst,#gstamt,#roundoff,#roundoffamt{
		font-weight: bold;
		padding-right:5px;
		padding-top:3px;
	}
	
	
	
	
	
	#size{
		position:absolute;
		bottom:174px;
		height:8mm;
		max-height: 8mm;
		min-height: 8mm;
		width:195mm;
		max-width:195mm ;
		min-width:195mm;
		overflow: hidden;
		margin:0px;
	}
	.item2{
		table-layout:fixed;
		height:8mm;
		max-height: 8mm;
		min-height: 8mm;
		width:195mm;
		max-width:195mm ;
		min-width:195mm;
		border:1px solid black;
		border-collapse: collapse;
	}
	.item2 tr td{
		text-align: right;
		border-left:1px solid black;
	}
	.item2 #lastrow{
		font-size: 16px;
		font-weight: bold;
	}
	.item2 #lastrow #srno{
		width:7mm;
		max-width:7mm ;
		min-width:7mm;
	}
	.item2 #lastrow #lastrowtot{
		width:78mm;
		max-width:78mm ;
		min-width:78mm;
		padding-right:6px;
	}
	.item2 #lastrow #hsn{
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
	}
	.item2 #lastrow #lastrowqty{
		width:9mm;
		max-width:9mm ;
		min-width:9mm;
		text-align: center;
		padding-left:2px;
	}
	.item2 #lastrow #gt{
		width:11mm;
		max-width:11mm ;
		min-width:11mm;
	} 
	.item2 #lastrow #price{
		width:20mm;
		max-width:20mm ;
		min-width:20mm;
	}
	.item2 #lastrow #lastrowamt{
		border-right: none;
		width:23mm;
		max-width:23mm;
		min-width:23mm;
	}
	.item2 #lastrow #dis{
		width:10mm;
		max-width:10mm ;
		min-width:10mm;
	}
	
	
	#wordrow{
		position:absolute;
		bottom:100px;
		height:20mm;
		max-height: 20mm;
		min-height: 20mm;
		width: calc(195mm - 2px);
		max-width:calc(195mm - 2px);
		min-width:calc(195mm - 2px);
		overflow: hidden;
		border-right:1px solid black;
		border-left:1px solid black;
	}
	
	#wordhead{
		text-align: left;
		padding-top:8px;
		padding-left: 5px;
	}
	#word{
		font-weight: bold;
		text-align: left;
		padding-top:5px;
		padding-bottom: 25px;
		padding-left: 5px;
	}
	
	
	
	.footer1{
		position:absolute;
		bottom:30px;
		height:20mm;
		max-height: 20mm;
		min-height: 20mm;
		width: calc(195mm - 2px);
		max-width:calc(195mm - 2px);
		min-width:calc(195mm - 2px);
		overflow: hidden;
		border-right:1px solid black;
		border-left:1px solid black;
		border-bottom:1px solid black;
	}
	#tnc{
		text-align: left;
		padding-left: 5px;
		float: left;
	}
	#tnchead{
		font-size: 12px;
		font-weight: bold;
	}
	#tnctext{
		font-size:10px;
	}
	#sign{
		float:right;
		text-align: right;
	}
	#signline1{
		padding-right:24px;
		padding-top:3px;
		padding-bottom:3px;
	}
	#signline2{
		padding-right: 5px;
		padding-top:3px;
		padding-bottom:3px;
	}
	
	
	
	
	
	#footertd{
		position: absolute;
		bottom: 0px;
		width:100%;
		text-align: center;
	}
	#footertd #line1{
		font-size:11px;;
	}
	#footertd #line2{
		font-size:10px;
	}
	
	
</style>
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

<div id="header212">
	<div class="header21">
		<div id="buyer">
			<b>Buyer :</b><br>
			<div style="font-size:15px;text-transform: capitalize;"><b><%=c.getString("name")%></b></div>
			<div>Plot / Survey No. 113/23<br>Opp. Krishna Rice Mill Bh. Rathi Rice Mill<br>Sanand - Ahmedabad<br>State Code : Gujarat - 24</div>
			<div style="padding-top:5px;font-size: 14px;" ><b>GSTIN : <%=c.getString("gstNo") %></b></div>
		</div>
		<div id="invoiceInfo">
			<div>Invoice No. : <%=b.getInt("invoiceNo") %></div>
			<div>Invoice Date : <% Date ob = b.getDate("invoiceDate");%><%=ob.getDate()%>/<%=ob.getMonth() %>/<%=ob.getYear()+1900%></div>
		</div>
	</div>
		
	<div class="header22">
		<div id="logo" ><img src="logo.jpg"></div>
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
	
	<%for(int i=0;i<40;i++){ %>
	<tr> 
		<td style="visibility: hidden;" >.</td>
		<td style="border-left:1px solid black;"></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<%} %>
		
</table>
</div>

<div id="size">
<table class="item2" cellspacing="0">
	<tr id="lastrow" >
		<td id="srno" ></td>
		<td id="lastrowtot">Total</td>
		<td id="hsn"></td>
		<td id="gt"></td>
		<td id="lastrowqty" >
			<%
			Statement st4 = con.createStatement();
			ResultSet temp = st4.executeQuery("select SUM(qty)'sum' from salesitem where invoiceNo='14'");temp.next();
			int q = temp.getInt("sum");%>
			<%=q%>
		</td>
		<td id="price" ></td>
		<td id="dis" ></td>
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
</center></div>
</body>
</html>