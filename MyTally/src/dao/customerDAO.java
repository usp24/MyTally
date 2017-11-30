package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

public List<customerVO> select() throws ClassNotFoundException, SQLException {

	List<customerVO> list = new ArrayList<customerVO>();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from customer");
	while(rs.next()){
		customerVO customerVO = new customerVO();
		customerVO.setCustomerName(rs.getString("name"));
		customerVO.setCustomerAddress1(rs.getString("address1"));
		customerVO.setCustomerAddress2(rs.getString("address2"));
		customerVO.setCustomerGSTNo(rs.getString("gstNo"));
		customerVO.setCustomerCity(rs.getString("city"));
		customerVO.setCustomerStatecode(rs.getString("stateCode"));
		list.add(customerVO);
	}
	return list;
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
