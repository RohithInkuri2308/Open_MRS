package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import BaseClass.MRS_Base;
import PageObjects.HOME_DEMO_OBJECTS;


public class Login_MRS2 extends MRS_Base{
	WebDriver driver;
	
	@Parameters({"Browser","URL","UserName","password"})
	@Test
	public void LoginTest(String Browser, String URL, String UserName, String password) {
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
	

}
