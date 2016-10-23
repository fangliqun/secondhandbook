package cqupt.po;
//订单
public class Sale {
	private int saleid;
	private int buyuserid;//买家
	private int saleuserid;//卖家
	private int bookid;//买的那本书
	private int paystate;//1代表支付，0代表未支付
	private int deliverstate;//发货情况
	private int takestate;//收货情况
	public int getSaleid() {
		return saleid;
	}
	public void setSaleid(int saleid) {
		this.saleid = saleid;
	}
	
	public int getBuyuserid() {
		return buyuserid;
	}
	public void setBuyuserid(int buyuserid) {
		this.buyuserid = buyuserid;
	}
	public int getSaleuserid() {
		return saleuserid;
	}
	public void setSaleuserid(int saleuserid) {
		this.saleuserid = saleuserid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public int getDeliverstate() {
		return deliverstate;
	}
	public void setDeliverstate(int deliverstate) {
		this.deliverstate = deliverstate;
	}
	public int getTakestate() {
		return takestate;
	}
	public void setTakestate(int takestate) {
		this.takestate = takestate;
	}
	
}
