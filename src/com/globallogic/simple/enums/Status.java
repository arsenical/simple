package com.globallogic.simple.enums;

public enum Status {
	PASSED("PASSED  "), FAILED("FAILED  "), WARNING("WARNING "), SKIPPED("SKIPPED ");

	private String reportStatus;
	
	Status(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportStatus() {
		return reportStatus;
	}

}
