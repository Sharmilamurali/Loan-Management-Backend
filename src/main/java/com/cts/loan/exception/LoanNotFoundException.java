package com.cts.loan.exception;

public class LoanNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoanNotFoundException(String message) {
		super(message);
	}
}
