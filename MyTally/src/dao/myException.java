package dao;

public class myException extends Throwable{

	public String usernameInValid(){
		return "Username Allready Taken, Please Choose Other !!!";
	}
	public String loginFail(){
		return "Login Fail ,Please Try Again !!!";
	}
	public String purchaseInvoiceWorng(){
		return "InvoiceNumber is already used !!!";
	}
	public String salesInvoiceWorng(){
		return "InvoiceNumber is already used !!!";
	}
}
