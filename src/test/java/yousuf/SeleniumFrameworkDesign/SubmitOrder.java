package yousuf.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
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

public class SubmitOrder extends BaseTest {

	String productName;
	
	String email;
	
	String password;
	
	String countryName="India";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(String email,String password,String productName) throws IOException
	{
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(email, password);
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(productName);
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
	public Object[][] getData() 
	{
		
		return new Object[][] {{"yusufjamil75@gmail.com","Yousuf1@","IPHONE 13 PRO"},
		{"shetty@gmail.com","IamKing@000","ZARA COAT 3"}};
	}
	
	

}
