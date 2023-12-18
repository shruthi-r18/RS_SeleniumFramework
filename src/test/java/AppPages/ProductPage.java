package AppPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.CommonUtils;

public class ProductPage extends BasePage{

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@class=\"col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted\"]")
	List<WebElement> productList;
	
	@FindBy(xpath = "//div[@class=\"col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted\"]")
	WebElement products;
	
	By addToCart = By.xpath("//div[@class=\"card-body\"]/button[@class=\"btn w-10 rounded\"]");
	
	
	@FindBy(css = "div.toast-container div.toast-message ")
	WebElement message;
	
	@FindBy(css = "div.toast-container div.toast-title")
	WebElement login_SuccessMessage;

 public String selectProduct(WebDriver driver,String productName) {
		
		CommonUtils.waitForElement(driver, products);
		WebElement selectedProduct = productList.stream().filter(product->
		                             product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				                     .findFirst().orElse(null);
		
		selectedProduct.findElement(addToCart).click(); 
		
		CommonUtils.waitForElement(driver, message);
		
		String msg = message.getText();
		return msg;
	}

public String loginSuccessMessage(WebDriver driver) {
	// TODO Auto-generated method stub
	CommonUtils.waitForElement(driver, login_SuccessMessage);
	return login_SuccessMessage.getText();
}


}
