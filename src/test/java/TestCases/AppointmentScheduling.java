package TestCases;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BaseClass.MRS_Base;
import PageObjects.Appointments_Objects;
import PageObjects.HOME_DEMO_OBJECTS;
import Utilities.Utilities;

public class AppointmentScheduling extends MRS_Base{

	
	@Parameters({"Browser","URL","UserName","password"})
	@Test(priority = 1)
	public void Login(String Browser, String URL, String UserName, String password) {
		driver=intializeBrowser(Browser,URL);

		HOME_DEMO_OBJECTS home=new HOME_DEMO_OBJECTS(driver);
		home.clickonopenMRS2();
		home.enterusername(UserName);
		home.enterpassword(password);
		home.selectlocation();
		home.clickonLogin();

		String expect="Logout";
		String actual=home.LogoutButton();;
		Assert.assertEquals(expect, actual, "Login failed: Title mismatch");
	}

	@Test(priority = 2)
	public void Datevalidation() {
      
		Appointments_Objects ao=new Appointments_Objects(driver);
		ao.clickonAppSch();
		ao.clickonDailyApp();
		
		SoftAssert sa=new SoftAssert();
		
		String format="dd-MMMM-yyyy";
		LocalDate today=LocalDate.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern(format,Locale.ENGLISH);
		String sysDate=today.format(formatter);
		System.out.println("System Date:" +sysDate);
		
		String actual=ao.getdate();
		System.out.println("Application Date:" +actual);
		
		sa.assertEquals(sysDate,actual,"Both dates are wrong.");
		sa.assertAll();
	}
	
	@Test(priority = 3)
	public void printURLs() throws InterruptedException, IOException {
		 Appointments_Objects ao=new Appointments_Objects(driver);
		 ao.alllinks();
		 Thread.sleep(5000);
		 List<WebElement>links=driver.findElements(By.xpath("//div[.//h3[text()='Service Types']]//a | //*[text()='Service Types']/following::ul//li"));
		 for(WebElement link:links) {
			 System.out.println(link.getText());
			
		 }
		 System.out.println("total links: "+links.size());
		 
		 File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 File destination=new File("C:\\Users\\realme\\Desktop\\Selenium_Practice\\OPEN_MRS\\Screenshots\\Passed\\ServiceTypes"+Utilities.timegenerator()+".png");
		 FileUtils.copyFile(srcFile, destination);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
