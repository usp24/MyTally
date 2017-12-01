package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
									catch (myException | SQLException e) {
										System.out.println("purchase :: doPost :: myException :: "+((myException) e).purchaseInvoiceWorng());
										response.sendRedirect("purchase/purchasebill.jsp");
									}
									break;
			
			case "purchasebillbefore" : 
										try {
												purchasebillbefore(request, response);
										} catch (ClassNotFoundException e) {
												e.printStackTrace();
										} catch (SQLException e) {
											e.printStackTrace();
										}
										break;
		
			default : System.out.println("*** Default Case *** :: purchse.java");
					  response.sendRedirect("other/menu.jsp");break;
		}
	}

	void purchasebill(HttpServletRequest request, HttpServletResponse response) throws IOException, myException, SQLException{
		
		purchaseVO purchaseVO = new purchaseVO();
		supplierVO supplierVO = new supplierVO();
		purchaseDAO purchaseDAO = new purchaseDAO();
		supplierDAO supplierDAO = new supplierDAO();
		
		String purchaseInvoiceNos = (String)request.getParameter("purchaseInvoiceNumber");
			Integer purchaseInvoiceNo = new Integer(purchaseInvoiceNos);
		String purchaseInvoiceDate = (String)request.getParameter("purchaseInvoiceDate");
		String supplierName = (String)request.getParameter("supplierName");
		String supplierAddress1 = (String)request.getParameter("supplierAddress1");
		String supplierAddress2 = (String)request.getParameter("supplierAddress2");
		String supplierCity = (String)request.getParameter("supplierCity");
		String supplierStatecode = (String)request.getParameter("supplierStatecode");
		String supplierGSTNo = (String)request.getParameter("supplierGSTNo");
		String purchaseNumOfItemss = (String)request.getParameter("purchaseNumOfItems");
			Integer purchaseNumOfItems = new Integer(purchaseNumOfItemss);
		
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
		purchaseVO.setPurchaseNumOfItems(purchaseNumOfItems);

		purchaseDAO.insertbill(purchaseVO, supplierVO);
		
		response.sendRedirect("purchase/purchaseitem.jsp?s="+supplierVO.getSupplierName()+"&g="+supplierVO.getSupplierGSTNo()+"&i="+purchaseVO.getPurchaseInvoiceNo()+"&n="+purchaseVO.getPurchaseNumOfItems()+"");
	}
	
	void purchasebillbefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		supplierDAO supplierDAO = new supplierDAO();
		List<supplierVO> list = supplierDAO.select();
		
		purchaseDAO purchaseDAO = new purchaseDAO();
		int max = purchaseDAO.maxInvoiceNo();
		
		HttpSession session = request.getSession();
		session.setAttribute("slist", list);
		session.setAttribute("maxp",max);
		
		response.sendRedirect("purchase/purchasebill.jsp");
	}
}
