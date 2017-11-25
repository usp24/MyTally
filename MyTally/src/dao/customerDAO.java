package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import vo.customerVO;

public class customerDAO {


public void insertSales(customerVO s){
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			Statement st = con.createStatement();
			st.executeUpdate("insert into customer(name,address1,address2,GSTNo) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerGSTNo()+"')");
		}
		catch(Exception e){
			System.out.println("CustomerDAO :: insertSales :: "+e);
		}
		finally{
			//st.close();
			//con.close();
		}
	}
/*
public void insertDirect(CustomerVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		st.executeUpdate("insert into Customer(name,address1,address2,GSTNo) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerGSTNo()+"')");
	}
	catch(Exception e){
		System.out.println("SalesDAO :: insertbill :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}*/
}
