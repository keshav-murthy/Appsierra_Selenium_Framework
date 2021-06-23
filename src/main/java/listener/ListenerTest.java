package listener;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commons.TestBase;
import pages.LoginPage;

public class ListenerTest implements ITestListener, IAnnotationTransformer {

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());

	public void onFinish(ITestResult result) throws Exception {
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {

		String folderPath = "./screenshots/";
		createFolder(folderPath);
		try {

			String filename = result.getMethod().getMethodName();
			WebDriver driver = ((TestBase) result.getInstance()).driver;
			FileOutputStream out = new FileOutputStream(folderPath + filename + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void createFolder(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
	}

	public void onTestStart(ITestResult result) {
		lOGGER.info("*******************" + result.getTestClass().getName() + "." + result.getMethod().getMethodName()
				+ " started*******************");
		try {
			MyScreenRecorder.startRecording(result.getTestClass().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		lOGGER.info("*******************" + result.getTestClass().getName() + "." + result.getMethod().getMethodName()
				+ " passed*******************");
	}
}