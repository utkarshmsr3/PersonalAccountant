package com.myproject.jdbc.pojos;

import java.util.Date;

public class Expenses {
	int expId;
	String expAc;
	int userId;
	int expCatId;
	double amount;
	java.util.Date tranDate;
	String payBy;
	String remark;
	
	
	public Expenses() {
		super();
	}
	public Expenses(int expId, String expAc, int userId, int expCatId, double amount, Date tranDate, String payBy,
			String remark) {
		super();
		this.expId = expId;
		this.expAc = expAc;
		this.userId = userId;
		this.expCatId = expCatId;
		this.amount = amount;
		this.tranDate = tranDate;
		this.payBy = payBy;
		this.remark = remark;
	}
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	public String getExpAc() {
		return expAc;
	}
	public void setExpAc(String expAc) {
		this.expAc = expAc;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getExpCatId() {
		return expCatId;
	}
	public void setExpCatId(int expCatId) {
		this.expCatId = expCatId;
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
	public String getPayBy() {
		return payBy;
	}
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((expAc == null) ? 0 : expAc.hashCode());
		result = prime * result + expCatId;
		result = prime * result + expId;
		result = prime * result + ((payBy == null) ? 0 : payBy.hashCode());
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
		Expenses other = (Expenses) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (expAc == null) {
			if (other.expAc != null)
				return false;
		} else if (!expAc.equals(other.expAc))
			return false;
		if (expCatId != other.expCatId)
			return false;
		if (expId != other.expId)
			return false;
		if (payBy == null) {
			if (other.payBy != null)
				return false;
		} else if (!payBy.equals(other.payBy))
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
		return "Expenses [expId=" + expId + ", expAc=" + expAc + ", userId=" + userId + ", expCatId=" + expCatId
				+ ", amount=" + amount + ", tranDate=" + tranDate + ", payBy=" + payBy + ", remark=" + remark + "]";
	}



}
