package vo;

public class itemVO {

	private int id;
	
	private String ItemName;
	private String ItemSrNo;
	private String ItemDiscription;
	private String ItemHSN;
	private int ItemGST;
	
	private int purchaseItemQty;
	private double purchaseItemUnitPrice;
	private double purchaseItemDiscount;
	private double purchaseItemAmount;
	
	private int salesItemQty;
	private double salesItemUnitPrice;
	private double salesItemDiscount;
	private double salesItemAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getItemSrNo() {
		return ItemSrNo;
	}
	public void setItemSrNo(String itemSrNo) {
		ItemSrNo = itemSrNo;
	}
	public String getItemDiscription() {
		return ItemDiscription;
	}
	public void setItemDiscription(String itemDiscription) {
		ItemDiscription = itemDiscription;
	}
	public String getItemHSN() {
		return ItemHSN;
	}
	public void setItemHSN(String itemHSN) {
		ItemHSN = itemHSN;
	}
	public int getItemGST() {
		return ItemGST;
	}
	public void setItemGST(int itemGST) {
		ItemGST = itemGST;
	}
	public int getPurchaseItemQty() {
		return purchaseItemQty;
	}
	public void setPurchaseItemQty(int purchaseItemQty) {
		this.purchaseItemQty = purchaseItemQty;
	}
	public double getPurchaseItemUnitPrice() {
		return purchaseItemUnitPrice;
	}
	public void setPurchaseItemUnitPrice(double purchaseItemUnitPrice) {
		this.purchaseItemUnitPrice = purchaseItemUnitPrice;
	}
	public double getPurchaseItemDiscount() {
		return purchaseItemDiscount;
	}
	public void setPurchaseItemDiscount(double purchaseItemDiscount) {
		this.purchaseItemDiscount = purchaseItemDiscount;
	}
	public double getPurchaseItemAmount() {
		return purchaseItemAmount;
	}
	public void setPurchaseItemAmount(double purchaseItemAmount) {
		this.purchaseItemAmount = purchaseItemAmount;
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
}
