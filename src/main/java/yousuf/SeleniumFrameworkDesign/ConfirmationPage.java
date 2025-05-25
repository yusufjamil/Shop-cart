package yousuf.SeleniumFrameworkDesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasses.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmationMessage() 
	{
		return confirmMessage.getText();
	}
}
