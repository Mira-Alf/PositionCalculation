package com.mys.ubs.endofdaypositioncalculation.data;

public class InternalAccountFactory implements AccountFactory {

	@Override
	public Account newAccount(String accountNumber) {
		return new InternalAccount(accountNumber);
	}

}
