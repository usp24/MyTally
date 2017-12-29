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
	
	public void insertPurchase(supplierVO s) throws Exception{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id,purchaseInvoiceNo from supplier where GSTNo='"+s.getSupplierGSTNo()+"'");
			if(rs.next()){				
				String s1 = rs.getString("purchaseInvoiceNo").concat(s.getSupplierPurchaseInvoiceNo()+"*");
				st.executeUpdate("update supplier set name='"+s.getSupplierName()+"',GSTNo='"+s.getSupplierGSTNo()+"',address1='"+s.getSupplierAddress1()+"',address2='"+s.getSupplierAddress2()+"',city='"+s.getSupplierCity()+"',stateCode='"+s.getSupplierStatecode()+"',purchaseInvoiceNo='"+s1+"' where id='"+rs.getInt("id")+"'");
			}
			else
				st.executeUpdate("insert into supplier(name,address1,address2,city,stateCode,GSTNo,purchaseInvoiceNo) values('"+s.getSupplierName()+"','"+s.getSupplierAddress1()+"','"+s.getSupplierAddress2()+"','"+s.getSupplierCity()+"','"+s.getSupplierStatecode()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierPurchaseInvoiceNo()+"*"+"')");
			
		}
		catch(Exception e){
			System.out.println("supplierDAO :: insertPurchase :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<supplierVO> getSupplierDetail() throws ClassNotFoundException, SQLException {

	List<supplierVO> list = new ArrayList<supplierVO>();
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode from supplier group by GSTNo order by name");
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
	
	public List<supplierVO> getPurchaseEditDetail(supplierVO s) throws ClassNotFoundException, SQLException {

		List<supplierVO> list = new ArrayList<supplierVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select supplierName from purchasebill where invoiceNo='"+s.getSupplierPurchaseInvoiceNo()+"'");
		r.next();
		ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode from supplier where name='"+r.getString("supplierName")+"'");
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
	
	public void deleteBill(supplierVO supplierVO) throws Exception {
		
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
			
			ResultSet rs = st.executeQuery("select supplierName from purchasebill where invoiceNo='"+supplierVO.getSupplierPurchaseInvoiceNo()+"'");
			if(rs.next()){
			ResultSet r = st2.executeQuery("select purchaseInvoiceNo from supplier where name='"+rs.getString("supplierName")+"'");
			if(r.next()){
			
			String iv = supplierVO.getSupplierPurchaseInvoiceNo();
			String ivAll = r.getString("purchaseInvoiceNo");
			String arr[] = ivAll.split("\\*");
			String n="";
			for(int i=0;i<arr.length;i++){
				if(arr[i].equals(iv))
					continue;
				else
					n = n.concat(arr[i]).concat("*");
			}
			st3.executeUpdate("update supplier set purchaseInvoiceNo='"+n+"' where name='"+rs.getString("supplierName")+"' ");
			st4.executeUpdate("delete from purchasebill where invoiceNo='"+supplierVO.getSupplierPurchaseInvoiceNo()+"'");
			r.close();
			}
			rs.close();
			}

			ResultSet r2 = st5.executeQuery("select name,srno,qty from purchaseitem where purchaseInvoiceNo='"+supplierVO.getSupplierPurchaseInvoiceNo()+"'");
			ResultSet r3;
			while(r2.next()){
				
				String name = r2.getString("name");
				r3 = st6.executeQuery("select qty,srno from item where name='"+name+"'");
				if(r3.next()){
					
			        String ari[] = r3.getString("srno").split("\\*");
			     	String arp[] = r2.getString("srno").split("\\*");
			        
			        List<String> list_i = new ArrayList<String>();
			        List<String> d = new ArrayList<String>();

			        for(int j=0;j<ari.length;j++)
			        	list_i.add(ari[j]);
			        for(int j=0;j<arp.length;j++)
			        	d.add(arp[j]);
		        
			        list_i.removeAll(d);
			        String n2="";
					for(int i=0;i<list_i.size();i++){
			            n2 = n2.concat((String)list_i.get(i)).concat("*");   
			        }
					st7.executeUpdate("update item set srno='"+n2+"',qty='"+(r3.getInt("qty") - r2.getInt("qty"))+"' where name='"+name+"'");
					r3.close();
				}
			}
			st8.executeUpdate("delete from purchaseitem where purchaseInvoiceNo='"+supplierVO.getSupplierPurchaseInvoiceNo()+"'");
			
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
			System.out.println("supplierDAO :: insertPurchase :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<supplierVO> supplierEntryBefore() throws ClassNotFoundException, SQLException {

		List<supplierVO> list = new ArrayList<supplierVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode,mobileNo,email from supplier group by GSTNo order by name");
		while(rs.next()){
			supplierVO supplierVO = new supplierVO();
			supplierVO.setSupplierName(rs.getString("name"));
			supplierVO.setSupplierAddress1(rs.getString("address1"));
			supplierVO.setSupplierAddress2(rs.getString("address2"));
			supplierVO.setSupplierGSTNo(rs.getString("gstNo"));
			supplierVO.setSupplierCity(rs.getString("city"));
			supplierVO.setSupplierStatecode(rs.getString("stateCode"));
			supplierVO.setSupplierMobileNo(rs.getString("mobileNo"));
			supplierVO.setSupplierEmail(rs.getString("email"));
			list.add(supplierVO);
		}
		return list;
	}

	public void insertDirect(supplierVO s) throws SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("insert into supplier(name,address1,address2,GSTNo,city,stateCode,mobileNo,email) values('"+s.getSupplierName()+"','"+s.getSupplierAddress1()+"','"+s.getSupplierAddress2()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierCity()+"','"+s.getSupplierStatecode()+"','"+s.getSupplierMobileNo()+"','"+s.getSupplierEmail()+"')");
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: insertbill :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}

	public boolean delete(supplierVO s) throws SQLException {
	
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			System.out.println(s.getSupplierName());
			ResultSet rs = st.executeQuery("select id from purchasebill where supplierName='"+s.getSupplierName()+"'");
			if(rs.next())
				return false;
			else{
				st2.executeUpdate("delete from supplier where name='"+s.getSupplierName()+"'");
				st2.executeUpdate("delete from purchasebill where supplierName='"+s.getSupplierName()+"'");
				return true;
			}
		}
		catch(Exception e){
			System.out.println("supplierDAO :: delete :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return false;
	}

	public List<supplierVO> editDetail(supplierVO s) throws SQLException{
		
		List<supplierVO> list = new ArrayList<supplierVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,address1,address2,GSTNo,city,stateCode,mobileNo,email from supplier where name='"+s.getSupplierName()+"'");
			rs.next();
				supplierVO supplierVO = new supplierVO();
				supplierVO.setSupplierName(rs.getString("name"));
				supplierVO.setSupplierAddress1(rs.getString("address1"));
				supplierVO.setSupplierAddress2(rs.getString("address2"));
				supplierVO.setSupplierGSTNo(rs.getString("gstNo"));
				supplierVO.setSupplierCity(rs.getString("city"));
				supplierVO.setSupplierStatecode(rs.getString("stateCode"));
				supplierVO.setSupplierMobileNo(rs.getString("mobileNo"));
				supplierVO.setSupplierEmail(rs.getString("email"));
				list.add(supplierVO);
		}
		catch(Exception e){
			System.out.println("supplierDAO :: delete :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}

	public void updateDirect(supplierVO supplierVO, String oldSupplierName) throws SQLException {
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("update purchasebill set supplierName='"+supplierVO.getSupplierName()+"' where supplierName='"+oldSupplierName+"'");
			st.executeUpdate("update supplier set name='"+supplierVO.getSupplierName()+"' , address1='"+supplierVO.getSupplierAddress1()+"' , address2='"+supplierVO.getSupplierAddress2()+"' , city='"+supplierVO.getSupplierCity()+"' , stateCode='"+supplierVO.getSupplierStatecode()+"' , GSTNo='"+supplierVO.getSupplierGSTNo()+"' , mobileNo='"+supplierVO.getSupplierMobileNo()+"' , email='"+supplierVO.getSupplierEmail()+"' where name='"+oldSupplierName+"' ");
		
		}
		catch(Exception e){
			System.out.println("supplierDAO :: updateDirect :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}
}
