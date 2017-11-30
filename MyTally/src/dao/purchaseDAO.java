package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import vo.purchaseVO;
import vo.supplierVO;

public class purchaseDAO {
	
public void insertbill(purchaseVO p,supplierVO s) throws myException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from purchasebill where supplierName='"+s.getSupplierName()+"' AND invoiceNo='"+p.getPurchaseInvoiceNo()+"' ");
			if(rs.next()==false)
				st.executeUpdate("insert into purchasebill(invoiceNo,invoiceDate,supplierName,numOfItems) values('"+p.getPurchaseInvoiceNo()+"','"+p.getPurchaseInvoiceDate()+"','"+s.getSupplierName()+"','"+p.getPurchaseNumOfItems()+"')");
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
			//st.close();
			//con.close();
		}
	}

public void insertbill2(purchaseVO p,supplierVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		st.executeUpdate("update purchasebill set totalAmountGST='"+p.getPurchaseTotalAmountGST()+"',totalAmount='"+p.getPurchaseTotalAmount()+"',totalRoundOffAmount='"+p.getPurchaseTotalRoundOffAmount()+"',GST14='"+p.getPurchaseGST14()+"',GST9='"+p.getPurchaseGST9()+"'  where supplierName='"+s.getSupplierName()+"' AND invoiceNo='"+p.getPurchaseInvoiceNo()+"' ");
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: insertbill2 :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}

public void insertitem(purchaseVO p,supplierVO s){
	
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select id from purchaseitem where supplierName='"+s.getSupplierName()+"' AND invoiceNo='"+p.getPurchaseInvoiceNo()+"' AND name='"+p.getPurchaseItemName()+"' AND discription='"+p.getPurchaseItemDiscription()+"' AND HSN='"+p.getPurchaseItemHSN()+"'");
		if(rs.next()==false)
			st.executeUpdate("insert into purchaseitem(invoiceNo,supplierName,name,discription,qty,HSN,GST,unitPrice,totalAmount,discount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierName()+"','"+p.getPurchaseItemName()+"','"+p.getPurchaseItemDiscription()+"','"+p.getPurchaseItemQty()+"','"+p.getPurchaseItemHSN()+"','"+p.getPurchaseItemGST()+"','"+p.getPurchaseItemUnitPrice()+"','"+p.getPurchaseItemAmount()+"','"+p.getPurchaseItemDiscount()+"')");
		else{
			ResultSet rs2 = st.executeQuery("select id from purchaseitem where supplierName='"+s.getSupplierName()+"' AND invoiceNo='"+p.getPurchaseInvoiceNo()+"' AND name='"+p.getPurchaseItemName()+"' AND discription='"+p.getPurchaseItemDiscription()+"' AND HSN='"+p.getPurchaseItemHSN()+"'");
			while(rs2.next())
				st.executeUpdate("delete from purchaseitem where id='"+rs2.getInt("id")+"'");
			st.executeUpdate("insert into purchaseitem(invoiceNo,supplierName,name,discription,qty,HSN,GST,unitPrice,totalAmount,discount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierName()+"','"+p.getPurchaseItemName()+"','"+p.getPurchaseItemDiscription()+"','"+p.getPurchaseItemQty()+"','"+p.getPurchaseItemHSN()+"','"+p.getPurchaseItemGST()+"','"+p.getPurchaseItemUnitPrice()+"','"+p.getPurchaseItemAmount()+"','"+p.getPurchaseItemDiscount()+"')");
		}
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: insertitem :: "+e);
	}
	finally{
		//st.close();
		//con.close();
	}
}
public int maxInvoiceNo(){
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from purchasebill");
		if(rs.next()==true)
			return rs.getInt("max");
		else
			throw new Exception();
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: maxInvoiceNo :: "+e);
		return 0;
	}
	finally{
		//st.close();
		//con.close();
	}
}
}
