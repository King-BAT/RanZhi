package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import appModules.SlyRanZhiTest_Action;
import core.BaseTest;
import utils.Log;

public class LoginTest extends BaseTest {
	public LoginTest(String type) throws Exception {
		super("firefox");
		// TODO Auto-generated constructor stub
	}

	@Test
	public void loginSuccess() {
		String url = "http://localhost:8032/ranzhi/www/";
		webtest.open(url);
		try {
			SlyRanZhiTest_Action.login("admin", "admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("Login Fail");
		}
		Assert.assertTrue(ifContains("退出"));
		driver.quit();
	}
}
