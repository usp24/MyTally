package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.myException;
import dao.purchaseDAO;
import dao.supplierDAO;
import vo.purchaseVO;
import vo.supplierVO;

@WebServlet("/purchase")
public class purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = (String)request.getParameter("ch");
		switch(ch){
			case "purchasebill" : 
									try{
										purchasebill(request,response);
									} 
									catch (myException e) {
										System.out.println("purchase :: doPost :: myException :: "+e.purchaseInvoiceWorng());
										response.sendRedirect("purchasebill.jsp");
									}
									break;
			case "purchaseitem" : purchaseitem(request,response);break;
			default : System.out.println("*** Default Case *** :: purchse.java");
					  response.sendRedirect("menu.jsp");break;
		}
	}

	void purchasebill(HttpServletRequest request, HttpServletResponse response) throws IOException, myException{
		
		purchaseVO purchaseVO = new purchaseVO();
		supplierVO supplierVO = new supplierVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		supplierDAO supplierDAO = new supplierDAO();
		HttpSession session = request.getSession();
		
		String purchaseInvoiceNos = (String)request.getParameter("purchaseInvoiceNumber");
			Integer purchaseInvoiceNo = new Integer(purchaseInvoiceNos);
		String purchaseInvoiceDate = (String)request.getParameter("purchaseInvoiceDate");
		String supplierName = (String)request.getParameter("supplierName");
		String supplierAddress1 = (String)request.getParameter("supplierAddress1");
		String supplierAddress2 = (String)request.getParameter("supplierAddress2");
		String supplierGSTNo = (String)request.getParameter("supplierGSTNo");
		String purchaseNumOfItemss = (String)request.getParameter("purchaseNumOfItems");
			Integer purchaseNumOfItems = new Integer(purchaseNumOfItemss);
		
		supplierVO.setSupplierName(supplierName);
		supplierVO.setSupplierAddress1(supplierAddress1);
		supplierVO.setSupplierAddress2(supplierAddress2);
		supplierVO.setSupplierGSTNo(supplierGSTNo);
		
		purchaseVO.setPurchaseInvoiceDate(purchaseInvoiceDate);
		purchaseVO.setPurchaseInvoiceNo(purchaseInvoiceNo);
		purchaseVO.setPurchaseNumOfItems(purchaseNumOfItems);

		supplierDAO.insertPurchase(supplierVO);
		purchaseDAO.insertbill(purchaseVO, supplierVO);
		
		session.setAttribute("s",supplierVO.getSupplierName());
		session.setAttribute("x",purchaseVO.getPurchaseInvoiceNo());
		session.setAttribute("n",purchaseVO.getPurchaseNumOfItems());
		response.sendRedirect("purchaseitem.jsp");
	}
	
	void purchaseitem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		purchaseVO purchaseVO = new purchaseVO();
		supplierVO supplierVO = new supplierVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		HttpSession session = request.getSession();
		
		String s = (String)session.getAttribute("s");
		int x = (Integer)session.getAttribute("x");
		int n = (Integer)session.getAttribute("n");
		supplierVO.setSupplierName(s);
		purchaseVO.setPurchaseInvoiceNo(x);
		
		Double purchaseTotalAmount=0.000;
		Double GST14=0.000;
		Double GST9=0.000;
		
		for(int i=1;i<=n;i++){
			
			String purchaseItemName = (String)request.getParameter("purchaseItemName"+i);
			String purchaseItemDiscription = (String)request.getParameter("purchaseItemDiscription"+i);
			String purchaseItemHSN = (String)request.getParameter("purchaseItemHSN"+i);
			String purchaseItemGSTs = (String)request.getParameter("purchaseItemGST"+i);
				Integer purchaseItemGST = new Integer(purchaseItemGSTs);
			String purchaseItemUnitPrices = (String)request.getParameter("purchaseItemUnitPrice"+i);
				Double purchaseItemUnitPrice = new Double(purchaseItemUnitPrices);
			String purchaseItemQtys = (String)request.getParameter("purchaseItemQty"+i);
				Integer purchaseItemQty = new Integer(purchaseItemQtys);
			String purchaseItemAmounts = (String)request.getParameter("purchaseItemAmount"+i);
				Double purchaseItemAmount = new Double(purchaseItemAmounts);
			String purchaseItemDiscounts = (String)request.getParameter("purchaseItemDiscount"+i);
				Double purchaseItemDiscount = Double.parseDouble(purchaseItemDiscounts);
				
			purchaseVO.setPurchaseItemDiscount(purchaseItemDiscount);	
			purchaseVO.setPurchaseItemName(purchaseItemName);
			purchaseVO.setPurchaseItemDiscription(purchaseItemDiscription);
			purchaseVO.setPurchaseItemHSN(purchaseItemHSN);
			purchaseVO.setPurchaseItemGST(purchaseItemGST);
			purchaseVO.setPurchaseItemUnitPrice(purchaseItemUnitPrice);
			purchaseVO.setPurchaseItemQty(purchaseItemQty);
			
			purchaseItemAmount -= (purchaseItemDiscount/100)*purchaseItemAmount;
			purchaseVO.setPurchaseItemAmount(purchaseItemAmount);
			purchaseTotalAmount += purchaseItemAmount;
			
			if(purchaseItemGST.intValue()==18)
				GST9 += purchaseItemAmount*0.09;
			else if(purchaseItemGST.intValue()==28)
				GST14 += purchaseItemAmount*0.14;
			
			purchaseDAO.insertitem(purchaseVO, supplierVO);
		}
		
		purchaseVO.setPurchaseGST14(GST14);
		purchaseVO.setPurchaseGST9(GST9);
		purchaseTotalAmount += GST14 + GST9;
		long purchaseTotalRoundOffAmount = (new Double(Math.round(purchaseTotalAmount))).longValue();
		purchaseVO.setPurchaseTotalRoundOffAmount(purchaseTotalRoundOffAmount);
		purchaseVO.setPurchaseTotalAmount(purchaseTotalAmount);
		purchaseDAO.insertbill2(purchaseVO, supplierVO);
		
		response.sendRedirect("menu.jsp");
	}
}
