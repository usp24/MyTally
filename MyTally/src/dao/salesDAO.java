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
				st.executeUpdate("insert into salesbill(invoiceNo,invoiceDate,customerName,customerGSTNo) values('"+p.getSalesInvoiceNo()+"','"+p.getSalesInvoiceDate()+"','"+s.getCustomerName()+"','"+s.getCustomerGSTNo()+"')");
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
			st.executeUpdate("update salesbill set totalAmountGST='"+p.getSalesTotalAmountGST()+"',totalAmount='"+p.getSalesTotalAmount()+"',totalRoundOffAmount='"+p.getSalesTotalRoundOffAmount()+"',GST14='"+p.getSalesGST14()+"',GST9='"+p.getSalesGST9()+"'  where invoiceNo='"+p.getSalesInvoiceNo()+"'");
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
			p.setSalesInvoiceNo(rs.getInt("invoiceNo"));
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
		ResultSet rs = st.executeQuery("SELECT invoiceNo,customerName,DATE_FORMAT(`invoiceDate`, '%d/%m/%Y') AS invoiceDate,totalRoundOffAmount FROM salesbill ORDER BY YEAR(invoiceDate) DESC, MONTH(invoiceDate) DESC, DAY(invoiceDate) desc");
		while(rs.next()){
			salesVO p = new salesVO();
			p.setSalesInvoiceNo(rs.getInt("invoiceNo"));
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

	public int maxInvoiceNo() throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from salesbill");
			if(rs.next()==true)
				return (rs.getInt("max")+1);
			else
				throw new Exception();
		}
		catch(Exception e){
			System.out.println("salesDAO :: maxInvoiceNo :: "+e);
			return -1;
		}
		finally{
			st.close();
			con.close();
		}
	}
}
