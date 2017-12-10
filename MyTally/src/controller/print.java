package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/print")
public class print extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ch = request.getParameter("ch");
		String ns = request.getParameter("n");
			Integer n = new Integer(ns);
		
		switch(ch){
		
		case "sale" : sale(n);break;
		case "purchase" : purchase(n);break;
		default : System.out.println("*** DEFALUT *** :: print");
		
		}
	}
	
	private void sale(int n){
		
	}
	
	private void purchase(int n){
		
	}

}
