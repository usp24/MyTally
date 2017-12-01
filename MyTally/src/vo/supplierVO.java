package vo;

public class supplierVO {

		private int id;
		private String supplierName;
		private String supplierAddress1;
		private String supplierAddress2;
		private String supplierCity;
		private String supplierStatecode;
		private String supplierGSTNo;
		private String supplierPurchaseInvoiceNo;
		private String supplierMobileNo;
		private String supplierEmail;
		
		public supplierVO(){}
		public supplierVO(supplierVO ob){
			this.supplierName = ob.supplierName;
			this.supplierAddress1 = ob.supplierAddress1;
			this.supplierAddress2 = ob.supplierAddress2;
			this.supplierCity = ob.supplierCity;
			this.supplierStatecode = ob.supplierStatecode;
			this.supplierGSTNo = ob.supplierGSTNo;
			this.supplierPurchaseInvoiceNo = ob.supplierPurchaseInvoiceNo;
			this.supplierMobileNo = ob.supplierMobileNo;
			this.supplierEmail = ob.supplierEmail;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public String getSupplierAddress1() {
			return supplierAddress1;
		}
		public void setSupplierAddress1(String supplierAddress1) {
			this.supplierAddress1 = supplierAddress1;
		}
		public String getSupplierAddress2() {
			return supplierAddress2;
		}
		public void setSupplierAddress2(String supplierAddress2) {
			this.supplierAddress2 = supplierAddress2;
		}
		public String getSupplierCity() {
			return supplierCity;
		}
		public void setSupplierCity(String supplierCity) {
			this.supplierCity = supplierCity;
		}
		public String getSupplierStatecode() {
			return supplierStatecode;
		}
		public void setSupplierStatecode(String supplierStatecode) {
			this.supplierStatecode = supplierStatecode;
		}
		public String getSupplierGSTNo() {
			return supplierGSTNo;
		}
		public void setSupplierGSTNo(String supplierGSTNo) {
			this.supplierGSTNo = supplierGSTNo;
		}
		public String getSupplierPurchaseInvoiceNo() {
			return supplierPurchaseInvoiceNo;
		}
		public void setSupplierPurchaseInvoiceNo(String supplierPurchaseInvoiceNo) {
			this.supplierPurchaseInvoiceNo = supplierPurchaseInvoiceNo;
		}
		public String getSupplierMobileNo() {
			return supplierMobileNo;
		}
		public void setSupplierMobileNo(String supplierMobileNo) {
			this.supplierMobileNo = supplierMobileNo;
		}
		public String getSupplierEmail() {
			return supplierEmail;
		}
		public void setSupplierEmail(String supplierEmail) {
			this.supplierEmail = supplierEmail;
		}
		
		
}
