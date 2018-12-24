package com.edu.qgSingleAPI;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.edu.core.ApiListener;
import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import com.edu.common.Common;

/*
 * @author=孙立莹
 */
@Listeners(ApiListener.class)
public class ListTest extends BaseTest {
	Checker check;
	String subUrl = "/fgadmin/address/list";
	String result = null;

	public void testList(String u_name, String u_pwd, String retmsg) throws Exception {
		CookieStore cookie = Common.getLoginCookie(u_name, u_pwd);
		result = HttpDriver.doGet(baseUrl + subUrl, cookie);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retmsg);
	}

	public void testListNoCookie(String retmsg) throws Exception {
		result = HttpDriver.doGet(baseUrl + subUrl);
		System.out.println(result);
		check = new Checker(result);
		check.verifyTextPresent("message");
		check.verifyXpath("message", retmsg);
	}

	@Test
//	成功获取收货地址信息
	public void testListSuccess() throws Exception {
		String name = "200000000044";
		String pwd = "netease123";
		String message = "success";
		this.testList(name, pwd, message);
	}

	@Test
//	未登录时获取用户收货地址
	public void testListFailDueToNoLogin() throws Exception {
		String message = "请重新登录";
		this.testListNoCookie(message);
	}

	@Test
//	登录时用户名错误获取收货地址
	public void testListFailDueToWrongUser() throws Exception {
		String name = "2000000000044";
		String pwd = "netease123";
		String message = "请重新登录";
		this.testList(name, pwd, message);
	}

	@Test
//	登录时密码错误获取收货地址
	public void testListFailDueToWringPwd() throws Exception {
		String name = "20000000044";
		String pwd = "netease1234";
		String message = "请重新登录";
		this.testList(name, pwd, message);
	}

}
