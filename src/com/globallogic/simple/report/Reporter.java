package com.globallogic.simple.report;

import java.util.ArrayList;
import java.util.List;

import com.globallogic.simple.enums.Status;

/**
 * This class generates HTML report.
 */
public class Reporter {
	private ArrayList<IterationResult> iterationResults;
	private IterationResult currentIteration;
	private List<String> warnings;
	private String failedIterations;

	public Reporter() {
		this.iterationResults = new ArrayList<IterationResult>();
		this.failedIterations = "Failed iterations: ";
	}

	public void generateReport(String testName) throws Exception {
		failedIterations = failedIterations.substring(0, failedIterations.length() - 1) + ".";

		Status testStatus = getTestStatus();
		System.out.println("\n=============================== Test Report ===============================\n");
		System.out.println("Test name: '" + testName + "'");
		System.out.println("Test result: " + testStatus.toString());
		System.out.println("\nIterations:\n");
		System.out.println("   Number  Result  Details");
		System.out.println("   ------------------------------------------------------");
		for (IterationResult iterResult : iterationResults) {
			System.out.print("   " + iterResult.getIterationNumber() + "       " + iterResult.getStatus().getReportStatus());
			System.out.println(iterResult.getMessage().split("\n")[0]);
		}
	}

	public void setCurrentIteration(int iterationNumber) {
		this.currentIteration = new IterationResult(iterationNumber);
	}

	public void onIterationFailed(String message) {
		currentIteration.setStatus(Status.FAILED);
		failedIterations += currentIteration.getIterationNumber() + ",";
		warnings = ErrorsHolder.getWarnings();

		message = "Errors: " + getErrorsCount() + ". Warnings: " + warnings.size() + ". "
				+ message.replace("\n", "");
		if (warnings.size() > 0) {
			message += getWarningsString();
		}
		currentIteration.setMessage(message);
		iterationResults.add(currentIteration);
	}

	public void onIterationPassed() {
		warnings = ErrorsHolder.getWarnings();
		if (warnings.size() > 0) {
			currentIteration.setStatus(Status.WARNING);
			currentIteration.setMessage("Errors: 0. Warnings: " + warnings.size() + "." + getWarningsString());
		} else {
			currentIteration.setStatus(Status.PASSED);
			currentIteration.setMessage("Errors: 0. Warnings: 0.");
		}

		iterationResults.add(currentIteration);
	}

	public void onIterationSkipped() {
		currentIteration.setStatus(Status.SKIPPED);
		currentIteration.setMessage("Iteration was skipped.");
		iterationResults.add(currentIteration);
	}

	private int getErrorsCount() {
		int result = 1;
		if (ErrorsHolder.getFailuresCount() > 0) {
			result = ErrorsHolder.getFailuresCount();
		}
		return result;
	}

	private Status getTestStatus() {
		for (IterationResult result : iterationResults) {
			if (result.getStatus().equals(Status.FAILED)) {
				return Status.FAILED;
			}
		}
		for (IterationResult result : iterationResults) {
			if (result.getStatus().equals(Status.WARNING)) {
				return Status.WARNING;
			}
		}
		return Status.PASSED;
	}

	private String getWarningsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<br/><br/><b>Warnings:</b>");
		for (String warning : warnings) {
			builder.append("<br/>" + warning);
		}
		return builder.toString();
	}

	public String getFailedIterations() {
		return failedIterations;
	}

}
