package com.myproject.jdbc.pojos;

import java.util.Date;

public class Incomes {
	int incId;
	String incAc;
	int userId;
	int incCatId;
	double amount;
	java.util.Date tranDate;
	String receiveBy;
	String remark;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((incAc == null) ? 0 : incAc.hashCode());
		result = prime * result + incCatId;
		result = prime * result + incId;
		result = prime * result + ((receiveBy == null) ? 0 : receiveBy.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		Incomes other = (Incomes) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (incAc == null) {
			if (other.incAc != null)
				return false;
		} else if (!incAc.equals(other.incAc))
			return false;
		if (incCatId != other.incCatId)
			return false;
		if (incId != other.incId)
			return false;
		if (receiveBy == null) {
			if (other.receiveBy != null)
				return false;
		} else if (!receiveBy.equals(other.receiveBy))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
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
		return "Incomes [incId=" + incId + ", incAc=" + incAc + ", userId=" + userId + ", incCatId=" + incCatId
				+ ", amount=" + amount + ", tranDate=" + tranDate + ", receiveBy=" + receiveBy + ", remark=" + remark
				+ "]";
	}
	public int getIncId() {
		return incId;
	}
	public void setIncId(int incId) {
		this.incId = incId;
	}
	public String getIncAc() {
		return incAc;
	}
	public void setIncAc(String incAc) {
		this.incAc = incAc;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIncCatId() {
		return incCatId;
	}
	public void setIncCatId(int incCatId) {
		this.incCatId = incCatId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public java.util.Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(java.util.Date tranDate) {
		this.tranDate = tranDate;
	}
	public String getReceiveBy() {
		return receiveBy;
	}
	public void setReceiveBy(String receiveBy) {
		this.receiveBy = receiveBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Incomes(int incId, String inAc, int userId, int incCatId, double amount, Date tranDate, String receiveBy,
			String remark) {
		super();
		this.incId = incId;
		this.incAc = inAc;
		this.userId = userId;
		this.incCatId = incCatId;
		this.amount = amount;
		this.tranDate = tranDate;
		this.receiveBy = receiveBy;
		this.remark = remark;
	}
	public Incomes() {
		super();
	}
	
	
}
