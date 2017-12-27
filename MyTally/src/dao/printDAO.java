package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vo.itemVO;
import vo.purchaseVO;
import vo.supplierVO;
import vo.salesVO;
import vo.customerVO;

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
	
	public List<salesVO> getSalesBill(salesVO salesVO) throws SQLException{
		
		List<salesVO> list = new ArrayList<salesVO>();
		SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-yyyy");
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select invoiceNo,invoiceDate,totalAmount,GST14,GST9,totalAmountGST,totalRoundOffAmount from salesbill where invoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
			while(rs.next()){
				salesVO salesVO2 = new salesVO();
				salesVO2.setSalesInvoiceNo(rs.getInt("invoiceNo"));
				salesVO2.setSalesInvoiceDate(sm.format(rs.getDate("invoiceDate")));
				salesVO2.setSalesTotalAmount(rs.getDouble("totalAmount"));
				salesVO2.setSalesGST14(rs.getDouble("GST14"));
				salesVO2.setSalesGST9(rs.getDouble("GST9"));
				salesVO2.setSalesTotalAmountGST(rs.getDouble("totalAmountGST"));
				salesVO2.setSalesTotalRoundOffAmount(rs.getLong("totalRoundOffAmount"));
				list.add(salesVO2);
			}
		}catch (Exception e) {
			System.out.println("printDAO :: getSalesBill :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
	
	public List<itemVO> getSalesItem(salesVO salesVO) throws SQLException{
		
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet r = st2.executeQuery("select SUM(qty)as 'sum' from salesitem where salesInvoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
			r.next();
			ResultSet rs = st.executeQuery("select name,srno,description,HSN,GST,qty,unitPrice,discount,totalAmount from salesitem where salesInvoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
			while(rs.next()){
				itemVO i = new itemVO();
				i.setItemName(rs.getString("name"));
				i.setItemSrNo(rs.getString("srno"));
				i.setItemDescription(rs.getString("description"));
				i.setItemHSN(rs.getString("HSN"));
				i.setItemGST(rs.getInt("GST"));
				i.setItemQty(rs.getInt("qty"));
				i.setItemSalesPrice(rs.getDouble("unitPrice"));
				i.setItemPurchasePrice(rs.getDouble("discount"));
				i.setExtra(r.getString("sum"));
				i.setExtrad(rs.getDouble("totalAmount"));
				list.add(i);
			}
			st2.close();
		}
		catch(Exception e){
			System.out.println("printDAO :: getSalesItem :: "+e);
			return null;
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}

	public List<customerVO> getCustomer(salesVO salesVO) throws SQLException{
		
		List<customerVO> list = new ArrayList<customerVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet r = st.executeQuery("select customerName from salesbill where invoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
			r.next();
			ResultSet rs = st2.executeQuery("select name,address1,address2,GSTNo,city,stateCode from customer where name='"+r.getString("customerName")+"'");
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
			st2.close();
		}catch (Exception e) {
			System.out.println("printDAO :: getCustomer :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}

	public int counts(salesVO salesVO) throws SQLException {
		int n=0;
		int cnt=0;
		int scnt=0;
		int gcnt=0;
		int yy = 1;
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs = st.executeQuery("select name,srno,description,GST from salesitem where salesInvoiceNo='"+salesVO.getSalesInvoiceNo()+"'");
			while(rs.next()){
				n++;
				if(!rs.getString("description").equals(""))
					cnt++;
				String s[] = rs.getString("srno").split("\\*");
				for(int i=0;i<s.length;i=i+2)
					if(!s[i].equals(""))
						scnt++;
				if(rs.getInt("GST")!=18)
					gcnt=2;
				
				if(n + cnt + (scnt) + gcnt > 34){
					yy=2;
					break;
				}
			}
			st2.close();
			if(yy==2)
				return n;
			else
				return 0;
		}
		catch(Exception e){
			System.out.println("printDAO :: getSalesItem :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return 0;
	}
	
	public int countp(purchaseVO purchaseVO) throws SQLException {
		
		int n=0;
		int cnt=0;
		int scnt=0;
		int gcnt=0;
		int yy=1;
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs = st.executeQuery("select name,srno,description,GST from purchaseitem where purchaseInvoiceNo='"+purchaseVO.getPurchaseInvoiceNo()+"'");
			while(rs.next()){
				n++;
				if(!rs.getString("description").equals(""))
					cnt++;
				String s[] = rs.getString("srno").split("\\*");
				for(int i=0;i<s.length;i=i+2)
					if(!s[i].equals(""))
						scnt++;
				if(rs.getInt("GST")!=18)
					gcnt=2;
				if(n + cnt + (scnt) + gcnt > 34){
					yy=2;
					break;
				}
			}
			st2.close();
			if(yy==2)
				return n;
			else
				return 0;
		}
		catch(Exception e){
			System.out.println("printDAO :: getSalesItem :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return 0;
	}
}
