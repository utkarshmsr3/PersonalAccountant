package com.myproject.jdbc.pojos;

public class Quote {
	int quoteId;
	String quote;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quote == null) ? 0 : quote.hashCode());
		result = prime * result + quoteId;
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
		Quote other = (Quote) obj;
		if (quote == null) {
			if (other.quote != null)
				return false;
		} else if (!quote.equals(other.quote))
			return false;
		if (quoteId != other.quoteId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Quote [quoteId=" + quoteId + ", quote=" + quote + "]";
	}
	public Quote(int quoteId, String quote) {
		super();
		this.quoteId = quoteId;
		this.quote = quote;
	}
	public Quote() {
		super();
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	
}
