package vo;

public class salesVO {

	private int id;
	private int salesInvoiceNo;
	private String salesInvoiceDate;
	private int salesNumOfItems;
	private double salesTotalAmount;
	private double salesGST9;
	private double salesGST14;
	private double salesTotalAmountGST;
	private long salesTotalRoundOffAmount;
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
	public int getSalesNumOfItems() {
		return salesNumOfItems;
	}
	public void setSalesNumOfItems(int salesNumOfItems) {
		this.salesNumOfItems = salesNumOfItems;
	}
	public double getSalesTotalAmount() {
		return salesTotalAmount;
	}
	public void setSalesTotalAmount(double salesTotalAmount) {
		this.salesTotalAmount = salesTotalAmount;
	}
	public double getSalesGST9() {
		return salesGST9;
	}
	public void setSalesGST9(double salesGST9) {
		this.salesGST9 = salesGST9;
	}
	public double getSalesGST14() {
		return salesGST14;
	}
	public void setSalesGST14(double salesGST14) {
		this.salesGST14 = salesGST14;
	}
	public double getSalesTotalAmountGST() {
		return salesTotalAmountGST;
	}
	public void setSalesTotalAmountGST(double salesTotalAmountGST) {
		this.salesTotalAmountGST = salesTotalAmountGST;
	}
	public long getSalesTotalRoundOffAmount() {
		return salesTotalRoundOffAmount;
	}
	public void setSalesTotalRoundOffAmount(long salesTotalRoundOffAmount) {
		this.salesTotalRoundOffAmount = salesTotalRoundOffAmount;
	}
}
