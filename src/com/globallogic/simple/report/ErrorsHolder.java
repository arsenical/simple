package com.globallogic.simple.report;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

/**
 * This class holds current failures and warnings. Iterations and tests fails
 * are triggered here.
 * 
 * @author maksym.barvinskyi
 */
public class ErrorsHolder {
	private static boolean iterationFailed;
	private static boolean testFailed = false;
	private static final List<String> failures = new ArrayList<String>();
	static final List<String> warnings = new ArrayList<String>();

	/**
	 * This should be called at the end of every iteration, so that the failures
	 * of one iteration should not appear as failure on other iteration. This
	 * doesn't clear testFailed field.
	 */
	public static void cleanUp() {
		failures.clear();
		warnings.clear();
		iterationFailed = false;
	}

	public static void setFailsTrue() {
		iterationFailed = true;
		testFailed = true;
	}

	/**
	 * Fails JUnit test if there were at least one failed iteration.
	 * 
	 * @param message
	 *            - the list of failed iterations or other message to fail the
	 *            test with.
	 */
	public static void failUnitTest(String message) {
		if (testFailed) {
			Assert.fail(message);
		}
	}

	/**
	 * Fails current iteration if there were errors before.
	 */
	public static void failIterationOnError() {
		if (iterationFailed) {
			StringBuilder builder = new StringBuilder();

			for (String failure : failures) {
				builder.append("\n" + failure);
			}
			Assert.fail(builder.toString());
		}
	}

	public static void addFailure(String failure) {
		failures.add(failure);
	}

	public static void addWarning(String warning) {
		System.out.println("WARNING: '" + warning + "'.");
		warnings.add(warning);
	}

	public static List<String> getWarnings() {
		return warnings;
	}

	public static int getFailuresCount() {
		return failures.size();
	}

	public static void failIteration(String message) {
		failures.add(message);
		setFailsTrue();
		failIterationOnError();
	}

}
