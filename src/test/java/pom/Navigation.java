package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigation {
	private WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String baseUrl = "https://www.amazon.in";
	
	@CacheLookup
	@FindBy(xpath = "//span[@class='nav-line-2 ']")
	WebElement accountsAndListElement;
	
	@CacheLookup
	@FindBy(xpath = "//span[contains(text(),'Create a Wish List')]")
	WebElement createWishListElement;
	
	@CacheLookup
	@FindBy(xpath = "//div[@role='heading']")
	WebElement yourList;
		
	@CacheLookup
	@FindBy(linkText = "Amazon Pay")
	WebElement amazonPayElement;
	
	@CacheLookup
	@FindBy(xpath = "//span[contains(text(),'Amazon Pay balance')]")
	WebElement amazonPayBalanceText;

	@CacheLookup
	@FindBy(linkText = "New Releases")
	WebElement newReleasesElement;
	
	@CacheLookup
	@FindBy(xpath = "//div[contains(text(),'Amazon Hot New Releases')]")
	WebElement newReleasesText;

	@CacheLookup
	@FindBy(xpath = "//a[@data-nav-role='signin']")
	WebElement signInBtn;
	
	@CacheLookup
	@FindBy(xpath = "//span[contains(text(),'Sign Out')]")
	WebElement signOut;
	
	@CacheLookup
	@FindBy(xpath = "//h1[contains(text(),'Login')]")
	WebElement loginText;

	
	public Navigation(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void createWishList() {
		driver.navigate().to(baseUrl);
		action.moveToElement(accountsAndListElement)
		.moveToElement(createWishListElement).click()
		.build().perform();
		wait.until(ExpectedConditions.visibilityOf(yourList));
	}
	
	public void login() {
		driver.navigate().to(baseUrl);
		action.moveToElement(accountsAndListElement)
		.moveToElement(signInBtn).click()
		.build().perform();
		wait.until(ExpectedConditions.visibilityOf(loginText));
	}
	
	public void signOut() {
		driver.navigate().to(baseUrl);
		WebElement elem = driver.findElement(By.xpath("//span[@class='nav-line-2 ']"));
		wait.until(ExpectedConditions.elementToBeClickable(elem));
		action.moveToElement(elem)
		.moveToElement(signOut).click()
		.build().perform();
	}
	
	public void amazonPay() {
		driver.navigate().to(baseUrl);
		amazonPayElement.click();
		wait.until(ExpectedConditions.visibilityOf(amazonPayBalanceText));
		
	}

	public void newReleases() {
		driver.navigate().to(baseUrl);
		newReleasesElement.click();	
		wait.until(ExpectedConditions.visibilityOf(newReleasesText));
	}
}
