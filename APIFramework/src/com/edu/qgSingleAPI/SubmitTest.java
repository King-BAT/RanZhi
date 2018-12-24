package com.edu.qgSingleAPI;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.common.*;

import net.sf.json.JSONObject;

/*
 * @author = 生春月
 */
public class SubmitTest {

	CookieStore cookie;
	String url;
	JSONObject para;

	@BeforeMethod
	public void before() throws IOException, Exception {
		cookie = Common.getLoginCookie("200000000011", "netease123");
		url = "http://study-perf.qa.netease.com/fgadmin/orders/submit";
		para = new JSONObject();
	}

	@Test(description = "提交成功")
	public void submit1() {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少skuIds参数")
	public void submit2() {
		para.element("skuNumbers", "1");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少skuNumbers参数")
	public void submit3() {
		para.element("skuIds", "2");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少stockIds参数")
	public void submit4() {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少addressDetail参数")
	public void submit5() {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少province参数")
	public void submit6() {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，province和city不匹配")
	public void submit7() throws IOException, Exception {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("stockIds", 74966313);
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "石家庄市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少skuIds和skunumbers两个参数")
	public void submit8() {
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，缺少skuIds、skunumbers和stockIds参数")
	public void submit9() {
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，skuIds为int类型")
	public void submita() {
		para.element("skuIds", 1);
		para.element("skuNumbers", "1");
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，skunumbers参数类型为int类型")
	public void submitb() {
		para.element("skuIds", "2");
		para.element("skuNumbers", 1);
		para.element("stockIds", "74966313");
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
	}

	@Test(description = "提交失败，stockIds参数为int类型")
	public void submitc() throws Exception {
		para.element("skuIds", "2");
		para.element("skuNumbers", "1");
		para.element("stockIds", 74966313);
		para.element("receiverName", "晓");
		para.element("cellPhone", "15226588864");
		para.element("addressDetail", "组织");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("tranfportFee", 6.0);
		para.element("lohisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);
		String result = HttpDriver.doPost(url, para, cookie);
		System.out.println(result);
	}

	@AfterMethod
	public void after() throws Exception {
		String result = HttpDriver.doPost(url, para, cookie);
		System.out.println(result);
	}
}
