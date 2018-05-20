package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.myException;
import vo.itemVO;
import vo.purchaseVO;
import vo.supplierVO;

public class purchaseDAO {

	Connection con;
	Statement st;	
	
	public void insertbill(purchaseVO p,supplierVO s) throws myException, Exception{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from purchasebill where invoiceNo='"+p.getPurchaseInvoiceNo()+"'");
			if(rs.next()==false)
				st.executeUpdate("insert into purchasebill(invoiceNo,invoiceDate,supplierName,supplierGSTNo,narration) values('"+p.getPurchaseInvoiceNo()+"','"+p.getPurchaseInvoiceDate()+"','"+s.getSupplierName()+"','"+s.getSupplierGSTNo()+"','"+p.getNarration()+"')");
			else
				throw new myException();
		}
		catch(myException e){
			System.out.println("purchaseDAO :: insertbill :: myException :: "+e.purchaseInvoiceWorng());
			throw e;
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: insertbill :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}

	public void insertbill2(purchaseVO p) throws Exception{
	
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("update purchasebill set totalAmountGST='"+p.getPurchaseTotalAmountGST()+"',totalAmount='"+p.getPurchaseTotalAmount()+"',totalRoundOffAmount='"+p.getPurchaseTotalRoundOffAmount()+"',GST14='"+p.getPurchaseGST14()+"',GST9='"+p.getPurchaseGST9()+"',IGST='"+p.getPurchaseIGST()+"'  where invoiceNo='"+p.getPurchaseInvoiceNo()+"'");
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: insertbill2 :: "+e);
			throw e;
		}
		finally{
			st.close();
			con.close();
		}
	}
	
public void insertPurchase(purchaseVO p,itemVO i) throws SQLException{
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		st.executeUpdate("insert into purchaseitem(purchaseInvoiceNo,name,srno,description,HSN,GST,qty,unitPrice,discount,totalAmount) values('"+p.getPurchaseInvoiceNo()+"','"+i.getItemName()+"','"+i.getItemSrNo()+"','"+i.getItemDescription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"','"+i.getItemQty()+"','"+i.getItemPurchasePrice()+"','"+p.getPurchaseItemDiscount()+"','"+p.getPurchaseItemTotalAmount()+"')");				
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: insertpurchase :: "+e);
	}
	finally{
		st.close();
		con.close();
	}
}
//
//public int maxInvoiceNo() throws SQLException{
//	try{	
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
//		st = con.createStatement();
//		ResultSet rs = st.executeQuery("select MAX(invoiceNo)'max' from purchasebill");
//		if(rs.next()==true)
//			return (rs.getInt("max")+1);
//		else
//			throw new Exception();
//	}
//	catch(Exception e){
//		System.out.println("purchaseDAO :: maxInvoiceNo :: "+e);
//		return -1;
//	}
//	finally{
//		st.close();
//		con.close();
//	}
//}

public List<purchaseVO> getInvoiceNo() throws SQLException{
	
	List<purchaseVO> list = new ArrayList<purchaseVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet rs = st.executeQuery("select invoiceNo from purchasebill");
		while(rs.next()){
			purchaseVO p = new purchaseVO();
			p.setPurchaseInvoiceNo(rs.getString("invoiceNo"));
			list.add(p);
		}
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: getInvoiceNo :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

public List<purchaseVO> getPurchaseBills() throws SQLException{
	
	List<purchaseVO> list = new ArrayList<purchaseVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT invoiceNo,supplierName,DATE_FORMAT(`invoiceDate`, '%d/%m/%Y') AS invoiceDate,totalRoundOffAmount FROM purchasebill ORDER BY YEAR(invoiceDate) DESC, MONTH(invoiceDate) DESC, DAY(invoiceDate) DESC");
		while(rs.next()){
			purchaseVO p = new purchaseVO();
			p.setPurchaseInvoiceNo(rs.getString("invoiceNo"));
			p.setPurchaseInvoiceDate(rs.getString("invoiceDate"));
			p.setExtra(rs.getString("supplierName"));
			Long aa = rs.getLong("totalRoundOffAmount");
			p.setExtra2((String.format(("%,.2f"),Double.parseDouble(aa.toString()))));
			list.add(p);
		}
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: getPurchaseBills :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

 public List<itemVO> edit(purchaseVO purchaseVO) throws SQLException{
	
	List<itemVO> list = new ArrayList<itemVO>();
	try{	
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		st = con.createStatement();
		ResultSet r = st.executeQuery("select invoiceDate from purchasebill where invoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
		r.next();
		String date = r.getString("invoiceDate");
		ResultSet rs = st.executeQuery("select name,srno,description,HSN,GST,qty,unitPrice,discount from purchaseitem where purchaseInvoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
		while(rs.next()){
			itemVO i = new itemVO();
			i.setItemName(rs.getString("name"));
			i.setItemSrNo(rs.getString("srno"));
			i.setItemDescription(rs.getString("description"));
			i.setItemHSN(rs.getString("HSN"));
			i.setItemGST(rs.getInt("GST"));
			i.setItemQty(rs.getInt("qty"));
			i.setItemPurchasePrice(rs.getDouble("unitPrice"));
			i.setItemSalesPrice(rs.getDouble("discount"));
			i.setExtra(date);
			list.add(i);
		}
	}
	catch(Exception e){
		System.out.println("purchaseDAO :: edit :: "+e);
		return null;
	}
	finally{
		st.close();
		con.close();
	}
	return list;
}

	public List<purchaseVO> getVehical(supplierVO s) throws SQLException {

		List<purchaseVO> list = new ArrayList<purchaseVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select IGST,narration from purchasebill where invoiceNo = '"+s.getSupplierPurchaseInvoiceNo()+"' ");
			while(rs.next()){
				purchaseVO p = new purchaseVO();
				p.setPurchaseIGST(rs.getString("IGST"));
				p.setNarration(rs.getString("narration"));
				list.add(p);
			}
		}
		catch(Exception e){
			System.out.println("purchaseDAO :: getVehical :: "+e);
			return null;
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
}
