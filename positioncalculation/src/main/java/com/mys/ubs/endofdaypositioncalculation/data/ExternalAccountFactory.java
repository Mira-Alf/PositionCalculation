package com.mys.ubs.endofdaypositioncalculation.data;

public class ExternalAccountFactory implements AccountFactory {

	@Override
	public Account newAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return new ExternalAccount(accountNumber);
	}

}
