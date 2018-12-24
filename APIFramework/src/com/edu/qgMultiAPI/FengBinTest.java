package com.edu.qgMultiAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import com.edu.utils.ReadProperties;
import com.edu.common.Common;

import net.sf.json.JSONObject;

/*
 * @author = 冯滨
 */
public class FengBinTest {

	String result = null;
	Common com;

	// 登录方法
	public void dologin(String u_name, String u_pwd) throws IOException {
		String loginUrl = ReadProperties.getPropertyValue("url") + "/common/fgadmin/login";
		JSONObject js = new JSONObject();
		js.element("password", u_pwd);
		js.element("phoneArea", 86);
		js.element("phoneNumber", u_name);

		result = HttpDriver.doPost(loginUrl, js);
	}

	// 获取商品列表
	public String skuList() throws Exception {
		String skulistUrl = ReadProperties.getPropertyValue("url") + "/common/skuList";
		result = HttpDriver.doGet(skulistUrl);
		Checker check = new Checker(result);
		return result;
	}

	// 添加收货地址
	public String address() throws Exception {
		String addAddressUrl = ReadProperties.getPropertyValue("url") + "/fgadmin/address/new";
		CookieStore cook = Common.getLoginCookie();
		JSONObject json = new JSONObject();
		json.element("id", ReadProperties.getPropertyValue("id"));
		json.element("receiverName", ReadProperties.getPropertyValue("receiverName"));
		json.element("addressDetail", ReadProperties.getPropertyValue("addressDetail"));
		json.element("cellPhone", ReadProperties.getPropertyValue("cellPhone"));
		json.element("province", ReadProperties.getPropertyValue("province"));
		json.element("city", ReadProperties.getPropertyValue("city"));
		json.element("area", ReadProperties.getPropertyValue("area"));
		String result = HttpDriver.doPost(addAddressUrl, json, cook);
		return result;
	}

	// 提交订单
	public String sub(String accessDevice, String accessSource, String addressDetail, String area, String cellPhone,
			String city, String invoiceHead, String logisticsCompanyId, String needInvoice, String province,
			String receiverName, String skuIds, String skuNumbers, String stockIds, String transportFee,
			String voiceStatus) throws Exception, Exception {
		String submitUrl = ReadProperties.getPropertyValue("url") + "/fgadmin/orders/submit";
		CookieStore cook = com.getLoginCookie();
		JSONObject json = new JSONObject();
		json.element("accessDevice", accessDevice);
		json.element("accessSource", accessSource);
		json.element("addressDetail", addressDetail);
		json.element("area", area);
		json.element("cellPhone", cellPhone);
		json.element("city", city);
		json.element("invoiceHead", invoiceHead);
		json.element("logisticsCompanyId", logisticsCompanyId);
		json.element("needInvoice", needInvoice);
		json.element("province", province);
		json.element("receiverName", receiverName);
		json.element("skuIds", skuIds);
		json.element("skuNumbers", skuNumbers);
		json.element("stockIds", stockIds);
		json.element("transportFee", transportFee);
		json.element("voiceStatus", voiceStatus);
		result = HttpDriver.doPost(submitUrl, json, cook);
		return result;
	}

	// 计算运费
	public String fee(String addressDetial, String id) throws IOException {
		String feeUrl = ReadProperties.getPropertyValue("url") + "/common/getTransportFee";
		Map map = new HashMap();
		map.put("id", id);
		map.put("addressDetial", addressDetial);
		result = HttpDriver.doGet(feeUrl, map);
		return result;
	}

	// 删除地址
	public String deleteById(String num) throws Exception {
		String deleteUrl = ReadProperties.getPropertyValue("url") + "/common/getTransportFee";
		CookieStore cook = Common.getLoginCookie();
		JSONObject json = new JSONObject();
		json.element("id", num);
		result = HttpDriver.doPost(deleteUrl, json, cook);
		return result;
	}

	// 查看指定商品信息
	public String skuListById(int goodsId) throws Exception {
		String skulistUrl = ReadProperties.getPropertyValue("url") + "/common/skuList";
		HttpDriver driver = new HttpDriver();
		String newUrl = skulistUrl + "?goodsId=" + goodsId;
		String result = HttpDriver.doGet(newUrl);
		Checker check = new Checker(result);
		return result;
	}

	@Test(description = "登录成功-获取商品列表-添加收货地址-提交订单")
	public void scene1() throws Exception {
		System.out.println("login");
		this.dologin("200000000033", "netease123");
		System.out.println("skulist");
		this.skuList();
		System.out.println("address");
		this.address();
		System.out.println("submit");
		this.sub("0", "noSource", "河北师范大学", "裕华区", "15226542585", "石家庄市", "", "1", "0", "河北省", "小飞龙", "3", "1",
				"74966313", "6", "0");
	}

	@Test(description = "查看商品信息-计算运费-提交订单")
	public void scene2() throws Exception {
		System.out.println("skulist");
		this.skuList();
		System.out.println("fee");
		this.fee("浙江省_杭州市_滨江区", "1");
		System.out.println("submit");
		this.sub("0", "noSource", "河北师范大学", "裕华区", "15226542585", "石家庄市", "", "1", "0", "河北省", "小飞龙", "3", "1",
				"74966313", "6", "0");

	}

	@Test(description = "登录成功-查看商品信息-删除指定收货地址")
	public void scene3() throws Exception {
		System.out.println("login");
		this.dologin("200000000033", "netease123");
		System.out.println("skulist");
		this.skuList();
		System.out.println("delete ");
		result = this.deleteById("82315956");
		System.out.println("addaddress");
		this.address();

	}

	@Test(description = "登录成功-获取指定商品信息-删除指定收货地址（想要的地址不能再添加）")
	public void scene4() throws Exception {
		System.out.println("login");
		this.dologin("200000000033", "netease123");
		System.out.println("skulistById");
		this.skuListById(1);
		System.out.println("delete ");
		result = this.deleteById("82315956");

	}

	@Test(description = "登陆成功-获取指定商品信息-获取运费-提交订单")
	public void scene5() throws Exception {
		System.out.println("login");
		this.dologin("200000000033", "netease123");
		System.out.println("skulistById");
		this.skuListById(2);
		System.out.println("fee");
		this.fee("浙江省_杭州市_滨江区", "1");
		System.out.println("submit");
		this.sub("0", "noSource", "河北师范大学", "裕华区", "15226542585", "石家庄市", "", "1", "0", "河北省", "小飞龙", "3", "1",
				"74966313", "6", "0");
	}
}
