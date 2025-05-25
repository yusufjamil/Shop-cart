package yousuf.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import yousuf.SeleniumFrameworkDesign.testcomponents.BaseTest;
import org.testng.IRetryAnalyzer;

public class ErrorValidations extends BaseTest
{
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=IRetryAnalyzer.class)
	
	public void submitOrder() throws IOException, InterruptedException{
	
	String email="yusufjamil75@gmail.co";
	
	String password="Yousuf1@";
	
	landingPage.loginApplication(email, password);
	
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
}
