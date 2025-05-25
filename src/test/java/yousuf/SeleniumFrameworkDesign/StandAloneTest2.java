package yousuf.SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import yousuf.SeleniumFrameworkDesign.CartPage;
import yousuf.SeleniumFrameworkDesign.CheckoutPage;
import yousuf.SeleniumFrameworkDesign.ConfirmationPage;
import yousuf.SeleniumFrameworkDesign.LandingPage;
import yousuf.SeleniumFrameworkDesign.ProductCatalogue;
import yousuf.SeleniumFrameworkDesign.testcomponents.BaseTest;

public class StandAloneTest2 extends BaseTest {


	@Test
	public void submitOrder() throws IOException
	{
		// TODO Auto-generated method stub
		
		String productName="IPHONE 13 PRO";
		
		String email="yusufjamil75@gmail.com";
		
		String password="Yousuf1@";
		
		String countryName="India";
		
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

}
