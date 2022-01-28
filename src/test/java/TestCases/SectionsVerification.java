package TestCases;

import static commons.Configuration.url;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.TestBase;
import pages.HomePage;

public class SectionsVerification extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(url.asString());
	}

	@Test
	public void verifySectionHeadings() throws Exception {

		HomePage homepage = new HomePage(driver);
		homepage.printAllHeadings();
		System.out.println("--------------------->>>>>>>>JUST A DEMO POV");
			
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(Exception.class);
		wait.until(null);
	}
}