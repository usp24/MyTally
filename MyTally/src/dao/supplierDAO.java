package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import vo.purchaseVO;
import vo.supplierVO;

public class supplierDAO {

public void insertPurchase(supplierVO s){
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			Statement st = con.createStatement();
			st.executeUpdate("insert into supplier(name,address1,address2,GSTNo) values('"+s.getSupplierName()+"','"+s.getSupplierAddress1()+"','"+s.getSupplierAddress2()+"','"+s.getSupplierGSTNo()+"')");
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: insertPurchase :: "+e);
		}
		finally{
			//st.close();
			//con.close();
		}
	}
/*
public void insertDirect(supplierVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		st.executeUpdate("insert into supplier(name,address1,address2,GSTNo) values('"+s.getSupplierName()+"','"+s.getSupplierAddress1()+"','"+s.getSupplierAddress2()+"','"+s.getSupplierGSTNo()+"')");
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: insertbill :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}*/
}
