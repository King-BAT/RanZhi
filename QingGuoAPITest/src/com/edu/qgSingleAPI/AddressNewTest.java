package com.edu.qgSingleAPI;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.edu.common.Common;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;

import net.sf.json.JSONObject;

/*
* @author = 杨凯静
*/
public class AddressNewTest {
	String newUrl = "http://study-perf.qa.netease.com/fgadmin/address/new";
	CookieStore cookie = null;
	String result = null;
	Checker check = null;
	JSONObject para = null;
	String name1 = "200000000066";
	String password1 = "netease123";
	String name2 = "200000000067";
	String password2 = "netease123";
	String name3 = "200000000068";
	String password3 = "netease123";

	// 登录
	public CookieStore loginByCookie(String name, String password) throws IOException, Exception {

		CookieStore cookie = Common.getLoginCookie(name, password);
		return cookie;
	}

	// 已登录的添加地址
	public String AddressNewAfterLogin() throws Exception {
		result = HttpDriver.doPost(newUrl, para, cookie);
		System.out.println(result);
		check = new Checker(result);
		return result;
	}

	// 用例addressnew-1 添加收货地址成功
	@Test
	public void addressnew1Test() throws Exception {
		/*
		 * String name = "200000000066"; String password = "netease123";
		 */
		// CookieStore cookie = Common.getLoginCookie(name, password);
		cookie = loginByCookie(name1, password1);
		// JSONObject para = new JSONObject();
		para = new JSONObject();
		para.element("addressDetail", "中山路25号");
		para.element("area", "滨江区");
		para.element("cellPhone", "11111111111");
		para.element("city", "杭州市");
		para.element("id", "");
		para.element("province", "浙江省");
		para.element("receiverName", "张三");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "success");
	}

	// 用例addressnew-2 添加收货地址失败，receiveName参数类型不正确 用例addressnew-21
	// 添加已存在的收货地址失败（用例一已添加）
	@Test
	public void addressnew2Test() throws Exception {
		/*
		 * String name = "20000000000"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", 123);
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "receiverName参数类型不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-3 添加收货地址失败，cellPhone参数类型不正确
	@Test
	public void addressnew3Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", "123");
		long phone = 12345678901l;
		para.element("cellPhone", phone);
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "cellPhone参数类型不正确");
	}

	// 用例addressnew-4 添加收货地址失败，addressDetail参数类型不正确
	@Test
	public void addressnew4Test() throws Exception {
		/*
		 * String name = "20000000000"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", 123);
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "addressDetail参数类型不正确");
	}

	// 用例addressnew-5 添加收货地址失败，province参数类型不正确
	@Test
	public void addressnew5Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("province", 571);
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "province参数类型不正确");
	}

	// 用例addressnew-6 添加收货地址失败，city参数类型不正确
	@Test
	public void addressnew6Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", 571);
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "city参数类型不正确");
	}

	// 用例addressnew-7添加收货地址失败，area参数类型不正确
	@Test
	public void addressnew7Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", 571);
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		JSONObject feejson = JSONObject.fromObject(result);
		try {
			Assert.assertEquals(feejson.getString("message"), "area参数类型不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "失败");
		}
//		Assert.assertEquals(feejson.getInt("code"), 200);
	}

	// 用例addressnew-8添加收货地址失败，缺少receiverName参数
	@Test
	public void addressnew8Test() throws Exception {
		/*
		 * String name = "200000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "receiverName参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-9添加收货地址失败，缺少cellPhone参数
	@Test
	public void addressnew9Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		String result = HttpDriver.doPost(newUrl, para, cookie);
		System.out.println(result);
		Checker check = new Checker(result);
		// check.verifyXpath("message", "cellPhone参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-10添加收货地址失败，缺少addressDetail参数
	@Test
	public void addressnew10Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "addressDetail参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-11添加收货地址失败，缺少province参数
	@Test
	public void addressnew11Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		String result = HttpDriver.doPost(newUrl, para, cookie);
		System.out.println(result);
		Checker check = new Checker(result);
		// check.verifyXpath("message", "province参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-12添加收货地址失败，缺少city参数
	@Test
	public void addressnew12Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("area", "滨江区");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "city参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-13添加收货地址失败，缺少area参数
	@Test
	public void addressnew13Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "area参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-14添加收货地址失败，缺少area参数和city参数
	@Test
	public void addressnew14Test() throws Exception {
		/*
		 * String name = "200000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "area和city参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-15添加收货地址失败，缺少area参数、city参数、province参数
	@Test
	public void addressnew15Test() throws Exception {
		/*
		 * String name = "20000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "123");
		para.element("cellPhone", "12345678901");
		para.element("addressDetail", "浙江大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		// check.verifyXpath("message", "area、city、province参数不存在");
		check.verifyXpath("message", "may not be null");
	}

	// 用例addressnew-16 添加收货地址成功，直辖市
	@Test
	public void addressnew16Test() throws Exception {
		/*
		 * String name = "200000000066"; String password = "netease123"; CookieStore
		 * cookie = Common.getLoginCookie(name, password); JSONObject para = new
		 * JSONObject();
		 */
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "上海市");
		para.element("city", "上海市");
		para.element("area", "黄埔区");
		para.element("addressDetail", "复旦大学");
		/*
		 * String result = HttpDriver.doPost(newUrl, para, cookie);
		 * System.out.println(result); Checker check = new Checker(result);
		 */
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "success");
	}

	// 用例addressnew-17 添加收货地址失败，区不匹配
	@Test
	public void addressnew17Test() throws Exception {
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "江苏省");
		para.element("city", "南京市");
		para.element("area", "西湖区");
		para.element("addressDetail", "南京大学");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-18 添加收货地址失败，市不匹配
	@Test
	public void addressnew18Test() throws Exception {
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "浙江省");
		para.element("city", "南京市");
		para.element("area", "西湖区");
		para.element("addressDetail", "南京大学");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-19 添加收货地址失败，市和区都不匹配
	@Test
	public void addressnew19Test() throws Exception {
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "江苏省");
		para.element("city", "杭州市");
		para.element("area", "西湖区");
		para.element("addressDetail", "南京大学");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-20 添加收货地址失败，省市区都不匹配
	@Test
	public void addressnew20Test() throws Exception {
		para = new JSONObject();
		cookie = loginByCookie(name2, password2);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "安徽省");
		para.element("city", "南京市");
		para.element("area", "西湖区");
		para.element("addressDetail", "南京大学");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-22 添加超过6个收货地址失败
	@Test
	public void addressnew22Test() throws Exception {
		para = new JSONObject();
		cookie = loginByCookie(name1, password1);
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "12345678901");
		para.element("province", "上海市");
		para.element("city", "上海市");
		para.element("area", "黄埔区");
		para.element("addressDetail", "复旦大学");
		result = AddressNewAfterLogin();
		check.verifyXpath("message", "最多只能添加6个地址");
	}

	// 用例addressnew-23未登录时添加收货地址失败
	@Test
	public void addressnew23Test() throws Exception {
		JSONObject para = new JSONObject();
		para = new JSONObject();
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "11111111111");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "中山路25号");
		String result = HttpDriver.doPost(newUrl, para);
		System.out.println(result);
		Checker check = new Checker(result);
		check.verifyXpath("message", "请重新登录");
	}

	// 用例addressnew-24添加收货地址失败，receiverName参数为""
	@Test
	public void addressnew24Test() throws Exception {
		cookie = loginByCookie(name3, password3);
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "");
		para.element("cellPhone", "11111111111");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "中山路25号");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-25添加收货地址失败，cellPhone参数为null
	@Test
	public void addressnew25Test() throws Exception {
		cookie = loginByCookie(name3, password3);
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "张三");
		String phone = null;
		para.element("cellPhone", phone);
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "中山路25号");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-26添加收货地址失败，addressDetail参数为""
	@Test
	public void addressnew26Test() throws Exception {
		cookie = loginByCookie(name3, password3);
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "11111111111");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		para.element("addressDetail", "");
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}

	// 用例addressnew-27添加收货地址失败，addressDetail参数为""
	@Test
	public void addressnew27Test() throws Exception {
		cookie = loginByCookie(name3, password3);
		para = new JSONObject();
		para.element("id", "");
		para.element("receiverName", "张三");
		para.element("cellPhone", "11111111111");
		para.element("province", "浙江省");
		para.element("city", "杭州市");
		para.element("area", "滨江区");
		String detail = null;
		para.element("addressDetail", detail);
		result = AddressNewAfterLogin();
		try {
			JSONObject feejson = JSONObject.fromObject(result);
			Assert.assertEquals(feejson.getString("message"), "请求参数不正确");
		} catch (Exception e) {
			System.out.println(this.getClass() + "测试失败");
		}
	}
}
