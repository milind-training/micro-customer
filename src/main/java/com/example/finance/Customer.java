package com.example.finance;

public class Customer {
	
	private Integer customerId;
	private Integer bankAccountNo;
	private Integer creditCardNo;
	private Integer debitCardNo;
	
	public Customer(Integer customerId) {
		super();
		this.customerId = customerId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(Integer bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public Integer getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(Integer creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public Integer getDebitCardNo() {
		return debitCardNo;
	}
	public void setDebitCardNo(Integer debitCardNo) {
		this.debitCardNo = debitCardNo;
	}
	
	
	
}
