package stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import yousuf.SeleniumFrameworkDesign.CartPage;
import yousuf.SeleniumFrameworkDesign.CheckoutPage;
import yousuf.SeleniumFrameworkDesign.ConfirmationPage;
import yousuf.SeleniumFrameworkDesign.LandingPage;
import yousuf.SeleniumFrameworkDesign.ProductCatalogue;
import yousuf.SeleniumFrameworkDesign.testcomponents.BaseTest;

public class StepDefinitionsImpl extends BaseTest
{
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException 
	{
		landingPage=launchApplication();
	}
	
	//Log in with username and password .+-matches with string ^$-suggests regular expression
	@Given ("^Log in with Username (.+) and Password (.+)$")
	public void log_in_with_username_and_password(String Username,String Password) throws IOException
	{
		productCatalogue=landingPage.loginApplication(Username, Password);
	}
	
	@When("^I add product (.+) from Cart$")
	public void I_add_product_from_Cart(String productName) throws IOException
	{
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws IOException
	{
		CartPage cartPage= productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) throws IOException
	{
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("^(.*) message is displayed$")
	public void Incorrect_message_is_diplayed(String errorMessage) throws InterruptedException 
	{
		Assert.assertEquals(errorMessage, landingPage.getErrorMessage());
		driver.quit();
	}
	
}
