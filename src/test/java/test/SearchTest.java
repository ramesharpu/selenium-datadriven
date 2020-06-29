package test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pom.ProductSearch;

public class SearchTest extends BaseTest{
	ProductSearch ps;
	String searchText = "Redmi Note 8 Pro";
	WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass() {
		ps = new ProductSearch(driver);
		wait = new WebDriverWait(driver, 20);
	}
	
	@Test(description = "Test case to search for Mi Mobile")
	public void searchForMiMobile() {
		ps.searchProduct(searchText);
		String totalResult = ps.getSearchResultsSummary(); 
		assertTrue(totalResult.contains(searchText));
	}

	@Test(description = "Test case to select 4 Starts and above average customer rating",
			dependsOnMethods = "searchForMiMobile")
	public void select4StartAndUpRating() {
		ps.avgCustomerReviewFilter(4);
		WebElement filterElem = driver.findElement(By.xpath(	"//a/span[text()='4 Stars & Up']"));
		String filterMessage = "4 Stars & Up";
		assertTrue(filterElem.getText().equals(filterMessage));
	}
	
	@Test(description = "Test case to select mi mobile after searching it",
			dependsOnMethods = "select4StartAndUpRating")
	public void productSelectionTest() {
		String productLinkName = ps.getTheFirstProductText(searchText);
		ps.selectTheFirstProduct(searchText);

		Set<String> listOfWindows = driver.getWindowHandles();
		List<String> tabs = new ArrayList<String>(listOfWindows); 
		driver.close();
		driver.switchTo().window(tabs.get(1));
		String pageTitle = driver.getTitle();
		assertTrue(pageTitle.contains(productLinkName));		
	}
	
	@Parameters({"mobilePrice"})
	@Test(description = "Test case to validate the Mi Mobile price",
			dependsOnMethods = "productSelectionTest")
	public void priceValidation(String expectedPrice) {
		String actualPrice = ps.getPrice().substring(2);
		assertTrue(actualPrice.equals(expectedPrice));
	}

	@Parameters({"pincode"})
	@Test(description = "Test case to validate delivery location",
			dependsOnMethods = "productSelectionTest")
	public void deliveryLocationValidation(String pincode) {
		ps.deliveryLocation(pincode);
		WebElement elem = driver.findElement(By.xpath("//div[@id='newAccordionRow']//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]"));
		assertTrue(elem.getText().contains(pincode));
	}

	@Test(description = "Test case to validate the sponsored link",
			dependsOnMethods = "deliveryLocationValidation")
	public void sponsoredProductValidation() {
		ps.switchToSponsoredProductIframe();
		String xpath = "//a[@class='a-link-normal sp_hqp_shared_adLink a-text-normal']";
		WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
		elem.click();

		Set<String> listOfWindows = driver.getWindowHandles();
		List<String> tabs = new ArrayList<String>(listOfWindows); 
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		ps.switchToMainPage();
	}

	@Test(description = "Test case to validate the Add to Cart functionality",
			dependsOnMethods = "sponsoredProductValidation")
	public void addToCartValidation() {
		String xpathExpression = "//div[@id='attach-added-to-cart-message']//h4[@class='a-alert-heading' and contains(text(), 'Added to Cart')]";
		String expectedMsg = "Added to Cart";
		ps.addToCart();
		String confirmMsg = driver.findElement(By.xpath(xpathExpression)).getText();
		assertTrue(confirmMsg.equals(expectedMsg));
		ps.closeAddToCartWindow();
	}
		
	@Test(description = "Test case to scroll down till Technical Details section on the product details page",
			dependsOnMethods = "addToCartValidation")
	public void scrollTillTechnicalDetails() {
		WebElement elem = driver.findElement(By.xpath("//span[text()='Technical Details']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView();", elem);
	}



}
