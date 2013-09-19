package com.globallogic.simple.tests;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.globallogic.simple.data.DataFile;
import com.globallogic.simple.report.ErrorsHolder;
import com.globallogic.simple.report.Reporter;
import com.globallogic.simple.utils.FileSystemUtils;

/**
 * Class that contains properties and methods which are common for all the tests. Start point of tests execution -
 * testTemplate() method.
 * 
 * @author Maksym Barvinskyi
 */
public abstract class BaseTest {
	private Reporter reporter;
	private String dataFilePath;
	private String testName;
	private List<HashMap<String, String>> dataTable;
	protected HashMap<String, String> data;
	protected WebDriver browser;

	public BaseTest(String dataFilePath, String testName) throws Exception {
		this.testName = testName;
		this.dataFilePath = dataFilePath;
	}

	/**
	 * Single unit test that contains basic structure of every automation test.
	 */
	@Test
	public void testTemplate() {
		try {
			beforeTestExecute();
			for (int iter = 0; iter < dataTable.size(); iter++) {
				beforeIteration(iter);
				try {
					startBrowser();
					onExecute();
					closeBrowser();
					onIterationPassed();
				} catch (AssertionError error) {
					onError(error);
				} catch (Exception exception) {
					onError(exception);
				}
			}
		} catch (AssertionError configException) {
			configException.printStackTrace();
			Assert.fail("Configuration Exception: " + configException.getMessage());
		} catch (Exception configException) {
			configException.printStackTrace();
			Assert.fail("Configuration Exception: " + configException.getMessage());
		}
		afterTestExecute();
	}

	/**
	 * Actions of the functional test. Normally they are presented as private methods of high level action.
	 */
	protected abstract void onExecute();

	/**
	 * Starts the browser instance.
	 */
	public void startBrowser() {
		this.browser = new FirefoxDriver();
	}

	private void onIterationPassed() {
		reporter.onIterationPassed();
	}

	/**
	 * Actions that will be performed before test execution. Here the system property of Chrome and IE drivers location
	 * are set up.
	 * 
	 * @throws Exception
	 */
	private void beforeTestExecute() throws Exception {
		setSystemPropertyOfDriversPaths();
		this.reporter = new Reporter();
		this.dataTable = new DataFile(dataFilePath).getDataTable();
	}

	private void beforeIteration(int iteration) {
		ErrorsHolder.cleanUp();
		reporter.setCurrentIteration(iteration + 1);
		data = dataTable.get(iteration);
	}

	private void onError(Throwable fail) {
		ErrorsHolder.setFailsTrue();
		String message = "";
		if (fail.getMessage() != null) {
			message = fail.getMessage();
		}
		reporter.onIterationFailed(message);
		closeBrowser();
	}

	private void afterTestExecute() {
		try {
			reporter.generateReport(testName);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Report Exception: " + e.getMessage());
		}
		ErrorsHolder.failUnitTest(reporter.getFailedIterations());
	}

	/**
	 * Closes the browser instance.
	 */
	private void closeBrowser() {
		this.browser.quit();
	}

	private void setSystemPropertyOfDriversPaths() {
		System.setProperty("webdriver.chrome.driver", FileSystemUtils.addAbsolutePath("lib\\drivers\\chromedriver.exe"));
		System.setProperty("webdriver.ie.driver", FileSystemUtils.addAbsolutePath("lib\\drivers\\IEDriverServer.exe"));
	}
}
