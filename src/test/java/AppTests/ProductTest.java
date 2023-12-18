package AppTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppPages.LoginPage;
import AppPages.ProductPage;
import ConstantsUtils.FileConstants;
import Utils.JsonUtils;
import Utils.PropertiesFileUtils;

public class ProductTest extends BaseTest{
	WebDriver driver;
 

@Test(dataProvider="getData",groups = "Smoke" )
	
	public void TC06_SelectProductInProductPage(HashMap<String,String> jsonInput) throws IOException {
		WebDriver driver= BaseTest.getdriver();
		LoginPage lp= new LoginPage(driver);
		ProductPage pp = new ProductPage(driver);
		
		Assert.assertEquals(lp.launchApp(driver, PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url")), 
				             PropertiesFileUtils.readPropertiesFile(FileConstants.LAUNCH_APP_URL,"login_url_verify"),"Application URLs do not match");
		test.debug("Landed on application page successfully");
//		input from properties file
//		Assert.assertTrue(lp.enterUsername(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"username")),"Enter a valid username");
//	    input from json
		Assert.assertTrue(lp.enterUsername(jsonInput.get("email")),"Enter a valid username");
		test.debug("Username entered : Success");
//		input from properties file	
//		Assert.assertTrue(lp.enterPassword(PropertiesFileUtils.readPropertiesFile(FileConstants.LOGIN_CREDENTIALS,"password")),"Enter a valid password");
//	    input from json
		Assert.assertTrue(lp.enterPassword(jsonInput.get("password")),"Enter a valid password");
		test.debug("Password entered : Success");
		Assert.assertTrue(lp.submitLoginInfo(),"Login button should be clicked");
		test.debug("Submitted the credentials : Success");
		Assert.assertEquals(pp.loginSuccessMessage(driver), PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"login_success"));
//		input from properties file	
//		Assert.assertEquals(pp.selectProduct(driver,PropertiesFileUtils.readPropertiesFile(FileConstants.APP_PRODUCT_LIST,"product1")),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"productAdded_message"));
//	    input from json
		Assert.assertEquals(pp.selectProduct(driver,jsonInput.get("product")),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"productAdded_message"));
		Assert.assertEquals(pp.selectProduct(driver,jsonInput.get("product1")),PropertiesFileUtils.readPropertiesFile(FileConstants.APP_DISPLAY_MESSAGES,"productAdded_message"));

}




@DataProvider
public Object[][] getData() throws IOException
{

	
	List<HashMap<String, String>> data;
	
		data = JsonUtils.getJsonDataToMap();
	
	return new Object[][]  {{data.get(0)}, {data.get(1) } };
	
}
}
