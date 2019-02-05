package com.myproject.jdbc.pojos;

public class ExpenseCategory {
	int expCatId;
	String expCatName;
	String expCatDetails;
	int userId;
	public ExpenseCategory(int expCatId, String expCatName, String expCatDetails, int userId) {
		super();
		this.expCatId = expCatId;
		this.expCatName = expCatName;
		this.expCatDetails = expCatDetails;
		this.userId = userId;
	}
	public ExpenseCategory() {
		super();
	}
	public int getExpCatId() {
		return expCatId;
	}
	public void setExpCatId(int expCatId) {
		this.expCatId = expCatId;
	}
	public String getExpCatName() {
		return expCatName;
	}
	public void setExpCatName(String expCatName) {
		this.expCatName = expCatName;
	}
	public String getExpCatDetails() {
		return expCatDetails;
	}
	public void setExpCatDetails(String expCatDetails) {
		this.expCatDetails = expCatDetails;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expCatDetails == null) ? 0 : expCatDetails.hashCode());
		result = prime * result + expCatId;
		result = prime * result + ((expCatName == null) ? 0 : expCatName.hashCode());
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
		ExpenseCategory other = (ExpenseCategory) obj;
		if (expCatDetails == null) {
			if (other.expCatDetails != null)
				return false;
		} else if (!expCatDetails.equals(other.expCatDetails))
			return false;
		if (expCatId != other.expCatId)
			return false;
		if (expCatName == null) {
			if (other.expCatName != null)
				return false;
		} else if (!expCatName.equals(other.expCatName))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ExpenseCategory [expCatId=" + expCatId + ", expCatName=" + expCatName + ", expCatDetails="
				+ expCatDetails + ", userId=" + userId + "]";
	}
	
	
}
