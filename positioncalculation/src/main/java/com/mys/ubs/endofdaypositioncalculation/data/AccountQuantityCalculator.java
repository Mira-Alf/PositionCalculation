package com.mys.ubs.endofdaypositioncalculation.data;

public interface AccountQuantityCalculator {
	
	public long calculateUpdatedQuantity(Transaction transaction, long currentQuantity);

}
