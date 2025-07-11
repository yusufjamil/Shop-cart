package abstractclasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import yousuf.SeleniumFrameworkDesign.CartPage;
import yousuf.SeleniumFrameworkDesign.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='orders']")
	WebElement orders;
	
	public void waitForElementToAppear(By findBy) 
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) 
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(By findBy) 
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage() 
	{
		cartButton.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() 
	{
		orders.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
	}
}
