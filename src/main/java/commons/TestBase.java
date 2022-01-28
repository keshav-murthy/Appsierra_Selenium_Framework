package commons;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.ListenerTest;
import listener.MyScreenRecorder;

@Listeners(ListenerTest.class)
public class TestBase {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		this.driver = createDriver();
		setUpWebDriver(driver);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			MyScreenRecorder.stopRecording();
			driver.close();
			driver.quit();
		}
	}

	private WebDriver createDriver() {
		String hubUrl = System.getProperty("hubUrl");

		// If the hubUrl is specified use the remote driver, else try to use the local
		// chrome driver.
		if (hubUrl != null) {
			String browserName = System.getProperty("browserName");
			if (browserName == null) {
				throw new RuntimeException("You must specify a browserName");
			}
			try {
				return new RemoteWebDriver(new URL(hubUrl), getOptions(browserName));
			} catch (MalformedURLException e) {
				throw new RuntimeException("The supplied hubUrl: " + hubUrl + " is not a valid url.");
			}
		} else {

			// If we're not using a hub, then attempt to instantiate a local chrome or
			// firefox or IE driver.

			String browserName = System.getProperty("browserName");
			if (browserName == null) {
				throw new RuntimeException("You must specify a browserName");
			}

			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver((ChromeOptions) getOptions(browserName));
			}

			if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver((FirefoxOptions) getOptions(browserName));
			}

			if (browserName.equals("internetexplorer")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver((InternetExplorerOptions) getOptions("internet explorer"));
			}
			return driver;
		}
	}

	private MutableCapabilities getOptions(String browserName) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(browserName);

		if (browserName.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			return options.merge(cap);
		}

		if (browserName.equals("internet explorer")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			return options.merge(cap);
		}

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-gpu");
			options.addArguments("--disable-print-preview");
//            options.addArguments("headless");
//            options.addArguments("window-size=1200x600");
			return options.merge(cap);
		}

		throw new RuntimeException(browserName + " is an invalid browserName.");
	}

	public String workingDirectory() {
		String configuredWorkingDirectory = System.getProperty("workingDirectory");
		if (configuredWorkingDirectory != null) {
			return configuredWorkingDirectory;
		}
		return System.getProperty("user.dir");
	}

	private static void setUpWebDriver(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
	}
}