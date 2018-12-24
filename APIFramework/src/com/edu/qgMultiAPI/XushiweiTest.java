package com.edu.qgMultiAPI;

import com.edu.core.BaseTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.edu.core.HttpDriver;
import com.edu.common.*;
import net.sf.json.JSONObject;

/*
 * @author = 徐世伟
 */
public class XushiweiTest extends BaseTest {
	String loginUrl = "http://study-perf.qa.netease.com/common/fgadmin/login";
	String skulistUrl = "http://study-perf.qa.netease.com/common/skuList";
	String listUrl = "http://study-perf.qa.netease.com/fgadmin/address/list";
	String deleteUrl = "http://study-perf.qa.netease.com/fgadmin/address/delete";
	String newUrl = "http://study-perf.qa.netease.com/fgadmin/address/new ";
	String submitUrl = "http://study-perf.qa.netease.com/fgadmin/orders/submit";
	String feeUrl = "http://study-perf.qa.netease.com/common/getTransportFee";

	@Test(description = "登录成功-查询收货地址成功-计算运费失败-提交订单成功")
	public void first() throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneNumber", "200000000055");
		para.element("phoneArea", "86");
		para.element("password", "netease123");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", 1);
		map.put("addressDetail", "浙江省_杭州市_滨江区");

		JSONObject submitpara = new JSONObject();
		submitpara.element("skuIds", "3");
		submitpara.element("skuNumbers", "1");
		submitpara.element("stockIds", "74966313");
		submitpara.element("receiverName", "小飞龙");
		submitpara.element("cellPhone", "12345678909");
		submitpara.element("addressDetail", "河北师范");
		submitpara.element("province", "四川省");
		submitpara.element("city", "成都市");
		submitpara.element("area", "锦江区");
		submitpara.element("voiceStatus", 0);
		submitpara.element("needInvoice", 0);
		submitpara.element("invoiceHead", "");
		submitpara.element("transportFee", 6);
		submitpara.element("logisticsCompanyId", 1);
		submitpara.element("accessSource", "noSource");
		submitpara.element("accessDevice", 0);

		CookieStore cookieStore = Common.getLoginCookie("200000000055", "netease123");

		String loginresult = HttpDriver.doPost(loginUrl, para);
		JSONObject loginjson = JSONObject.fromObject(loginresult);
		Assert.assertEquals(loginjson.getString("message"), "success");
		Assert.assertEquals(loginjson.getInt("code"), 200);

		String listresult = HttpDriver.doGet(listUrl, cookieStore);
		JSONObject listjson = JSONObject.fromObject(listresult);
		Assert.assertEquals(listjson.getString("message"), "success");
		Assert.assertEquals(listjson.getInt("code"), 200);

		String feeresult = HttpDriver.doGet(feeUrl, map);
		JSONObject feejson = JSONObject.fromObject(feeresult);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);

		String submitresult = HttpDriver.doPost(submitUrl, submitpara, cookieStore);
		JSONObject submitjson = JSONObject.fromObject(submitresult);
		Assert.assertEquals(submitjson.getString("message"), "success");
		Assert.assertEquals(submitjson.getInt("code"), 200);
	}

	@Test(description = "登录成功-有收获地址-计算运费失败-提交订单")
	public void second() throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneNumber", "200000000055");
		para.element("phoneArea", "86");
		para.element("password", "netease123");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", "1");
		map.put("addressDetail", "浙江省_杭州市_滨江区");
		JSONObject submitpara = new JSONObject();
		submitpara.element("skuIds", "3");
		submitpara.element("skuNumbers", "1");
		submitpara.element("stockIds", "");
		submitpara.element("receiverName", "小飞龙");
		submitpara.element("cellPhone", "12345678909");
		submitpara.element("addressDetail", "河北师范");
		submitpara.element("province", "四川省");
		submitpara.element("city", "成都市");
		submitpara.element("area", "锦江区");
		submitpara.element("voiceStatus", 0);
		submitpara.element("needInvoice", 0);
		submitpara.element("invoiceHead", "");
		submitpara.element("transportFee", 6);
		submitpara.element("logisticsCompanyId", 1);
		submitpara.element("accessSource", "noSource");
		submitpara.element("accessDevice", 0);
		CookieStore cookieStore = Common.getLoginCookie("200000000055", "netease123");
		String loginresult = HttpDriver.doPost(loginUrl, para);
		JSONObject loginjson = JSONObject.fromObject(loginresult);
		Assert.assertEquals(loginjson.getString("message"), "success");
		Assert.assertEquals(loginjson.getInt("code"), 200);

		String listresult = HttpDriver.doGet(listUrl, cookieStore);
		JSONObject listjson = JSONObject.fromObject(listresult);
		Assert.assertEquals(listjson.getString("message"), "success");
		Assert.assertEquals(listjson.getInt("code"), 200);

		String feeresult = HttpDriver.doGet(feeUrl, map);
		JSONObject feejson = JSONObject.fromObject(feeresult);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);

		String submitresult = HttpDriver.doPost(submitUrl, submitpara, cookieStore);
		JSONObject submitjson = JSONObject.fromObject(submitresult);
		Assert.assertEquals(submitjson.getString("message"), "请求失败");
		Assert.assertEquals(submitjson.getInt("code"), 400);
	}

	@Test(description = "登录成功-查询收货地址-计算运费失败-提交订单失败")
	public void third() throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneNumber", "200000000055");
		para.element("phoneArea", "86");
		para.element("password", "netease123");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", 1);
		map.put("addressDetail", "浙江省_杭州市_滨江区");
		JSONObject submitpara = new JSONObject();
		submitpara.element("skuIds", 3);
		submitpara.element("skuNumbers", "1");
		submitpara.element("stockIds", "74966313");
		submitpara.element("receiverName", "小飞龙");
		submitpara.element("cellPhone", "12345678909");
		submitpara.element("addressDetail", "河北师范");
		submitpara.element("province", "四川省");
		submitpara.element("city", "成都市");
		submitpara.element("area", "锦江区");
		submitpara.element("voiceStatus", 0);
		submitpara.element("needInvoice", 0);
		submitpara.element("invoiceHead", "");
		submitpara.element("transportFee", 6);
		submitpara.element("logisticsCompanyId", 1);
		submitpara.element("accessSource", "noSource");
		submitpara.element("accessDevice", 0);
		CookieStore cookieStore = Common.getLoginCookie("200000000055", "netease123");
		String loginresult = HttpDriver.doPost(loginUrl, para);
		JSONObject loginjson = JSONObject.fromObject(loginresult);
		Assert.assertEquals(loginjson.getString("message"), "success");
		Assert.assertEquals(loginjson.getInt("code"), 200);

		String listresult = HttpDriver.doGet(listUrl, cookieStore);
		JSONObject listjson = JSONObject.fromObject(listresult);
		Assert.assertEquals(listjson.getString("message"), "success");
		Assert.assertEquals(listjson.getInt("code"), 200);

		String feeresult = HttpDriver.doGet(feeUrl, map);
		JSONObject feejson = JSONObject.fromObject(feeresult);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);

		String submitresult = HttpDriver.doPost(submitUrl, submitpara, cookieStore);
		JSONObject submitjson = JSONObject.fromObject(submitresult);
		Assert.assertEquals(submitjson.getString("message"), "success");
		Assert.assertEquals(submitjson.getInt("code"), 200);
	}

	@Test(description = "登录失败-无查询收货地址-计算运费失败-提交订单")
	public void forth() throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneNumber", "200000000055");
		para.element("phoneArea", "86");
		para.element("password", "netease1234");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", "1");
		map.put("addressDetail", "浙江省_杭州市_滨江区");
		JSONObject submitpara = new JSONObject();
		submitpara.element("skuIds", "3");
		submitpara.element("skuNumbers", "1");
		submitpara.element("stockIds", "74966313");
		submitpara.element("receiverName", "小飞龙");
		submitpara.element("cellPhone", "12345678909");
		submitpara.element("addressDetail", "河北师范");
		submitpara.element("province", "四川省");
		submitpara.element("city", "成都市");
		submitpara.element("area", "锦江区");
		submitpara.element("voiceStatus", 0);
		submitpara.element("needInvoice", 0);
		submitpara.element("invoiceHead", "");
		submitpara.element("transportFee", 6);
		submitpara.element("logisticsCompanyId", 1);
		submitpara.element("accessSource", "noSource");
		submitpara.element("accessDevice", 0);
		CookieStore cookieStore = Common.getLoginCookie("200000000055", "netease123");
		String loginresult = HttpDriver.doPost(loginUrl, para);
		JSONObject loginjson = JSONObject.fromObject(loginresult);
		Assert.assertEquals(loginjson.getString("message"), "用户名或者密码错误");
		Assert.assertEquals(loginjson.getInt("code"), 400);

		String listresult = HttpDriver.doGet(listUrl, cookieStore);
		JSONObject listjson = JSONObject.fromObject(listresult);
		Assert.assertEquals(listjson.getString("message"), "success");
		Assert.assertEquals(listjson.getInt("code"), 200);

		String feeresult = HttpDriver.doGet(feeUrl, map);
		JSONObject feejson = JSONObject.fromObject(feeresult);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);

		String submitresult = HttpDriver.doPost(submitUrl, submitpara, cookieStore);
		JSONObject submitjson = JSONObject.fromObject(submitresult);
		Assert.assertEquals(submitjson.getString("message"), "success");
		Assert.assertEquals(submitjson.getInt("code"), 200);
	}

	@Test(description = "登录失败-有查询收货地址-计算运费失败-提交订单")
	public void fifth() throws Exception {
		JSONObject para = new JSONObject();
		para.element("phoneNumber", "200000000055");
		para.element("phoneArea", "86");
		para.element("password", "netease1234");

		Map<String, Object> feepara = new HashMap<String, Object>();
		feepara.put("goodsId", 1);
		feepara.put("addressDetail", "浙江省_杭州市_滨江区");

		JSONObject submitpara = new JSONObject();
		submitpara.element("skuIds", "3");
		submitpara.element("skuNumbers", "1");
		submitpara.element("stockIds", "74966313");
		submitpara.element("receiverName", "小飞龙");
		submitpara.element("cellPhone", "12345678909");
		submitpara.element("addressDetail", "河北师范");
		submitpara.element("province", "四川省");
		submitpara.element("city", "成都市");
		submitpara.element("area", "锦江区");
		submitpara.element("voiceStatus", 0);
		submitpara.element("needInvoice", 0);
		submitpara.element("invoiceHead", "");
		submitpara.element("transportFee", 6);
		submitpara.element("logisticsCompanyId", 1);
		submitpara.element("accessSource", "noSource");
		submitpara.element("accessDevice", 0);
		CookieStore cookieStore = Common.getLoginCookie("200000000055", "netease1234");
		String loginresult = HttpDriver.doPost(loginUrl, para);
		JSONObject loginjson = JSONObject.fromObject(loginresult);
		Assert.assertEquals(loginjson.getString("message"), "用户名或者密码错误");
		Assert.assertEquals(loginjson.getInt("code"), 400);

		String listresult = HttpDriver.doGet(listUrl, cookieStore);
		JSONObject listjson = JSONObject.fromObject(listresult);
		Assert.assertEquals(listjson.getString("message"), "请重新登录");
		Assert.assertEquals(listjson.getInt("code"), 403);

		String feeresult = HttpDriver.doGet(feeUrl, feepara);
		JSONObject feejson = JSONObject.fromObject(feeresult);
		Assert.assertEquals(feejson.getString("message"), "请求失败");
		Assert.assertEquals(feejson.getInt("code"), 400);

		String submitresult = HttpDriver.doPost(submitUrl, submitpara, cookieStore);
		JSONObject submitjson = JSONObject.fromObject(submitresult);
		Assert.assertEquals(submitjson.getString("message"), "请重新登录");
		Assert.assertEquals(submitjson.getInt("code"), 403);
	}
}
