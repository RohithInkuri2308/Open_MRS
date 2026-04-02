package Listeners;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.ExtenReporter;

public class MyListeners implements ITestListener{
	ExtentReports extentReports;
	ExtentTest extest;

	String URL = "https://demo.openmrs.org/";
	String Browser = "Chrome";
	String UserName = "admin";
	String password="Admin123";

	@Override
	public void onStart(ITestContext context) {
		extentReports=ExtenReporter.generateExtenreport(URL,Browser,UserName,password);

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName=result.getName();
		extest=extentReports.createTest(testName);
		extest.log(Status.INFO,testName+" Started execution.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();

		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		if(driver!=null) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String destinationScreenshot = System.getProperty("user.dir")
					+ "\\Screenshots\\Passed\\" + testName + ".png";

			try {
				FileHandler.copy(srcFile, new File(destinationScreenshot));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extest.addScreenCaptureFromPath(destinationScreenshot);
		}
		extest=extentReports.createTest(testName);
		extest.log(Status.PASS,testName+ " Passed.");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();

		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		try {
			if (driver != null) {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				String destinationScreenshot = System.getProperty("user.dir")
						+ "\\Screenshots\\Failed\\" + testName + ".png";

				FileHandler.copy(srcFile, new File(destinationScreenshot));
				extest.addScreenCaptureFromPath(destinationScreenshot);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		extest.log(Status.INFO,result.getThrowable());
		extest.log(Status.FAIL,testName+" Failed.");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extest.log(Status.INFO, result.getThrowable());
		extest.log(Status.SKIP, testName +" got skipped.");

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
