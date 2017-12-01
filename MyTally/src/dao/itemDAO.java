package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.customerVO;
import vo.itemVO;
import vo.purchaseVO;
import vo.salesVO;
import vo.supplierVO;

public class itemDAO {
	
	Connection con;
	Statement st;

	public void insertPurchase(purchaseVO p,supplierVO s,itemVO i) throws SQLException{
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select id,salesItemQty from item where name='"+i.getItemName()+"' AND purchaseInvoiceNo is null");
			if(rs.next()){
				int q = rs.getInt("salesItemQty");
				String[] s1 = i.getItemSrNo().split("\\*"); 
				if(q>1){
					for(int j=0;j<i.getPurchaseItemQty();j++){
						st.executeUpdate("update item set purchaseInvoiceNo,supplierGSTNo,supplierName,name,srno,discription,HSN,GST,purchaseItemQty,purchaseItemUnitPrice,purchaseItemDiscount,purchaseItemAmount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierName()+"','"+i.getItemName()+"','"+s1[j]+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"',1,'"+i.getPurchaseItemUnitPrice()+"','"+i.getPurchaseItemDiscount()+"','"+i.getPurchaseItemAmount()+"')");	
					}
				}
				else
					st.executeUpdate("insert into item(purchaseInvoiceNo,supplierGSTNo,supplierName,name,srno,discription,HSN,GST,purchaseItemQty,purchaseItemUnitPrice,purchaseItemDiscount,purchaseItemAmount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierName()+"','"+i.getItemName()+"','"+i.getItemSrNo()+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"','"+i.getPurchaseItemQty()+"','"+i.getPurchaseItemUnitPrice()+"','"+i.getPurchaseItemDiscount()+"','"+i.getPurchaseItemAmount()+"')");
			}
			
			int q = i.getPurchaseItemQty();
			String[] s1 = i.getItemSrNo().split("\\*"); 
			if(q>1){
				for(int j=0;j<q;j++){
					System.out.println("dao2 : "+s1[j]);
					st.executeUpdate("insert into item(purchaseInvoiceNo,supplierGSTNo,supplierName,name,srno,discription,HSN,GST,purchaseItemQty,purchaseItemUnitPrice,purchaseItemDiscount,purchaseItemAmount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierName()+"','"+i.getItemName()+"','"+s1[j]+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"',1,'"+i.getPurchaseItemUnitPrice()+"','"+i.getPurchaseItemDiscount()+"','"+i.getPurchaseItemAmount()+"')");	
				}
			}
			else
				st.executeUpdate("insert into item(purchaseInvoiceNo,supplierGSTNo,supplierName,name,srno,discription,HSN,GST,purchaseItemQty,purchaseItemUnitPrice,purchaseItemDiscount,purchaseItemAmount) values('"+p.getPurchaseInvoiceNo()+"','"+s.getSupplierGSTNo()+"','"+s.getSupplierName()+"','"+i.getItemName()+"','"+i.getItemSrNo()+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"','"+i.getPurchaseItemQty()+"','"+i.getPurchaseItemUnitPrice()+"','"+i.getPurchaseItemDiscount()+"','"+i.getPurchaseItemAmount()+"')");
		}
		catch(Exception e){
			System.out.println("itemDAO :: insertpurchase :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}
	
	public void insertSales(salesVO p,customerVO s,itemVO i) throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from item where name='"+i.getItemName()+"'");
			if(rs.next()){
				st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' AND srno='"+i.getItemSrNo()+"'");
			}
			else
				st.executeUpdate("insert into item(salesInvoiceNo,customerGSTNo,customerName,name,srno,discription,HSN,GST,salesItemQty,salesItemUnitPrice,salesItemDiscount,salesItemAmount) values('"+p.getSalesInvoiceNo()+"','"+s.getCustomerGSTNo()+"','"+s.getCustomerName()+"','"+i.getItemName()+"','"+i.getItemSrNo()+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"','"+i.getSalesItemQty()+"','"+i.getSalesItemUnitPrice()+"','"+i.getSalesItemDiscount()+"','"+i.getSalesItemAmount()+"')");
			
			
			int q = i.getSalesItemQty();
			String[] s1 = i.getItemSrNo().split("\\*"); 
			if(q>1){
				if(s1.length>0){
				for(int j=0;j<q;j++){
					st.executeUpdate("update item set salesInvoiceNo,customerGSTNo,customerName,name,srno,discription,HSN,GST,salesItemQty,salesItemUnitPrice,salesItemDiscount,salesItemAmount) values('"+p.getSalesInvoiceNo()+"','"+s.getCustomerGSTNo()+"','"+s.getCustomerName()+"','"+i.getItemName()+"','"+s1[j]+"','"+i.getItemDiscription()+"','"+i.getItemHSN()+"','"+i.getItemGST()+"',1,'"+i.getSalesItemUnitPrice()+"','"+i.getSalesItemDiscount()+"','"+i.getSalesItemAmount()+"')");	
				}
				}
			}
			else // srno 1j hoy; purchased kreli hoy item
				st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' AND srno='"+i.getItemSrNo()+"'");
		}
		catch(Exception e){
			System.out.println("itemDAO :: insertsales :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		}

}
