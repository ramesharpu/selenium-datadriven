package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Common {
	String configFile = "config.properties";

	WebDriver driver; 

	@SuppressWarnings("deprecation")
	public void setupBrowser() {
		String currDir = System.getProperty("user.dir");
		String browser, url;

		ReadFiles readConfigFile = new ReadFiles();
		Properties prop = readConfigFile.readPropertyFile(configFile);
		browser = prop.getProperty("browser");
		url = prop.getProperty("url");


		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currDir+"\\drivers\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", currDir+"\\drivers\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			this.driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("ie")) {
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.
					INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			System.setProperty("webdriver.ie.driver", currDir+"\\drivers\\IEDriverServer.exe");

			this.driver = new InternetExplorerDriver(cap);
		}
		else if(browser.equalsIgnoreCase("html")) {
			driver = new HtmlUnitDriver();
		}
		else {
			System.out.println("Driver object is not provided or valid, hence not invoking the browser");
			System.exit(0);
		}

		this.driver.manage().window().maximize();

		if(url!="")
			this.driver.get(url);//opening the url
		else {
			System.out.println("url is not provided and hence quiting the test run");
			quitBrowser();			
		}
	}

	public void quitBrowser() {
		if(driver!=null)
			this.driver.quit();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
