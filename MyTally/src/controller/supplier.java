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

import dao.supplierDAO;
import vo.supplierVO;

@WebServlet("/supplier")
public class supplier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ch = (String)request.getParameter("ch");
		switch(ch){
			
		case "supplierEntryBefore" : try {
				supplierEntryBefore(request,response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		case "supplierView" :  try {
			supplierEntryBefore(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}break;
		case "newSupplier" : try {
				newSupplier(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		default : System.out.println("*** DEFAULT CASE ***");
		}
	}

	private void newSupplier(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		supplierDAO supplierDAO = new supplierDAO();
		supplierVO supplierVO = new supplierVO();
		
		supplierVO.setSupplierName(request.getParameter("supplierName"));
		supplierVO.setSupplierAddress1(request.getParameter("supplierAddress1"));
		supplierVO.setSupplierAddress2(request.getParameter("supplierAddress2"));
		supplierVO.setSupplierCity(request.getParameter("supplierCity"));
		supplierVO.setSupplierStatecode(request.getParameter("supplierStatecode"));
		supplierVO.setSupplierMobileNo(request.getParameter("supplierMobileNo"));
		supplierVO.setSupplierGSTNo(request.getParameter("supplierGSTNo"));
		supplierVO.setSupplierEmail("supplierEmail");
		supplierDAO.insertDirect(supplierVO);
		
		response.sendRedirect("supplier.jsp");
	}
	
	private void supplierEntryBefore(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		
		supplierDAO supplierDAO = new supplierDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(supplierDAO.supplierEntryBefore()));
		out.flush();
		out.close();
	}
}
