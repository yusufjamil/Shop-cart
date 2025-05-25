package yousuf.SeleniumFrameworkDesign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasses.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	By loader=By.xpath("//div[contains(@class,'loading')]");
	By toastMessage=By.cssSelector("[role='alert']");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList() 
	{
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals(productName))
		.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) 
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToDisappear(loader);
		waitForElementToAppear(toastMessage);
	}

}
