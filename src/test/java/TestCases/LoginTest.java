package TestCases;

import static commons.Configuration.password;
import static commons.Configuration.url;
import static commons.Configuration.username;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.TestBase;
import listener.MyScreenRecorder;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(url.asString());
	}

	@Test
	public void verifyLoginWithValidCred() throws Exception {

		HomePage homepage = new HomePage(driver);
		homepage.clickOnSection();
		homepage.windowHandling();
		homepage.clickOnSignin();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.username.sendKeys(username.asString());
		loginPage.password.sendKeys(password.asString());
		loginPage.loginButton.click();
		loginPage.verifySuccesfullLogin();
	}
}