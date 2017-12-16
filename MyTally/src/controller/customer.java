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

import dao.customerDAO;
import vo.customerVO;
@WebServlet("/customer")
public class customer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ch = (String)request.getParameter("ch");
		switch(ch){
			
		case "customerEntryBefore" : try {
				customerEntryBefore(request,response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		case "customerView" :  try {
			customerEntryBefore(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}break;
		case "newCustomer" : try {
				newCustomer(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		default : System.out.println("*** DEFAULT CASE ***");
		}
	}

	private void newCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		customerDAO customerDAO = new customerDAO();
		customerVO customerVO = new customerVO();
		
		customerVO.setCustomerName(request.getParameter("customerName"));
		customerVO.setCustomerAddress1(request.getParameter("customerAddress1"));
		customerVO.setCustomerAddress2(request.getParameter("customerAddress2"));
		customerVO.setCustomerCity(request.getParameter("customerCity"));
		customerVO.setCustomerStatecode(request.getParameter("customerStatecode"));
		customerVO.setCustomerMobileNo(request.getParameter("customerMobileNo"));
		customerVO.setCustomerGSTNo(request.getParameter("customerGSTNo"));
		customerVO.setCustomerEmail("customerEmail");
		customerDAO.insertDirect(customerVO);
		
		response.sendRedirect("customer.jsp");
	}
	
	private void customerEntryBefore(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		
		customerDAO customerDAO = new customerDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(customerDAO.customerEntryBefore()));
		out.flush();
		out.close();
	}

}
