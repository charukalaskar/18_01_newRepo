package coverFoxBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class Base {
	static protected WebDriver driver;

	// Open Browser
	public void launchCoverFox() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Reporter.log("Launching Browser:", true);
		driver.get("https://www.coverfox.com/");
		Thread.sleep(1000);

	}

	// Close Browser
	public void closeCoverFox() throws InterruptedException {
		Reporter.log("Closing Browser:", true);
		driver.close();
		Thread.sleep(1000);
	}
}
