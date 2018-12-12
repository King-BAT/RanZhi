package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ObjectMap;

public class SlyRanZhiPage {
	public WebElement element;
	public WebDriver driver;
	ObjectMap objMap = new ObjectMap("ObjectMap/ranzhi.properties");

	public SlyRanZhiPage(WebDriver wd) {
		this.driver = wd;
	}

	public WebElement getLoginName() throws Exception {
		this.element = driver.findElement(objMap.getlocator("login.name"));
		return element;
	}

	public WebElement getPassword() throws Exception {
		this.element = driver.findElement(objMap.getlocator("login.password"));
		return element;
	}

	public WebElement getSubmit() throws Exception {
		this.element = driver.findElement(objMap.getlocator("login.submit"));
		return element;
	}

	public WebElement getCRM() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm"));
		return element;
	}

	public WebElement getReturnDesk() throws Exception {
		this.element = driver.findElement(objMap.getlocator("showDesk"));
		return element;
	}

	public WebElement getPro() throws Exception {
		this.element = driver.findElement(objMap.getlocator("pro"));
		return element;
	}

	public WebElement getProTask() throws Exception {
		this.element = driver.findElement(objMap.getlocator("protask"));
		return element;
	}

	public WebElement getProTaskSearchTab() throws Exception {
		this.element = driver.findElement(objMap.getlocator("protasksearchTab"));
		return element;
	}

	public WebElement getProTaskSearchValue1() throws Exception {
		this.element = driver.findElement(objMap.getlocator("protasksearchvaluebox1"));
		return element;
	}

	public WebElement getReSet() throws Exception {
		this.element = driver.findElement(objMap.getlocator("reset"));
		return element;
	}

	public WebElement getSave() throws Exception {
		this.element = driver.findElement(objMap.getlocator("save"));
		return element;
	}

	public WebElement getSaveAlert() throws Exception {
		this.element = driver.findElement(objMap.getlocator("savealert"));
		return element;
	}

	public WebElement getConfirm() throws Exception {
		this.element = driver.findElement(objMap.getlocator("confirm"));
		return element;
	}

	public WebElement getSearch() throws Exception {
		this.element = driver.findElement(objMap.getlocator("search"));
		return element;
	}

	public WebElement getAllApps() throws Exception {
		this.element = driver.findElement(objMap.getlocator("allapps"));
		return element;
	}

//	public WebElement getRanZhi4_9() throws Exception{
//		this.element = driver.findElement(objMap.getlocator("ranzhi4.9"));
//		return element;
//	}
	public WebElement getWenDang() throws Exception {
		this.element = driver.findElement(objMap.getlocator("wendang"));
		return element;
	}

	public WebElement getALLClick() throws Exception {
		this.element = driver.findElement(objMap.getlocator("ALLClick"));
		return element;

	}

	public WebElement getDisLeftShow() throws Exception {
		this.element = driver.findElement(objMap.getlocator("disleftshow"));
		return element;
	}

	public WebElement getNameList() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.namelist"));
		return element;
	}

	public WebElement getAddNameList() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist"));
		return element;
	}

	public WebElement getRealName() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist.realname"));
		return element;
	}

	public WebElement getOrigin() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist.origin"));
		return element;
	}

	public WebElement getCompany() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist.company"));
		return element;
	}

	public WebElement getSex() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist.sex"));
		return element;
	}

	public WebElement getAddNameListSubmit() throws Exception {
		this.element = driver.findElement(objMap.getlocator("crm.addnamelist.submit"));
		return element;
	}

	public WebElement getMyHome() throws Exception {
		this.element = driver.findElement(objMap.getlocator("myhome"));
		return element;
	}

	public WebElement getContact() throws Exception {
		this.element = driver.findElement(objMap.getlocator("myhome.contact"));
		return element;
	}

	public WebElement getCreateContact() throws Exception {
		this.element = driver.findElement(objMap.getlocator("myhome.contact.createcontact"));
		return element;
	}

	public WebElement getCreateContactGroupName() throws Exception {
		this.element = driver.findElement(objMap.getlocator("myhome.groupname"));
		return element;
	}

	public WebElement getCreateContactSubmit() throws Exception {
		this.element = driver.findElement(objMap.getlocator("myhome.creategroup.submit"));
		return element;
	}

}
