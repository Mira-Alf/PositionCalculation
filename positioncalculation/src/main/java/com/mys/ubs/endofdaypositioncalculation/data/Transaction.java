package com.mys.ubs.endofdaypositioncalculation.data;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

public class Transaction implements Jsonable {
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType + ", instrument="
				+ instrument + ", quantity=" + quantity + ", instrumentRecord=" + instrumentRecord + ", transType="
				+ transType + "]";
	}
	private String transactionId;
	private String transactionType;
	private String instrument;
	private long quantity;
	private Instrument instrumentRecord;
	private TransactionType transType;
	
	public Instrument getInstrumentRecord() {
		return instrumentRecord;
	}
	
	public void postCreate(Instrument instrumentRecord, TransactionType transType) {
		this.instrumentRecord = instrumentRecord;
		this.transType = transType;
	}

	public void setInstrumentRecord(Instrument instrumentRecord) {
		this.instrumentRecord = instrumentRecord;
	}

	public TransactionType getTransType() {
		return transType;
	}

	public void setTransType(TransactionType transType) {
		this.transType = transType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toJson() {
		final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
	}
	@Override
	public void toJson(Writer writer) throws IOException {
		final JsonObject json = new JsonObject();
        json.put("transactionId", getTransactionId());
        json.put("instrument", getInstrument());
        json.put("transactionType", getTransactionType());
        json.put("quantity", getQuantity());
        json.toJson(writer);		
	}

}
