package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import my.myException;
import dao.itemDAO;
import dao.salesDAO;
import dao.customerDAO;
import vo.itemVO;
import vo.salesVO;
import vo.customerVO;

@WebServlet("/sales")
public class sales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = (String)request.getParameter("ch");
		switch(ch)
		{	
			
			case "salesBillBefore" : 
										try {
												salesBillBefore(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
			case "salesBillBefore2" : 
										try {
												salesBillBefore2(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
			case "salesBillShow" : 
										try {
												salesBillShow(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
										
			case "sales" : 
							try{
								Integer n =	Integer.parseInt(request.getParameter("n"));
								Integer iv = Integer.parseInt(request.getParameter("salesInvoiceNumber"));
								salesMethod(request,response,n,iv);
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
			
			case "editEntry" : 
							try{
								Integer n = Integer.parseInt(request.getParameter("n"));
								String old = request.getParameter("old_ivn");
								Integer iv = Integer.parseInt(request.getParameter("salesInvoiceNumber"));
								deleteBill(request,response,old);
								salesMethod(request,response,n,iv);
								response.sendRedirect("sales.jsp");
							}catch (Exception e) {
								e.printStackTrace();
							}break;
			case "delete" : 
							try{
								String iv = request.getParameter("n");
								deleteBill(request,response,iv);
								response.sendRedirect("sales.jsp");
							}catch (Exception e) {
								e.printStackTrace();
							}break;
		
			default : System.out.println("*** Default Case *** :: purchse.java");
					  response.sendRedirect("menu.jsp");break;
		}
	}

	
	void salesBillBefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		salesDAO salesDAO = new salesDAO();
		customerDAO customerDAO = new customerDAO();
		
		int max = salesDAO.maxInvoiceNo();
		HttpSession session = request.getSession();
		session.setAttribute("max",max);
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(customerDAO.getCustomerDetail()));
		out.flush();
		out.close();
	}
	
	void salesBillBefore2(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		salesDAO salesDAO = new salesDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(salesDAO.getInvoiceNo()));
		out.flush();
		out.close();
	}
	
	void salesBillShow(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		salesDAO salesDAO = new salesDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(salesDAO.getSalesBills()));
		out.flush();
		out.close();
	}
	
	void salesCustomerEntry(HttpServletRequest request, HttpServletResponse response) throws myException, Exception{
		
		salesVO salesVO = new salesVO();
		customerVO customerVO = new customerVO();
		salesDAO salesDAO = new salesDAO();
		customerDAO customerDAO = new customerDAO();
		
		
		Integer salesInvoiceNo = Integer.parseInt(request.getParameter("salesInvoiceNumber"));
		String salesInvoiceDate = (String)request.getParameter("salesInvoiceDate");
		String customerName = (String)request.getParameter("customerName").toUpperCase();
		String customerAddress1 = (String)request.getParameter("customerAddress1");
		String customerAddress2 = (String)request.getParameter("customerAddress2");
		String customerCity = (String)request.getParameter("customerCity");
		String customerStatecode = (String)request.getParameter("customerStatecode");
		String customerGSTNo = (String)request.getParameter("customerGSTNo");
		
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerAddress1(customerAddress1);
		customerVO.setCustomerAddress2(customerAddress2);
		customerVO.setCustomerCity(customerCity);
		customerVO.setCustomerStatecode(customerStatecode);
		customerVO.setCustomerGSTNo(customerGSTNo);
		customerVO.setCustomerSalesInvoiceNo(salesInvoiceNo.toString());
		
		customerDAO.insertSales(customerVO);
		
		salesVO.setSalesInvoiceNo(salesInvoiceNo);
		salesVO.setSalesInvoiceDate(salesInvoiceDate);

		salesDAO.insertbill(salesVO, customerVO);
	}

	
	void salesMethod(HttpServletRequest request, HttpServletResponse response,int n,int iv) throws Exception{
		
		try {
			salesCustomerEntry(request, response);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		salesVO salesVO = new salesVO();
		itemVO itemVO = new itemVO();
		salesDAO salesDAO = new salesDAO();
		itemDAO itemDAO = new itemDAO();
		
		salesVO.setSalesInvoiceNo(iv);
		
		Double salesTotalAmount=0.000;
		Double salesTotalAmountGST=0.000;
		Double GST14=0.000;
		Double GST9=0.000;
		
		for(int i=1;i<=n;i++){
			
			if((String)request.getParameter("ItemName"+i)==null)continue;
			String itemName = (String)request.getParameter("ItemName"+i).toUpperCase();
			String itemDescription = (String)request.getParameter("ItemDescription"+i);
			String itemHSN = (String)request.getParameter("ItemHSN"+i);
			String itemGSTs = (String)request.getParameter("ItemGST"+i);
				Integer itemGST = new Integer(itemGSTs);
			
				itemVO.setItemName(itemName);
				itemVO.setItemDescription(itemDescription);
				itemVO.setItemHSN(itemHSN);
				itemVO.setItemGST(itemGST);
			
			Integer salesItemQty = Integer.parseInt(request.getParameter("salesItemQty"+i));
			String itemSrNo = "";
			for(int j=1;j<=salesItemQty;j++){
				String z = (String)request.getParameter("ItemSrNo"+i+""+j);
				if(z!=null)
					itemSrNo = itemSrNo + z + "*" ;
			}
			
				String salesItemUnitPrices = (String)request.getParameter("salesItemUnitPrice"+i);
			Double salesItemUnitPrice = new Double(salesItemUnitPrices);
				String salesItemTotalAmounts = (String)request.getParameter("salesItemTotalAmount"+i);
			Double salesItemTotalAmount = new Double(salesItemTotalAmounts);
				String salesItemDiscounts = (String)request.getParameter("salesItemDiscount"+i);
			Double salesItemDiscount = Double.parseDouble(salesItemDiscounts);
			
				itemVO.setItemSrNo(itemSrNo);
				itemVO.setItemQty(salesItemQty);
				itemVO.setItemSalesPrice(salesItemUnitPrice);
				
			itemDAO.updateSalesItem(itemVO);
					
				salesItemTotalAmount -= (salesItemDiscount/100)*salesItemTotalAmount;
				
				salesVO.setSalesItemDiscount(salesItemDiscount);
				salesVO.setSalesItemTotalAmount(salesItemTotalAmount);
				
			salesDAO.insertSales(salesVO, itemVO);
			
			salesTotalAmount += salesItemTotalAmount;
			if(itemGST.intValue()==18)
				GST9 += salesItemTotalAmount*0.09;
			else if(itemGST.intValue()==28)
				GST14 += salesItemTotalAmount*0.14;
			
		}
		
		salesTotalAmountGST += (2*GST14) + (2*GST9) + salesTotalAmount;
		long salesTotalRoundOffAmount = (new Double(Math.round(salesTotalAmountGST))).longValue();
		
			salesVO.setSalesGST14(GST14);
			salesVO.setSalesGST9(GST9);
			salesVO.setSalesTotalRoundOffAmount(salesTotalRoundOffAmount); 
			salesVO.setSalesTotalAmount(salesTotalAmount);
			salesVO.setSalesTotalAmountGST(salesTotalAmountGST);
			
			salesDAO.insertbill2(salesVO);
			
			response.sendRedirect("sales.jsp");
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		HttpSession session = request.getSession();
		Integer salesInvoiceNo = Integer.parseInt((String)session.getAttribute("ivn"));
		
		salesDAO salesDAO = new salesDAO();
		salesVO salesVO = new salesVO();
		salesVO.setSalesInvoiceNo(salesInvoiceNo);
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(salesDAO.edit(salesVO)));
		out.flush();
		out.close();
	}
	
	private void edit2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException{
		
		HttpSession session = request.getSession();
		String salesInvoiceNo = (String)session.getAttribute("ivn");
		
		customerVO customerVO = new customerVO();
		customerVO.setCustomerSalesInvoiceNo(salesInvoiceNo);
		customerDAO customerDAO = new customerDAO();
		
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(customerDAO.getSalesEditDetail(customerVO)));
		out.flush();
		out.close();
	}
	
	private void deleteBill(HttpServletRequest request, HttpServletResponse response, String invoiceNo) throws Exception{
		
		customerVO customerVO = new customerVO();
		customerVO.setCustomerSalesInvoiceNo(invoiceNo);
		customerDAO customerDAO = new customerDAO();
		customerDAO.deleteBill(customerVO);
	}
}