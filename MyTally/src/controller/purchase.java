package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import my.myException;
import dao.itemDAO;
import dao.purchaseDAO;
import dao.supplierDAO;
import vo.itemVO;
import vo.purchaseVO;
import vo.supplierVO;

@WebServlet("/purchase")
public class purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = (String)request.getParameter("ch");
		switch(ch)
		{	
		/*	case "purchaseBill" : 
									try{
										purchaseBill(request,response);
									} 
									catch (myException e) {
										System.out.println("purchase :: doPost :: myException :: "+((myException) e).purchaseInvoiceWorng());
										response.sendRedirect("purchase/purchasebill.jsp?ch=purchasebillbefore");
									} catch (Exception e) {
										System.out.println("purchase :: doPost :: Exception :: "+e);
										response.sendRedirect("purchase/purchasebill.jsp?ch=purchasebillbefore");
									}
									break;*/
			
			case "purchaseBillBefore" : 
										try {
												purchaseBillBefore(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
			case "purchaseBillBefore2" : 
										try {
												purchaseBillBefore2(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
			case "purchaseBillShow" : 
										try {
												purchaseBillShow(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
										
			case "purchase" : 
								try{
									purchaseMethod(request,response);
								}catch (Exception e) {
									e.printStackTrace();
								}break;
								
			case "edit" : 
							try{
								edit(request,response);
							}catch (Exception e) {
								e.printStackTrace();
							}break;
				
			case "edit2" : 
							try{
								edit2(request,response);
							}catch (Exception e) {
								e.printStackTrace();
							}break;
		
			default : System.out.println("*** Default Case *** :: purchse.java");
					  response.sendRedirect("menu.jsp");break;
		}
	}

	
	void purchaseBillBefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		purchaseDAO purchaseDAO = new purchaseDAO();
		HttpSession session = request.getSession();
		session.setAttribute("maxp",purchaseDAO.maxInvoiceNo());
		supplierDAO supplierDAO = new supplierDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(supplierDAO.getSupplierDetail()));
		out.flush();
		out.close();
	}
	
	void purchaseBillBefore2(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		purchaseDAO purchaseDAO = new purchaseDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(purchaseDAO.getInvoiceNo()));
		out.flush();
		out.close();
	}
	
	void purchaseBillShow(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		purchaseDAO purchaseDAO = new purchaseDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(purchaseDAO.getPurchaseBills()));
		out.flush();
		out.close();
	}
	
	void purchseSupplierEntry(HttpServletRequest request, HttpServletResponse response) throws myException, Exception{
		
		purchaseVO purchaseVO = new purchaseVO();
		supplierVO supplierVO = new supplierVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		supplierDAO supplierDAO = new supplierDAO();
		
		
		String purchaseInvoiceNos = (String)request.getParameter("purchaseInvoiceNumber");
			Integer purchaseInvoiceNo = new Integer(purchaseInvoiceNos);
		String purchaseInvoiceDate = (String)request.getParameter("purchaseInvoiceDate");
		String supplierName = (String)request.getParameter("supplierName").toUpperCase();
		String supplierAddress1 = (String)request.getParameter("supplierAddress1");
		String supplierAddress2 = (String)request.getParameter("supplierAddress2");
		String supplierCity = (String)request.getParameter("supplierCity");
		String supplierStatecode = (String)request.getParameter("supplierStatecode");
		String supplierGSTNo = (String)request.getParameter("supplierGSTNo");
		//String purchaseNumOfItemss = (String)request.getParameter("purchaseNumOfItems");
			//Integer purchaseNumOfItems = new Integer(purchaseNumOfItemss);
		
		supplierVO.setSupplierName(supplierName);
		supplierVO.setSupplierAddress1(supplierAddress1);
		supplierVO.setSupplierAddress2(supplierAddress2);
		supplierVO.setSupplierCity(supplierCity);
		supplierVO.setSupplierStatecode(supplierStatecode);
		supplierVO.setSupplierGSTNo(supplierGSTNo);
		supplierVO.setSupplierPurchaseInvoiceNo(purchaseInvoiceNo.toString());
		
		supplierDAO.insertPurchase(supplierVO);
		
		purchaseVO.setPurchaseInvoiceNo(purchaseInvoiceNo);
		purchaseVO.setPurchaseInvoiceDate(purchaseInvoiceDate);
		//purchaseVO.setPurchaseNumOfItems(purchaseNumOfItems);

		purchaseDAO.insertbill(purchaseVO, supplierVO);
	}

	
	void purchaseMethod(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			purchseSupplierEntry(request, response);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		Integer n =	-1;
		String purchaseInvoiceNos = (String)request.getParameter("purchaseInvoiceNumber");
			Integer iv = new Integer(purchaseInvoiceNos);
		
		purchaseVO purchaseVO = new purchaseVO();
		itemVO itemVO = new itemVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		itemDAO itemDAO = new itemDAO();
		
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
			
			response.sendRedirect("purchase.jsp");
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		HttpSession session = request.getSession();
		String purchaseInvoiceNos = (String)session.getAttribute("ivn");
		Integer purchaseInvoiceNo = Integer.parseInt(purchaseInvoiceNos);
		
		purchaseDAO purchaseDAO = new purchaseDAO();
		purchaseVO purchaseVO = new purchaseVO();
		purchaseVO.setPurchaseInvoiceNo(purchaseInvoiceNo);
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(purchaseDAO.edit(purchaseVO)));
		out.flush();
		out.close();
	}
	
	private void edit2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException{
		
		HttpSession session = request.getSession();
		String purchaseInvoiceNo = (String)session.getAttribute("ivn");
		
		supplierVO supplierVO = new supplierVO();
		supplierVO.setSupplierPurchaseInvoiceNo(purchaseInvoiceNo);
		supplierDAO supplierDAO = new supplierDAO();
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(supplierDAO.getPurchaseEditDetail(supplierVO)));
		out.flush();
		out.close();
	}
}




