<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="vo.itemVO" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sale</title>
<link rel="icon" type="image/png" href="img/fi1.ico">
<link rel="stylesheet" href="css/salesitem.css">
<script  type="text/javascript" src="scripts/jquery-3.2.1.min.js"></script>	
<script type="text/javascript">

var list;
var num;
var list1;
var list2;
var cnt = 2;

$(document).ready(function(){
	
	$.ajax({
		type : 'POST',
		url : 'sales?ch=salesBillBefore',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			var dl = document.getElementById("dl");
			list1 = $.parseJSON(x);
			dl.innerHTML = "";
			for(var i=0;i<list1.length;i++){
				dl.innerHTML += "<option value='"+list1[i].customerName+"'/>";
			}
		}
	});
	
	$.ajax({
		type : 'POST',
		url : 'sales?ch=salesBillBefore2',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			list2 = $.parseJSON(x);
		}
	});
	
	
	$.ajax({
		type : 'POST',
		url : 'item?ch=itemDetailSale',
		headres : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success : function(x){
			list = $.parseJSON(x);
			num = list.length;
			var dl = document.getElementById("dl_item");
			
			dl.innerHTML = "";
			for(var i=0;i<num;i++){
				dl.innerHTML += "<option value='"+list[i].itemName+"'/>";
			}
		}
	});
	
	$("#iv").blur(function(){
		var noz = document.getElementById("iv");
		var no = noz.value.split("/");
		if(no.length>1){
			var noi = Number(no[2]);
			for(var i=0;i<list2.length;i++){
				var l2i = list2[i].salesInvoiceNo;
				var l2ia = l2i.split("/");
				if(l2ia.length>1){
					var l2ii = Number(l2ia[2]);
					if(noi == l2ii){
						alert("Please Enter Valid Invoice No");
						noz.value='';
						noz.focus();
						break;
					}
				}
			}	
		}
		else{
			for(var i=0;i<list2.length;i++){
				if(noz.value == list2[i].salesInvoiceNo){
					alert("Please Enter Valid Invoice No");
					noz.value='';
					noz.focus();
					break;
				}
			}
		}
	});
	
			
	$("#name").on("input",function(){
		
		var name = document.getElementById("name");
		var add1 = document.getElementById("add1");
		var add2 = document.getElementById("add2");
		var city = document.getElementById("city");
		var statecode = document.getElementById("statecode");
		var gst = document.getElementById("gst");
		var customerMobileNo = document.getElementById("customerMobileNo");
		
		for(var i=0;i<list1.length;i++){
			if(name.value.toUpperCase()==list1[i].customerName){
				add1.value = list1[i].customerAddress1;
				add2.value = list1[i].customerAddress2;
				city.value = list1[i].customerCity;
				statecode.value = list1[i].customerStatecode;
				gst.value = list1[i].customerGSTNo;
				customerMobileNo.value = list1[i].customerMobileNo;
				break;
			}		
		}	
	});
});

function fn(row){
	 var qty = document.getElementById("q"+row);
	 var u = document.getElementById("p"+row);
	 var at = document.getElementById("amt"+row);
	 at.value = (qty.value*u.value);
}
	
function fn2(row){
	
	var qty = document.getElementById("q"+row);
	var div = document.getElementById("srdiv"+row);
	div.innerHTML="";
	if(qty.value>1){
		div.style.display = '';
		for(var i=1;i<qty.value;i++){
			div.innerHTML = div.innerHTML + "<input type='text' id='srno"+row+""+(i+1)+"' name='ItemSrNo"+row+""+(i+1)+"' list='dl_srno'  autocomplete='off' ><br>";
		}
	 }
	else{
		div.style.display = 'none';
		div.innerHTML="";
	}
}
	
function fn3(row){
	var name = document.getElementById("item"+row);
	var disc = document.getElementById("disc"+row);
	var hsn = document.getElementById("hsn"+row);
	var gst = document.getElementById("gst"+row);
	var unitPrice = document.getElementById("p"+row);
	var dl2 = document.getElementById("dl_srno");
	dl2.innerHTML = "";
	var div = document.getElementById("srdiv"+row);
	div.innerHTML="";
	var srn = document.getElementById("srno"+row+"1");
	srn.value='';
	
	for(var i=0;i<num;i++){
		if(list[i].itemName == name.value.toUpperCase()){
			hsn.value = list[i].itemHSN;
			gst.value = list[i].itemGST;
			disc.value = list[i].itemDescription;
			unitPrice.value = list[i].itemSalesPrice;
			
			var txt = list[i].itemSrNo;
			txt = txt.split('*');
			var s = txt.length;
			for(var j=1;j<=s;j++){
				var x = txt.pop();
				if(x!="")
					dl2.innerHTML += "<option value='"+x+"'/>";
			}
			break;		
		}
	}
}
	
function fb(){
	var tb = document.getElementById("tb1");
	var nr = document.getElementById("nr");
	
	var r = tb.insertRow(-1);
	var c1 = r.insertCell(0);
	var c2 = r.insertCell(1);
	var c3 = r.insertCell(2);
	var c4 = r.insertCell(3);
	var c5 = r.insertCell(4);
	var c6 = r.insertCell(5);
	var c7 = r.insertCell(6);
	var c8 = r.insertCell(7);
	var c9 = r.insertCell(8);
	var c10 = r.insertCell(9);
	var c11 = r.insertCell(10);
	var c12 = r.insertCell(11);
	
	c1.innerHTML = cnt;
	c2.innerHTML = "<input type='text' list='dl_item' name='ItemName"+cnt+"' id='item"+cnt+"' onblur='fn3("+cnt+")' class='name' autocomplete='off'>";
	c3.innerHTML = "<input type='text' name='ItemDescription"+cnt+"' id='disc"+cnt+"'  autocomplete='off'>";
	c4.innerHTML = "<input type='number' name='salesItemQty"+cnt+"' id='q"+cnt+"' onkeyup='fn("+cnt+")' onblur='fn2("+cnt+")'  autocomplete='off' ng-model={{qty"+cnt+"}} >";
	c5.innerHTML = "<input type='text' name='ItemSrNo"+cnt+"1' list='dl_srno' id='srno"+cnt+"1'  autocomplete='off'><div id='srdiv"+cnt+"' style='display:none;'>";
	c6.innerHTML = "<input type='text' name='ItemHSN"+cnt+"' id='hsn"+cnt+"'  autocomplete='off'>";
	c7.innerHTML = "<input type='number' name='ItemGST"+cnt+"' id='gst"+cnt+"' value='18'   autocomplete='off'><input type='text' value='%' readonly>";
	c8.innerHTML = "<input type='number' name='salesItemUnitPrice"+cnt+"' id='p"+cnt+"' onkeyup='fn("+cnt+")' step='any'   autocomplete='off'>";
	c9.innerHTML = "<input type='number' name='salesItemDiscount"+cnt+"' value='0' onblur='fn("+cnt+")' id='d"+cnt+"' step='any'  autocomplete='off' ><input type='text' value='%' readonly>";
	c10.innerHTML = "<input type='number' name='salesItemTotalAmount"+cnt+"' id='amt"+cnt+"' step='any' readonly>";
	c11.innerHTML = "<input type='button' value='+' onclick='fb()' >";
	c12.innerHTML = "<input type='button' value='x' onclick='fnd(this)'>";
	nr.value = cnt;
	cnt++;
}
	
function fnd(btn){
	var tb = document.getElementById("tb1");
	r = btn.parentNode.parentNode;
    tb.deleteRow(r.rowIndex);
}
	
function sub(){
	
	var flag = true;
	var date = document.getElementById("date");
	var iv = document.getElementById("iv");
	var iva = iv.value.split("/");
	var name = document.getElementById("name");
	var tb = document.getElementById("tb1");
	
	if(date.value==""){
		alert("Please Enter Invoice Date");
		date.focus();
		flag = false;
	}
	else if(iv.value==""){
		alert("Please Enter Valid Invoice Number");
		iv.focus();
		flag = false;
	}
	else if(iva.length > 1 && iva[2]==""){
			alert("Please Enter Valid Invoice Number");
			iv.focus();
			flag = false;	
	}
	else if(iva.length > 1 && iva[2].length>4){
			alert("Please Enter Valid Invoice Number");
			iv.focus();
			flag = false;	
	}
	else if(iva.length > 1 && iva.length>=4){
			alert("Please Enter Valid Invoice Number");
			iv.focus();
			flag = false;	
	}
	else if(name.value==""){
		name.focus();
		alert("Please Enter Customer's Name");
		flag = false;
	}
	else{
		for(var i=1;i<tb.rows.length;i++){
		
			var c = tb.rows[i].cells;
			var z1 = c[1].innerHTML;
			var z2 = c[3].innerHTML;
			var z3 = c[6].innerHTML;
			var z4 = c[7].innerHTML;
			
			var pn;
			var pq;
			var pg;
			var pu;
			
			var zzz = z1.substring("59","60");
			if(zzz<=9){
				pn = z1.substring("55","60");
				pq = z2.substring("46","48");
				pg = z3.substring("41","45");
				pu = z4.substring("52","54");
			}
			else if(zzz=="m"){
				pn = z1.substring("56","62");
				pq = z2.substring("47","50");
				pg = z3.substring("42","47");
				pu = z4.substring("53","56");
			}
			else{
				pn = z1.substring("57","64");
				pq = z2.substring("48","52");
				pg = z3.substring("43","49");
				pu = z4.substring("54","58");
			}
			
			var name = document.getElementById(pn);
			var qty = document.getElementById(pq);
			var gst = document.getElementById(pg);
			var unitPrice = document.getElementById(pu);
			
			if(name.value==""){
				alert("Please Enter Name of Item");
				name.focus();
				flag = false;
				break;
			}
			else if(qty.value==""){
				qty.focus();
				alert("Please Enter Qty of Item");
				flag = false;
				break;
			}
			else if(gst.value!="18" && gst.value!="28"){
				alert("GST Rate Can Only Be 18% OR 28%");
				gst.focus();
				flag = false;
				break;
			}
			else if(unitPrice.value==""){
				alert("Please Enter UnitPrice of Item ");
				unitPrice.focus();
				flag = false;
				break;
			}
		}
	}
	
	if(flag==true){
		/*var pay = document.getElementById("payi");
		if (confirm("Do You Want To Add PAYMENT DETAILS now ?") == true)
			pay.value = "yes";
		else
			pay.value = "no";
		*/
		return true;
	}
	else
		return false;
}	

$(window).scroll(function() {
    if ($(this).scrollTop() > 75) {
         $(".menubar").addClass('fix');
    } else {
         $(".menubar").removeClass('fix');
    }
});
</script>
</head>
<body onload="document.f1.salesInvoiceDate.focus()">
<div class="header">
	<div id="title" ><h1 id="titleh1" ><a href="menu.jsp" ><span id="u">U</span>Accounting</a></h1></div>
	<div id="text" ><p id="textp" >THE WORLD'S BEST ACCOUNTING SOFTWARE</p></div>
</div>
<div class="menubar">
	<ul>
		<li><a href="menu.jsp" >HOME</a></li>
		<li id="current" ><a href="sales.jsp" >SALE</a></li>
		<li><a href="purchase.jsp" >PURCHASE</a></li>
		<li><a href="item.jsp" >ITEM MASTER</a></li>
		<li><a href="stock.jsp" >STOCK</a></li>
		<li><a href="supplier.jsp" >SUPPLIERS</a></li>
		<li><a href="customer.jsp" >CUSTOMERS</a></li>
	</ul>
</div>


<button onclick="opz()"><img src="img/calc.jpg" style="width:32px;position:fixed;right:0px;top:200px;" /></button>
<div id="calc" style="right:0px;top:200px;position:fixed;background-color: #DfDfDf;padding:15px;display:none;">
<button onclick="clz()"><img src="img/cancle2.png" style="width:15px;position: fixed;right:5px;top:210px;"/></button>
<form>
Enter Final Amount<br><input type="number" id="calcFAmt" step="any"  autocomplete="off" ><br><br>
Enter GST (%)<br><input type="number" id="calcGST" step="any"  autocomplete="off" ><br><br>
<input type="button"  onclick="calc()" value="Count" style="background-color: #00aba9;border:none;padding:5px;color:white;font-size:16px;margin:3px;"/>
<input type="reset" value="Clear" style="background-color: #5E5E5E;border:none;padding:5px;color:white;font-size: 15px;margin:3px;">
 <br><br>
Unit Price (Without GST)<br><input type="number" id="calcRes" readonly step="any"  autocomplete="off" >
</form>
</div>
<script>
	function clz(){
		var clz = document.getElementById("calc");
		clz.style.display="none";
	}
	function opz(){
		var clz = document.getElementById("calc");
		clz.style.display="";
		document.getElementById("calcFAmt").focus();
	}
	function calc(){
		var fa = document.getElementById("calcFAmt");
		var g = document.getElementById("calcGST");
		var res = document.getElementById("calcRes");
		res.value = fa.value / (1+(g.value/100));
	}
</script>

<datalist id="dl" ></datalist>
<datalist id="dl_item" ></datalist>
<datalist id="dl_srno" ></datalist>

<form action="<%=request.getContextPath()%>/sales" method="post" onsubmit="return sub()" name="f1">
<div id="form1" >
	<c:set var="now" value="<%= new java.util.Date() %>" ></c:set>
	<f:formatDate var="dte" pattern="yyyy-MM-dd" value="${now}"></f:formatDate>
	<div class="boxName" >Sales Invoice Date* </div><input type="date" name="salesInvoiceDate" id="date" autocomplete="off" value="${dte}" >
	<div class="boxName" >Sales Invoice Number* </div><input type="text" name="salesInvoiceNumber" value="${param.max}" id="iv" autocomplete="off" style="text-transform: uppercase;">
	<div class="boxName" >Customer's Name* </div><input type="text" name="customerName" list="dl" id="name" autocomplete="off" >
	<div class="boxName" >Customer's GSTNo. </div><input type="text" name="customerGSTNo" id="gst" autocomplete="off" style="text-transform: uppercase;">
	<div class="boxName">Customer's Contact Number </div><input type="text" name="customerMobileNo" id="customerMobileNo" autocomplete="off">
	<div class="boxName" >Payment Terms </div><input type="text" name="paymentMode" id="paymentMode" autocomplete="off" style="text-transform: uppercase;">
</div>
<div id="form2" >
	<div class="boxName" >Customer's Address-1 </div><input type="text" name="customerAddress1" id="add1"  autocomplete="off" >
	<div class="boxName" >Customer's Address-2 </div><input type="text" name="customerAddress2" id="add2"  autocomplete="off" >
	<div class="boxName" >Area and City </div><input type="text" name="customerCity" id="city" autocomplete="off" >
	<div class="boxName" >State & Code </div><input type="text" name="customerStatecode" value="Gujarat, Code : 24" id="statecode" autocomplete="off" >	
	<div class="boxName">Challan Number </div><input type="text" name="challanNumber" id="challanNumber" autocomplete="off">
	<div class="boxName">Vehicle </div><input type="text" name="vehical" id="vehical" autocomplete="off">
	<div class="boxName" >Vehicle No.</div><input type="text" name="vehicalNo" id="vehicalNo" autocomplete="off">
</div>

<table id="tb1">
<tr>
	<th>No.</th>
	<th>Name*</th>
	<th>Description</th>
	<th>Qty*</th>
	<th>Sr. No.</th>
	<th>HSN</th>
	<th>GST*</th>
	<th>UnitPrice*</th>
	<th>Discount</th>
	<th>Amount</th>
	<th></th>
	<th></th>
</tr>
<tr>
	<td>1</td>
	<td><input type="text" list="dl_item" name="ItemName1" id="item1" onblur="fn3('1')" autocomplete="off" class="name"></td>
	<td><input type="text" name="ItemDescription1" id="disc1"  autocomplete="off" ></td>
	<td><input type="number" name="salesItemQty1" id="q1" onkeyup="fn('1')" onblur="fn2('1')"  autocomplete="off" ng-model={{qty}}></td>
	<td><input type="text" name="ItemSrNo11" list="dl_srno" id="srno11" autocomplete="off" ><div id="srdiv1" style="display:none;"></div></td>
	<td><input type="text" name="ItemHSN1" id="hsn1" autocomplete="off" ></td>
	<td><input type="number" name="ItemGST1" id="gst1" value="18"  autocomplete="off" ><input type="text" value="%" readonly></td>
	<td><input type="number" name="salesItemUnitPrice1" id="p1" onkeyup="fn('1')" step="any"  autocomplete="off" ></td>
	<td><input type="number" name="salesItemDiscount1" value="0" onblur="fn('1')" id="d1" step="any"  autocomplete="off" ><input type="text" value="%" readonly></td>
	<td><input type="number" name="salesItemTotalAmount1" id="amt1" step="any" readonly></td>
	<td><input type="button" value="+" onclick="fb()" ></td>
	<td></td>
</tr>
</table>

<input type="hidden" name="n" id="nr" value="1">
<input type="hidden" name="ch" value="sales">
<input type="hidden" name="pay" id="payi" value="no" >
<div>Is This Bill Contains IGST ? <input type="radio" name="IGSTRadio" value="yes"> Yes &nbsp;&nbsp; <input type="radio" name="IGSTRadio" value="no" checked="checked" > No</div>
<input type="submit" value="SAVE">
</form>
</body>
</html>