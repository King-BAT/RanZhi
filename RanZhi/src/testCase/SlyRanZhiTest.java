package testCase;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
/*
 * @author **孙立莹
 */
import org.testng.annotations.Test;

import appModules.SlyRanZhiTest_Action;
import core.BaseTest;
import utils.Log;
import utils.ReadProperties;
import core.WebTestListener;

//@Listeners(WebTestListener.class)
public class SlyRanZhiTest extends BaseTest {
	public SlyRanZhiTest(String type) throws Exception {
		super("firefox");
		// TODO Auto-generated constructor stub
	}

	@BeforeClass
	public void loginSuccess() throws IOException {
		String url = ReadProperties.getPropertyValue("slybase_url");
		webtest.open(url);
		try {
			SlyRanZhiTest_Action.login("admin", "admin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("Login Fail");
		}
	}


	@Test(priority = 0)
//	ID = 9834
	public void returnDesk() throws Exception {
		SlyRanZhiTest_Action.test9834();
	}

	@Test(priority = 1)
//	ID = 9492-1
	public void chongzhi() throws Exception {
		String searchcontent = "123";
		SlyRanZhiTest_Action.test9492_1(searchcontent);
	}

	@Test(priority = 2)
//	ID = 9492-2
	public void testsave() throws Exception {
		String searchcontent = "123";
		SlyRanZhiTest_Action.test9492_2(searchcontent);
	}

	@Test(priority = 3)
//	ID = 9492-3
	public void testsearch() throws Exception {
		String searchcontent = "123";
		SlyRanZhiTest_Action.test9492_3(searchcontent);
	}

	@Test(priority = 4)
//	ID = 10207
	public void testAllApps() throws Exception {
		String name = "孙立莹";
		String origin = "河北石家庄";
		String company = "河北师大";
		SlyRanZhiTest_Action.test10207(name, origin, company);
		;
	}

//	ID = 9511
	@Test(priority = 5)
	public void testTurnTo() throws Exception {
		SlyRanZhiTest_Action.test9511();
	}

//	ID = 8550
	@Test(priority = 6)
	public void testDisLeftShow() throws Exception {
		SlyRanZhiTest_Action.test8500();
	}

//	ID=10999
	@Test(priority = 7)
	public void testAddGroup() throws Exception {
		SlyRanZhiTest_Action.test10999("小组一");
	}
}
