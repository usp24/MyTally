package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import vo.salesVO;
import vo.customerVO;

public class salesDAO {

public void insertbill(salesVO p,customerVO s) throws myException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from salesbill where customerName='"+s.getCustomerName()+"' AND invoiceNo='"+p.getSalesInvoiceNo()+"' ");
			if(rs.next()==false)
				st.executeUpdate("insert into salesbill(invoiceNo,invoiceDate,customerName,numOfItems) values('"+p.getSalesInvoiceNo()+"','"+p.getSalesInvoiceDate()+"','"+s.getCustomerName()+"','"+p.getSalesNumOfItems()+"')");
			else
				throw new myException();
		}
		catch(myException e){
			System.out.println("salesDAO :: insertbill :: myException :: "+e.salesInvoiceWorng());
			throw e;
		}
		catch(Exception e){
			System.out.println("salesDAO :: insertbill :: "+e);
		}
		finally{
			//st.close();
			//con.close();
		}
	}

public void insertbill2(salesVO p,customerVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		st.executeUpdate("update salesbill set totalAmount='"+p.getSalesTotalAmount()+"',totalRoundOffAmount='"+p.getSalesTotalRoundOffAmount()+"',GST14='"+p.getSalesGST14()+"',GST9='"+p.getSalesGST9()+"'  where customerName='"+s.getCustomerName()+"' AND invoiceNo='"+p.getSalesInvoiceNo()+"' ");
	}
	catch(Exception e){
		System.out.println("salesDAO :: insertbill2 :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}

public void insertitem(salesVO p,customerVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select id from salesitem where customerName='"+s.getCustomerName()+"' AND invoiceNo='"+p.getSalesInvoiceNo()+"' AND name='"+p.getSalesItemName()+"' AND discription='"+p.getSalesItemDiscription()+"' AND HSN='"+p.getSalesItemHSN()+"'");
		if(rs.next()==false)
			st.executeUpdate("insert into salesitem(invoiceNo,customerName,name,discription,qty,HSN,GST,unitPrice,totalAmount,discount) values('"+p.getSalesInvoiceNo()+"','"+s.getCustomerName()+"','"+p.getSalesItemName()+"','"+p.getSalesItemDiscription()+"','"+p.getSalesItemQty()+"','"+p.getSalesItemHSN()+"','"+p.getSalesItemGST()+"','"+p.getSalesItemUnitPrice()+"','"+p.getSalesItemAmount()+"','"+p.getSalesItemDiscount()+"')");
		else{
			ResultSet rs2 = st.executeQuery("select id from salesitem where customerName='"+s.getCustomerName()+"' AND invoiceNo='"+p.getSalesInvoiceNo()+"' AND name='"+p.getSalesItemName()+"' AND discription='"+p.getSalesItemDiscription()+"' AND HSN='"+p.getSalesItemHSN()+"'");
			while(rs2.next())
				st.executeUpdate("delete from salesitem where id='"+rs2.getInt("id")+"'");
			st.executeUpdate("insert into salesitem(invoiceNo,customerName,name,discription,qty,HSN,GST,unitPrice,totalAmount,discount) values('"+p.getSalesInvoiceNo()+"','"+s.getCustomerName()+"','"+p.getSalesItemName()+"','"+p.getSalesItemDiscription()+"','"+p.getSalesItemQty()+"','"+p.getSalesItemHSN()+"','"+p.getSalesItemGST()+"','"+p.getSalesItemUnitPrice()+"','"+p.getSalesItemAmount()+"','"+p.getSalesItemDiscount()+"')");
		}
	}
	catch(Exception e){
		System.out.println("salesDAO :: insertitem :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}
public int maxInvoiceNo(){
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from salesbill");
		if(rs.next()==true)
			return rs.getInt("max");
		else
			throw new Exception();
	}
	catch(Exception e){
		System.out.println("salesDAO :: maxInvoiceNo :: "+e);
		return 0;
	}
	finally{
		//st.close();
		//con.close();
	}
}
}
