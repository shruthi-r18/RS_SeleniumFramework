package AppTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class BaseTest {

	public static ThreadLocal<WebDriver> threadlocalDriver = new ThreadLocal<WebDriver>();
	protected static Logger logger = LogManager.getLogger("");
	protected static ExtentSparkReporter report = null;
//	ExtentHtmlReporter report = null;
	protected static ExtentReports extent = new ExtentReports();
	public static ExtentTest test = null;
	public static WebDriver driver;

	@BeforeSuite
	public void setup() {

		logger.info("BaseTest : setup() : Report configuration started");
		String filePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\" + filePrefix
				+ "_ecom.html";
		report = new ExtentSparkReporter(filePath);// use extentsparkreport to attach screenshot
//		report = new ExtentHtmlReporter(filePath);//cannot attach screen shot imge with extentreoprthtml
		report.config().setDocumentTitle("Rahul Shetty Academy - Ecommerce Web Application Testing");
		report.config().setReportName("Login Testing");
		extent.attachReporter(report);
		extent.setSystemInfo("Ecommerce Testing", "Anju");
		logger.info("BaseTest : setup() : Report configuration complete");

	}

	@AfterSuite
	public void teardown() {
		extent.flush();
		logger.info("BaseTest : teardown() : Teardown complete");
	}

	@BeforeMethod
	public void setdriver() {
		driver = BaseTest.getBrowserType("firefox", "false");
		logger.info("BaseTest : setdriver() : Browser configuration complete");
		threadlocalDriver.set(driver);
		logger.info("BaseTest : setdriver() : driver saved on ThreadLocal object");
	}

	public static WebDriver getdriver() {
		logger.info("BaseTest : getdriver() : driver retrieved from thread");
		return threadlocalDriver.get();
	}

	@AfterMethod
	public void removedriver() {
		BaseTest.getdriver().close();
		threadlocalDriver.remove();
		logger.info("BaseTest : removedriver() : driver removed from thread");
	}

	private static WebDriver getBrowserType(String bName, String headless) {
		String browserName = bName.toLowerCase();

		switch (browserName) {

		case "chrome":
			logger.info("BaseTest: getBrowserType() : chrome driver configuration started");
			driver = new ChromeDriver();
			if (headless.equalsIgnoreCase("true")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
				logger.info("BaseTest: getBrowserType() : chrome headless mode activated");
			}
			logger.info("BaseTest: getBrowserType() : chrome driver configured");
			break;

		case "firefox":
			logger.info("BaseTest: getBrowserType() : firefox driver configuration started");
			driver = new FirefoxDriver();
			if (headless.equalsIgnoreCase("true")) {
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
				logger.info("BaseTest: getBrowserType() : firefox headless mode activated");
			}
			logger.info("BaseTest: getBrowserType() : firefox driver configured");
			break;

		case "edge":
			logger.info("BaseTest: getBrowserType() : edge driver configuration started");
			driver = new EdgeDriver();
			if (headless.equalsIgnoreCase("true")) {
				EdgeOptions eo = new EdgeOptions();
				eo.addArguments("--headless");
				driver = new EdgeDriver(eo);
				logger.info("BaseTest: getBrowserType() : edge headless mode activated");
			}
			logger.info("BaseTest: getBrowserType() : edge driver configured");
			break;

		case "safari":
			logger.info("BaseTest: getBrowserType() : safari driver configuration started");
			driver = new SafariDriver();
			logger.info("BaseTest: getBrowserType() : safari driver configured");
			break;

		case "":
			logger.info("BaseTest: getBrowserType() : chrome browser set as default, driver configuration started");
			driver = new ChromeDriver();
			logger.info("BaseTest: getBrowserType() : chrome browser set as default, driver configured");
			break;

		}
		driver.manage().window().maximize();

		return driver;
	}
}
