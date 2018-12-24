package com.edu.qgMultiAPI;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.HttpDriver;
import net.sf.json.JSONObject;

/*
 * @author=生春月
 */
public class ShengchunyueTest {

	String username = "200000000011";
	String password = "netease123";
	String loginUrl = "http://study-perf.qa.netease.com/common/fgadmin/login";
	String addressListUrl = "http://study-perf.qa.netease.com/fgadmin/address/list";
	String deleteUrl = "http://study-perf.qa.netease.com/fgadmin/address/delete";
	String newAddressUrl = "http://study-perf.qa.netease.com/fgadmin/address/new";
	String skuListUrl = "http://study-perf.qa.netease.com/common/skuList/";
	String getFeeUrl = "http://study-perf.qa.netease.com/common/getTransportFee?id=1&addressDetail=addressDetail";
	String submitUrl = "http://study-perf.qa.netease.com/fgadmin/orders/submit";

	@Test(description = "登录成功--查询收获地址--删除收获地址")
	public void scene1() throws IOException, Exception {
		// 登录成功
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", password);
		String result1 = HttpDriver.doPost(loginUrl, para);
		System.out.println(result1);
		// 查询收获地址
		CookieStore cookie = Common.getLoginCookie(username, password);
		String result2 = HttpDriver.doGet(addressListUrl, cookie);
		System.out.println(result2);
		// 删除收获地址
		JSONObject json = JSONObject.fromObject(result2);
		JSONObject jresult = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		int id = jresult.getInt("id");
		System.out.println(id);
		JSONObject del = new JSONObject();
		del.element("id", id);
		String result3 = HttpDriver.doPost(deleteUrl, del, cookie);
		System.out.println(result3);

	}

	@Test(description = "登录成功--查询收获地址--添加收获地址--删除收获地址")
	public void scene2() throws IOException, Exception {
		CookieStore cookie = Common.getLoginCookie(username, password);
		// 登录成功
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", password);
		String result1 = HttpDriver.doPost(loginUrl, para);
		System.out.println(result1);
		// 添加收获地址
		JSONObject address = new JSONObject();
		address.element("id", "");
		address.element("receiverName", "李四");
		address.element("cellPhone", "200000000011");
		address.element("province", "江苏省");
		address.element("city", "南京市");
		address.element("area", "鼓楼区");
		address.element("addressDetail", "南京大学");
		String result2 = HttpDriver.doPost(newAddressUrl, address, cookie);
		System.out.println(result2);
		// 查询收获地址
		String result3 = HttpDriver.doGet(addressListUrl, cookie);
		System.out.println(result3);
		// 删除收获地址
		JSONObject json = JSONObject.fromObject(result3);
		JSONObject jresult = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		int id = jresult.getInt("id");
		System.out.println(id);
		JSONObject del = new JSONObject();
		del.element("id", id);
		String result4 = HttpDriver.doPost(deleteUrl, del, cookie);
		System.out.println(result4);
	}

	@Test(description = "登录成功--获取所有商品的sku列表--查询收货地址--计算运费--提交订单")
	public void scene3() throws IOException, Exception {
		// 登录成功
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", password);
		String result1 = HttpDriver.doPost(loginUrl, para);
		System.out.println(result1);
		// 获取所有商品的sku列表
		String result2 = HttpDriver.doGet(skuListUrl);
		System.out.println(result2);
		// 查询收获地址
		CookieStore cookie = Common.getLoginCookie(username, password);
		String result3 = HttpDriver.doGet(addressListUrl, cookie);
		System.out.println(result3);
		// 计算运费
		JSONObject json = JSONObject.fromObject(result3);
		JSONObject jresult = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String pro = jresult.getString("province");
		String city = jresult.getString("city");
		String area = jresult.getString("area");
		String addressDetail = pro + "_" + city + "_" + area;
		String result4 = HttpDriver.doGet(getFeeUrl);
		System.out.println(result4);
		// 提交订单
		JSONObject para2 = new JSONObject();
		para2.element("skuIds", "2,3");
		para2.element("skuNumbers", "1,1");
		para2.element("stockIds", "74966312,74966313");
		para2.element("receiverName", "张三");
		para2.element("cellPhone", "13245678986");
		para2.element("addressDetail", addressDetail);
		para2.element("province", pro);
		para2.element("city", city);
		para2.element("area", area);
		para2.element("voiceStatus", 0);
		para2.element("needInvoice", 0);
		para2.element("invoiceHead", "");
		para2.element("tranfportFee", 0);
		para2.element("lohisticsCompanyId", 1);
		para2.element("accessSource", "noSource");
		para2.element("accessDevice", 0);
		String result5 = HttpDriver.doPost(submitUrl, para2, cookie);
		System.out.println(result5);
	}

	@Test(description = "登录失败--查询收获地址--计算运费")
	public void scene4() throws IOException, Exception {
		// 登录失败
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", username);
		para.element("password", password);
		String result1 = HttpDriver.doPost(loginUrl, para);
		System.out.println(result1);
		// 查询收获地址
		CookieStore cookie = Common.getLoginCookie(username, password);
		String result2 = HttpDriver.doGet(addressListUrl, cookie);
		System.out.println(result2);
		// 计算运费
		JSONObject json = JSONObject.fromObject(result2);
		JSONObject jresult = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String pro = jresult.getString("province");
		String city = jresult.getString("city");
		String area = jresult.getString("area");
		String addressDetail = pro + "_" + city + "_" + area;
		String result3 = HttpDriver.doGet(getFeeUrl);
		System.out.println(result3);
	}
}
