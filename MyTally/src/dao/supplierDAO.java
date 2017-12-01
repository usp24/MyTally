package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.supplierVO;

public class supplierDAO {
	
	Statement st;
	Connection con;
public void insertPurchase(supplierVO s) throws SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select purchaseInvoiceNo from supplier where GSTNo='"+s.getSupplierGSTNo()+"'");
			
			if(rs.next()){				
				String s1 = rs.getString("purchaseInvoiceNo").concat(s.getSupplierPurchaseInvoiceNo()+"*");
				st.executeUpdate("update supplier set name='"+s.getSupplierName()+"',address1='"+s.getSupplierAddress1()+"',address2='"+s.getSupplierAddress2()+"',city='"+s.getSupplierCity()+"',stateCode='"+s.getSupplierStatecode()+"',purchaseInvoiceNo='"+s1+"' where GSTNo='"+s.getSupplierGSTNo()+"'");
			}
			else
				st.executeUpdate("insert into supplier(name,address1,address2,city,stateCode,GSTNo,purchaseInvoiceNo) values('"+s.getSupplierName()+"','"+s.getSupplierAddress1()+"','"+s.getSupplierAddress2()+"','"+s.getSupplierCity()+"','"+s.getSupplierStatecode()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierPurchaseInvoiceNo()+"*"+"')");
			
		}
		catch(Exception e){
			System.out.println("supplierDAO :: insertPurchase :: "+e);
		}
		finally{
			st.close();
			con.close();
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
