package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.myException;
import dao.salesDAO;
import dao.customerDAO;
import vo.salesVO;
import vo.customerVO;

@WebServlet("/sales")
public class sales extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = (String)request.getParameter("ch");
		switch(ch){
			case "salesbill" : 
									try{
										salesbill(request,response);
									} 
									catch (myException e) {
										System.out.println("sales :: doPost :: myException :: "+e.salesInvoiceWorng());
										response.sendRedirect("salesbill.jsp");
									}
									break;
			case "salesitem" : salesitem(request,response);break;
			default : System.out.println("*** Default Case *** :: sales.java");
					  response.sendRedirect("menu.jsp");break;
		}
	}

	void salesbill(HttpServletRequest request, HttpServletResponse response) throws IOException, myException{
		
		salesVO salesVO = new salesVO();
		customerVO customerVO = new customerVO();
		salesDAO salesDAO = new salesDAO();
		customerDAO customerDAO = new customerDAO();
		HttpSession session = request.getSession();
		
		String salesInvoiceNos = (String)request.getParameter("salesInvoiceNumber");
			Integer salesInvoiceNo = new Integer(salesInvoiceNos);
		String salesInvoiceDate = (String)request.getParameter("salesInvoiceDate");
		String customerName = (String)request.getParameter("customerName");
		String customerAddress1 = (String)request.getParameter("customerAddress1");
		String customerAddress2 = (String)request.getParameter("customerAddress2");
		String customerGSTNo = (String)request.getParameter("customerGSTNo");
		String salesNumOfItemss = (String)request.getParameter("salesNumOfItems");
			Integer salesNumOfItems = new Integer(salesNumOfItemss);
		
		customerVO.setCustomerName(customerName);
		customerVO.setCustomerAddress1(customerAddress1);
		customerVO.setCustomerAddress2(customerAddress2);
		customerVO.setCustomerGSTNo(customerGSTNo);
		
		salesVO.setSalesInvoiceDate(salesInvoiceDate);
		salesVO.setSalesInvoiceNo(salesInvoiceNo);
		salesVO.setSalesNumOfItems(salesNumOfItems);

		customerDAO.insertSales(customerVO);
		salesDAO.insertbill(salesVO, customerVO);
		
		session.setAttribute("ss",customerVO.getCustomerName());
		session.setAttribute("xs",salesVO.getSalesInvoiceNo());
		session.setAttribute("ns",salesVO.getSalesNumOfItems());
		response.sendRedirect("salesitem.jsp");
	}
	
	void salesitem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		salesVO salesVO = new salesVO();
		customerVO customerVO = new customerVO();
		salesDAO salesDAO = new salesDAO();
		HttpSession session = request.getSession();
		
		String s = (String)session.getAttribute("ss");
		int x = (Integer)session.getAttribute("xs");
		int n = (Integer)session.getAttribute("ns");
		customerVO.setCustomerName(s);
		salesVO.setSalesInvoiceNo(x);
		
		Double salesTotalAmount=0.000;
		Double GST14=0.000;
		Double GST9=0.000;
		
		for(int i=1;i<=n;i++){
			
			String salesItemName = (String)request.getParameter("salesItemName"+i);
			String salesItemDiscription = (String)request.getParameter("salesItemDiscription"+i);
			String salesItemHSN = (String)request.getParameter("salesItemHSN"+i);
			String salesItemGSTs = (String)request.getParameter("salesItemGST"+i);
				Integer salesItemGST = new Integer(salesItemGSTs);
			String salesItemUnitPrices = (String)request.getParameter("salesItemUnitPrice"+i);
				Double salesItemUnitPrice = new Double(salesItemUnitPrices);
			String salesItemQtys = (String)request.getParameter("salesItemQty"+i);
				Integer salesItemQty = new Integer(salesItemQtys);
			String salesItemAmounts = (String)request.getParameter("salesItemAmount"+i);
				Double salesItemAmount = new Double(salesItemAmounts);
			String salesItemDiscounts = (String)request.getParameter("salesItemDiscount"+i);
				Double salesItemDiscount = Double.parseDouble(salesItemDiscounts);
				
			salesVO.setSalesItemDiscount(salesItemDiscount);	
			salesVO.setSalesItemName(salesItemName);
			salesVO.setSalesItemDiscription(salesItemDiscription);
			salesVO.setSalesItemHSN(salesItemHSN);
			salesVO.setSalesItemGST(salesItemGST);
			salesVO.setSalesItemUnitPrice(salesItemUnitPrice);
			salesVO.setSalesItemQty(salesItemQty);
			
			salesItemAmount -= (salesItemDiscount/100)*salesItemAmount;
			salesVO.setSalesItemAmount(salesItemAmount);
			salesTotalAmount += salesItemAmount;
			
			if(salesItemGST.intValue()==18)
				GST9 += salesItemAmount*0.09;
			else if(salesItemGST.intValue()==28)
				GST14 += salesItemAmount*0.14;
			
			salesDAO.insertitem(salesVO, customerVO);
		}
		
		salesVO.setSalesGST14(GST14);
		salesVO.setSalesGST9(GST9);
		salesTotalAmount += GST14 + GST9;
		long salesTotalRoundOffAmount = (new Double(Math.round(salesTotalAmount))).longValue();
		salesVO.setSalesTotalRoundOffAmount(salesTotalRoundOffAmount);
		salesVO.setSalesTotalAmount(salesTotalAmount);
		salesDAO.insertbill2(salesVO, customerVO);
		
		response.sendRedirect("menu.jsp");
	}

}
