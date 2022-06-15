package com.meshop.chat.entity;

public class Chatroom {
	private int no;
	private String sellerId;
	private String buyerId;
	private int productId;
	private String title;
	private String storeName;
	
	public Chatroom() {
		
	}
	public Chatroom(int no, String sellerId, String buyerId, int productId, String title, String storeName) {
		super();
		this.no = no;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.productId = productId;
		this.title = title;
		this.storeName = storeName;
	}
	@Override
	public String toString() {
		return "Chatroom [no=" + no + ", sellerId=" + sellerId + ", buyerId=" + buyerId + ", productId=" + productId
				+ ", title=" + title + ", storeName=" + storeName + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
	
}
