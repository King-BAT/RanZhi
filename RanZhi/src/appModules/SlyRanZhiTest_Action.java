package appModules;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageObject.SlyRanZhiPage;
import core.BaseTest;

public class SlyRanZhiTest_Action {
	static WebDriver wd = BaseTest.getDriver();
	static SlyRanZhiPage rzp = new SlyRanZhiPage(wd);

	public SlyRanZhiTest_Action(WebDriver wd) {
		SlyRanZhiTest_Action.wd = wd;
	}

	public static void login(String username, String pwd) throws Exception {
		rzp.getLoginName().sendKeys(username);
		rzp.getPassword().sendKeys(pwd);
		rzp.getSubmit().click();
		Thread.sleep(2000);
	}

	public static void test9834() throws Exception {
//		**点击客户管理
		rzp.getCRM().click();
		Thread.sleep(4000);
//		**点击左下角小电脑图标
		rzp.getReturnDesk().click();
		Thread.sleep(2000);
	}

	public static void test9492_1(String searchcontent) throws Exception {
//		**点击项目
		wd.switchTo().defaultContent();
		rzp.getPro().click();
		Thread.sleep(2000);
		wd.switchTo().frame("iframe-3");
//		**点击任务
		rzp.getProTask().click();
		Thread.sleep(2000);
//		**点击搜索按钮
		rzp.getProTaskSearchTab().click();
		Thread.sleep(2000);
//		**在搜索框中输入内容
		rzp.getProTaskSearchValue1().sendKeys(searchcontent);
//		**点击重置按钮
		rzp.getReSet().click();

	}

	public static void test9492_2(String searchcontent) throws Exception {
//		**点击项目
		wd.switchTo().defaultContent();
		rzp.getPro().click();
		Thread.sleep(2000);
		wd.switchTo().frame("iframe-3");
//		**点击任务
		rzp.getProTask().click();
		Thread.sleep(2000);
//		**点击搜索按钮
		rzp.getProTaskSearchTab().click();
		Thread.sleep(2000);
//		**在搜索框中输入内容
		rzp.getProTaskSearchValue1().sendKeys(searchcontent);
//		**点击保存按钮
		rzp.getSave().click();
		Thread.sleep(2000);
		rzp.getSaveAlert().sendKeys(searchcontent);
		rzp.getConfirm().click();

	}

	public static void test9492_3(String searchcontent) throws Exception {
		wd.switchTo().defaultContent();
//		**点击项目
		rzp.getPro().click();
		Thread.sleep(4000);
		wd.switchTo().frame("iframe-3");
//		**点击任务
		rzp.getProTask().click();
		Thread.sleep(2000);
//		**点击搜索按钮
		rzp.getProTaskSearchTab().click();
		Thread.sleep(2000);
//		**在搜索框中输入内容
		rzp.getProTaskSearchValue1().sendKeys(searchcontent);
//		**点击搜索按钮
		rzp.getSearch().click();
		Thread.sleep(2000);

	}

	public static void test10207(String name, String origin, String company) throws Exception {
		wd.switchTo().defaultContent();
//		**点击客户管理
		rzp.getCRM().click();
		Thread.sleep(2000);
//		**点击名单
		wd.switchTo().frame("iframe-1");
		rzp.getNameList().click();
		Thread.sleep(2000);
//		**点击添加名单
		rzp.getAddNameList().click();
		Thread.sleep(2000);
		rzp.getRealName().sendKeys(name);
		rzp.getOrigin().sendKeys(origin);
		rzp.getCompany().sendKeys(company);
		rzp.getSex().click();
		Thread.sleep(2000);
		rzp.getAddNameListSubmit().click();

	}

	public static void test9511() throws Exception {
		wd.switchTo().defaultContent();
		rzp.getAllApps().click();
		Thread.sleep(2000);
//		rzp.getALLClick().click();
//		Thread.sleep(2000);
		rzp.getWenDang().click();
		Thread.sleep(2000);
	}

	public static void test8500() throws Exception {
		wd.switchTo().defaultContent();
		rzp.getAllApps().click();
		Thread.sleep(2000);
		rzp.getDisLeftShow().click();
	}

	public static void test10999(String groupname) throws Exception {
		wd.switchTo().defaultContent();
//		**点击我的地盘
		rzp.getMyHome().click();
		Thread.sleep(2000);
//		
//		**点击联系人
		rzp.getContact().click();
		Thread.sleep(2000);
//		**点击创建分组
		wd.switchTo().frame("iframe-dashboard");
		rzp.getCreateContact().click();
		Thread.sleep(2000);
		rzp.getCreateContactGroupName().sendKeys(groupname);
		rzp.getCreateContactSubmit().click();
		Thread.sleep(2000);
		Assert.assertEquals(true, wd.getPageSource().contains("保存"));

	}
}
