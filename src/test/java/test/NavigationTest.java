package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.Navigation;

public class NavigationTest extends BaseTest{
	Navigation nav;

	@BeforeClass
	public void beforeClass() {
		nav = new Navigation(driver);
	}


	@Test(description = "'Create A Wish List' navigation test")
	public void createWishListNavigationTest() {
		nav.createWishList();
		String text = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		assertTrue(text.equals("Your Lists"));
	}

	@Test(description = "'Amazon Pay' navigation test")
	public void amazonPayNavigationTest() {
		nav.amazonPay();
		boolean elem = driver.findElement(By.xpath("//span[contains(text(),'Amazon Pay balance')]")).isDisplayed();
		assertTrue(elem);
	}

	@Test(description = "'New Releases' navigation test")
	public void newReleasesNavigationTest() {
		nav.newReleases();	  
		boolean elem = driver.findElement(By.xpath("//div[contains(text(),'Amazon Hot New Releases')]")).isDisplayed();
		assertTrue(elem);
	}


}
