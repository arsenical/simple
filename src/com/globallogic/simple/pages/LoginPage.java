package com.globallogic.simple.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.globallogic.simple.report.ErrorsHolder;

public class LoginPage extends BasePage {

	private WebElement edtLogin;
	private WebElement edtPassword;
	private WebElement btnLogin;

	public LoginPage(WebDriver browser) {
		super(browser);
	}

	public void setLogin(String login) {
		edtLogin = browser.findElement(By.id("login"));
		edtLogin.clear();
		edtLogin.sendKeys(login);
	}

	public void setPassword(String password) {
		edtPassword = browser.findElement(By.id("pswd"));
		edtPassword.clear();
		edtPassword.sendKeys(password);
	}

	public void clickLoginButton() {
		btnLogin = browser.findElement(By.id("btn-login"));
		btnLogin.click();
	}

	public void verifyLoginButtonEnabled(boolean expectedState) {
		btnLogin = browser.findElement(By.id("btn-login"));
		boolean actualState = btnLogin.getAttribute("disabled") == null;
		if (actualState != expectedState) {
			ErrorsHolder.failIteration("Verification of Login button enabled state failed. Actual: " + actualState
					+ ". Expected: " + expectedState + ".");
		}
	}

}
