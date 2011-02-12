package org.lollapalooza.util.transaction;

public class NonIdempotentTransactionControllerFactory {
	public static NonIdempotent create(String type) throws Exception {
		if (type.equalsIgnoreCase("UUID-based"))
			return new NonIdempotentTransactionControllerUUID();
		else
			throw new java.lang.Exception("Unknown idempotent controller type.");
	}
}
