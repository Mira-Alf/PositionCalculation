package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mys.ubs.endofdaypositioncalculation.data.EndOfDayPosition;
import com.mys.ubs.endofdaypositioncalculation.data.Position;

public class EndOfDayPositionCalculator {
	
	public static String CSV_FILE_NAME = "C:\\Users\\aparn\\git\\"
			+ "PositionCalculation\\positioncalculation\\src\\main\\resources\\Expected_EndOfDay_Positions.txt";
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
	
	public void writeToCSV() throws IOException {
		File csvOutputFile = new File(CSV_FILE_NAME);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	    	pw.println("instrument,account,accountType,quantity,delta");
	        positionReader.getPositions().stream()
	          .map(Position::getEndOfDayPosition)
	          .map(EndOfDayPosition::getCSVLine)
	          .forEach(pw::println);
	    }
	}
	
	public String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	
	public String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}

}
