package my;

public class myException extends Throwable{
	private static final long serialVersionUID = 1L;
	
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
	public String outOfStock(){
		return "Qty Out Of Stock for Sale!!!";
	}
	public String itemNameInValid(){
		return "Item Already Exists With Same Name !!!";
	}
}
