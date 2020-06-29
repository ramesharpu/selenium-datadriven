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

public class ProductSearch {
	private WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String baseUrl = "https://www.amazon.in";

	@CacheLookup
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchTextBox;

	@CacheLookup
	@FindBy(xpath = "//input[@value='Go']")
	WebElement searchBtn;

	@CacheLookup
	@FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
	WebElement searchResults;

	@CacheLookup
	@FindBy(xpath = "//i[@class='a-icon a-icon-star-medium a-star-medium-1']")
	WebElement oneStarAndUp;

	@CacheLookup
	@FindBy(xpath = "//i[@class='a-icon a-icon-star-medium a-star-medium-2']")
	WebElement twoStarAndUp;

	@CacheLookup
	@FindBy(xpath = "//i[@class='a-icon a-icon-star-medium a-star-medium-3']")
	WebElement threeStarAndUp;

	@CacheLookup
	@FindBy(xpath = "//i[@class='a-icon a-icon-star-medium a-star-medium-4']")
	WebElement fourStarAndUp;
	
	@CacheLookup
	@FindBy(id = "priceblock_ourprice")
	WebElement productPrice;

	@CacheLookup
	@FindBy(xpath = "//div[@id='newAccordionRow']//div[@id='contextualIngressPtLabel_deliveryShortLine']")
	WebElement setDeliveryLocationLink;
	
	@CacheLookup
	@FindBy(id = "GLUXZipUpdateInput")
	WebElement pincodeTextBox;
	
	@CacheLookup
	@FindBy(xpath = "//input[@aria-labelledby='GLUXZipUpdate-announce']")
	WebElement pincodeApplyBtn;

	@CacheLookup
	@FindBy(xpath = "//iframe[@id='ape_Detail_hero-quick-promo_Desktop_iframe']")
	WebElement sponsoredProductIframe;
	
	@CacheLookup
	@FindBy(id = "add-to-cart-button")
	WebElement addToCartBtn;
	
	@CacheLookup
	@FindBy(id = "//div[@id='attach-added-to-cart-message']//h4[@class='a-alert-heading' and contains(text(), 'Added to Cart')]")
	WebElement addToCartMessage;
	
	
	@CacheLookup
	@FindBy(id = "attach-close_sideSheet-link")
	WebElement addToCartWindowCloseBtn;
	
	
	@CacheLookup
	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckoutBtn;
	
	@CacheLookup
	@FindBy(xpath = "//span[text()='Technical Details']")
	WebElement technicalDetails;
	
	

	public ProductSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	public void searchProduct(String productName) {
		driver.navigate().to(baseUrl);
		searchTextBox.sendKeys(productName);
		searchBtn.click();
	}

	public String getSearchResultsSummary() {
		return searchResults.getText();
	}

	/**
	 * 
	 * @param totalStars
	 * Accepted values for totalStars is - 1, 2, 3 or 4
	 */
	public void avgCustomerReviewFilter(int totalStars) {
		switch(totalStars) {
		case 1:
			oneStarAndUp.click();
			break;
		case 2:
			twoStarAndUp.click();
			break;
		case 3:
			threeStarAndUp.click();
			break;
		case 4:
			fourStarAndUp.click();
			break;
		default:
			System.out.println("Invalid average customer review filter provided");
		}
	}
	
	public String getTheFirstProductText(String productText) {
		String elem = "//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(), '" + productText + "')]";
		return driver.findElement(By.xpath(elem)).getText();
	}
	
	public void selectTheFirstProduct(String productText) {
		String elem = "//span[@class='a-size-medium a-color-base a-text-normal' and contains(text(), '" + productText + "')]";
		driver.findElement(By.xpath(elem)).click();
	}
	
	public String getPrice() {
		return productPrice.getText();
	}
	
	public void deliveryLocation(String pincode) {
		wait.until(ExpectedConditions.elementToBeClickable(setDeliveryLocationLink));
		setDeliveryLocationLink.click();
		wait.until(ExpectedConditions.visibilityOf(pincodeTextBox));
		pincodeTextBox.sendKeys(pincode);
		pincodeApplyBtn.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id='newAccordionRow']//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]")));
	}
	
	public void switchToSponsoredProductIframe() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sponsoredProductIframe));
	}
	
	public void switchToMainPage() {
		driver.switchTo().defaultContent();
	}
	
	public void addToCart() {
		addToCartBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
	}
	
	public void closeAddToCartWindow() {
		addToCartWindowCloseBtn.click();
	}
}
