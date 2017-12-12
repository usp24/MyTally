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

import dao.itemDAO;
import vo.itemVO;

@WebServlet("/item")
public class item extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = request.getParameter("ch");
		
		switch(ch){
		
		case "itemEntryBefore" : try{
										itemEntryBefore(request,response);
									} catch (ClassNotFoundException | SQLException e) {
										System.out.println("item.java :: itemEntryBefore :: "+e);
									}break;
									
		case "itemEntry" : try {
				itemEntry(request,response);
				response.sendRedirect("menu.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}break;
	
		case "itemDetail" : try {
			itemDetail(request,response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}break;	
		
		default : System.out.println("*** DEFAULT CASE *** :: item.java");break;
		
		}
	}

	void itemEntryBefore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getItemNames()));
	}
	
	void itemDetail(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getItemDetail()));
	}

	void itemEntry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
		
		itemVO itemVO = new itemVO();
		itemDAO itemDAO = new itemDAO();
		
		String itemName = (String)request.getParameter("itemName").toUpperCase();
		String itemDescription = (String)request.getParameter("itemDescription");
		String itemHSN = (String)request.getParameter("itemHSN");
		
		Integer itemGST;
		if(request.getParameter("itemGST").equals(""))
			itemGST = 0;
		else
			itemGST = Integer.parseInt(request.getParameter("itemGST"));
		
		Double itemPurchasePrice;
		if(request.getParameter("itemPurchasePrice").equals(""))
			itemPurchasePrice = 0.0;
		else
			itemPurchasePrice = Double.parseDouble(request.getParameter("itemPurchasePrice"));
		
		Double itemSalesPrice;
		if(request.getParameter("itemSalesPrice").equals(""))
			itemSalesPrice = 0.0;
		else
			itemSalesPrice = Double.parseDouble(request.getParameter("itemSalesPrice"));
	
			itemVO.setItemName(itemName);
			itemVO.setItemDescription(itemDescription);
			itemVO.setItemHSN(itemHSN);
			itemVO.setItemGST(itemGST);
			itemVO.setItemPurchasePrice(itemPurchasePrice);
			itemVO.setItemSalesPrice(itemSalesPrice);
			
		itemDAO.insertItem(itemVO);
		
	}
}