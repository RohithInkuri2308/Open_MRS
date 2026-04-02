package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Appointments_Objects {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(xpath="//a[@id='appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension']")
	private	WebElement appointmentsceduling;

	@FindBy(xpath = "//a[@class='btn btn-default btn-lg button app big align-self-center dailyappoinment']")
	private WebElement Dailyappointments;


	@FindBy(xpath = "//input[@datepicker-popup='dd-MMMM-yyyy']")
	private WebElement Date;

	@FindBy(linkText = "View all types")
	private WebElement alltypelinks;


    @FindBy(xpath="//span[@ng-click='closeShowAllAppointmentsModal()']")
    private WebElement closebutton;
    
    
	public Appointments_Objects(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		PageFactory.initElements(driver,this);
	}

	public void clickonAppSch() {
		wait.until(ExpectedConditions.elementToBeClickable(appointmentsceduling)).click();;
		//appointmentsceduling.click();
	}
	public void clickonDailyApp() {
		Dailyappointments.click();
	}

	public String getdate() {
		wait.until(ExpectedConditions.visibilityOf(Date));
		return	Date.getAttribute("value");
	}

	public void alllinks() {
		alltypelinks.click();
	}
	public void clickOnClose() {
		//wait.until(ExpectedConditions.elementToBeClickable(closebutton)).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", closebutton);
	}
	
}
