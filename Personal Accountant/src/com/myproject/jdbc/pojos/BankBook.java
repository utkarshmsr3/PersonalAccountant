package com.myproject.jdbc.pojos;

import java.util.Date;

public class BankBook {
	int acId;
	String account;
	java.util.Date tranDate;
	double amount;
	int userId;
	String operation;
	public BankBook(int acId, String account, Date tranDate, double amount, int userId, String operation) {
		super();
		this.acId = acId;
		this.account = account;
		this.tranDate = tranDate;
		this.amount = amount;
		this.userId = userId;
		this.operation = operation;
	}
	public BankBook() {
		super();
	}
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public java.util.Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(java.util.Date tranDate) {
		this.tranDate = tranDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acId;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((tranDate == null) ? 0 : tranDate.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankBook other = (BankBook) obj;
		if (acId != other.acId)
			return false;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (tranDate == null) {
			if (other.tranDate != null)
				return false;
		} else if (!tranDate.equals(other.tranDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BankBook [acId=" + acId + ", account=" + account + ", tranDate=" + tranDate + ", amount=" + amount
				+ ", userId=" + userId + ", operation=" + operation + "]";
	}
	
	
}
