package pageObject;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;

import core.BaseTest;
import core.WebDriverEngine;
import utils.ReadProperties;

public class ZfyRanZhiPage extends BaseTest {

	public ZfyRanZhiPage(String type) throws Exception {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public void loginRZ() throws IOException {
		String url = ReadProperties.getPropertyValue("zfybase_url");
		this.webtest.open(url);
		this.webtest.type("name=account", "zfy666");
		this.webtest.type("name=password", "522506");
		this.webtest.click("xpath=//button[@type='submit']");
	}

	public void getOA() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("OA"));
	}

	public void getOAFrame() throws IOException {
		this.webtest.enterFrame(ReadProperties.getLocatPropertyValue("OAFrame"));
	}

	public void leaveOAFrame() {
		this.webtest.leaveFrame();
	}

	public void getHome() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("home_page"));
	}

	public void addBlock() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("add_block"));
		this.webtest.selectByVisibleText(ReadProperties.getLocatPropertyValue("block_type"),
				/* ReadProperties.getLocatPropertyValue("btype_name") */"系统公告");
		this.webtest.type(ReadProperties.getLocatPropertyValue("block_title"),
				ReadProperties.getLocatPropertyValue("btitle_name"));
		this.webtest.selectByVisibleText(ReadProperties.getLocatPropertyValue("grid_size"),
				ReadProperties.getLocatPropertyValue("gsize"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("color_button"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("chosen_color"));
		this.webtest.typeAndClear(ReadProperties.getLocatPropertyValue("default_tips"),
				ReadProperties.getLocatPropertyValue("set_tips"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
	}

	public void addBlocks() throws IOException {
		Random random = new Random();
		String common = ReadProperties.getLocatPropertyValue("common_parts");
		String[] color = { "'default']", "'primary']", "'warning']", "'danger']", "'success']", "'info']" };
		String BlockName = "添加区块测试_";
		for (int i = 0; i < 10; i++) {
			int block_r = random.nextInt(2) + 1;// 生成随机数来保证添加区块类型是随机的
			int size_r = random.nextInt(6);// 生成随机数来保证添加区块的大小是随机的
			int color_r = random.nextInt(6);// 生成随机数来保证区块颜色是随机的
			this.webtest.click(ReadProperties.getLocatPropertyValue("add_block"));
			this.webtest.selectByIndex(ReadProperties.getLocatPropertyValue("block_type"), block_r);
			this.webtest.type(ReadProperties.getLocatPropertyValue("block_title"), BlockName + i);
			this.webtest.selectByIndex(ReadProperties.getLocatPropertyValue("grid_size"), size_r);
			this.webtest.click(ReadProperties.getLocatPropertyValue("color_button"));
			this.webtest.click(common + color[color_r]);
			if (block_r == 1) {
				this.webtest.typeAndClear(ReadProperties.getLocatPropertyValue("default_tips"),
						ReadProperties.getLocatPropertyValue("set_tips"));
			}
			this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
		}
	}

	public void getPublicNotice() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("public_notice"));
	}

	public void publicNotice(String title, String content, String root_name) throws IOException, InterruptedException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("class_manage"));
		this.webtest.type(ReadProperties.getLocatPropertyValue("root_class"), root_name);
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("notice_list"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("add_notice"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("notice_type"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("notice_chosen"));
		this.webtest.type(ReadProperties.getLocatPropertyValue("title"), title);
		this.webtest.tapClick();
		this.webtest.tapType(content);
		this.webtest.enterClick();
		this.webtest.tapType(content);
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
		/* boolean bl = this.webtest.isTextPresent(title + "</a>"); */
		/* assertEquals(bl, true); */
	}

	public void getDayoff() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("Dayoff"));
	}

	public void dayoffOvertime() throws IOException, InterruptedException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("create_new"));
		if (this.webtest.isChecked(ReadProperties.getLocatPropertyValue("dayoff_type"))) {
			this.webtest.click(ReadProperties.getLocatPropertyValue("dayoff_type"));
		}
		this.webtest.type(ReadProperties.getLocatPropertyValue("dayoff"),
				ReadProperties.getLocatPropertyValue("dayoff_title"));
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("off_start"));
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("off_end"));
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("describe_off"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));

		this.webtest.click(ReadProperties.getLocatPropertyValue("create_new"));
		if (this.webtest.isChecked(ReadProperties.getLocatPropertyValue("working_type"))) {
			this.webtest.click(ReadProperties.getLocatPropertyValue("working_type"));
		}
		this.webtest.type(ReadProperties.getLocatPropertyValue("dayoff"),
				ReadProperties.getLocatPropertyValue("working_title"));
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("working_start"));
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("working_end"));
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType(ReadProperties.getLocatPropertyValue("describe_work"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
	}

	public void clickTest() throws IOException {
		for (int i = 30; i > 0; i--) {
			this.webtest.click(ReadProperties.getLocatPropertyValue("public_notice"));
		}
	}

	public void getApplyReimbursement() throws IOException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("reimbursement"));
	}

	public void scriptCommonChange(String id) {
		String key = "document.getElementById(\"" + id + "\").style ='display: ';";
		((JavascriptExecutor) WebDriverEngine.getDriver()).executeScript(key);
	}

	public void apply(String title, String content) throws IOException, InterruptedException {
		this.webtest.click(ReadProperties.getLocatPropertyValue("apply_reimbursement"));
		this.webtest.type(ReadProperties.getLocatPropertyValue("tname"), title);
		this.webtest.click(ReadProperties.getLocatPropertyValue("adepts"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("chose_adept"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("contract_check"));
		scriptCommonChange("category");
		this.webtest.selectByIndex(ReadProperties.getLocatPropertyValue("category"), 1);
		scriptCommonChange("customer");
		this.webtest.selectByVisibleText(ReadProperties.getLocatPropertyValue("customer"), "张希希");
		scriptCommonChange("contract");
		this.webtest.selectByIndex(ReadProperties.getLocatPropertyValue("contract"), 1);
		this.webtest.click(/* ReadProperties.getLocatPropertyValue("detail") */"link=明细");
		this.webtest.click(ReadProperties.getLocatPropertyValue("datelist"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("day1"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("cate_list1"));
		this.webtest.tapType("理财");
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType("1000");
		this.webtest.tapClick();
		this.webtest.tapClick();
		this.webtest.tapType("报销来源一");
		this.webtest.click(ReadProperties.getLocatPropertyValue("add_xpath"));
		this.webtest.tapClick();
		this.webtest.click(ReadProperties.getLocatPropertyValue("day2"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("cate_list2"));
		this.webtest.tapType("手续");
		this.webtest.enterClick();
		this.webtest.tapClick();
		this.webtest.tapType("500");
		this.webtest.tapClick();
		this.webtest.tapClick();
		this.webtest.tapType("报销来源二");
		this.webtest.click(ReadProperties.getLocatPropertyValue("close_xpath"));
		this.webtest.type(ReadProperties.getLocatPropertyValue("desc_tx"), content);
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
	}

	public void examine() throws IOException, InterruptedException {
		this.webtest.click(/* ReadProperties.getLocatPropertyValue("ready_examine") */"link=待审批");
		this.webtest.click(/* ReadProperties.getLocatPropertyValue("information") */"link=详情");
		this.webtest.click(ReadProperties.getLocatPropertyValue("examine_button"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("examine_pass"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("reimbursement_link"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("sure_link"));
		this.webtest.click(ReadProperties.getLocatPropertyValue("dep_chosen"));
		this.webtest.enterClick();
		this.webtest.click(ReadProperties.getLocatPropertyValue("submit"));
	}

	public void close() {
		BaseTest.driver.quit();
	}

}
