package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.mys.ubs.endofdaypositioncalculation.data.Instrument;
import com.mys.ubs.endofdaypositioncalculation.data.Transaction;
import com.mys.ubs.endofdaypositioncalculation.data.TransactionType;


public class TransactionReader {
	public static String TRANSACTIONS_FILE_PATH = "C:\\Users\\aparn\\git\\"
			+ "PositionCalculation\\positioncalculation\\src\\main\\resources\\Input_Transactions.json";
	private List<Transaction> transactions;
	private Map<Instrument, List<Transaction>> transactionsMap;
	private final File transactionsFile;
	private final InstrumentBuilder instrumentBuilder;
	
	public TransactionReader(InstrumentBuilder instrumentBuilder) {
		this(TRANSACTIONS_FILE_PATH, instrumentBuilder);
	}
	
	public TransactionReader(String fileName, InstrumentBuilder instrumentBuilder) {
		this.transactionsFile = new File(fileName);
		this.instrumentBuilder = instrumentBuilder;
	}
	
	public void calculateTransactionsMap() {
		try (FileReader fileReader = new FileReader(transactionsFile)) {

            JsonArray objects = null;
			try {
				objects = Jsoner.deserializeMany(fileReader);
			} catch (JsonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(objects.size());
            Mapper mapper = new DozerBeanMapper();

            JsonArray o = (JsonArray) objects.get(0);
            transactions = o.stream()
				.map(x -> mapper.map(x, Transaction.class))
				.collect(Collectors.toList());
            transactions.forEach(transaction->{
            	transaction.postCreate(
            			instrumentBuilder.getInstrument(transaction.getInstrument()), 
            			Enum.valueOf(TransactionType.class, transaction.getTransactionType()));
            });
            transactionsMap = transactions.stream()
            				.collect(Collectors.groupingBy(Transaction::getInstrumentRecord));
            transactionsMap.keySet().forEach(k->{
            	System.out.println("Key = "+k);
            	transactionsMap.get(k).forEach(System.out::println);
            });
            

        } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public Map<Instrument, List<Transaction>> getTransactionsMap() {
		return transactionsMap;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
}
