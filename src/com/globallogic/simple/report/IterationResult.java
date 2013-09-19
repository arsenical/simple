package com.globallogic.simple.report;

import com.globallogic.simple.enums.Status;

public class IterationResult {
	private int iterationNumber;
	private Status status;
	private String message;

	public IterationResult(int iterationNumber) {
		this.iterationNumber = iterationNumber;
		this.message = "";
		this.status = Status.PASSED;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}
		
	public int getIterationNumber() {
		return iterationNumber;
	}

	public String getMessage() {
		return message;
	}
}
