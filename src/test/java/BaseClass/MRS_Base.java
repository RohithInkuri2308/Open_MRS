package BaseClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MRS_Base {
	protected WebDriver driver;
	
	
	@Test()
	public WebDriver intializeBrowser(String Browser, String URL) {
		if(Browser.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver(); 
		}else if(Browser.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		else if(Browser.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}else {
			driver=new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get(URL);
		return driver;
		
	}

}
