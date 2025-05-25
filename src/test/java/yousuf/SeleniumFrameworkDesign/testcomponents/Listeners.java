package yousuf.SeleniumFrameworkDesign.testcomponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import yousuf.SeleniumFrameworkDesign.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener 
{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	//Thread Safe-Doesn't override objects during concurrency
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
 
	public void onTestStart(ITestResult result) 
	{
		test=extent.createTest(result.getMethod().getMethodName());
		//Thread id-Error Validation
		extentTest.set(test);
	}
	public void onTestSuccess(ITestResult result) 
	{
		//test.log(Status.PASS, "Test Pass");
		extentTest.get().log(Status.PASS, "Test Pass");
	}
	public void onTestFailure(ITestResult result) 
	{
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String filePath=null;
		try {
			filePath=getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}
