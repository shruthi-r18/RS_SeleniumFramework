package AppTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AppPages.LoginPage;
import ConstantsUtils.FileConstants;
import Utils.PropertiesFileUtils;

public class LoginTest extends BaseTest {
	
	@Test(groups = "Smoke")
	
	public void TC01_LoginWithValidCredentials() throws IOException {
		WebDriver driver= BaseTest.getdriver();
		LoginPage lp= new LoginPage(driver);
		
		Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
				             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
		test.debug("Landed on application page successfully");
		Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"username")),"Enter a valid username");
		test.debug("Username entered : Success");
		Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"password")),"Enter a valid password");
		test.debug("Password entered : Success");
		Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
		test.debug("Submitted the credentials : Success");
		Assert.assertTrue(lp.validateLoggedInApp(driver));
	}

@Test
	
	public void TC02_LoginWithInValidUsername() throws IOException {
		WebDriver driver= BaseTest.getdriver();
		LoginPage lp= new LoginPage(driver);
		
		Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
				             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
		test.debug("Landed on application page successfully");
		Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"invalid_username")),"Enter a valid username");
		test.debug("Username entered : Success");
		Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"password")),"Enter a valid password");
		test.debug("Password entered : Success");
		Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
		test.debug("Submitted the credentials : Success");
		Assert.assertEquals(lp.getErrorMessage(driver),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"login_error"));
		test.debug("Errormessage displayed : Success");	
}
@Test

public void TC03_LoginWithInValidPassword() throws IOException {
	WebDriver driver= BaseTest.getdriver();
	LoginPage lp= new LoginPage(driver);
	
	Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
			             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
	test.debug("Landed on application page successfully");
	Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"username")),"Enter a valid username");
	test.debug("Username entered : Success");
	Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"invalid_password")),"Enter a valid password");
	test.debug("Password entered : Success");
	Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
	test.debug("Submitted the credentials : Success");
	Assert.assertEquals(lp.getErrorMessage(driver),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"login_error"));
	test.debug("Errormessage displayed : Success");	
}
@Test

public void TC04_LoginWithNoUsername() throws IOException {
	WebDriver driver= BaseTest.getdriver();
	LoginPage lp= new LoginPage(driver);
	
	Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
			             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
	test.debug("Landed on application page successfully");
	Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"no_username")),"Enter a valid username");
	test.debug("Username entered : Success");
	Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"password")),"Enter a valid password");
	test.debug("Password entered : Success");
	Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
	test.debug("Submitted the credentials : Success");
	Assert.assertEquals(lp.noCredentialErrorMessage(),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"no_username"));
	test.debug("Errormessage displayed : Success");
}

@Test

public void TC05_LoginWithNoPassword() throws IOException {
	WebDriver driver= BaseTest.getdriver();
	LoginPage lp= new LoginPage(driver);
	
	Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
			             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
	test.debug("Landed on application page successfully");
	Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"username")),"Enter a valid username");
	test.debug("Username entered : Success");
	Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"no_password")),"Enter a valid password");
	test.debug("Password entered : Success");
	Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
	test.debug("Submitted the credentials : Success");
	Assert.assertEquals(lp.noCredentialErrorMessage(),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"no_password"));
	test.debug("Errormessage displayed : Success");
}
}
