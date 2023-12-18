package AppPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
  

import Utils.CommonUtils;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	public WebElement userName;
	
	@FindBy(id="userPassword")
	public WebElement password;
	
	@FindBy(id="login")
	public WebElement loginButton;
	
	@FindBy(css = " div.invalid-feedback")
	public WebElement noCredentials;
	
	@FindBy(css = "div.toast-message")
	public WebElement invalidCredentials;
	
	@FindBy (xpath="//section[@id=\"sidebar\"]/p[text()=\" Home | \"]")
	WebElement validateLogin;
	
	
	
	public String launchApp(WebDriver driver ,String url) {
		driver.get(url);
		return driver.getCurrentUrl();
		
	}
	public boolean enterUsername(String name) {
		boolean isDone=false;
		if(userName.isDisplayed()) {
			userName.sendKeys(name);
			isDone=true;
		logger.info("LoginPage :enterUsername() : Username entered into the textbox ");	
		}else {
			logger.info("LoginPage :enterUsername() : Username WebElement not visible");
		}
		return isDone;
	}
	
	public boolean enterPassword(String pass) {
		boolean isDone=false;
		if(password.isDisplayed()) {
			password.sendKeys(pass);
			isDone=true;
			logger.info("LoginPage :enterPassword() : Password entered into the textbox ");	
			}else {
				logger.info("LoginPage :enterPassword() : Password WebElement not visible");
			}
		return isDone;
	}
	
	public boolean submitLoginInfo() {
		boolean isDone=false;
		if(loginButton.isDisplayed()) {
			loginButton.click();;
			isDone=true;
			logger.info("LoginPage :submitLoginInfo() : Login button is clicked ");	
			}else {
				logger.info("LoginPage :submitLoginInfo() : Login button WebElement not visible");
			}
		return isDone;
	}
	
	public String getErrorMessage(WebDriver driver) {
		CommonUtils.waitForElement(driver, invalidCredentials);
		String message = "";
		if (invalidCredentials.isDisplayed()){
			message = invalidCredentials.getText();
			logger.info("LoginPage :getErrorMessage() : Message captured -"+invalidCredentials.getText());	
		}else {
			logger.info("LoginPage :getErrorMessage() : Message not captured ");
		}
		return message;
	}
	
	public String noCredentialErrorMessage() {
		String message = "";
		if (noCredentials.isDisplayed()){
			message = noCredentials.getText();
			logger.info("LoginPage :noCredentialErrorMessage() : Message captured -"+noCredentials.getText());	
		}else {
			logger.info("LoginPage :noCredentialErrorMessage() : Message not captured ");
		}
		return message;
	}
	public boolean validateLoggedInApp(WebDriver driver) {
		boolean isDone=false;
		CommonUtils.waitForElement(driver, validateLogin);
		if(validateLogin.isDisplayed()) {
			isDone=true;
			logger.info("LoginPage :validateLoggedInApp() : Applicatin login success ");
		}else {
			logger.info("LoginPage :validateLoggedInApp() :Application is not displayed ");
		}
		ProductPage pg = new ProductPage(driver);
		
		
		return isDone;
	}
}
