package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ObjectMap;

public class YkjExitaPage {
	public WebElement element;
	public WebDriver driver;
	ObjectMap objMap = new ObjectMap("ObjectMap/exitpage.properties");
	
	public YkjExitaPage(WebDriver wd) {
		this.driver = wd;
	}
	//admin定位
	public WebElement getAdmin() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.admin"));
		return element;
	}
	//退出定位
	public WebElement getExit() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.exit"));
		return element;
	}
	//签退定位
	public WebElement getAddExit() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.addexit"));
		return element;
	}
	//桌面图标定位
	public WebElement getShowDesk() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.showDesk"));
		return element;
	}
	//团队定位
	public WebElement getTeamlink() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.teamlink"));
		return element;
	}
	//记账金额定位
	public WebElement getCashlink() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.cashlink"));
		return element;
	}
	//admin主题定位
	public WebElement getTheme() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.theme"));
		return element;
	}
	//admin主题选择定位
	public WebElement getThemeChosen() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.themechosen"));
		return element;
	}
	//字体选择定位
	public WebElement getLiteralType() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.literaltype"));
		return element;
	}
	//字体类型选择定位
	public WebElement getLiteralTypeChosen() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.literaltypeChosen"));
		return element;
	}
	//编辑定位
	public WebElement getEdit() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.edit"));
		return element;
	}
	//编辑信息定位
	public WebElement getEditMessage() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editmessage"));
		return element;
	}
	//真实姓名定位
	public WebElement getRealname() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.realname"));
		return element;
	}
	//性别定位
	public WebElement getSex() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.sex"));
		return element;
	}
	//部门定位
	public WebElement getDept() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editdept"));
		return element;
	}
	//角色定位
	public WebElement getRole() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editrole"));
		return element;
	}
	//密码定位
	public WebElement getPassword() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editpassword1"));
		return element;
	}
	//重置密码定位
	public WebElement getPasswordReset() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editpassword2"));
		return element;
	}
	//邮箱定位
	public WebElement getEmail() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editemail"));
		return element;
	}
	//保存按钮定位
	public WebElement getSave() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editesave"));
		return element;
	}
	//上传头像
	public WebElement getPhoto() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editphoto"));
		return element;
	}
	//保存头像
	public WebElement getPhotoSave() throws Exception {
		this.element =driver.findElement(objMap.getlocator("exitpage.editsavephoto"));
		return element;
	}
}
