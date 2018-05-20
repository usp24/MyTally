package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vo.paymentVO;

public class paymentDAO {

	Connection con;
	Statement st;	
	
	public void insertAll(paymentVO paymentVO) throws SQLException {
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mytally","root","root");
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from payment where invoiceNo="+paymentVO.getInvoiceNo());
			if(!rs.next())
				st.executeUpdate("insert into payment(invoiceNo,invoiceDate,invoiceAmt,supplierName,customerName,paymentMode,other,payment,discount,paymentDate,bankName,branch,chequeNo,chequeDate) values('"+paymentVO.getInvoiceNo()+"','"+paymentVO.getInvoiceDate()+"','"+paymentVO.getInvoiceAmt()+"','"+paymentVO.getSupplierName()+"','"+paymentVO.getCustomerName()+"','"+paymentVO.getPaymentMode()+"','"+paymentVO.getOther()+"','"+paymentVO.getPayment()+"','"+paymentVO.getDiscount()+"','"+paymentVO.getPaymentDate()+"','"+paymentVO.getBankName()+"','"+paymentVO.getBranch()+"','"+paymentVO.getChequeNo()+"','"+paymentVO.getChequeDate()+"')");
		}
		catch(Exception e){
			System.out.println("payDAO :: insertAll :: "+e);
		}
		finally{
			st.close();
			con.close();
		}
		
	}

}
