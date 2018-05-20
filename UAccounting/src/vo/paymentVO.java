package vo;

import java.text.DecimalFormat;

public class paymentVO {

	private int id;
	private String invoiceNo;
	private String invoiceDate;
	private double invoiceAmt; 
	private String supplierName;
	private String customerName;
	private String paymentMode;
	private String other;
	private double payment;
	private double discount;
	private String paymentDate;
	private String bankName;
	private String branch;
	private String chequeNo;
	private String chequeDate;
	private String tid;
	
	private Double set(double d){
		if(d!=0){
			DecimalFormat df = new DecimalFormat("#.00");
			return Double.parseDouble(df.format(d));
		}
		else
			return 0.00;
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public double getInvoiceAmt() {
		return set(invoiceAmt);
	}
	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = set(invoiceAmt);
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public double getPayment() {
		return set(payment);
	}
	public void setPayment(double payment) {
		this.payment = set(payment);
	}
	public double getDiscount() {
		return set(discount);
	}
	public void setDiscount(double discount) {
		this.discount = set(discount);
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
}
