package com.mys.ubs.endofdaypositioncalculation.data;

public class Instrument {
	
	private String stockSymbol;
	
	public Instrument(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	@Override
	public String toString() {
		return "Instrument [stockSymbol=" + stockSymbol + "]";
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
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
		Instrument other = (Instrument) obj;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		return true;
	}
	
	
}
