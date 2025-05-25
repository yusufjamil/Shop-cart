package yousuf.SeleniumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractclasses.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(className="action__submit")
	WebElement placeOrder;
	
	By results=By.xpath("//section[contains(@class,'ta-results')]");
	
	public void selectCountry(String countryName) 
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() 
	{
		try 
		{
			placeOrder.click();
		} 
		catch (ElementClickInterceptedException e) 
		{
		    System.out.println("Click intercepted! Using JavaScript click instead.");
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].click();",placeOrder );
		}
		
		return new ConfirmationPage(driver);
	}

}
