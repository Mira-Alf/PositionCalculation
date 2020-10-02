package com.mys.ubs.endofdaypositioncalculation.data;

public enum TransactionType {
	
	B("Buy"), S("Sell");
	
	private String name;
	
	TransactionType(String name) {
		this.name = name;
	}

}
