package testCase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import appModules.Login_Action;
import core.BaseTest;
import utils.ReadProperties;

public class FbRanZhiTest extends BaseTest {
	public FbRanZhiTest(String type) throws Exception {
		super("firefox");
	}

	@BeforeClass
	public void doBeforeClass() throws Exception {
		String url = ReadProperties.getPropertyValue("fbbase_url");
		webtest.open(url);
		Login_Action.login(getDriver(), "admin", "admin");
	}

	// 9275
	@Test(priority = 0)
	public void AouncementDelete() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// 点击日常办公
		webtest.click("id=s-menu-2");
		Thread.sleep(3000);
		webtest.enterFrame("iframe-2");
		Thread.sleep(4000);
		// 点击公告
		webtest.click("xpath=/html/body/nav[1]/ul/li[3]/a");
		Thread.sleep(2000);
		// 点击搜索
		webtest.click("xpath=//a[contains(.,'搜索')]");
		Thread.sleep(2000);
		// 搜索框中输入搜索内容
		webtest.type("xpath=//input[@name='value1']", "aaa");
		webtest.type("xpath=//input[@name='value4']", "aaa");
		// 点击搜索
		webtest.click("xpath=//button[contains(.,'搜索')]");

	}

	// 团队 8425添加博客
	@Test(priority = 1)
	public void LoginRanZhi() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		// 点击团队
		webtest.click("id=s-menu-6");
		Thread.sleep(5000);
		// 点击博客
		webtest.enterFrame("iframe-6");
		webtest.click("xpath=//a[contains(.,'博客')]");
		Thread.sleep(3000);

		// 点"添加博客"
		webtest.click("xpath=//a[contains(.,'添加博客')]");
		Thread.sleep(3000);
		webtest.click("class=chosen-choices");
		Thread.sleep(3000);
		// 填写博客内容
		webtest.click("xpath=/html/body/div[2]/div/div[2]/form/table/tbody/tr[1]/td[1]/div[2]/div/ul/li[2]");
		webtest.type("name=title", "生活小助手");
		webtest.click("xpath=//iframe[contains(@class,'ke-edit-iframe')]");
		Thread.sleep(2000);
		webtest.tapType("你好，明天");
		webtest.click("xpath=//button[contains(.,'保存')]");
	}

	// 9842
	@Test(priority = 2)
	public void ContactTry() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// 我的地盘
		webtest.click("id=s-menu-dashboard");

		Thread.sleep(2000);
		// 联系人
		webtest.click("xpath=//a[contains(.,'联系人')]");
		Thread.sleep(4000);
		// 点击创建分组
		webtest.enterFrame("iframe-dashboard");
		webtest.click("xpath=//a[contains(.,' 创建分组')]");
		Thread.sleep(2000);
		webtest.click("xpath=//input[@name='name']");
		Thread.sleep(2000);
		// 分组名称
		webtest.tapType("成员");
		webtest.click("xpath=//ul[@class='chosen-choices']");
		Thread.sleep(2000);
		// 分组成员
		webtest.tapType("admin");
		webtest.enterClick();
		// 勾选公开
		webtest.click("xpath=//input[@name='public']");
		Thread.sleep(1000);
		// 保存
		webtest.click("xpath=//button[@type='submit']");
	}

	// 9760
	@Test(priority = 3)
	public void CustomDo() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		//客户管理
		webtest.click("id=s-menu-1");
		Thread.sleep(3000);
		//订单
		webtest.enterFrame("iframe-1");
		Thread.sleep(3000);
		webtest.click("xpath=/html/body/nav[1]/ul/li[2]/a");
		Thread.sleep(3000);
		//创建订单
		webtest.click("xpath=//a[contains(.,' 创建订单')]");
		Thread.sleep(2000);
		//选择客户
		webtest.click("xpath=/html/body/div/div[1]/div[2]/form/table/tbody/tr[1]/td/div[2]/div/a");
		Thread.sleep(3000);
		webtest.click("xpath=/html/body/div/div[1]/div[2]/form/table/tbody/tr[1]/td/div[2]/div/div/ul/li[2]");
		Thread.sleep(3000);
		webtest.click("xpath=//ul[@class='chosen-choices']");
		Thread.sleep(2000);
		webtest.click("xpath=/html/body/div/div[1]/div[2]/form/table/tbody/tr[6]/td/div[2]/div/div/ul/li");
		
//		webtest.click("");
		webtest.type("xpath=//input[@name='plan']", "10000");
		
		webtest.click("xpath=//button[@id='submit']");
		Thread.sleep(2000);
		//点击沟通
		webtest.click("xpath=//a[contains(.,'沟通')]");
		Thread.sleep(5000);
		webtest.click("xpath=//*[@id=\"contact_chosen\"]");
		Thread.sleep(2000);
		webtest.tapType("yy");
		webtest.enterClick();
		//webtest.getElementFinder();
		Thread.sleep(2000);
		webtest.click("xpath=//textarea[@name='comment']");
		Thread.sleep(2000);
		webtest.tapType("符合商业利益");
		webtest.click("xpath=//button[@id='submit']");
		
	}

	// 后台管理9172
	@Test(priority = 4)
	public void AddDepartment() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		webtest.click("id=s-menu-superadmin");
		Thread.sleep(5000);
		webtest.enterFrame("iframe-superadmin");
		webtest.click("xpath=//a[contains(.,'组织')]");
		Thread.sleep(3000);
		webtest.click("xpath=//a[contains(.,'维护部门')]");
		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(By.id("children[]"));
		list.get(list.size()-1).sendKeys("结构");
		Thread.sleep(3000);
		webtest.click("xpath=//button[contains(.,'保存')]");
}

	// 8432
	@Test(priority = 5)
	public void ReturnCard() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// 团队
		webtest.click("id=s-menu-6");
		Thread.sleep(6000);
		webtest.enterFrame("iframe-6");
		// 论坛
		webtest.click("xpath=/html/body/nav/ul/li[2]/a");
		Thread.sleep(2000);
		// 点击某子模块
		webtest.click("xpath=//a[contains(.,'员工')]");
		Thread.sleep(2000);
		// 点击子模块内的帖子
		webtest.click("xpath=/html/body/div/div/div[2]/div/table/tbody/tr/td[2]/a");
		Thread.sleep(2000);
		// 定位回帖
		webtest.click("xpath=//iframe[contains(@class,'ke-edit-iframe')]");
		Thread.sleep(2000);
		// 回帖内容
		webtest.tapType("你好");
		// 保存
		webtest.click("xpath=//button[contains(.,'保存')]");
	}

	// 团队8426,8427查找已经存在的博客并编辑
	@Test(priority = 6)
	public void SearchBk() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		// 点击“团队”
		webtest.click("id=s-menu-6");
		Thread.sleep(2000);
		webtest.enterFrame("iframe-6");
		Thread.sleep(2000);
		// 点击团队下的博客
		webtest.click("xpath=//a[contains(.,'博客')]");
		Thread.sleep(3000);
		// 搜索博客
		webtest.type("xpath=//input[@name='searchInput']", "为人处世");
		Thread.sleep(2000);
		webtest.click("xpath=//i[@class='icon icon-search']");
		Thread.sleep(3000);
		// 编辑博客
		webtest.click("xpath=/html/body/div/div/div[2]/section/div[1]/div[2]/span[4]/a[1]");
		Thread.sleep(2000);
		webtest.click("xpath=//iframe[contains(@class,'ke-edit-iframe')]");
		Thread.sleep(2000);
		webtest.tapType("对待他人的态度就代表这别人对你的态度");
		webtest.click("xpath=//button[@type='submit']");
	}

	// 9062
	@Test(priority = 7)
	public void SwitchThemeTest() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		webtest.click("id=start");
		Thread.sleep(2000);
		// 点击右下角个人头像
		webtest.mouseoverElement("xpath=/html/body/div[1]/div[1]/ul/li[4]/a/i");
		Thread.sleep(1000);
		// 选择主题清晰
		webtest.click("xpath=/html/body/div[1]/div[1]/ul/li[4]/ul/li[2]/a");
	}

	// 8435
	@Test(priority = 8)
	public void Top() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		webtest.click("id=s-menu-6");
		Thread.sleep(6000);
		webtest.enterFrame("iframe-6");
		webtest.click("xpath=/html/body/nav/ul/li[2]/a");
		Thread.sleep(2000);
		webtest.click("xpath=//a[contains(.,'员工')]");
		Thread.sleep(2000);
		webtest.click("xpath=/html/body/div/div/div[2]/div/table/tbody/tr/td[2]/a");
		Thread.sleep(2000);
		webtest.click("xpath=//i[@class='icon-flag-alt']");
		Thread.sleep(2000);
		webtest.click("xpath=/html/body/div/div/div[2]/div[1]/div[3]/div/span/ul/li[2]/a");
	}
}
