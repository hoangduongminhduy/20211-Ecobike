package entity;

import entity.*;

public class Payment {
	private String errorCode;
	private Card card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	public Payment(String errorCode, Card card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public int getAmount() {
		return amount;
	}
}
