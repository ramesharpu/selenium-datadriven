package test;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pom.Login;

public class LoginTest extends BaseTest {
	String expectedEmailErrorHeader = "There was a problem";
	String expectedEmailErrorMessage = "We cannot find an account with that email address";

	String expectedPhoneErrorHeader = "Incorrect phone number";
	String expectedPhoneErrorMessage = "We cannot find an account with that mobile number";


	@Parameters({"invalidEmailId"})
	@Test(description = "Test to validate login functionality with invalid email id")
	public void invalidEmailValidation(String emailId) {
		Login login = new Login(driver);
		Map<String, String> result = login.invalidLoginMessage(emailId);
		assert(expectedEmailErrorHeader.equals(result.get("errorHeader")));
		assert(expectedEmailErrorMessage.equals(result.get("errorMessage")));
	}
	
	@Parameters({"invalidPhoneNumber"})
	@Test(description = "Test to validate login functionality with invalid mobile number")
	public void invalidPhoneValidation(String invalidPhoneNumber) {
		Login login = new Login(driver);
		Map<String, String> result = login.invalidLoginMessage(invalidPhoneNumber);
		assert(expectedPhoneErrorHeader.equals(result.get("errorHeader")));
		assert(expectedPhoneErrorMessage.equals(result.get("errorMessage")));
	}

	@Parameters({"validUsername", "validPassword"})
	@Test(description = "Test to validate login functionality with valid username/password",
	enabled = false)
	public void validEmailLoginValidation(String username, String password) {
		String welcomeString = "Hi, Ramesh";
		By welcomeMessage = By.xpath("//span[@class='nav-line-3']");
		Login login = new Login(driver);
		login.successfulLogin(username, password);
		String actualWelcomeString = driver.findElement(welcomeMessage).getText();
		System.out.println(actualWelcomeString);
		assertTrue(welcomeString.equals(welcomeString));
		login.logout();
	}
}
