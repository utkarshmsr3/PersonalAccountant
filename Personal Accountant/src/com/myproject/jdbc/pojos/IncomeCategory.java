package com.myproject.jdbc.pojos;

public class IncomeCategory {
	int incCatId;
	String incCatName;
	String incCatDetails;
	int userId;
	public IncomeCategory() {
		super();
	}
	public IncomeCategory(int incCatId, String incCatName, String incCatDetails, int userId) {
		super();
		this.incCatId = incCatId;
		this.incCatName = incCatName;
		this.incCatDetails = incCatDetails;
		this.userId = userId;
	}
	public int getIncCatId() {
		return incCatId;
	}
	public void setIncCatId(int incCatId) {
		this.incCatId = incCatId;
	}
	public String getIncCatName() {
		return incCatName;
	}
	public void setIncCatName(String incCatName) {
		this.incCatName = incCatName;
	}
	public String getIncCatDetails() {
		return incCatDetails;
	}
	public void setIncCatDetails(String incCatDetails) {
		this.incCatDetails = incCatDetails;
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
		result = prime * result + ((incCatDetails == null) ? 0 : incCatDetails.hashCode());
		result = prime * result + incCatId;
		result = prime * result + ((incCatName == null) ? 0 : incCatName.hashCode());
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
		IncomeCategory other = (IncomeCategory) obj;
		if (incCatDetails == null) {
			if (other.incCatDetails != null)
				return false;
		} else if (!incCatDetails.equals(other.incCatDetails))
			return false;
		if (incCatId != other.incCatId)
			return false;
		if (incCatName == null) {
			if (other.incCatName != null)
				return false;
		} else if (!incCatName.equals(other.incCatName))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "IncomeCategory [incCatId=" + incCatId + ", incCatName=" + incCatName + ", incCatDetails="
				+ incCatDetails + ", userId=" + userId + "]";
	}
	
	
}
