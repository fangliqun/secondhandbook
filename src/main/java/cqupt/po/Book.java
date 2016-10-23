package cqupt.po;

public class Book {
	private int bookid;
	private String bookname;
	private String editor;
	private String department;
	private String bookage;
	private String category;
	private String money;
	private String state;
	private int  uploaduserid;
	public int getUploaduserid() {
		return uploaduserid;
	}
	public void setUploaduserid(int uploaduserid) {
		this.uploaduserid = uploaduserid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getBookage() {
		return bookage;
	}
	public void setBookage(String bookage) {
		this.bookage = bookage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
