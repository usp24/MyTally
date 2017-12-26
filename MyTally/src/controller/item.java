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

import dao.customerDAO;
import dao.itemDAO;
import dao.salesDAO;
import vo.customerVO;
import vo.itemVO;
import vo.purchaseVO;
import vo.salesVO;

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
									response.sendRedirect("item.jsp");
								} catch (SQLException e) {
									e.printStackTrace();
								}break;
						
		case "itemDetail" : try {
			itemDetail(request,response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}break;
		
		case "itemDetailSale" : try {
			itemDetailSale(request,response);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}break;
		
		case "itemStock" : try {
			itemStock(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		
		case "stockPurchase" : try {
			stockPurchase(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		
		case "stockSales" : try {
			stockSales(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		
		case "itemView" : try {
			itemView(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		
		case "edit" : try {
				edit(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}break;
	
		case "delete" : try {
			delete(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		
		case "editItem" : try {
			editItem(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}break;
		default : System.out.println("*** DEFAULT CASE *** :: item.java");break;
		
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		String itemName = request.getParameter("n");
		itemVO itemVO = new itemVO();
		itemDAO itemDAO = new itemDAO();
		itemVO.setItemName(itemName);
		boolean result = itemDAO.delete(itemVO);
		if(result==true)
			response.sendRedirect("item.jsp?dr=t");
		else
			response.sendRedirect("item.jsp?dr=f");
	}

	private void editItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		itemVO itemVO = new itemVO();
		itemDAO itemDAO = new itemDAO();
		
		String itemName = (String)request.getParameter("itemName").toUpperCase();
		String itemDescription = (String)request.getParameter("itemDescription");
		String itemHSN = (String)request.getParameter("itemHSN");
		String on = request.getParameter("on");
		
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
			
		itemDAO.updateItem(itemVO,on);
		response.sendRedirect("item.jsp");
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String itemName = request.getParameter("n");
		itemVO itemVO = new itemVO();
		itemVO.setItemName(itemName);
		itemDAO itemDAO = new itemDAO();
		List<itemVO> l = itemDAO.editDetail(itemVO);
		itemVO = l.get(0);
		response.sendRedirect("editItem.jsp?n="+itemName+"&name="+itemVO.getItemName()+"&d="+itemVO.getItemDescription()+"&h="+itemVO.getItemHSN()+"&g="+itemVO.getItemGST()+"&p="+itemVO.getItemPurchasePrice()+"&s="+itemVO.getItemSalesPrice());
	}

	private void itemView(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.viewAllItem()));
	}

	private void stockPurchase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String itemName = request.getParameter("name");
		itemVO itemVO = new itemVO();
		itemVO.setItemName(itemName);
		itemDAO itemDAO = new itemDAO();
				/*List<purchaseVO> l = itemDAO.getstockPurchaseBill(itemVO);
				System.out.println(l.size()+"/////**\\\\");
				for(int k=0;k<l.size();k++)
		            System.out.println(l.get(k).getPurchaseInvoiceNo()+"..............");*/
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getstockPurchaseBill(itemVO)));
	}
	
	private void stockSales(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String itemName = request.getParameter("name");
		itemVO itemVO = new itemVO();
		itemVO.setItemName(itemName);
		itemDAO itemDAO = new itemDAO();
			/*	List<salesVO> l = itemDAO.getstockSalesBill(itemVO);
				System.out.println(l.size()+"/////**\\\\");
				for(int k=0;k<l.size();k++)
		            System.out.println(l.get(k).getSalesInvoiceNo()+"..............");*/
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getstockSalesBill(itemVO)));
	}

	private void itemStock(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getStock()));
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
	
	void itemDetailSale(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
		
		itemDAO itemDAO = new itemDAO();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(itemDAO.getItemDetailSale()));
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