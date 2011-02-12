package org.lollapalooza.util.transaction;

import java.util.ArrayList;

public class NonIdempotentTransactionControllerUUID implements NonIdempotent {
	private ArrayList<String> commitedTransactionIdList;
	
	public NonIdempotentTransactionControllerUUID() {
		commitedTransactionIdList = new ArrayList<String>();
	}

	@Override
	public void commit(String transactionId) {
		commitedTransactionIdList.add(transactionId);
	}

	@Override
	public String getNewTransactionId() {
		return java.util.UUID.randomUUID().toString();
	}

	@Override
	public boolean isCommited(String transactionId) {
		return (commitedTransactionIdList.contains(transactionId));
	}
}
