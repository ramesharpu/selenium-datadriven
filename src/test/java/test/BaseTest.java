package test;

import utils.Common;

import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public class BaseTest {
	Common common;
	static WebDriver driver;

	@BeforeSuite
	public void beforeSuite() {
		common = new Common();
		common.setupBrowser();
		driver = common.getDriver();
	}

	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
}
