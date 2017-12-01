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
									catch (myException | SQLException e) {
										System.out.println("sales :: doPost :: myException :: "+((myException) e).salesInvoiceWorng());
										response.sendRedirect("sales/salesbill.jsp");
									}
									break;

			case "salesbillbefore" : try {
				salesbillbefore(request,response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}break;
			default : System.out.println("*** Default Case *** :: sales.java");
					  response.sendRedirect("other/menu.jsp");break;
		}
	}

	void salesbill(HttpServletRequest request, HttpServletResponse response) throws IOException, myException, SQLException{
		
		salesVO salesVO = new salesVO();
		customerVO customerVO = new customerVO();
		salesDAO salesDAO = new salesDAO();
		customerDAO customerDAO = new customerDAO();
		
		String salesInvoiceNos = (String)request.getParameter("salesInvoiceNumber");
			Integer salesInvoiceNo = new Integer(salesInvoiceNos);
		String salesInvoiceDate = (String)request.getParameter("salesInvoiceDate");
		String customerName = (String)request.getParameter("customerName");
		String customerAddress1 = (String)request.getParameter("customerAddress1");
		String customerAddress2 = (String)request.getParameter("customerAddress2");
		String customerCity = (String)request.getParameter("customerCity");
		String customerStatecode = (String)request.getParameter("customerStatecode");
		String customerGSTNo = (String)request.getParameter("customerGSTNo");
		String salesNumOfItemss = (String)request.getParameter("salesNumOfItems");
			Integer salesNumOfItems = new Integer(salesNumOfItemss);
		
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
		salesVO.setSalesNumOfItems(salesNumOfItems);
	
		salesDAO.insertbill(salesVO, customerVO);
		
		response.sendRedirect("sales/salesitem.jsp?s="+customerVO.getCustomerName()+"&g="+customerVO.getCustomerGSTNo()+"&i="+salesVO.getSalesInvoiceNo()+"&n="+salesVO.getSalesNumOfItems()+"");
	}

	void salesbillbefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		
		customerDAO customerDAO = new customerDAO();
		List<customerVO> list = customerDAO.select();
		
		salesDAO salesDAO = new salesDAO();
		int max = salesDAO.maxInvoiceNo();
		
		HttpSession session = request.getSession();
		session.setAttribute("clist", list);
		session.setAttribute("maxs",max);
		
		response.sendRedirect("sales/salesbill.jsp");
	}
}
