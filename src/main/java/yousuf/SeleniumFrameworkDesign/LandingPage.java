package yousuf.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasses.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement email=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(className="toast-message")
	WebElement errorMessage;
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String userEmail,String userPassword) 
	{
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() throws InterruptedException 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
}
