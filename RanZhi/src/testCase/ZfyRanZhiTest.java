package testCase;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import appModules.ZfyRanZhiTestAction;

public class ZfyRanZhiTest {

	@BeforeClass
	public void loginSuccess() throws Exception {
		try {
			ZfyRanZhiTestAction.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 0)
	public void testOA_add_block() {
		try {
			ZfyRanZhiTestAction.test_01();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 1)
	public void testOA_add_blocks() {
		try {
			ZfyRanZhiTestAction.test_02();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 2)
	public void testOA_public_notice() throws IOException, InterruptedException {
		String title = "PO测试公告标题";
		String content = "正文内容！";
		String root_name = "根类目";
		ZfyRanZhiTestAction.test_03(title, content,root_name);
		
	}
	@Test(priority = 3)
	public void testOA_dayoff_overtime() throws InterruptedException {
		try {
			ZfyRanZhiTestAction.test_04();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 4)
	public void testOA_PN_click() {
		try {
			ZfyRanZhiTestAction.test_05();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority = 5)
	public void testOA_apply_reimbursement() throws InterruptedException {
		String title = "PO测试申请报销";
		String content = "正文内容!";
		try {
			ZfyRanZhiTestAction.test_06(title, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void close() {
		ZfyRanZhiTestAction.rzp.close();
	}
}
