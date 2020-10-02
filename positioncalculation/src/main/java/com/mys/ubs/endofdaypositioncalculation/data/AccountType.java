package com.mys.ubs.endofdaypositioncalculation.data;

public enum AccountType {

	I("Internal"), E("External");
	
	private String name;
	
	AccountType(String name) {
		this.name = name;
	}
	
	public AccountFactory getAccountFactory() {
		if(this == I)
			return new InternalAccountFactory();
		else
			return new ExternalAccountFactory();
	}
	
}
