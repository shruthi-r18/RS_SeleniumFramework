package Listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import  AppTests.BaseTest;



public class listener extends BaseTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		BaseTest.test  = extent.createTest(result.getName());
		logger.info("listener: onTestStart() : test object created ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		BaseTest.test.pass(MarkupHelper.createLabel(result.getName()+" passed", ExtentColor.GREEN));
		logger.debug("listener: onTestSuccess: Test passed  "+result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 BaseTest.test.fail(result.getThrowable());
		 try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String screenShotPath="";
		try {
			screenShotPath = Utils.CommonUtils.captureScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("listeners: onTestFailure: screen shot captured for test "+result.getName());
		try {
			BaseTest.test.addScreenCaptureFromPath(screenShotPath, result.getMethod().getMethodName());
//			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BaseTest.test.fail(MarkupHelper.createLabel(result.getName()+" failed", ExtentColor.RED));
		logger.debug("listener: onTestFailure: Test Failed "+result.getName());
		
		
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
