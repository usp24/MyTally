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
	
	public void insertSales(customerVO s) throws Exception{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id,salesInvoiceNo from customer where GSTNo='"+s.getCustomerGSTNo()+"'");
			if(rs.next()){				
				String s1 = rs.getString("salesInvoiceNo").concat(s.getCustomerSalesInvoiceNo()+"*");
				st.executeUpdate("update customer set name='"+s.getCustomerName()+"',GSTNo='"+s.getCustomerGSTNo()+"',address1='"+s.getCustomerAddress1()+"',address2='"+s.getCustomerAddress2()+"',city='"+s.getCustomerCity()+"',stateCode='"+s.getCustomerStatecode()+"',salesInvoiceNo='"+s1+"' where id='"+rs.getInt("id")+"'");
			}
			else
				st.executeUpdate("insert into customer(name,address1,address2,city,stateCode,GSTNo,salesInvoiceNo) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerCity()+"','"+s.getCustomerStatecode()+"','"+s.getCustomerGSTNo()+"','"+s.getCustomerSalesInvoiceNo()+"*"+"')");
			
		}
		catch(Exception e){
			System.out.println("customerDAO :: insertSales :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<customerVO> getCustomerDetail() throws ClassNotFoundException, SQLException {

	List<customerVO> list = new ArrayList<customerVO>();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode from customer group by GSTNo order by name");
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
	
	public List<customerVO> getSalesEditDetail(customerVO s) throws ClassNotFoundException, SQLException {

		List<customerVO> list = new ArrayList<customerVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select customerName from salesbill where invoiceNo='"+s.getCustomerSalesInvoiceNo()+"'");
		r.next();
		ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode from customer where name='"+r.getString("customerName")+"'");
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

	public void deleteBill(customerVO customerVO) throws Exception {
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			Statement st3 = con.createStatement();
			Statement st4 = con.createStatement();
			Statement st5 = con.createStatement();
			Statement st6 = con.createStatement();
			Statement st7 = con.createStatement();
			Statement st8 = con.createStatement();
			
			ResultSet rs = st.executeQuery("select customerName from salesbill where invoiceNo='"+customerVO.getCustomerSalesInvoiceNo()+"'");
			if(rs.next()){
			ResultSet r = st2.executeQuery("select salesInvoiceNo from customer where name='"+rs.getString("customerName")+"'");
			if(r.next()){
			
			String iv = customerVO.getCustomerSalesInvoiceNo();
			String ivAll = r.getString("salesInvoiceNo");
			String arr[] = ivAll.split("\\*");
			String n="";
			for(int i=0;i<arr.length;i++){
				if(arr[i].equals(iv))
					continue;
				else
					n = n.concat(arr[i]).concat("*");
			}
			st3.executeUpdate("update customer set salesInvoiceNo='"+n+"' where name='"+rs.getString("customerName")+"' ");
			st4.executeUpdate("delete from salesbill where invoiceNo='"+customerVO.getCustomerSalesInvoiceNo()+"'");
			r.close();
			}
			rs.close();
			}
			
			ResultSet r2 = st5.executeQuery("select name,srno,qty from salesitem where salesInvoiceNo='"+customerVO.getCustomerSalesInvoiceNo()+"'");
			ResultSet r3;
			while(r2.next()){
				
				String name = r2.getString("name");
				r3 = st6.executeQuery("select qty,srno from item where name='"+name+"'");
				if(r3.next()){
			        String n2 = r2.getString("srno").concat(r3.getString("srno"));
					st7.executeUpdate("update item set srno='"+n2+"',qty='"+(r3.getInt("qty") + r2.getInt("qty"))+"' where name='"+name+"'");
					r3.close();
				}
			}
			st8.executeUpdate("delete from salesitem where salesInvoiceNo='"+customerVO.getCustomerSalesInvoiceNo()+"'");
			r2.close();
			st2.close();
			st3.close();
			st4.close();
			st5.close();
			st6.close();
			st7.close();
			st8.close();
		}
		catch(Exception e){
			System.out.println("customerDAO :: insertSales :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}
	public void insertDirect(customerVO s) throws SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("insert into customer(name,address1,address2,GSTNo,city,stateCode,mobileNo,email) values('"+s.getCustomerName()+"','"+s.getCustomerAddress1()+"','"+s.getCustomerAddress2()+"','"+s.getCustomerGSTNo()+"','"+s.getCustomerCity()+"','"+s.getCustomerStatecode()+"','"+s.getCustomerMobileNo()+"','"+s.getCustomerEmail()+"')");
		}
		catch(Exception e){
			System.out.println("customerDAO :: insertDirect :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}
	
	public List<customerVO> customerEntryBefore() throws ClassNotFoundException, SQLException {

		List<customerVO> list = new ArrayList<customerVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode,mobileNo,email from customer group by GSTNo order by name");
		while(rs.next()){
			customerVO customerVO = new customerVO();
			customerVO.setCustomerName(rs.getString("name"));
			customerVO.setCustomerAddress1(rs.getString("address1"));
			customerVO.setCustomerAddress2(rs.getString("address2"));
			customerVO.setCustomerGSTNo(rs.getString("gstNo"));
			customerVO.setCustomerCity(rs.getString("city"));
			customerVO.setCustomerStatecode(rs.getString("stateCode"));
			customerVO.setCustomerMobileNo(rs.getString("mobileNo"));
			customerVO.setCustomerEmail(rs.getString("email"));
			list.add(customerVO);
		}
		return list;
	}
}
