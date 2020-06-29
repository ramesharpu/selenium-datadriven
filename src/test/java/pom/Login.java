package pom;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	@SuppressWarnings("unused")
	private WebDriver driver;
	Actions action;
	WebDriverWait wait;
	Navigation nav;
	
	@CacheLookup
	@FindBy(xpath = "//div[@id='auth-error-message-box']")
	WebElement errorMessageBox;
	
	@CacheLookup
	@FindBy(xpath = "//h4[@class='a-alert-heading']")
	WebElement errorMessageHeading;
	
	@CacheLookup
	@FindBy(xpath = "//span[@class='a-list-item']")
	WebElement errorMessageDescription;
	
	@CacheLookup
	@FindBy(id = "ap_email")
	WebElement email;
	
	@CacheLookup
	@FindBy(id = "continue")
	WebElement continueBtn;
	
	@CacheLookup
	@FindBy(id = "ap_password")
	WebElement passwd;
	
	@CacheLookup
	@FindBy(id = "signInSubmit")
	WebElement loginBtn;
	
	public void enterUsername(String username) {
		email.sendKeys(username);
		continueBtn.click();
	}
	
	public void enterPassword(String password) {
		passwd.sendKeys(password);
		loginBtn.click();
	}
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		nav = new Navigation(driver);
	}
	
	
	public Map<String, String> invalidLoginMessage(String username) {
		nav.login();
		enterUsername(username);
		wait.until(ExpectedConditions.visibilityOf(errorMessageBox));
		Map<String, String> errorDetails = new HashMap<String, String>();
		errorDetails.put("errorHeader", errorMessageHeading.getText().strip());
		errorDetails.put("errorMessage", errorMessageDescription.getText().strip());
		return errorDetails;		
	}
	
	public void successfulLogin(String username, String password) {
		nav.login();
		enterUsername(username);
		enterPassword(password);
	}
	
	public void logout() {
		nav.signOut();

	}

}
