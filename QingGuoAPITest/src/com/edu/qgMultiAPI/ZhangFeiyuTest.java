package com.edu.qgMultiAPI;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.HttpDriver;

import com.edu.utils.Checker;

import net.sf.json.JSONObject;

/*
 * @author=张飞宇
 */
public class ZhangFeiyuTest {
	String baseurl = "http://study-perf.qa.netease.com";
	String listurl = "/fgadmin/address/list?tm=1545495924820";
	String delurl = "/fgadmin/address/delete";
	String addurl = "/fgadmin/address/new";
	String feeurl = "/common/getTransportFee?id=1&addressDetail=%E6%B5%99%E6%B1%9F%E7%9C%81_%E6%9D%AD%E5%B7%9E%E5%B8%82_%E6%BB%A8%E6%B1%9F%E5%B8%82&tm=1545553794046";
	String suburl = "/fgadmin/orders/submit";
	String skuurl = "/common/skuList";
	JSONObject para = null;
	JSONObject json = null;
	CookieStore cookie = null;
	String result;
	String[] ID_list;
	int total_address;
	private String u_name = "200000000077";
	private String u_pwd = "netease123";
	private Checker checker = null;

	// 场景一：登录——获取收货地址表——获取邮费——提交订单
	@Test(priority = 0)
	public void scene01_login_success() throws IOException, Exception {
		cookie = Common.getLoginCookie(u_name, u_pwd);
		result = Common.res;
		System.out.println(result);
//		checker = new Checker(result);
//		checker.assertQulity("code", "200");
	}

	@Test(priority = 1)
	public void scene01_list_success() throws Exception {
		result = HttpDriver.doGet(baseurl + listurl, cookie);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 2)
	public void scene01_fee_success() throws Exception {
		para = new JSONObject();
		para.element("id", "1");
		para.element("addressDetail", "浙江省_杭州市_滨江市");
		para.element("tm", "1545553765731");
		result = HttpDriver.doGet(baseurl + feeurl, cookie);
		checker = new Checker(result);
		checker.assertQulity("result", "6.0");
	}

	@Test(priority = 3)
	public void scene01_submit_success() throws Exception {
		para = new JSONObject();
		para.element("accessDevice", "0");
		para.element("accessSource", "noSource");
		para.element("addressDetail", "浙江大学");
		para.element("area", "滨江市");
		para.element("cellPhone", "12345678900");
		para.element("city", "杭州市");
		para.element("invoiceHead", "");
		para.element("logisticsCompanyId", "1");
		para.element("needInvoice", "0");
		para.element("province", "浙江省");
		para.element("receiverName", "测试用户");
		para.element("skuIds", "2,3");
		para.element("skuNumbers", "1,1");
		para.element("stockIds", "74966312,74966313");
		para.element("transportFee", "0");
		para.element("voiceStatus", "0");
		result = HttpDriver.doPost(baseurl + suburl, para, cookie);
		/* System.out.println(result); */
		// dopost方法请求头header添加add csrfToken，csrfToken
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	// 场景二：获取收获地址的id列表————删除最后一个收货地址————添加新的收货地址
	@Test(priority = 4)
	public void scene02_getlist_success() throws IOException, Exception {
		cookie = Common.getLoginCookie(u_name, u_pwd);
		result = HttpDriver.doGet(baseurl + listurl, cookie);
		json = JSONObject.fromObject(result).getJSONObject("result");
		int id_num = json.getInt("total");
		this.total_address = id_num;
		String[] id_list = new String[id_num];
		for (int i = 0; i < id_num; i++) {
			id_list[i] = json.getJSONArray("list").getJSONObject(i).getString("id");
		}
		this.ID_list = id_list;
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 5)
	public void scene02_del_success() throws Exception {
		para = new JSONObject();
		para.element("id", ID_list[total_address - 1]);
		result = HttpDriver.doPost(baseurl + delurl, para, cookie);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 6)
	public void scene02_add_success() throws Exception {
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "测试用户");
		para.element("cellPhone", "12345678900");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江市");
		para.element("addressDetail", "浙江大学");
		result = HttpDriver.doPost(baseurl + addurl, para, cookie);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	// 场景三：查看产品信息————查看id为1的产品信息————查看id为2的产品信息————查看id为3的产品信息
	@Test(priority = 7)
	public void scene03_sku_success() throws Exception {
		result = HttpDriver.doGet(baseurl + skuurl);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 8)
	public void scene03_sku1_success() throws Exception {
		result = HttpDriver.doGet(baseurl + skuurl, "goodsId=1");
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 9)
	public void scene03_sku2_success() throws Exception {
		result = HttpDriver.doGet(baseurl + skuurl, "goodsId=2");
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 10)
	public void scene03_sku3_success() throws Exception {
		result = HttpDriver.doGet(baseurl + skuurl, "goodsId=3");
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	// 场景四：登录————获取收货地址————删除第一个地址————添加新地址————提交订单
	@Test(priority = 11)
	public void scene04_login_success() throws IOException, Exception {
		cookie = Common.getLoginCookie(u_name, u_pwd);
		result = Common.res;
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 12)
	public void scene04_getlist_success() throws IOException, Exception {
		cookie = Common.getLoginCookie(u_name, u_pwd);
		result = HttpDriver.doGet(baseurl + listurl, cookie);
		json = JSONObject.fromObject(result).getJSONObject("result");
		int id_num = json.getInt("total");
		this.total_address = id_num;
		String[] id_list = new String[id_num];
		for (int i = 0; i < id_num; i++) {
			id_list[i] = json.getJSONArray("list").getJSONObject(i).getString("id");
		}
		this.ID_list = id_list;
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 13)
	public void scene04_del_success() throws Exception {
		para = new JSONObject();
		para.element("id", ID_list[0]);
		result = HttpDriver.doPost(baseurl + delurl, para, cookie);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 14)
	public void scene04_add_success() throws Exception {
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "测试用户");
		para.element("cellPhone", "12345678900");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江市");
		para.element("addressDetail", "浙江大学");
		result = HttpDriver.doPost(baseurl + addurl, para, cookie);
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}

	@Test(priority = 15)
	public void scene04_submit_success() throws Exception {
		para = new JSONObject();
		para.element("accessDevice", "0");
		para.element("accessSource", "noSource");
		para.element("addressDetail", "浙江大学");
		para.element("area", "滨江市");
		para.element("cellPhone", "12345678900");
		para.element("city", "杭州市");
		para.element("invoiceHead", "");
		para.element("logisticsCompanyId", "1");
		para.element("needInvoice", "0");
		para.element("province", "浙江省");
		para.element("receiverName", "测试用户");
		para.element("skuIds", "2,3");
		para.element("skuNumbers", "1,1");
		para.element("stockIds", "74966312,74966313");
		para.element("transportFee", "0");
		para.element("voiceStatus", "0");
		result = HttpDriver.doPost(baseurl + suburl, para, cookie);
		/* System.out.println(result); */
		// dopost方法请求头header添加add csrfToken，csrfToken
		checker = new Checker(result);
		checker.assertQulity("code", "200");
	}
}
