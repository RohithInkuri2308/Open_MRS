package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HOME_DEMO_OBJECTS {
	WebDriver driver;

	@FindBy(xpath="//a[contains(text(),'Explore OpenMRS 2')]")
	private WebElement openMRS2;

	@FindBy(id="username")
	private WebElement username;

	@FindBy(id="password")
	private WebElement password;

	@FindBy(xpath="//li[contains(text(),'Pharmacy')]")
	private WebElement Location;

	@FindBy(id="loginButton")
	private WebElement login;

	@FindBy(xpath ="//li[@class='nav-item logout']//a")
	private WebElement logoutbutton;

	public String logout;


	public HOME_DEMO_OBJECTS(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void clickonopenMRS2() {
		openMRS2.click();
	}

	public void enterusername(String user) {
		username.sendKeys(user);
	}

	public void enterpassword(String pass) {
		password.sendKeys(pass);
	}

	public void selectlocation() {
		Location.click();
	}

	public void clickonLogin() {
		login.click();
	}

	public String LogoutButton() {
		return logout=logoutbutton.getText();
	}
}
