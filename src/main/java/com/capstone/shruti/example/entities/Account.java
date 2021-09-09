package com.capstone.shruti.example.entities;

public class Account {
	private long accountNo;
	private long balance;
	private String accountType;
	
	public Account(long accountNo, long balance, String accountType) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.accountType = accountType;
	}
	
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
}
