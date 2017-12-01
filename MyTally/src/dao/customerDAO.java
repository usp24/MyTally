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

	Statement st;
	Connection con;
public void insertSales(customerVO s) throws SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select salesInvoiceNo from customer where GSTNo='"+s.getCustomerGSTNo()+"'");
			
			if(rs.next()){				
				String s1 = rs.getString("salesInvoiceNo").concat(s.getCustomerSalesInvoiceNo()+"*");
				st.executeUpdate("update customer set name='"+s.getCustomerName()+"',address1='"+s.getCustomerAddress1()+"',address2='"+s.getCustomerAddress2()+"',city='"+s.getCustomerCity()+"',stateCode='"+s.getCustomerStatecode()+"',salesInvoiceNo='"+s1+"' where GSTNo='"+s.getCustomerGSTNo()+"'");
			}
			else
				st.executeUpdate("insert into customer(name,address1,address2,city,stateCode,GSTNo,salesInvoiceNo) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerCity()+"','"+s.getCustomerStatecode()+"','"+s.getCustomerGSTNo()+"','"+s.getCustomerSalesInvoiceNo()+"*"+"')");
			
		}
		catch(Exception e){
			System.out.println("customerDAO :: insertSales :: "+e);
		}
		finally{
			st.close();
			con.close();
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
public void insertDirect(customerVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		st.executeUpdate("insert into customer(name,address1,address2,GSTNo) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerGSTNo()+"')");
	}
	catch(Exception e){
		System.out.println("salesDAO :: insertbill :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}*/
}
