package pages;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath = "//a//h2")
	public WebElement header;

	@FindBy(xpath = "//p[@class='text_popup']/a[contains(text(),'Signin')]")
	public WebElement signin;

	@FindBy(xpath = "//h1")
	public List<WebElement> sectionHeading;

	private static final Logger lOGGER = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnSection() {
		wait.forElementToBeVisible(header);
		js.clickElement(header);
		lOGGER.info("Clicking on section for registration page pop-up");
	}

	public void windowHandling() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String i : windowHandles)
			driver.switchTo().window(i);
		lOGGER.info("Switching to the login window");
	}

	public void clickOnSignin() {
		wait.forElementToBeVisible(signin);
		js.clickElement(signin);
		lOGGER.info("Clicking on signin of registration page");
	}

	public void printAllHeadings() {
		for (int i = 2; i < sectionHeading.size(); i++) {
			wait.forElementToBeVisible(sectionHeading.get(i));
			System.out.println(sectionHeading.get(i).getText());
		}
	}
}