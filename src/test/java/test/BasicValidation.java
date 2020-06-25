package test;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class BasicValidation extends BaseTest{
	
	@Test(description = "Testcase to validate the url redirection")
	public void urlRedirectionValidation() {
		String expectedUrl = "https://www.amazon.in/";
		String actualurl = driver.getCurrentUrl();
		assertTrue(expectedUrl.equals(actualurl), "URL redirection error");
	}
	
	@Test(description = "Testcase to validate title")
	public void titleValidation() {
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		assertTrue(expectedTitle.equals(actualTitle), "Page title does not match.");
	}
}
