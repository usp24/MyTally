package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.myException;
import vo.salesVO;
import vo.customerVO;
import vo.itemVO;

public class salesDAO {


	Connection con;
	Statement st;	
	
	public void insertbill(salesVO p,customerVO s) throws myException, Exception{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from salesbill where invoiceNo='"+p.getSalesInvoiceNo()+"'");
			if(rs.next()==false)
				st.executeUpdate("insert into salesbill(invoiceNo,invoiceDate,customerName,customerGSTNo,vehical,vehicalNo,challanNumber,paymentMode) values('"+p.getSalesInvoiceNo()+"','"+p.getSalesInvoiceDate()+"','"+s.getCustomerName()+"','"+s.getCustomerGSTNo()+"','"+p.getVehical()+"','"+p.getVehicalNo()+"','"+p.getChallanNumber()+"','"+p.getPaymentMode()+"')");
			else
				throw new myException();
		}
		catch(myException e){
			System.out.println("salesDAO :: insertbill :: myException :: "+e.salesInvoiceWorng());
			throw e;
		}
		catch(Exception e){
			System.out.println("salesDAO :: insertbill :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}

	public void insertbill2(salesVO p) throws Exception{
	
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("update salesbill set totalAmountGST='"+p.getSalesTotalAmountGST()+"',totalAmount='"+p.getSalesTotalAmount()+"',totalRoundOffAmount='"+p.getSalesTotalRoundOffAmount()+"',GST14='"+p.getSalesGST14()+"',GST9='"+p.getSalesGST9()+"',IGST='"+p.getSalesIGST()+"'  where invoiceNo='"+p.getSalesInvoiceNo()+"'");
		}
		catch(Exception e){
			System.out.println("salesDAO :: insertbill2 :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}
	
public void insertSales(salesVO p,itemVO i) throws SQLException{
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		st.executeUpdate("insert into salesitem(salesInvoiceNo,name,srno,description,HSN,GST,qty,unitPrice,discount,totalAmount) values('"+p.getSalesInvoiceNo()+"','"+i.getItemName()+"','"+i.getItemSrNo()+"','"+i.getItemDescription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"','"+i.getItemQty()+"','"+i.getItemSalesPrice()+"','"+p.getSalesItemDiscount()+"','"+p.getSalesItemTotalAmount()+"')");				
	}
	catch(Exception e){
		System.out.println("salesDAO :: insertsales :: "+e);
	}
	finally{
		st.close();
		con.close();
	}
}

public List<salesVO> getInvoiceNo() throws SQLException{
	
	List<salesVO> list = new ArrayList<salesVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select invoiceNo from salesbill");
		while(rs.next()){
			salesVO p = new salesVO();
			p.setSalesInvoiceNo(rs.getString("invoiceNo"));
			list.add(p);
		}
	}
	catch(Exception e){
		System.out.println("salesDAO :: getInvoiceNo :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

public List<salesVO> getSalesBills() throws SQLException{
	
	List<salesVO> list = new ArrayList<salesVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT invoiceNo,customerName,DATE_FORMAT(`invoiceDate`, '%d/%m/%Y') AS invoiceDate,totalRoundOffAmount FROM salesbill ORDER BY invoiceNo DESC");
		while(rs.next()){
			salesVO p = new salesVO();
			p.setSalesInvoiceNo(rs.getString("invoiceNo"));
			p.setSalesInvoiceDate(rs.getString("invoiceDate"));
			p.setExtra(rs.getString("customerName"));
			Long aa = rs.getLong("totalRoundOffAmount");
			p.setExtra2((String.format(("%,.2f"),Double.parseDouble(aa.toString()))));
			list.add(p);
		}
	}
	catch(Exception e){
		System.out.println("salesDAO :: getSalesBills :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

 public List<itemVO> edit(salesVO salesVO) throws SQLException{
	
	List<itemVO> list = new ArrayList<itemVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet r = st.executeQuery("select invoiceDate from salesbill where invoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
		r.next();
		String date = r.getString("invoiceDate");
		ResultSet rs = st.executeQuery("select name,srno,description,HSN,GST,qty,unitPrice,discount from salesitem where salesInvoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
		while(rs.next()){
			itemVO i = new itemVO();
			i.setItemName(rs.getString("name"));
			i.setItemSrNo(rs.getString("srno"));
			i.setItemDescription(rs.getString("description"));
			i.setItemHSN(rs.getString("HSN"));
			i.setItemGST(rs.getInt("GST"));
			i.setItemQty(rs.getInt("qty"));
			i.setItemSalesPrice(rs.getDouble("unitPrice"));
			i.setItemPurchasePrice(rs.getDouble("discount"));
			i.setExtra(date);
			list.add(i);
		}
	}
	catch(Exception e){
		System.out.println("salesDAO :: edit :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

	public String maxInvoiceNo() throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from salesbill");
			if(rs.next()==true) {
				String max = rs.getString("max");
				String[] s1 = max.split("/");
				
				String s="";
				String y = "";
				if(s1.length > 1) {
					Integer z = Integer.parseInt(s1[2]) + 1;
					Integer x = String.valueOf(z).length();
					if(x==1)
						s="000" + z.toString();
					else if(x==2)
						s+="00" + z.toString();
					else if(x==3)
						s+="0" + z.toString();
					else if(x==4)
						s+=s1[2];
					else
						throw new Exception();
					y = s1[0] + "/" + s1[1] + "/" + s;
				}
				else {
					Integer z = Integer.parseInt(max) + 1;
					y = z.toString();
					return y;
				}
				return y;
			}
			else
				throw new Exception();
		}
		catch(Exception e){
			System.out.println("salesDAO :: maxInvoiceNo :: "+e);
			return "";
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<salesVO> getVehical(String salesInvoiceNo) throws SQLException {

		List<salesVO> list = new ArrayList<salesVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select vehical,vehicalNo,IGST,challanNumber,paymentMode from salesbill where invoiceNo ='"+salesInvoiceNo+"'");
			while(rs.next()){
				salesVO p = new salesVO();
				p.setVehical(rs.getString("vehical"));
				p.setVehicalNo(rs.getString("vehicalNo"));
				p.setSalesIGST(rs.getString("IGST"));
				p.setPaymentMode(rs.getString("paymentMode"));
				p.setChallanNumber(rs.getString("challanNumber"));
				list.add(p);
			}
		}
		catch(Exception e){
			System.out.println("salesDAO :: getVehical :: "+e);
			return null;
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
}
