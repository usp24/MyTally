package vo;

public class userinfoVO {
	
	private int id;
	private String businessName;
	private String username;
	private String password;
	private String email;
	private String lgusername;
	private String lgpassword;
	
	public String getLgusername() {
		return lgusername;
	}
	public void setLgusername(String lgusername) {
		this.lgusername = lgusername;
	}
	public String getLgpassword() {
		return lgpassword;
	}
	public void setLgpassword(String lgpassword) {
		this.lgpassword = lgpassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
