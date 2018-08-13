package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.paymentDAO;
import vo.paymentVO;

@WebServlet("/payment")
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String ch = request.getParameter("ch");
		switch(ch){
			case "directSales" : directSales(request,response);  
			default : System.out.println("*** DEFAULT CASE :: Payment servlet ***");
		}
	}
	
	private void directSales(HttpServletRequest request, HttpServletResponse response) {
		
		paymentVO paymentVO = new paymentVO();
		
		String invoiceNo = request.getParameter("no");
		String invoiceDate = request.getParameter("date");
		Double invoiceAmt = Double.parseDouble(request.getParameter("amt"));
		String customerName = request.getParameter("name");
		String paymentMode = request.getParameter("mode");
		String other = request.getParameter("other");
		Double payment = Double.parseDouble(request.getParameter("amount"));
		Double discount = Double.parseDouble(request.getParameter("discount"));
		String paymentDate = request.getParameter("paymentDate");
		
		String bankName = request.getParameter("bankName");
		String branch = request.getParameter("branchName");
		String chequeNo = request.getParameter("chequeNo");
		String chequeDate = request.getParameter("chequeDate");
		
		if(paymentMode.equals("online")){
			String tid = request.getParameter("tid");
			paymentVO.setChequeNo(tid);
		}
		
		paymentVO.setInvoiceNo(invoiceNo);
		paymentVO.setInvoiceDate(invoiceDate);
		paymentVO.setInvoiceAmt(invoiceAmt);
		paymentVO.setCustomerName(customerName);
		paymentVO.setPaymentMode(paymentMode);
		paymentVO.setOther(other);
		paymentVO.setPayment(payment);
		paymentVO.setDiscount(discount);
		paymentVO.setPaymentDate(paymentDate);
		paymentVO.setBankName(bankName);
		paymentVO.setBranch(branch);
		paymentVO.setChequeNo(chequeNo);
		paymentVO.setChequeDate(chequeDate);
		
		paymentDAO paymentDAO = new paymentDAO();
		//paymentDAO.insertAll(paymentVO); 
	}

}
