package vo;

import java.text.DecimalFormat;

public class itemVO {
	
	private int id;
	
	private String itemName;
	private String itemSrNo;
	private String itemDescription;
	private String itemHSN;
	private int itemGST;
	private double itemPurchasePrice;
	private double itemSalesPrice;
	private int itemQty;
	private String extra;
	private Double extrad;

	private double set(double d){
		if(d!=0){
			DecimalFormat df = new DecimalFormat("#.00");
			return Double.parseDouble(df.format(d));
		}
		else
			return 0.00;
	}
	
	public Double getExtrad() {
		return extrad;
	}

	public void setExtrad(Double extrad) {
		this.extrad = extrad;
	}

	public double getItemPurchasePrice() {
		return set(itemPurchasePrice);
	}
	public void setItemPurchasePrice(double itemPurchasePrice) {
		this.itemPurchasePrice = set(itemPurchasePrice);
	}
	public double getItemSalesPrice() {
		return set(itemSalesPrice);
	}
	public void setItemSalesPrice(double itemSalesPrice) {
		this.itemSalesPrice = set(itemSalesPrice);
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName.toUpperCase();
	}
	public void setItemName(String itemName) {
		this.itemName = itemName.toUpperCase();
	}
	public String getItemSrNo() {
		return itemSrNo;
	}
	public void setItemSrNo(String itemSrNo) {
		this.itemSrNo = itemSrNo;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemHSN() {
		return itemHSN;
	}
	public void setItemHSN(String itemHSN) {
		this.itemHSN = itemHSN;
	}
	public int getItemGST() {
		return itemGST;
	}
	public void setItemGST(int itemGST) {
		this.itemGST = itemGST;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
}
