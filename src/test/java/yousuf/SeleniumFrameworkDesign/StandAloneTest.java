package yousuf.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import yousuf.SeleniumFrameworkDesign.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String itemName="QWERTY";
		WebDriverManager.chromedriver().driverVersion("133.0.6943.53").setup();
		
		WebDriver driver=new ChromeDriver();
		LandingPage landingPage=new LandingPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		WebElement email=driver.findElement(By.id("userEmail"));
		email.sendKeys("yusufjamil75@gmail.com");
		
		WebElement password=driver.findElement(By.id("userPassword"));
		password.sendKeys("Yousuf1@");
		
		WebElement login=driver.findElement(By.id("login"));
		login.click();
		
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals(itemName))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath
		("//div[contains(@class,'loading')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='alert']")));
		
		WebElement cartButton=driver.findElement(By.cssSelector("button[routerlink*='cart']"));
		cartButton.click();
		
		List<WebElement> cartItems=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartItems.stream().anyMatch(cartItem->cartItem.getText().
		equalsIgnoreCase(itemName));
		
		Assert.assertTrue(match);
		
		WebElement checkout=driver.findElement(By.cssSelector(".totalRow button"));
		checkout.click();
		
		Actions a=new Actions(driver);
		
		WebElement selectCountry=driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		
		a.sendKeys(selectCountry, "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
		
		WebElement countryOption=driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
		countryOption.click();
		
		driver.close();
	}

}
