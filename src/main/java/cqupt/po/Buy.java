package cqupt.po;
//购物车
public class Buy {
	private int buyid;
	private int bookid;//购买的书
	private int userid;//购买者
	public int getBuyid() {
		return buyid;
	}
	public void setBuyid(int buyid) {
		this.buyid = buyid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	
}
