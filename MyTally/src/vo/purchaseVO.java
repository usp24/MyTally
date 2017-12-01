package vo;

public class purchaseVO {
	
	private int id;
	private int purchaseInvoiceNo;
	private String purchaseInvoiceDate;
	private int purchaseNumOfItems;
	private double purchaseTotalAmount;
	private double purchaseGST9;
	private double purchaseGST14;
	private double purchaseTotalAmountGST;
	private long purchaseTotalRoundOffAmount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseInvoiceNo() {
		return purchaseInvoiceNo;
	}
	public void setPurchaseInvoiceNo(int purchaseInvoiceNo) {
		this.purchaseInvoiceNo = purchaseInvoiceNo;
	}
	public String getPurchaseInvoiceDate() {
		return purchaseInvoiceDate;
	}
	public void setPurchaseInvoiceDate(String purchaseInvoiceDate) {
		this.purchaseInvoiceDate = purchaseInvoiceDate;
	}
	public int getPurchaseNumOfItems() {
		return purchaseNumOfItems;
	}
	public void setPurchaseNumOfItems(int purchaseNumOfItems) {
		this.purchaseNumOfItems = purchaseNumOfItems;
	}
	public double getPurchaseTotalAmount() {
		return purchaseTotalAmount;
	}
	public void setPurchaseTotalAmount(double purchaseTotalAmount) {
		this.purchaseTotalAmount = purchaseTotalAmount;
	}
	public double getPurchaseGST9() {
		return purchaseGST9;
	}
	public void setPurchaseGST9(double purchaseGST9) {
		this.purchaseGST9 = purchaseGST9;
	}
	public double getPurchaseGST14() {
		return purchaseGST14;
	}
	public void setPurchaseGST14(double purchaseGST14) {
		this.purchaseGST14 = purchaseGST14;
	}
	public double getPurchaseTotalAmountGST() {
		return purchaseTotalAmountGST;
	}
	public void setPurchaseTotalAmountGST(double purchaseTotalAmountGST) {
		this.purchaseTotalAmountGST = purchaseTotalAmountGST;
	}
	public long getPurchaseTotalRoundOffAmount() {
		return purchaseTotalRoundOffAmount;
	}
	public void setPurchaseTotalRoundOffAmount(long purchaseTotalRoundOffAmount) {
		this.purchaseTotalRoundOffAmount = purchaseTotalRoundOffAmount;
	}
	
	
}
