package appModules;

import java.io.IOException;

import pageObject.ZfyRanZhiPage;

public class ZfyRanZhiTestAction {
	public static ZfyRanZhiPage rzp = null;
	
	
	public static void login() throws Exception {
		rzp = new ZfyRanZhiPage("firefox");
		rzp.loginRZ();
	}
	
	public static void test_01() throws IOException {
		rzp.getOA();
		rzp.getOAFrame();
		rzp.getHome();
		rzp.addBlock();
		rzp.leaveOAFrame();
		
	}
	
	public static void test_02() throws IOException{
		rzp.getOA();
		rzp.getOAFrame();
		rzp.addBlocks();
		rzp.leaveOAFrame();
	}
	
	public static void test_03(String title,String content,String root_name) throws IOException, InterruptedException {
		rzp.getOA();
		rzp.getOAFrame();
		rzp.getPublicNotice();
		rzp.publicNotice(title, content,root_name);
		rzp.leaveOAFrame();
	}
	
	public static void test_04() throws IOException, InterruptedException {
		rzp.getOA();
		rzp.getOAFrame();
		rzp.getDayoff();
		rzp.dayoffOvertime();
		rzp.leaveOAFrame();
	}
	
	public static void test_05() throws IOException {
		rzp.getOA();
		rzp.getOAFrame();
		rzp.clickTest();
		rzp.leaveOAFrame();
	}
	
	public static void test_06(String title,String content) throws IOException, InterruptedException {
		rzp.getOA();
		rzp.getOAFrame();
		rzp.getApplyReimbursement();
		rzp.apply(title, content);
		rzp.examine();
		rzp.leaveOAFrame();
	}
}
