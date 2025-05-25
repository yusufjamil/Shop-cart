package yousuf.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasses.AbstractComponent;

public class CartPage extends AbstractComponent 
{
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName) 
	{
		Boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().
		equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() 
	{
		checkout.click();
		return new CheckoutPage(driver);
	}
}
