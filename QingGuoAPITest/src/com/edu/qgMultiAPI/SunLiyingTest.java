package com.edu.qgMultiAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import net.sf.json.JSONObject;

/*
 * @author=孙立莹
 */
public class SunLiyingTest extends BaseTest {
	String result = null;
	String name = "200000000044";
	String pwd = "netease123";
	Common common = new Common();
	Checker check;

//	登录
	public void LoginSuccess(String name, String pwd) throws Exception {
		String loginUrl = baseUrl + common.loginUrl;
		JSONObject js = new JSONObject();
		js.element("password", pwd);
		js.element("phoneArea", 86);
		js.element("phoneNumber", name);
		result = HttpDriver.doPost(loginUrl, js);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	登录失败
	public void LoginFail(String name, String pwd) throws Exception {
		String loginUrl = baseUrl + common.loginUrl;
		JSONObject js = new JSONObject();
		js.element("password", pwd);
		js.element("phoneArea", 86);
		js.element("phoneNumber", name);
		result = HttpDriver.doPost(loginUrl, js);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "用户名或者密码错误");
	}

//	获取skuList
	public void SkuList() throws Exception {
		int goodsId = 1;
		result = HttpDriver.doGet(baseUrl + common.SkuListUrl + "?goodsId" + Integer.toString(goodsId));
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	获取list成功
	public void ListSuccess() throws Exception {
		CookieStore cook = Common.getLoginCookie(name, pwd);
		result = HttpDriver.doGet(baseUrl + common.AddressListUrl, cook);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	获取list失败
	public void ListFail(String name, String pwd) throws Exception {
		CookieStore cook = Common.getLoginCookie(name, pwd);
		result = HttpDriver.doGet(baseUrl + common.AddressListUrl, cook);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("code", "403");
	}

//	计算运费
	public void getFee() throws Exception {
		String feeUrl = baseUrl + common.TransportFeeUrl;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("addressDetial", "河北省_石家庄市_裕华区");
		result = HttpDriver.doGet(feeUrl, map);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	新建订单
	public void newAddress() throws IOException, Exception {
		String addAddressUrl = baseUrl + common.NewAddressUrl;
		CookieStore cook = Common.getLoginCookie(name, pwd);
		JSONObject json = new JSONObject();
		json.element("id", "");
		json.element("receiverName", "张三");
		json.element("addressDetail", "河北省_石家庄市_裕华区");
		json.element("cellPhone", "123456789");
		json.element("province", "河北省");
		json.element("city", "石家庄市");
		json.element("area", "裕华区");
		result = HttpDriver.doPost(addAddressUrl, json, cook);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	提交订单
	public void submit() throws IOException, Exception {
		String submitUrl = baseUrl + common.SubmitUrl;
		CookieStore cook = Common.getLoginCookie(name, pwd);
		JSONObject json = new JSONObject();
		json.element("skuIds", "2,3");
		json.element("skuNumbers", "1,1");
		json.element("stockIds", "74966312,74966313");
		json.element("receiverName", "张三");
		json.element("cellPhone", "1234566");
		json.element("addressDetail", "1栋3单元");
		json.element("province", "浙江省");
		json.element("city", "杭州市");
		json.element("area", "滨江区");
		json.element("voiceStatus", "0");
		json.element("needInvoice", "0");
		json.element("invoiceHead", "1");
		json.element("transportFee", "0");
		json.element("logisticsCompanyId", "1");
		json.element("accessSource", "noSource");
		json.element("accessDevice", "0");
		result = HttpDriver.doPost(submitUrl, json, cook);
		System.out.println(result);
		check = new Checker(result);
		check.verifyXpath("message", "success");
	}

//	删除订单
	public void delete() throws IOException, Exception {
		String deleteUrl = baseUrl + common.DeleteAddressUrl;
		CookieStore cook = Common.getLoginCookie(name, pwd);
		JSONObject json = new JSONObject();
		json.element("id", 82319421);
		result = HttpDriver.doPost(deleteUrl, json, cook);
	}

	@Test(description = "登录成功--获取skuList成功--获取list成功--计算运费--新建订单--提交订单--删除订单")
	public void test_scence1() throws Exception {
		String name = "200000000044";
		String pwd = "netease123";
		this.LoginSuccess(name, pwd);
		this.SkuList();
		this.ListSuccess();
		this.getFee();
		this.newAddress();
		this.submit();
		this.delete();
	}

	@Test(description = "登录成功--新建地址--删除订单")
	public void test_scence2() throws Exception {
		String name = "200000000044";
		String pwd = "netease123";
		this.LoginSuccess(name, pwd);
		this.newAddress();
		this.delete();
	}

	@Test(description = "登录成功--新建订单--提交订单")
	public void test_scence3() throws Exception {
		String name = "200000000044";
		String pwd = "netease123";
		this.LoginSuccess(name, pwd);
		this.newAddress();
		this.submit();
	}

	@Test(description = "登录失败--查询收货地址失败")
	public void test_scence4() throws Exception {
		String name = "200000000044011";
		String pwd = "netease123";
		this.LoginFail(name, pwd);
		this.ListFail(name, pwd);
	}

	@Test(description = "登录成功--查询地址--计算运费--新建订单--提交订单--删除订单")
	public void test_scence5() throws Exception {
		String name = "200000000044";
		String pwd = "netease123";
		this.LoginSuccess(name, pwd);
		this.ListSuccess();
		this.getFee();
		this.newAddress();
		this.delete();
	}
}
