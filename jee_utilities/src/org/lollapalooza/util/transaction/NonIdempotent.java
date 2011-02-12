package org.lollapalooza.util.transaction;

public interface NonIdempotent {
	public String getNewTransactionId();
	public void commit(String transactionId);
	public boolean isCommited(String transactionId);
}
