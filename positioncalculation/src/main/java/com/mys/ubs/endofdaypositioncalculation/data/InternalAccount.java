package com.mys.ubs.endofdaypositioncalculation.data;


public class InternalAccount extends Account {

	public InternalAccount(String accountNumber) {
		super(accountNumber, AccountType.I);
	}

	@Override
	public long calculateUpdatedQuantity(Transaction transaction, long currentQuantity) {
		switch(transaction.getTransType()) {
			case B:
				return currentQuantity - transaction.getQuantity();
			case S:
				return currentQuantity + transaction.getQuantity();
		}
		return 0L;
	}

}
