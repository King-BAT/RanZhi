package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ObjectMap;

public class LoginPage{
	public WebElement element;
	public WebDriver driver;
	ObjectMap objMap = new ObjectMap("ObjectMap/ranzhi.properties");
	
	public LoginPage(WebDriver wd) {
		this.driver = wd;
	}
	public WebElement getUsername() throws Exception {
		this.element=driver.findElement(objMap.getlocator("login.name"));
		return element;
	}
	public WebElement getPassword() throws Exception {
		this.element=driver.findElement(objMap.getlocator("login.password"));
		return element;
	}
	
	public WebElement getSubmitBtn() throws Exception {
		this.element=driver.findElement(objMap.getlocator("login.submit"));
		return element;
	}

}
