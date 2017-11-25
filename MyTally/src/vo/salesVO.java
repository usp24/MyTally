package vo;

public class salesVO {

	private int id;
	private int salesInvoiceNo;
	private String salesInvoiceDate;
	private double salesTotalAmount;
	private long salesTotalRoundOffAmount;
	private int salesNumOfItems;
	private double salesGST14;
	private double salesGST9;
	
	private String salesItemName;
	private String salesItemDiscription;
	private String salesItemHSN;
	private int salesItemGST;
	private int salesItemQty;
	private double salesItemUnitPrice;
	private double salesItemDiscount;
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
	public double getSalesTotalAmount() {
		return salesTotalAmount;
	}
	public void setSalesTotalAmount(double salesTotalAmount) {
		this.salesTotalAmount = salesTotalAmount;
	}
	public long getSalesTotalRoundOffAmount() {
		return salesTotalRoundOffAmount;
	}
	public void setSalesTotalRoundOffAmount(long salesTotalRoundOffAmount) {
		this.salesTotalRoundOffAmount = salesTotalRoundOffAmount;
	}
	public int getSalesNumOfItems() {
		return salesNumOfItems;
	}
	public void setSalesNumOfItems(int salesNumOfItems) {
		this.salesNumOfItems = salesNumOfItems;
	}
	public double getSalesGST14() {
		return salesGST14;
	}
	public void setSalesGST14(double salesGST14) {
		this.salesGST14 = salesGST14;
	}
	public double getSalesGST9() {
		return salesGST9;
	}
	public void setSalesGST9(double salesGST9) {
		this.salesGST9 = salesGST9;
	}
	public String getSalesItemName() {
		return salesItemName;
	}
	public void setSalesItemName(String salesItemName) {
		this.salesItemName = salesItemName;
	}
	public String getSalesItemDiscription() {
		return salesItemDiscription;
	}
	public void setSalesItemDiscription(String salesItemDiscription) {
		this.salesItemDiscription = salesItemDiscription;
	}
	public String getSalesItemHSN() {
		return salesItemHSN;
	}
	public void setSalesItemHSN(String salesItemHSN) {
		this.salesItemHSN = salesItemHSN;
	}
	public int getSalesItemGST() {
		return salesItemGST;
	}
	public void setSalesItemGST(int salesItemGST) {
		this.salesItemGST = salesItemGST;
	}
	public int getSalesItemQty() {
		return salesItemQty;
	}
	public void setSalesItemQty(int salesItemQty) {
		this.salesItemQty = salesItemQty;
	}
	public double getSalesItemUnitPrice() {
		return salesItemUnitPrice;
	}
	public void setSalesItemUnitPrice(double salesItemUnitPrice) {
		this.salesItemUnitPrice = salesItemUnitPrice;
	}
	public double getSalesItemDiscount() {
		return salesItemDiscount;
	}
	public void setSalesItemDiscount(double salesItemDiscount) {
		this.salesItemDiscount = salesItemDiscount;
	}
	public double getSalesItemAmount() {
		return salesItemAmount;
	}
	public void setSalesItemAmount(double salesItemAmount) {
		this.salesItemAmount = salesItemAmount;
	}
	private double salesItemAmount;
}
