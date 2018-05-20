package vo;

import java.text.DecimalFormat;

public class salesVO {

	private int id;
	private String salesInvoiceNo;
	private String salesInvoiceDate;
	private int salesNumOfItems;
	private double salesTotalAmount;
	private double salesGST9;
	private double salesGST14;
	private String salesIGST;
	private double salesTotalAmountGST;
	private long salesTotalRoundOffAmount;
	private String extra;
	private String extra2;
	private double salesItemDiscount;
	private double salesItemTotalAmount;
	private String vehical;
	private String vehicalNo;
	private String paymentMode;
	private String challanNumber;
	
	
	public String getSalesIGST() {
		return salesIGST;
	}

	public void setSalesIGST(String salesIGST) {
		this.salesIGST = salesIGST;
	}

	public String getVehical() {
		return vehical;
	}

	public void setVehical(String vehical) {
		this.vehical = vehical;
	}

	public String getVehicalNo() {
		return vehicalNo;
	}

	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}

	private Double set(double d){
		if(d!=0){
			DecimalFormat df = new DecimalFormat("#.00");
			return Double.parseDouble(df.format(d));
		}
		else
			return 0.00;
	}
	
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getChallanNumber() {
		return challanNumber;
	}

	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}

	public String getExtra() {
		return extra;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	public double getSalesItemDiscount() {
		return set(salesItemDiscount);
	}
	public void setSalesItemDiscount(double salesItemDiscount) {
		this.salesItemDiscount = set(salesItemDiscount);
	}
	public double getSalesItemTotalAmount() {
		return set(salesItemTotalAmount);
	}
	public void setSalesItemTotalAmount(double salesItemTotalAmount) {
		this.salesItemTotalAmount = set(salesItemTotalAmount);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSalesInvoiceNo() {
		return salesInvoiceNo;
	}
	public void setSalesInvoiceNo(String salesInvoiceNo) {
		this.salesInvoiceNo = salesInvoiceNo;
	}
	public String getSalesInvoiceDate() {
		return salesInvoiceDate;
	}
	public void setSalesInvoiceDate(String salesInvoiceDate) {
		this.salesInvoiceDate = salesInvoiceDate;
	}
	public int getSalesNumOfItems() {
		return salesNumOfItems;
	}
	public void setSalesNumOfItems(int salesNumOfItems) {
		this.salesNumOfItems = salesNumOfItems;
	}
	public double getSalesTotalAmount() {
		return set(salesTotalAmount);
	}
	public void setSalesTotalAmount(double salesTotalAmount) {
		this.salesTotalAmount = set(salesTotalAmount);
	}
	public double getSalesGST9() {
		return set(salesGST9);
	}
	public void setSalesGST9(double salesGST9) {
		this.salesGST9 = set(salesGST9);
	}
	public double getSalesGST14() {
		return set(salesGST14);
	}
	public void setSalesGST14(double salesGST14) {
		this.salesGST14 = set(salesGST14);
	}
	public double getSalesTotalAmountGST() {
		return set(salesTotalAmountGST);
	}
	public void setSalesTotalAmountGST(double salesTotalAmountGST) {
		this.salesTotalAmountGST = set(salesTotalAmountGST);
	}
	public long getSalesTotalRoundOffAmount() {
		return salesTotalRoundOffAmount;
	}
	public void setSalesTotalRoundOffAmount(long salesTotalRoundOffAmount) {
		this.salesTotalRoundOffAmount = salesTotalRoundOffAmount;
	}

}
