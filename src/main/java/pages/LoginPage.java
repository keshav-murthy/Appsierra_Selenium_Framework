package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "(//input[@name='username'])[2]")
	public WebElement username;

	@FindBy(xpath = "(//input[@name='password'])[2]")
	public WebElement password;

	@FindBy(xpath = "(//input[@value='Submit'])[2]")
	public WebElement loginButton;

	@FindBy(id = "alert1")
	public WebElement alert;

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void verifySuccesfullLogin() {

		wait.forElementToBeVisible(alert);
		Assert.assertTrue(alert.isDisplayed());
		lOGGER.info("Checking whether alert is popping-up upon succesfull login");
	}

	public void verifyLoginFailure() {

		wait.forElementToBeVisible(alert);
		Assert.assertTrue(alert.isDisplayed());
		Assert.assertFalse(alert.isDisplayed());
		lOGGER.info("Checking whether alert is popping-up upon succesfull login");
	}
}