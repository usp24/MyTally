package vo;

public class salesVO {

	private int id;
	private int salesInvoiceNo;
	private String salesInvoiceDate;
	private double salesDiscount;
	private double salesTotalAmount;
	private double salesTotalRoundOffAmount;
	
	private String salesItemName;
	private String salesItemDescription;
	private String salesItemGST;
	private String salesItemPrice;
	private String salesItemQty;
	private String salesItemHSN;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalesInvoiceNo() {
		return salesInvoiceNo;
	}
	public void setSalesInvoiceNo(int salesInvoiceNo) {
		this.salesInvoiceNo = salesInvoiceNo;
	}
	public String getSalesInvoiceDate() {
		return salesInvoiceDate;
	}
	public void setSalesInvoiceDate(String salesInvoiceDate) {
		this.salesInvoiceDate = salesInvoiceDate;
	}
	public double getSalesDiscount() {
		return salesDiscount;
	}
	public void setSalesDiscount(double salesDiscount) {
		this.salesDiscount = salesDiscount;
	}
	public double getSalesTotalAmount() {
		return salesTotalAmount;
	}
	public void setSalesTotalAmount(double salesTotalAmount) {
		this.salesTotalAmount = salesTotalAmount;
	}
	public double getSalesTotalRoundOffAmount() {
		return salesTotalRoundOffAmount;
	}
	public void setSalesTotalRoundOffAmount(double salesTotalRoundOffAmount) {
		this.salesTotalRoundOffAmount = salesTotalRoundOffAmount;
	}
	public String getSalesItemName() {
		return salesItemName;
	}
	public void setSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
	}
	public String getSalesItemDescription() {
		return salesItemDescription;
	}
	public void setSalesItemDescription(String salesItemDescription) {
		this.salesItemDescription = salesItemDescription;
	}
	public String getSalesItemGST() {
		return salesItemGST;
	}
	public void setSalesItemGST(String salesItemGST) {
		this.salesItemGST = salesItemGST;
	}
	public String getSalesItemPrice() {
		return salesItemPrice;
	}
	public void setSalesItemPrice(String salesItemPrice) {
		this.salesItemPrice = salesItemPrice;
	}
	public String getSalesItemQty() {
		return salesItemQty;
	}
	public void setSalesItemQty(String salesItemQty) {
		this.salesItemQty = salesItemQty;
	}
	public String getSalesItemHSN() {
		return salesItemHSN;
	}
	public void setSalesItemHSN(String salesItemHSN) {
		this.salesItemHSN = salesItemHSN;
	}
}
