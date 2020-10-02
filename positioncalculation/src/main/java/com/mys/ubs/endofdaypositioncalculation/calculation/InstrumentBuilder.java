package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.util.*;

import com.mys.ubs.endofdaypositioncalculation.data.Instrument;

public class InstrumentBuilder {
	
	private final Set<Instrument> instruments = new HashSet<>();
	
	public Instrument getInstrument(String stockSymbol) {
		Optional<Instrument> instrument = instruments.stream()
				.filter(i->i.getStockSymbol().equals(stockSymbol)).findFirst();
		
		return instrument.orElseGet(()->{
			Instrument newInstrument = new Instrument(stockSymbol);
			instruments.add(newInstrument);
			return newInstrument;
		});
	}

}
