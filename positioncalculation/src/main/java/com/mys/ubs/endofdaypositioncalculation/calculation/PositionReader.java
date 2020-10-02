package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.mys.ubs.endofdaypositioncalculation.data.Account;
import com.mys.ubs.endofdaypositioncalculation.data.AccountFactory;
import com.mys.ubs.endofdaypositioncalculation.data.AccountType;
import com.mys.ubs.endofdaypositioncalculation.data.Instrument;
import com.mys.ubs.endofdaypositioncalculation.data.Position;

public class PositionReader {
	public static String POSITIONS_FILE_PATH = "C:\\Users\\aparn\\git\\"
			+ "PositionCalculation\\positioncalculation\\src\\main\\resources\\Input_StartOfDay_Positions.txt";
	
	private List<Position> positions = new ArrayList<>();
	private Map<Instrument, List<Position>> positionsMap = new HashMap<>();
	private final File positionsFile;
	private InstrumentBuilder instrumentBuilder;

	
	
	private Consumer<String> mapToPosition = (line)->{
		String[] items = line.split(",");
		if(items.length != 4)
			throw new IllegalArgumentException("Number of items in the positions file does not have exact number of items");		
		Instrument instrument = instrumentBuilder.getInstrument(items[0]);
		Account account = getAccount(items[1], items[2]);
		long quantity = Long.parseLong(items[3]);
		Position position = new Position(account, instrument, quantity);
		positions.add(position);
	};
	
	private Account getAccount(String accountNumber, String acType) {
		AccountType accountType = Enum.valueOf(AccountType.class,  acType);
		return accountType.getAccountFactory().newAccount(accountNumber);		
	}
	public PositionReader(InstrumentBuilder instrumentBuilder) {
		this(POSITIONS_FILE_PATH, instrumentBuilder);
	}
	
	public PositionReader(String fileName, InstrumentBuilder instrumentBuilder) {
		positionsFile = new File(fileName);
		this.instrumentBuilder = instrumentBuilder;
	}

	public void calculatePositionsMap() {
		try( InputStream inputFS = new FileInputStream(positionsFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)) ) {
			br.lines().skip(1).forEach(mapToPosition);
			positionsMap = positions.stream()
					.collect(Collectors.groupingBy(Position::getInstrument));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Instrument, List<Position>> getPositionsMap() {
		return positionsMap;
	}
	
	public List<Position> getPositions() {
		return positions;
	}
}
