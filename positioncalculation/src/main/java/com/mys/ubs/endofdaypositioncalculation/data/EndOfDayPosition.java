package com.mys.ubs.endofdaypositioncalculation.data;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EndOfDayPosition {
	
	@Override
	public String toString() {
		return "EndOfDayPosition [position=" + position + ", quantity=" + quantity + ", deltaQuantity=" + deltaQuantity
				+ "]";
	}

	private final Position position;
	private long quantity;
	private long deltaQuantity;
	
	private EndOfDayPosition(Position position) {
		this.position = position;
	}
	
	public static EndOfDayPosition newEndOfDayPosition(Position p) {
		return new EndOfDayPosition(p);
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getDeltaQuantity() {
		return deltaQuantity;
	}

	private void updateDeltaQuantity() {
		this.deltaQuantity = quantity - position.getQuantity();
	}

	public Position getPosition() {
		return position;
	}
	
	public void updateEndOfDayPosition(Transaction transaction) {
		this.quantity = position.getAccount().calculateUpdatedQuantity(transaction, quantity);
		updateDeltaQuantity();
	}
	
	public String getCSVLine() {
		String[] items = { position.getInstrument().getStockSymbol(), 
				position.getAccount().getAccountNumber(),
				position.getAccount().getType().name(),
				String.valueOf(quantity),
				String.valueOf(deltaQuantity)
		};
		return Arrays.stream(items).collect(Collectors.joining(","));
	}
	
	

}
