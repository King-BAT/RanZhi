package com.edu.qgMultiAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

public class YangKaijingTest {
	CloseableHttpClient httpClient = null;
	String loginUrl = "http://study-perf.qa.netease.com/common/fgadmin/login";
	String skulistUrl = "http://study-perf.qa.netease.com/common/skuList";
	String addressListUrl = "http://study-perf.qa.netease.com/fgadmin/address/list";
	String getTranUrl = "http://study-perf.qa.netease.com/common/getTransportFee?id=%d&addressDetial=%s";
	String addressnewUrl = "http://study-perf.qa.netease.com/fgadmin/address/new";
	String assressdeleteUrl = "http://study-perf.qa.netease.com/fgadmin/address/delete";
	String submitUrl = "http://study-perf.qa.netease.com/fgadmin/orders/submit";
	String address = null;
	double transportFee;

	@BeforeMethod
	public void initHttpClient() {
		httpClient = HttpClients.createDefault();
	}

	@AfterMethod
	public void closeHttpClient() throws Exception {
		httpClient.close();
	}

	public String login(String name, String password) throws Exception {
		CloseableHttpResponse respone = null;
		JSONObject para = new JSONObject();
		para.element("phoneArea", "86");
		para.element("phoneNumber", name);
		para.element("password", password);
		HttpPost post = new HttpPost(loginUrl);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data;
		String content = null;
		data = new StringEntity(para.toString());
		post.setEntity(data);
		respone = httpClient.execute(post);
		HttpEntity entity = respone.getEntity();
		content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();
		return content;
	}

	// 浏览商品列表
	public String getSkulist() throws Exception {
		CloseableHttpResponse respone = null;
		HttpGet get = new HttpGet(skulistUrl);
		get.addHeader("Content-Type", "application/json");
		respone = httpClient.execute(get);
		HttpEntity entity = (HttpEntity) respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();
		return content;
	}

	public String getSkulist(int id) throws Exception {
		JSONObject data = new JSONObject();
		data.element("id", id);
		String para = URLEncoder.encode(data.toString(), "UTF-8");
		CloseableHttpResponse respone = null;
		HttpGet get = new HttpGet(skulistUrl + "?" + para);
		get.addHeader("Content-Type", "application/json");
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();
		return content;
	}

	// 查询收货地址
	public String getAddress() throws Exception {
		HttpGet get = new HttpGet(addressListUrl);
		get.addHeader("Content-Type", "application/json");
		CloseableHttpResponse respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		address = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + address);
		EntityUtils.consume(entity);
		respone.close();
		return address;
	}

	// 查询收货地址（已登录--cookie）
	public String getAddressAfterLogin() throws Exception {
		CookieStore cookie = Common.getLoginCookie("200000000066", "netease123");
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();
		HttpGet get = new HttpGet(addressListUrl);
		get.addHeader("Content-Type", "application/json");
		CloseableHttpResponse respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		address = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + address);
		EntityUtils.consume(entity);
		respone.close();
		return address;
	}

	// 计算运费
	public String getTransportFee() throws Exception {
		JSONObject json = JSONObject.fromObject(this.address);
		JSONObject result = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String addressDetial = result.get("province") + "_" + result.get("city") + "_" + result.get("area");
		int id = result.getInt("id");
		// 创建格式化的字符串以及连接多个字符串对象
		String url = String.format(getTranUrl, id, addressDetial);
		HttpGet get = new HttpGet(url);
		get.addHeader("Content-Type", "application/json");
		CloseableHttpResponse respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String feeResult = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + feeResult);
		this.transportFee = JSONObject.fromObject(feeResult).getDouble("result");
		System.out.println("计算运费是：" + transportFee);
		EntityUtils.consume(entity);
		respone.close();
		return feeResult;
	}

	// 提交订单
	public String getSubmit() throws Exception {
		JSONObject json = JSONObject.fromObject(this.address);
		JSONObject result = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		String receiverName = result.getString("receiverName");
		String cellPhone = result.getString("cellPhone");
		String addressDetail = result.getString("addressDetail");
		String province = result.getString("province");
		String city = result.getString("city");
		String area = result.getString("area");
		double fee = this.transportFee;
		JSONObject para = new JSONObject();
		para.element("skuIds", "2,3");
		para.element("skuNumbers", "1,1");
		para.element("stockIds", "74966312,74966313");
		para.element("receiverName", receiverName);
		para.element("cellPhone", cellPhone);
		para.element("addressDetail", addressDetail);
		para.element("province", province);
		para.element("city", city);
		para.element("area", area);
		para.element("voiceStatus", 0);
		para.element("needInvoice", 0);
		para.element("invoiceHead", "");
		para.element("transportFee", fee);
		para.element("logisticsCompanyId", 1);
		para.element("accessSource", "noSource");
		para.element("accessDevice", 0);

		HttpPost post = new HttpPost(submitUrl);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data = new StringEntity(para.toString());
		post.setEntity(data);
		post.setHeader("csrfToken", "csrfToken");
		CloseableHttpResponse respone = httpClient.execute(post);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();

		return content;
	}

	// 添加收货地址
	public String addAddress(String receiverName, String cellPhone, String province, String city, String area,
			String addressDetail) throws Exception {
		JSONObject para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", receiverName);
		para.element("cellPhone", cellPhone);
		para.element("province", province);
		para.element("city", city);
		para.element("area", area);
		para.element("addressDetail", addressDetail);
		HttpPost post = new HttpPost(addressnewUrl);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data = new StringEntity(para.toString());
		post.setEntity(data);
		post.setHeader("csrfToken", "csrfToken");
		CloseableHttpResponse respone = httpClient.execute(post);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();
		return content;
	}

	// 删除收货地址
	public String getDel() throws Exception {
		JSONObject json = JSONObject.fromObject(this.address);
		JSONObject result = json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
		int id = result.getInt("id");
		JSONObject para = new JSONObject();
		para.element("id", id);
		HttpPost post = new HttpPost(assressdeleteUrl);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data = new StringEntity(para.toString());
		post.setEntity(data);
		post.setHeader("csrfToken", "csrfToken");
		CloseableHttpResponse respone = httpClient.execute(post);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		System.out.println("执行结果是：" + content);
		EntityUtils.consume(entity);
		respone.close();
		return content;

	}

	@Test // 获取所有商品的sku列表-获取goodsId=1的商品sku信息-获取goodsId=2的商品sku信息获取goodsId=3的商品sku信息
	public void sceneTest1() throws Exception {
		String sku = getSkulist();
		Checker check = new Checker(sku);
		check.verifyXpath("message", "success");
		String sku1 = getSkulist(1);
		Checker check1 = new Checker(sku1);
		check.verifyXpath("message", "success");
		String sku2 = getSkulist(2);
		Checker check2 = new Checker(sku2);
		check.verifyXpath("message", "success");
		String sku3 = getSkulist(3);
		Checker check3 = new Checker(sku3);
		check.verifyXpath("message", "success");
	}

	@Test // 完整下单流程(未登录、有收货地址) 登录成功-查询收货地址-计算运费-提交订单
	public void sceneTest2() throws Exception {
		// 1.登录成功
		String result1 = login("200000000066", "netease123");
		Checker check1 = new Checker(result1);
		check1.verifyXpath("message", "success");
		// 2.查询收货地址
		String result2 = getAddress();
		Checker check2 = new Checker(result2);
		check2.verifyXpath("message", "success");
		// 3.计算运费
		String result3 = getTransportFee();
		Checker check3 = new Checker(result3);
		check3.verifyXpath("message", "success");
		// 4.提交订单
		String result4 = getSubmit();
		Checker check4 = new Checker(result4);
		check4.verifyXpath("message", "success");
	}

	@Test // 完整下单流程(已登录、无收货地址) 查询收货地址-添加收货地址-计算运费-提交订单
	public void sceneTest3() throws Exception {
		// 1.查询收货地址
		String result1 = getAddressAfterLogin();
		Checker check1 = new Checker(result1);
		check1.verifyXpath("message", "success");
		// 2.添加收货地址
		String result2 = addAddress("张三", "11111111111", "浙江省", "杭州市", "滨江区", "中山路25号");
		Checker check2 = new Checker(result2);
		check2.verifyXpath("message", "success");
		// 3.计算运费
		String result3 = getTransportFee();
		Checker check3 = new Checker(result3);
		check3.verifyXpath("message", "success");
		// 4.提交订单
		String result4 = getSubmit();
		Checker check4 = new Checker(result4);
		check4.verifyXpath("message", "success");
	}

	@Test // 完整下单流程(已登录、有多个收货地址、删除地址) 查询收货地址-删除收货地址-计算运费-提交订单
	public void sceneTest4() throws Exception {
		// 1.查询收货地址
		String result1 = getAddressAfterLogin();
		Checker check1 = new Checker(result1);
		check1.verifyXpath("message", "success");
		// 2.删除收货地址
		String result2 = getDel();
		Checker check2 = new Checker(result2);
		check1.verifyXpath("message", "success");
		// 3.计算运费
		String result3 = getTransportFee();
		Checker check3 = new Checker(result3);
		check3.verifyXpath("message", "success");
		// 4.提交订单
		String result4 = getSubmit();
		Checker check4 = new Checker(result4);
		check4.verifyXpath("message", "success");
	}

	@Test // 完整下单流程(未登录、无收货地址,添加地址错误，重新添加)登录成功-
	// 查询收货地址-添加收货地址-删除收货地址-添加收货地址-计算运费-提交订单
	public void sceneTest5() throws Exception {
		// 1.登录成功
		String result1 = login("200000000066", "netease123");
		Checker check1 = new Checker(result1);
		check1.verifyXpath("message", "success");
		// 2.查询收货地址
		String result2 = getAddress();
		Checker check2 = new Checker(result2);
		check2.verifyXpath("message", "success");
		// 3.添加收货地址
		String result3 = addAddress("张三三", "11111111111", "浙江省", "杭州市", "滨江区", "中山路25号");
		Checker check3 = new Checker(result3);
		check3.verifyXpath("message", "success");
		// 4.删除收货地址
		String result4 = getDel();
		Checker check4 = new Checker(result4);
		check4.verifyXpath("message", "success");
		// 5.添加收货地址
		String result5 = addAddress("张三", "11111111111", "浙江省", "杭州市", "滨江区", "中山路25号");
		Checker check5 = new Checker(result5);
		check5.verifyXpath("message", "success");
		// 6.计算运费
		String result6 = getTransportFee();
		Checker check6 = new Checker(result6);
		check3.verifyXpath("message", "success");
		// 7.提交订单
		String result7 = getSubmit();
		Checker check7 = new Checker(result7);
		check7.verifyXpath("message", "success");

	}
}
