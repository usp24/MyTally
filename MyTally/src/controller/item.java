package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.itemDAO;
import dao.purchaseDAO;
import dao.salesDAO;
import vo.customerVO;
import vo.itemVO;
import vo.purchaseVO;
import vo.salesVO;
import vo.supplierVO;

@WebServlet("/item")
public class item extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = request.getParameter("ch");
		switch(ch){
		
		case "purchase" : try{
								purchase(request,response);
								response.sendRedirect("other/menu.jsp");
						}catch (SQLException e) {
								e.printStackTrace();
						}break;
						
		case "sales" :  try{
								sales(request,response);
								response.sendRedirect("other/menu.jsp");
						}catch (SQLException e) {
								e.printStackTrace();
						}break;				
		
		default : System.out.println("*** DEFAULT CASE *** :: item.java");break;
		
		}
	}
	
	
	void purchase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		purchaseVO purchaseVO = new purchaseVO();
		supplierVO supplierVO = new supplierVO();
		itemVO itemVO = new itemVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		itemDAO itemDAO = new itemDAO();
		
		String s = (String)request.getParameter("s");
		String g = (String)request.getParameter("g");
		String is = (String)request.getParameter("i");
			Integer iv = new Integer(is);
		String ns = (String)request.getParameter("n");
			Integer n = new Integer(ns);
		
		supplierVO.setSupplierName(s);
		supplierVO.setSupplierGSTNo(g);
		purchaseVO.setPurchaseInvoiceNo(iv);
		
		Double purchaseTotalAmount=0.000;
		Double purchaseTotalAmountGST=0.000;
		Double GST14=0.000;
		Double GST9=0.000;
		
		for(int i=1;i<=n;i++){
			
			String itemName = (String)request.getParameter("ItemName"+i);
			String itemDiscription = (String)request.getParameter("ItemDiscription"+i);
			String itemHSN = (String)request.getParameter("ItemHSN"+i);
			String itemGSTs = (String)request.getParameter("ItemGST"+i);
				Integer itemGST = new Integer(itemGSTs);
			
				itemVO.setItemName(itemName);
				itemVO.setItemDiscription(itemDiscription);
				itemVO.setItemHSN(itemHSN);
				itemVO.setItemGST(itemGST);
				
			String itemSrNo = "";	
			String purchaseItemQtys = (String)request.getParameter("purchaseItemQty"+i);
				Integer purchaseItemQty = new Integer(purchaseItemQtys);
			if(purchaseItemQty.intValue()>1){
				for(int j=1;j<=purchaseItemQty;j++){
					itemSrNo = itemSrNo.concat((String)request.getParameter("ItemSrNo"+i+""+j)) + "*";
				}
			}
			else
				itemSrNo = (String)request.getParameter("ItemSrNo"+i+"1");
			
			itemVO.setItemSrNo(itemSrNo);
			System.out.println("java : "+itemSrNo);
			
			String purchaseItemUnitPrices = (String)request.getParameter("purchaseItemUnitPrice"+i);
				Double purchaseItemUnitPrice = new Double(purchaseItemUnitPrices);
			Double purchaseItemAmount = new Double(0);
			String purchaseItemDiscounts = (String)request.getParameter("purchaseItemDiscount"+i);
				Double purchaseItemDiscount = Double.parseDouble(purchaseItemDiscounts);
			
				itemVO.setPurchaseItemUnitPrice(purchaseItemUnitPrice);
				itemVO.setPurchaseItemQty(purchaseItemQty);
				itemVO.setPurchaseItemDiscount(purchaseItemDiscount);
				
			purchaseItemAmount = purchaseItemUnitPrice - (purchaseItemDiscount/100)*purchaseItemUnitPrice;
			
				itemVO.setPurchaseItemAmount(purchaseItemAmount);
				itemDAO.insertPurchase(purchaseVO, supplierVO,itemVO);
			
			purchaseTotalAmount += (purchaseItemAmount * purchaseItemQty);
			if(itemGST.intValue()==18)
				GST9 += purchaseItemAmount*purchaseItemQty*0.09;
			else if(itemGST.intValue()==28)
				GST14 += purchaseItemAmount*purchaseItemQty*0.14;
			
		}
		
		purchaseTotalAmountGST += (2*GST14) + (2*GST9) + purchaseTotalAmount;
		long purchaseTotalRoundOffAmount = (new Double(Math.round(purchaseTotalAmountGST))).longValue();
		
			purchaseVO.setPurchaseGST14(GST14);
			purchaseVO.setPurchaseGST9(GST9);
			purchaseVO.setPurchaseTotalRoundOffAmount(purchaseTotalRoundOffAmount); 
			purchaseVO.setPurchaseTotalAmount(purchaseTotalAmount);
			purchaseVO.setPurchaseTotalAmountGST(purchaseTotalAmountGST);
			
			purchaseDAO.insertbill2(purchaseVO, supplierVO);
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
		}

}
