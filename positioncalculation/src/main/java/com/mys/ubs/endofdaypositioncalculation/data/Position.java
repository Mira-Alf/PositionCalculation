package com.mys.ubs.endofdaypositioncalculation.data;

public class Position {
	
	private final Account account;
	private final Instrument instrument;
	private final long quantity;
	private final EndOfDayPosition endOfDayPosition;
	
	public Position(Account account, Instrument instrument, long quantity) {
		this.account = account;
		this.instrument = instrument;
		this.quantity = quantity;
		this.endOfDayPosition = EndOfDayPosition.newEndOfDayPosition(this);
	}

	public EndOfDayPosition getEndOfDayPosition() {
		return endOfDayPosition;
	}

	@Override
	public String toString() {
		return "Position [account=" + account + ", instrument=" + instrument + ", quantity=" + quantity + "]";
	}

	public Account getAccount() {
		return account;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public long getQuantity() {
		return quantity;
	}

	
	

}
