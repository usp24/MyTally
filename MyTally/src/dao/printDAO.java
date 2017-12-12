package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vo.itemVO;
import vo.purchaseVO;
import vo.supplierVO;

public class printDAO {

	Connection con;
	Statement st;	
	
	public List<purchaseVO> getPurchaseBill(purchaseVO purchaseVO) throws SQLException{
		
		List<purchaseVO> list = new ArrayList<purchaseVO>();
		SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-yyyy");
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select invoiceNo,invoiceDate,totalAmount,GST14,GST9,totalAmountGST,totalRoundOffAmount from purchasebill where invoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
			while(rs.next()){
				purchaseVO purchaseVO2 = new purchaseVO();
				purchaseVO2.setPurchaseInvoiceNo(rs.getString("invoiceNo"));
				purchaseVO2.setPurchaseInvoiceDate(sm.format(rs.getDate("invoiceDate")));
				purchaseVO2.setPurchaseTotalAmount(rs.getDouble("totalAmount"));
				purchaseVO2.setPurchaseGST14(rs.getDouble("GST14"));
				purchaseVO2.setPurchaseGST9(rs.getDouble("GST9"));
				purchaseVO2.setPurchaseTotalAmountGST(rs.getDouble("totalAmountGST"));
				purchaseVO2.setPurchaseTotalRoundOffAmount(rs.getLong("totalRoundOffAmount"));
				list.add(purchaseVO2);
			}
		}catch (Exception e) {
			System.out.println("printDAO :: getPurchaseBill :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
	
	public List<itemVO> getPurchaseItem(purchaseVO purchaseVO) throws SQLException{
		
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet r = st2.executeQuery("select SUM(qty)as 'sum' from purchaseitem where purchaseInvoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
			r.next();
			ResultSet rs = st.executeQuery("select name,srno,description,HSN,GST,qty,unitPrice,discount,totalAmount from purchaseitem where purchaseInvoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
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
				i.setExtra(r.getString("sum"));
				i.setExtrad(rs.getDouble("totalAmount"));
				list.add(i);
			}
			st2.close();
		}
		catch(Exception e){
			System.out.println("printDAO :: getPurchaseItem :: "+e);
			return null;
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}

	public List<supplierVO> getSupplier(purchaseVO purchaseVO) throws SQLException{
		
		List<supplierVO> list = new ArrayList<supplierVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet r = st.executeQuery("select supplierName from purchasebill where invoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
			r.next();
			ResultSet rs = st2.executeQuery("select name,address1,address2,GSTNo,city,stateCode from supplier where name='"+r.getString("supplierName")+"'");
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
			st2.close();
		}catch (Exception e) {
			System.out.println("printDAO :: getSupplier :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
}
