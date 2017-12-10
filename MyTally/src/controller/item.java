package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.itemDAO;
import dao.purchaseDAO;
import vo.itemVO;
import vo.purchaseVO;

@WebServlet("/item")
public class item extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = request.getParameter("ch");
		switch(ch){
		case "itemEntryBefore" : try{
										itemEntryBefore(request,response);
									} catch (ClassNotFoundException | SQLException e) {
										System.out.println("item.java :: itemEntryBefore :: ");
										e.printStackTrace();
									}break;
									
		case "itemEntry" : try {
				itemEntry(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}break;
	
		case "itemDetail" : try {
			itemDetail(request,response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}break;
	
		/*case "purchase" : try{
								purchase(request,response);
								response.sendRedirect("menu.jsp");
						}catch (Exception e) {
								e.printStackTrace();
						}break;*/
						
		/*case "sales" :  try{
								sales(request,response);
								response.sendRedirect("other/menu.jsp");
						}catch (SQLException e) {
								e.printStackTrace();
						}break;*/				
		
		default : System.out.println("*** DEFAULT CASE *** :: item.java");break;
		
		}
	}

	void itemEntryBefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getItemNames()));
	}
	
	void itemDetail(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getItemDetail()));
	}

	void itemEntry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		itemVO itemVO = new itemVO();
		itemDAO itemDAO = new itemDAO();
		
		String itemName = (String)request.getParameter("itemName").toUpperCase();
		String itemDescription = (String)request.getParameter("itemDescription");
		String itemHSN = (String)request.getParameter("itemHSN");
			String itemGSTs = (String)request.getParameter("itemGST");
		Integer itemGST = new Integer(itemGSTs);
			String itemPurchasePrices = (String)request.getParameter("itemPurchasePrice");
		Double itemPurchasePrice = new Double(itemPurchasePrices);
			String itemSalesPrices = (String)request.getParameter("itemSalesPrice");
		Double itemSalesPrice = new Double(itemSalesPrices);
		
			itemVO.setItemName(itemName);
			itemVO.setItemDescription(itemDescription);
			itemVO.setItemHSN(itemHSN);
			itemVO.setItemGST(itemGST);
			itemVO.setItemPurchasePrice(itemPurchasePrice);
			itemVO.setItemSalesPrice(itemSalesPrice);
			
		itemDAO.insertItem(itemVO);
		
		response.sendRedirect("menu.jsp");
	}
}
/*	void purchase(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		purchaseVO purchaseVO = new purchaseVO();
		itemVO itemVO = new itemVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		itemDAO itemDAO = new itemDAO();
		
		String is = (String)request.getParameter("i");
			Integer iv = new Integer(is);
		String ns = (String)request.getParameter("n");
			Integer n = new Integer(ns);
		
		purchaseVO.setPurchaseInvoiceNo(iv);
		
		Double purchaseTotalAmount=0.000;
		Double purchaseTotalAmountGST=0.000;
		Double GST14=0.000;
		Double GST9=0.000;
		
		for(int i=1;i<=n;i++){
			
			String itemName = (String)request.getParameter("ItemName"+i).toUpperCase();
			String itemDescription = (String)request.getParameter("ItemDescription"+i);
			String itemHSN = (String)request.getParameter("ItemHSN"+i);
			String itemGSTs = (String)request.getParameter("ItemGST"+i);
				Integer itemGST = new Integer(itemGSTs);
			
				itemVO.setItemName(itemName);
				itemVO.setItemDescription(itemDescription);
				itemVO.setItemHSN(itemHSN);
				itemVO.setItemGST(itemGST);
			
				String purchaseItemQtys = (String)request.getParameter("purchaseItemQty"+i);
			Integer purchaseItemQty = new Integer(purchaseItemQtys);
			String itemSrNo = "";
			for(int j=1;j<=purchaseItemQty;j++){
				String z = (String)request.getParameter("ItemSrNo"+i+""+j);
				if(z!=null)
					itemSrNo = itemSrNo + z + "*" ;
			}
			
				String purchaseItemUnitPrices = (String)request.getParameter("purchaseItemUnitPrice"+i);
			Double purchaseItemUnitPrice = new Double(purchaseItemUnitPrices);
				String purchaseItemTotalAmounts = (String)request.getParameter("purchaseItemTotalAmount"+i);
			Double purchaseItemTotalAmount = new Double(purchaseItemTotalAmounts);
				String purchaseItemDiscounts = (String)request.getParameter("purchaseItemDiscount"+i);
			Double purchaseItemDiscount = Double.parseDouble(purchaseItemDiscounts);
			
				itemVO.setItemSrNo(itemSrNo);
				itemVO.setItemQty(purchaseItemQty);
				itemVO.setItemPurchasePrice(purchaseItemUnitPrice);
				
			itemDAO.updatePurchaseItem(itemVO);
					
				purchaseItemTotalAmount -= (purchaseItemDiscount/100)*purchaseItemTotalAmount;
				
				purchaseVO.setPurchaseItemDiscount(purchaseItemDiscount);
				purchaseVO.setPurchaseItemTotalAmount(purchaseItemTotalAmount);
				
			purchaseDAO.insertPurchase(purchaseVO, itemVO);
			
			purchaseTotalAmount += purchaseItemTotalAmount;
			if(itemGST.intValue()==18)
				GST9 += purchaseItemTotalAmount*0.09;
			else if(itemGST.intValue()==28)
				GST14 += purchaseItemTotalAmount*0.14;
			
		}
		
		purchaseTotalAmountGST += (2*GST14) + (2*GST9) + purchaseTotalAmount;
		long purchaseTotalRoundOffAmount = (new Double(Math.round(purchaseTotalAmountGST))).longValue();
		
			purchaseVO.setPurchaseGST14(GST14);
			purchaseVO.setPurchaseGST9(GST9);
			purchaseVO.setPurchaseTotalRoundOffAmount(purchaseTotalRoundOffAmount); 
			purchaseVO.setPurchaseTotalAmount(purchaseTotalAmount);
			purchaseVO.setPurchaseTotalAmountGST(purchaseTotalAmountGST);
			
			purchaseDAO.insertbill2(purchaseVO);
			
			response.sendRedirect("menu.jsp");
	}
}
	
	void sales(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
			
			salesVO salesVO = new salesVO();
			customerVO customerVO = new customerVO();
			itemVO itemVO = new itemVO();
			salesDAO salesDAO = new salesDAO();
			itemDAO itemDAO = new itemDAO();
			
			String s = (String)request.getParameter("s");
			String g = (String)request.getParameter("g");
			String is = (String)request.getParameter("i");
				Integer iv = new Integer(is);
			String ns = (String)request.getParameter("n");
				Integer n = new Integer(ns);
			
			customerVO.setCustomerName(s);
			customerVO.setCustomerGSTNo(g);
			salesVO.setSalesInvoiceNo(iv);
			
			Double salesTotalAmount=0.000;
			Double salesTotalAmountGST=0.000;
			Double GST14=0.000;
			Double GST9=0.000;
			
			for(int i=1;i<=n;i++){
				
				String itemName = (String)request.getParameter("ItemName"+i);
				String itemSrNo = (String)request.getParameter("ItemSrNo"+i);
				String itemDiscription = (String)request.getParameter("ItemDiscription"+i);
				String itemHSN = (String)request.getParameter("ItemHSN"+i);
				String itemGSTs = (String)request.getParameter("ItemGST"+i);
					Integer itemGST = new Integer(itemGSTs);
				
					itemVO.setItemName(itemName);
					itemVO.setItemSrNo(itemSrNo);
					itemVO.setItemDiscription(itemDiscription);
					itemVO.setItemHSN(itemHSN);
					itemVO.setItemGST(itemGST);
				
				String salesItemUnitPrices = (String)request.getParameter("salesItemUnitPrice"+i);
					Double salesItemUnitPrice = new Double(salesItemUnitPrices);
				String salesItemQtys = (String)request.getParameter("salesItemQty"+i);
					Integer salesItemQty = new Integer(salesItemQtys);
				String salesItemAmounts = (String)request.getParameter("salesItemAmount"+i);
					Double salesItemAmount = new Double(salesItemAmounts);
				String salesItemDiscounts = (String)request.getParameter("salesItemDiscount"+i);
					Double salesItemDiscount = Double.parseDouble(salesItemDiscounts);
				
					itemVO.setSalesItemUnitPrice(salesItemUnitPrice);
					itemVO.setSalesItemQty(salesItemQty);
					itemVO.setSalesItemDiscount(salesItemDiscount);
					
				salesItemAmount -= (salesItemDiscount/100)*salesItemAmount;
				
					itemVO.setSalesItemAmount(salesItemAmount);
					itemDAO.insertSales(salesVO, customerVO,itemVO);
				
				salesTotalAmount += salesItemAmount;
				if(itemGST.intValue()==18)
					GST9 += salesItemAmount*0.09;
				else if(itemGST.intValue()==28)
					GST14 += salesItemAmount*0.14;
				
			}
			
			salesTotalAmountGST += (2*GST14) + (2*GST9) + salesTotalAmount;
			long salesTotalRoundOffAmount = (new Double(Math.round(salesTotalAmountGST))).longValue();
			
				salesVO.setSalesGST14(GST14);
				salesVO.setSalesGST9(GST9);
				salesVO.setSalesTotalRoundOffAmount(salesTotalRoundOffAmount); 
				salesVO.setSalesTotalAmount(salesTotalAmount);
				salesVO.setSalesTotalAmountGST(salesTotalAmountGST);
				
				salesDAO.insertbill2(salesVO, customerVO);
*/		

