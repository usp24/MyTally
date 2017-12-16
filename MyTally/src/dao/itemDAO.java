package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;

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
			ResultSet rs = st.executeQuery("select name,srno,description,GST,HSN,purchasePrice from item");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				itemVO.setItemSrNo(rs.getString("srno"));
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
	
	public List<itemVO> getItemDetailSale() throws SQLException{
		
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,srno,description,GST,HSN,salesPrice from item");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				itemVO.setItemSrNo(rs.getString("srno"));
				itemVO.setItemDescription(rs.getString("description"));
				itemVO.setItemGST(rs.getInt("GST"));
				itemVO.setItemHSN(rs.getString("HSN"));
				itemVO.setItemSalesPrice(rs.getDouble("salesPrice"));
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
			st.executeUpdate("insert into item(name,description,HSN,GST,purchasePrice,salesPrice) values('"+itemVO.getItemName()+"','"+itemVO.getItemDescription()+"','"+itemVO.getItemHSN()+"','"+itemVO.getItemGST()+"','"+itemVO.getItemPurchasePrice()+"','"+itemVO.getItemSalesPrice()+"') ");
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
	
	public void updateSalesItem(itemVO itemVO) throws SQLException{
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select qty,srno,name from item");
			String n2 = "";
			int q = 0;
			boolean f = false;
			while(rs.next()){
				if(itemVO.getItemName().equals(rs.getString("name"))){
					if(itemVO.getItemSrNo()!=null){
						String ari[] = rs.getString("srno").split("\\*");
					    String arp[] = itemVO.getItemSrNo().split("\\*");
					 
					    List<String> list_i = new ArrayList<String>();
					    List<String> d = new ArrayList<String>();
					        
						for(int j=0;j<ari.length;j++)
						   list_i.add(ari[j]);
				        for(int j=0;j<arp.length;j++)
						   d.add(arp[j]);
				        
						n2 = "";
					    list_i.removeAll(d);
						for(int i=0;i<list_i.size();i++){
							n2 = n2.concat((String)list_i.get(i)).concat("*");   
					    }
					}
					q = rs.getInt("qty") - itemVO.getItemQty();
					st.executeUpdate("update item set srno='"+n2+"',description='"+itemVO.getItemDescription()+"',HSN='"+itemVO.getItemHSN()+"',GST='"+itemVO.getItemGST()+"',salesPrice='"+itemVO.getItemSalesPrice()+"',qty='"+q+"' where name='"+itemVO.getItemName()+"'");
					f=true;
					break;
				}
			}
			if(f==false){
				st.executeUpdate("insert into item(name,srno,qty,description,HSN,GST,salesPrice) values('"+itemVO.getItemName()+"','"+itemVO.getItemSrNo()+"','"+itemVO.getItemQty()+"','"+itemVO.getItemDescription()+"','"+itemVO.getItemHSN()+"','"+itemVO.getItemGST()+"','"+itemVO.getItemSalesPrice()+"') ");
			}
		}catch (Exception e) {
			System.out.println("itemDAO :: updateSalesItem :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
	}

	public List<itemVO> getStock() throws SQLException {
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,srno,description,GST,HSN,qty from item where qty>0 OR qty<0");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				itemVO.setItemSrNo(rs.getString("srno"));
				itemVO.setItemDescription(rs.getString("description"));
				itemVO.setItemGST(rs.getInt("GST"));
				itemVO.setItemHSN(rs.getString("HSN"));
				itemVO.setItemQty(rs.getInt("qty"));
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

	public List<purchaseVO> getstockPurchaseBill(itemVO itemVO) throws SQLException {
		List<purchaseVO> list = new ArrayList<purchaseVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet rs = st.executeQuery("select srno,qty from item where name='"+itemVO.getItemName()+"'");
			rs.next();
			int qtyi = rs.getInt("qty");
			String[] srnoi = rs.getString("srno").split("//*");
			List<String> i = new ArrayList<String>();
			for(int j=0;j<srnoi.length;j++){
				if(srnoi[j]!="")
					i.add(srnoi[j]);
			}
			ResultSet rs2 = st2.executeQuery("select purchaseInvoiceNo,qty,srno from purchaseitem where name='"+itemVO.getItemName()+"' order by id desc");
			if(i.size()!=0){
				while(qtyi!=0){			
					while(rs2.next()){
						String[] srnop = rs2.getString("srno").split("//*");
					    List<String> p = new ArrayList<String>();
				        for(int j=0;j<srnop.length;j++){
							if(srnop[j]!="")
								p.add(srnop[j]);
						}
				        int il = i.size();
					    i.removeAll(p);
					    if(i.size()!=il)
					    
						for(int k=0;k<srnop.length;k++){
							if(srnop[k]!="" && srnoi[k]!="" ){
								
							}
						}
						if(rs2.getInt("qty")>=rs.getInt("qty")){
							//list.add(purchaseVO);
						}
					}
				}
			}	
			else{
				
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

	public List<itemVO> viewAllItem() throws SQLException {
		List<itemVO> list = new ArrayList<itemVO>();
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select name,description,GST,HSN,purchasePrice,salesPrice from item order by name");
			while(rs.next()){
				itemVO itemVO = new itemVO();
				itemVO.setItemName(rs.getString("name"));
				itemVO.setItemDescription(rs.getString("description"));
				itemVO.setItemGST(rs.getInt("GST"));
				itemVO.setItemHSN(rs.getString("HSN"));
				itemVO.setItemPurchasePrice(rs.getDouble("purchasePrice"));
				itemVO.setItemSalesPrice(rs.getDouble("salesPrice"));
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
}