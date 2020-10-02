package com.mys.ubs.endofdaypositioncalculation.data;

public abstract class Account implements AccountQuantityCalculator {

	private final String accountNumber;
	private final AccountType type;
	
	public Account(String accountNumber, AccountType type) {
		this.accountNumber = accountNumber;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", type=" + type.name() + "]";
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public AccountType getType() {
		return type;
	}	
	
}
