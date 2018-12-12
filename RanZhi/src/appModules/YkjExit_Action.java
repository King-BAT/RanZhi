package appModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import pageObject.YkjExitaPage;

public class YkjExit_Action {
	// 点击admin退出
	public static void exit(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		ep.getAdmin().click();
		ep.getExit().click();
		System.out.println("成功退出");
	}

	// 点击签退
	public static void addexit(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		ep.getAddExit().click();
		System.out.println("成功退出");
	}

	// 点击桌面
	public static void showDesk(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		// 点击团队
		ep.getTeamlink().click();
		Thread.sleep(5000);
		ep.getShowDesk().click();
		System.out.println("进入桌面");
	}

//点击主题
	public static void themeChosen(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		// 点击admin
		ep.getAdmin().click();
		ep.getTheme().click();
		ep.getThemeChosen().click();
		Thread.sleep(5000);
		System.out.println("选择主题");
	}

//设置字体类型
	public static void literalTypeChosen(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		// 点击admin
		ep.getAdmin().click();
		ep.getLiteralType().click();
		ep.getLiteralTypeChosen().click();
		Thread.sleep(5000);
		System.out.println("选择字体类型");
	}

//编辑个人资料
	public static void EditMessage(WebDriver wd, String realname, String dept, String role, String password,
			String passwordreset, String emailaddress) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		// 点击admin
		ep.getAdmin().click();
		// 点击图片
		ep.getEdit().click();
		Thread.sleep(20000);
		// 点击编辑信息
		ep.getEditMessage().click();
		// 输入真实姓名
		ep.getRealname().sendKeys(realname);
		// 选择性别
		ep.getSex().click();
		// 选择部门
		Select f = new Select(ep.getDept());
		f.selectByValue(dept);
		// 选择角色
		Select r = new Select(ep.getRole());
		f.selectByValue(role);
		// 设置密码
		ep.getPassword().sendKeys(password);
		// 重复密码
		ep.getPasswordReset().sendKeys(passwordreset);
		// 设置邮箱
		ep.getEmail().sendKeys(emailaddress);
		// 保存
		ep.getSave().click();
		Thread.sleep(5000);
		System.out.println("设置个人信息");
	}

//上传头像
	public static void EditPhoto(WebDriver wd, String path) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		// 点击admin
		ep.getAdmin().click();
		// 点击图片
		ep.getEdit().click();
		Thread.sleep(20000);
		// 上传头像
		ep.getPhoto().sendKeys(path);
		Thread.sleep(5000);
		ep.getPhotoSave().click();
		Thread.sleep(5000);
		System.out.println("上传头像");
	}

//功能跳转
	public static void Change(WebDriver wd) throws Exception {

		YkjExitaPage ep = new YkjExitaPage(wd);
		ep.getTeamlink().click();
		Thread.sleep(5000);
		ep.getCashlink().click();
		Thread.sleep(5000);
		System.out.println("功能跳转");
	}

}