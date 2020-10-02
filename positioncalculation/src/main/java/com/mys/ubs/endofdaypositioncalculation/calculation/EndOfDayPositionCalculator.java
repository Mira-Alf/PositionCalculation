package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.util.List;

import com.mys.ubs.endofdaypositioncalculation.data.Position;

public class EndOfDayPositionCalculator {
	
	private final PositionReader positionReader;
	private final TransactionReader transactionReader;
	private final InstrumentBuilder instrumentBuilder = new InstrumentBuilder();
	
	public EndOfDayPositionCalculator() {
		positionReader = new PositionReader(instrumentBuilder);
		transactionReader = new TransactionReader(instrumentBuilder);
	}
	
	public EndOfDayPositionCalculator(String positionsFileName, String transactionsFileName) {
		positionReader = new PositionReader(positionsFileName, instrumentBuilder);
		transactionReader = new TransactionReader(transactionsFileName, instrumentBuilder);
	}
	
	public void calculateEndOfDayPositions() {
		positionReader.calculatePositionsMap();
		transactionReader.calculateTransactionsMap();
		transactionReader.getTransactionsMap().keySet().forEach(instrument->{
			
			List<Position> instrumentPositions = positionReader.getPositionsMap().get(instrument);
			
			transactionReader.getTransactionsMap().get(instrument).forEach(transaction->{
				instrumentPositions.forEach(position->{
					position.getEndOfDayPosition().updateEndOfDayPosition(transaction);
				});
			});
		});
		
		positionReader.getPositions().forEach(p->{
			System.out.println(p.getEndOfDayPosition());
		});
	}

}
