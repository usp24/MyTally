package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.customerVO;
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

public List<supplierVO> select() throws ClassNotFoundException, SQLException {

	List<supplierVO> list = new ArrayList<supplierVO>();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from supplier");
	while(rs.next()){
		supplierVO supplierVO = new supplierVO();
		supplierVO.setSupplierName(rs.getString("name"));
		supplierVO.setSupplierAddress1(rs.getString("address1"));
		supplierVO.setSupplierAddress2(rs.getString("address2"));
		supplierVO.setSupplierGSTNo(rs.getString("gstNo"));
		supplierVO.setSupplierCity(rs.getString("city"));
		supplierVO.setSupplierStatecode(rs.getString("stateCode"));
		list.add(supplierVO);
	}
	return list;
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
