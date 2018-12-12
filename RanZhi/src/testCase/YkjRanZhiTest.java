package testCase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModules.Login_Action;
import appModules.YkjExit_Action;
import core.BaseTest;
import utils.Log;
import utils.ReadProperties;

/*
 * @author 杨凯静
 */
public class YkjRanZhiTest extends BaseTest {
	String url = ReadProperties.getPropertyValue("ykjbase_url");

	public YkjRanZhiTest() throws Exception {
		super("firefox");
	}

	@BeforeMethod
	public void openbrowser() throws Exception {
		webtest.open(url);
	}

	@Test(priority = 1) // 登录成功
	public void successLogin() throws InterruptedException {
		try {
			Login_Action.login(getDriver(), "admin", "admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("111");
		}
		Thread.sleep(4000);
		Assert.assertTrue(ifContains("签退"));

	}

	@Test(priority = 2) // 功能跳转功能 禅道用例8453
	public void functionJumpFunction() throws Exception {
		Thread.sleep(1000);
		YkjExit_Action.Change(getDriver());
		Thread.sleep(15000);
		Assert.assertTrue(ifContains("账户"));
	}

	@Test(priority = 3) // 验证桌面功能 禅道用例编号8443
	public void showDeskFunction() throws Exception {
		Thread.sleep(1000);
		YkjExit_Action.showDesk(getDriver());
		Thread.sleep(2000);
		Assert.assertTrue(ifContains("admin"));

	}

	@Test(priority = 4) // 验证退出功能 禅道用例编号8455 添加博客功能验证 禅道用例编号8425-3
	public void exitFunction() throws Exception {
		Thread.sleep(5000);
		YkjExit_Action.exit(getDriver());
		Thread.sleep(5000);

	}

	@Test(priority = 5) // 上传头像 禅道用例8446
	public void uploadPhoto() throws Exception {
		// 图片的路径
		String path = "F:\\1.jpg";
		Thread.sleep(5000);
		YkjExit_Action.EditPhoto(getDriver(), path);
		Assert.assertTrue(ifContains("个人信息"));
	}

	@Test(priority = 6) // 更换主题类型功能 禅道用例8448
	public void changeTheme() throws Exception {
		Thread.sleep(5000);
		YkjExit_Action.themeChosen(getDriver());
	}

	@Test(priority = 7) // 验证 签退功能 禅道用例编号8442
	public void signBackFunction() throws Exception {
		Thread.sleep(5000);
		YkjExit_Action.addexit(getDriver());
		Thread.sleep(18000);
		Assert.assertTrue(ifContains("登录"));
		Thread.sleep(1000);
	}

	@Test(priority = 8) // 更换字体类型功能 禅道用例8447
	public void changeFontFunction() throws Exception {
		Login_Action.login(getDriver(), "admin", "admin");
		Thread.sleep(7000);
		YkjExit_Action.literalTypeChosen(getDriver());
		Assert.assertTrue(ifContains("sign out"));
	}
}
