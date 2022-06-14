package com.meshop.chat.entity;

import java.sql.Date;

public class Message {
	private int no;
	private String senderId;
	private String receiverId;
	private String message;
	private Date sendDate;
	
	public Message() {
		
	}
	public Message(int no, String senderId, String receiverId, String message, Date sendDate) {
		super();
		this.no = no;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.message = message;
		this.sendDate = sendDate;
	}
	@Override
	public String toString() {
		return "Message [no=" + no + ", senderId=" + senderId + ", receiverId=" + receiverId + ", message=" + message
				+ ", sendDate=" + sendDate + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
