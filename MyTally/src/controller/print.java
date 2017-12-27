package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.printDAO;
import vo.purchaseVO;
import vo.salesVO;

@WebServlet("/print")
public class print extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = request.getParameter("ch");
		switch(ch){
		
		case "sales" : saleBill(request,response);break;
		case "purchase" : purchase(request,response);break;
		default : System.out.println("*** DEFALUT *** :: print");
		
		}
	}
	
	private void saleBill(HttpServletRequest request, HttpServletResponse response){
	
		HttpSession session = request.getSession();
		Integer iv = Integer.parseInt((String)request.getParameter("printInvoiceNo"));
		
		salesVO salesVO = new salesVO();
		salesVO.setSalesInvoiceNo(iv);
	
		printDAO printDAO = new printDAO();
		try{
			session.setAttribute("bill",printDAO.getSalesBill(salesVO));
			session.setAttribute("item", printDAO.getSalesItem(salesVO));
			session.setAttribute("customer",printDAO.getCustomer(salesVO));
			int n = printDAO.counts(salesVO);
			if(n==0)
				response.sendRedirect("salesPrint.jsp");
			else
				response.sendRedirect("salesPrint2.jsp?num="+n);
		}
		catch(Exception e){
			System.out.println("print.java :: salesbill :: "+e);
		}
	}
	
	private void purchase(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		String iv = (String)request.getParameter("printInvoiceNo");
		
		purchaseVO purchaseVO = new purchaseVO();
		purchaseVO.setPurchaseInvoiceNo(iv);
		
		printDAO printDAO = new printDAO();
		try{
			session.setAttribute("bill",printDAO.getPurchaseBill(purchaseVO));
			session.setAttribute("item", printDAO.getPurchaseItem(purchaseVO));
			session.setAttribute("supplier",printDAO.getSupplier(purchaseVO));
			int n = printDAO.countp(purchaseVO);
			if(n==0)
				response.sendRedirect("purchasePrint.jsp");
			else
				response.sendRedirect("purchasePrint2.jsp?num="+n);
		}
		catch(Exception e){
			System.out.println("print.java :: purchasebill :: "+e);
		}
	}
	
	
}
