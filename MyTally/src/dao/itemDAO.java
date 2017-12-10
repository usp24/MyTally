package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.myException;
import vo.customerVO;
import vo.itemVO;
import vo.purchaseVO;
import vo.salesVO;
import vo.supplierVO;

public class itemDAO {
	
	Connection con;
	Statement st;

	public List<itemVO> getItemNames() throws SQLException{
		
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from item");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				list.add(itemVO);
			}
		}catch (Exception e) {
			System.out.println("itemDAO :: getItemName :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
	
	public List<itemVO> getItemDetail() throws SQLException{
		
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,description,GST,HSN,purchasePrice from item");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				itemVO.setItemDescription(rs.getString("description"));
				itemVO.setItemGST(rs.getInt("GST"));
				itemVO.setItemHSN(rs.getString("HSN"));
				itemVO.setItemPurchasePrice(rs.getDouble("purchasePrice"));
				list.add(itemVO);
			}
		}catch (Exception e) {
			System.out.println("itemDAO :: getItemDetail :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		return list;
	}
	
	public void insertItem(itemVO itemVO) throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			st.executeUpdate("insert into item(name,description,HSN,GST,purchasePrice,salePrice) values('"+itemVO.getItemName()+"','"+itemVO.getItemDescription()+"','"+itemVO.getItemHSN()+"','"+itemVO.getItemGST()+"','"+itemVO.getItemPurchasePrice()+"','"+itemVO.getItemSalesPrice()+"') ");
		}catch (Exception e) {
			System.out.println("itemDAO :: insertItem :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}

	public void updatePurchaseItem(itemVO itemVO) throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select qty,srno,name from item");
			String s1 = "";
			int q = 0;
			boolean f = false;
			while(rs.next()){
				if(itemVO.getItemName().equals(rs.getString("name"))){
					if(itemVO.getItemSrNo()!=null)
						s1 = rs.getString("srno") + itemVO.getItemSrNo();
					q = rs.getInt("qty") + itemVO.getItemQty();
					st.executeUpdate("update item set srno='"+s1+"',description='"+itemVO.getItemDescription()+"',HSN='"+itemVO.getItemHSN()+"',GST='"+itemVO.getItemGST()+"',purchasePrice='"+itemVO.getItemPurchasePrice()+"',qty='"+q+"' where name='"+itemVO.getItemName()+"'");
					f=true;
					break;
				}
			}
			if(f==false){
				st.executeUpdate("insert into item(name,srno,qty,description,HSN,GST,purchasePrice) values('"+itemVO.getItemName()+"','"+itemVO.getItemSrNo()+"','"+itemVO.getItemQty()+"','"+itemVO.getItemDescription()+"','"+itemVO.getItemHSN()+"','"+itemVO.getItemGST()+"','"+itemVO.getItemPurchasePrice()+"') ");
			}
		}catch (Exception e) {
			System.out.println("itemDAO :: updatePurchaseItem :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}
}
/*	
	public void insertSales(salesVO p,customerVO s,itemVO i) throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			
			ResultSet rs = st.executeQuery("select COUNT(id)"+"cnt"+" from item where name='"+i.getItemName()+"' AND salesInvoiceNo is null");
			rs.next();
			int cnt = rs.getInt("cnt");
			ResultSet rs2 = st.executeQuery("select id from item where name='"+i.getItemName()+"' AND salesInvoiceNo is null");
			if(cnt>0){
				int q = i.getSalesItemQty();
				System.out.println("1");
				if(q==1){
					System.out.println("2");
					if(i.getItemSrNo()!=null)
						st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' AND srno='"+i.getItemSrNo()+"'");
					else{
						rs2.next();
						System.out.println("3");
						st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' id='"+rs2.getInt("id")+"'");
					}
				}
				else if(q>1 & q<=cnt){
					System.out.println("4");
					String[] s1 = i.getItemSrNo().split("\\*");
					if(s1.length!=0){
						System.out.println("5");
						for(int j=0;j<q;j++){
							st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' AND srno='"+s1[j]+"'");	
						}
					}
					else{
						int j=1;
						System.out.println("6");
						while(rs2.next() & j<=q){
							st.executeUpdate("update item set salesInvoiceNo='"+p.getSalesInvoiceNo()+"',customerGSTNo='"+s.getCustomerGSTNo()+"',customerName='"+s.getCustomerName()+"',salesItemQty='"+i.getSalesItemQty()+"',salesItemUnitPrice='"+i.getSalesItemUnitPrice()+"',salesItemDiscount='"+i.getSalesItemDiscount()+"',salesItemAmount='"+i.getSalesItemAmount()+"' where name='"+i.getItemName()+"' id='"+rs2.getInt("id")+"'");
							j++;
						}
					}
				}
				else
					throw new myException();
			}
		}
		catch(Exception e){
			System.out.println("itemDAO :: insertsales :: "+e);
		} catch (myException e) {
			System.out.println("itemDAO :: insertsales :: myexception "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<vo.itemVO> selectItemBefore() throws ClassNotFoundException, SQLException {
		
		List<vo.itemVO> list = new ArrayList<vo.itemVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT name,HSN,GST FROM item GROUP BY name ORDER BY name");
		while(rs.next()){
			itemVO itemVO = new itemVO();
			itemVO.setItemName(rs.getString("name"));
			itemVO.setItemGST(rs.getInt("GST"));
			itemVO.setItemHSN(rs.getString("HSN"));
			list.add(itemVO);
		}
		return list;
	}
	
	public List<vo.itemVO> selectItemBeforeSale() throws ClassNotFoundException,SQLException {
		
		List<vo.itemVO> list = new ArrayList<vo.itemVO>();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT name,discription,HSN,GST,purchaseItemUnitPrice,COUNT(*) AS sqty FROM item where salesInvoiceNo is null GROUP BY name ORDER BY name");
		while(rs.next()){
			itemVO itemVO = new itemVO();
			itemVO.setItemName(rs.getString("name"));
			itemVO.setItemDiscription(rs.getString("discription"));
			itemVO.setItemSrNo(rs.getString("srno"));
			itemVO.setItemGST(rs.getInt("GST"));
			itemVO.setItemHSN(rs.getString("HSN"));
			itemVO.setSalesItemQty(rs.getInt("sqty"));
			itemVO.setPurchaseItemUnitPrice(rs.getDouble("purchaseItemUnitPrice"));
			list.add(itemVO);
		}
		return list;
	}	*/