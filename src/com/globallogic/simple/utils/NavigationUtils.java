package com.globallogic.simple.utils;

import org.openqa.selenium.WebDriver;

import com.globallogic.simple.pages.LoginPage;

public class NavigationUtils {
	public static LoginPage startWebApplication(WebDriver browser) {
		browser.get("file:///" + FileSystemUtils.addAbsolutePath("app/index.html"));
		return new LoginPage(browser);
	}
}
