package yousuf.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import yousuf.SeleniumFrameworkDesign.CartPage;
import yousuf.SeleniumFrameworkDesign.CheckoutPage;
import yousuf.SeleniumFrameworkDesign.ConfirmationPage;
import yousuf.SeleniumFrameworkDesign.LandingPage;
import yousuf.SeleniumFrameworkDesign.ProductCatalogue;
import yousuf.SeleniumFrameworkDesign.testcomponents.BaseTest;

public class SubmitOrderHashmap extends BaseTest {

	String productName;
	
	String email;
	
	String password;
	
	String countryName="India";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage= productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		
		String confirmMessage=confirmationPage.getConfirmationMessage();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() 
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication(email, password);
		OrdersPage ordersPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "yusufjamil75@gmail.com");
		map.put("password", "Yousuf1@");
		map.put("productName", "IPHONE 13 PRO");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "yusufjamil75@gmail.com");
		map1.put("password", "Yousuf1@");
		map1.put("productName", "ZARA COAT 3");
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

}
