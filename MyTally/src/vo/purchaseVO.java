package vo;

import java.text.DecimalFormat;
import java.util.Date;

public class purchaseVO {
	
	private int id;
	private String purchaseInvoiceNo;
	private String purchaseInvoiceDate;
	private int purchaseNumOfItems;
	private double purchaseTotalAmount;
	private double purchaseGST9;
	private double purchaseGST14;
	private double purchaseTotalAmountGST;
	private long purchaseTotalRoundOffAmount;
	private String extra;
	private double purchaseItemDiscount;
	private double purchaseItemTotalAmount;
	
	
	private Double set(double d){
		if(d!=0){
			DecimalFormat df = new DecimalFormat("#.00");
			return Double.parseDouble(df.format(d));
		}
		else
			return 0.00;
	}
	
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	public double getPurchaseItemDiscount() {
		return set(purchaseItemDiscount);
	}
	public void setPurchaseItemDiscount(double purchaseItemDiscount) {
		this.purchaseItemDiscount = set(purchaseItemDiscount);
	}
	public double getPurchaseItemTotalAmount() {
		return set(purchaseItemTotalAmount);
	}
	public void setPurchaseItemTotalAmount(double purchaseItemTotalAmount) {
		this.purchaseItemTotalAmount = set(purchaseItemTotalAmount);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPurchaseInvoiceNo() {
		return purchaseInvoiceNo;
	}
	public void setPurchaseInvoiceNo(String purchaseInvoiceNo) {
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
		return set(purchaseTotalAmount);
	}
	public void setPurchaseTotalAmount(double purchaseTotalAmount) {
		this.purchaseTotalAmount = set(purchaseTotalAmount);
	}
	public double getPurchaseGST9() {
		return set(purchaseGST9);
	}
	public void setPurchaseGST9(double purchaseGST9) {
		this.purchaseGST9 = set(purchaseGST9);
	}
	public double getPurchaseGST14() {
		return set(purchaseGST14);
	}
	public void setPurchaseGST14(double purchaseGST14) {
		this.purchaseGST14 = set(purchaseGST14);
	}
	public double getPurchaseTotalAmountGST() {
		return set(purchaseTotalAmountGST);
	}
	public void setPurchaseTotalAmountGST(double purchaseTotalAmountGST) {
		this.purchaseTotalAmountGST = set(purchaseTotalAmountGST);
	}
	public long getPurchaseTotalRoundOffAmount() {
		return purchaseTotalRoundOffAmount;
	}
	public void setPurchaseTotalRoundOffAmount(long purchaseTotalRoundOffAmount) {
		this.purchaseTotalRoundOffAmount = purchaseTotalRoundOffAmount;
	}
}
