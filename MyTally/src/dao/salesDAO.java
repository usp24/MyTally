package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.salesVO;
import vo.customerVO;

public class salesDAO {

	Connection con;
	Statement st;	
	
public void insertbill(salesVO p,customerVO s) throws myException, SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from salesbill where invoiceNo='"+p.getSalesInvoiceNo()+"'");
			if(rs.next()==false)
				st.executeUpdate("insert into salesbill(invoiceNo,invoiceDate,customerName,customerGSTNo,numOfItems) values('"+p.getSalesInvoiceNo()+"','"+p.getSalesInvoiceDate()+"','"+s.getCustomerName()+"','"+s.getCustomerGSTNo()+"','"+p.getSalesNumOfItems()+"')");
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
			st.close();
			con.close();
		}
	}

public void insertbill2(salesVO p,customerVO s) throws SQLException{
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		st.executeUpdate("update salesbill set totalAmountGST='"+p.getSalesTotalAmountGST()+"',totalAmount='"+p.getSalesTotalAmount()+"',totalRoundOffAmount='"+p.getSalesTotalRoundOffAmount()+"',GST14='"+p.getSalesGST14()+"',GST9='"+p.getSalesGST9()+"'  where customerGSTNo='"+s.getCustomerGSTNo()+"' AND invoiceNo='"+p.getSalesInvoiceNo()+"' ");
	}
	catch(Exception e){
		System.out.println("salesDAO :: insertbill2 :: "+e);
	}
	finally{
		st.close();
		con.close();
	}
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
