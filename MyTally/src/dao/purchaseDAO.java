package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.purchaseVO;
import vo.supplierVO;

public class purchaseDAO {

	Connection con;
	Statement st;	
	
public void insertbill(purchaseVO p,supplierVO s) throws myException, SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from purchasebill where invoiceNo='"+p.getPurchaseInvoiceNo()+"'");
			if(rs.next()==false)
				st.executeUpdate("insert into purchasebill(invoiceNo,invoiceDate,supplierName,supplierGSTNo,numOfItems) values('"+p.getPurchaseInvoiceNo()+"','"+p.getPurchaseInvoiceDate()+"','"+s.getSupplierName()+"','"+s.getSupplierGSTNo()+"','"+p.getPurchaseNumOfItems()+"')");
			else
				throw new myException();
		}
		catch(myException e){
			System.out.println("purchaseDAO :: insertbill :: myException :: "+e.purchaseInvoiceWorng());
			throw e;
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: insertbill :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}

public void insertbill2(purchaseVO p,supplierVO s) throws SQLException{
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		st.executeUpdate("update purchasebill set totalAmountGST='"+p.getPurchaseTotalAmountGST()+"',totalAmount='"+p.getPurchaseTotalAmount()+"',totalRoundOffAmount='"+p.getPurchaseTotalRoundOffAmount()+"',GST14='"+p.getPurchaseGST14()+"',GST9='"+p.getPurchaseGST9()+"'  where supplierGSTNo='"+s.getSupplierGSTNo()+"' AND invoiceNo='"+p.getPurchaseInvoiceNo()+"' ");
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: insertbill2 :: "+e);
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
		ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from purchasebill");
		if(rs.next()==true)
			return (rs.getInt("max")+1);
		else
			throw new Exception();
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: maxInvoiceNo :: "+e);
		return -1;
	}
	finally{
		st.close();
		con.close();
	}
}
}
